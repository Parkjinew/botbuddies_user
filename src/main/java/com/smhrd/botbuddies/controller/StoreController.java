package com.smhrd.botbuddies.controller;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Comparator;

// import org.apache.logging.log4j.util.PropertySource.Comparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.botbuddies.entity.Menu;
import com.smhrd.botbuddies.entity.Notification;
import com.smhrd.botbuddies.entity.Order;
import com.smhrd.botbuddies.entity.Reservation;
import com.smhrd.botbuddies.entity.Review;
import com.smhrd.botbuddies.entity.ReviewImg;
import com.smhrd.botbuddies.entity.ReviewImgList;
import com.smhrd.botbuddies.entity.Store;
import com.smhrd.botbuddies.entity.StoreMenu;
import com.smhrd.botbuddies.entity.Table;
import com.smhrd.botbuddies.entity.Tabling;
import com.smhrd.botbuddies.mapper.StoreMapper;
import com.smhrd.botbuddies.service.GeocodingService;



@RestController
public class StoreController {

    @Autowired
    private StoreMapper mapper;

    @Autowired
    private GeocodingService geocodingService;

    @RequestMapping("/storeList")
    public List<Store> storeList(@RequestBody Map<String, String> requestData) {
        System.out.println("들어왔음");
        System.out.println(requestData.get("id"));
        String id = requestData.get("id");
        List<Store> storeList = null;

        if(id.equals("0")){
            storeList = mapper.storeListAll();
        } else{
            
            storeList = mapper.storeList(id);
        }
        
        System.out.println(storeList.toString());

        return storeList;
        
        
    }

    @RequestMapping("/search_result")
    public List<Store> searchResult(@RequestBody Map<String, String> requestData){
        System.out.println("들어왔음");
        System.out.println(requestData.get("searchQuery"));
        String searchQuery = requestData.get("searchQuery");
        List<Store> storeList = null;
        storeList = mapper.searchResult(searchQuery);
       System.out.println(storeList);

        return storeList;

    }


    @RequestMapping("/storeinfo")
    public StoreMenu store_info(@RequestBody Map<String, String> requestData) {
        System.out.println("들어왔음");
        System.out.println(requestData.get("id"));
        String id = requestData.get("id");
        

        Store storeinfo = mapper.storeInfo(id);
        List<Menu> menuList = mapper.menuList(id);
        
        System.out.println(storeinfo.toString());
        for(Menu i : menuList){
            System.out.println(i.toString());
        }

        StoreMenu storeMenu = new StoreMenu(storeinfo, menuList);
        System.out.println(storeMenu.toString());

        
        return storeMenu;
        
        
    }

    @RequestMapping("/storeAlign")
    public List<Store> storeAlign(@RequestBody Map<String, String> requestData) {
        String align = requestData.get("align");
        String category = requestData.get("category");
        String userAddr = requestData.get("selectedAddress");
        
        String geo = geocodingService.getGeocode(userAddr);
        geo = geo.split("location")[1];
        String lat = geo.split(",")[0];
        String lng = geo.split(",")[1];
        lat = lat.split(": ")[2];
        lng = lng.split(": ")[1];
        lng = lng.split("}")[0];

        float latNum = Float.parseFloat(lat); 
        float lngNum = Float.parseFloat(lng);
        double lat1Radians= Math.toRadians(latNum);
        double lon1Radians = Math.toRadians(lngNum);

        final double R = 6371.0;
        
        System.out.println(align);
        System.out.println(category);
      
        System.out.println(latNum);
        System.out.println(lngNum);

        List<Store> storeList = null;

        if(category.equals("0")){
            if(align.equals("align")){
                storeList = mapper.storeListAll();

            } else if(align.equals("distance")){
                storeList = mapper.storeListAlladdr();

            } else if(align.equals("rating")){
                storeList = mapper.storeListAllScore();

            } else{
                storeList = mapper.storeListAllReview();

            }

        } else{
            if(align.equals("align")){
                storeList = mapper.storeList(category);

            } else if(align.equals("distance")){
                storeList = mapper.storeListaddr(category);

            } else if(align.equals("rating")){
                storeList = mapper.storeListScore(category);

            } else{
                storeList = mapper.storeListReview(category);
                
            }

        }

        ArrayList<Double> radians = new ArrayList<>();
        
        if(align.equals("distance")){


            for(int i = 0; i<storeList.size(); i++){
                String addr = storeList.get(i).getStore_addr();

                String sgeo = geocodingService.getGeocode(addr);

                sgeo = sgeo.split("location")[1];
                String slat = sgeo.split(",")[0];
                String slng = sgeo.split(",")[1];
                slat = slat.split(": ")[2];
                slng = slng.split(": ")[1];
                slng = slng.split("}")[0];

                float slatNum = Float.parseFloat(slat); 
                float slngNum = Float.parseFloat(slng);

                double lat2Radians = Math.toRadians(slatNum);
                double lon2Radians = Math.toRadians(slngNum);

                // 위도 및 경도의 차이 계산
                double dLat = lat2Radians - lat1Radians;
                double dLon = lon2Radians - lon1Radians;

                // 하버사인 제곱의 반을 계산
                double a = Math.pow(Math.sin(dLat / 2), 2) + Math.cos(lat1Radians) * Math.cos(lat2Radians) * Math.pow(Math.sin(dLon / 2), 2);

                // 중심 각도 계산
                double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

                // 거리 계산
                double distance = R * c;


                radians.add(distance);

            }

            final List<Store> storeList2 = storeList;
            final ArrayList<Double> radians2 = radians;

            storeList2.sort(Comparator.comparingDouble(store -> radians2.get(storeList2.indexOf(store))));

            storeList = storeList2;

        }

        

                

        return storeList;
        
    }

    @RequestMapping("/waitState")
    public int waitState(@RequestBody Map<String, String> requestData) {
        System.out.println("들어왔음");
        String user_id = requestData.get("user_id");
        
        System.out.println(user_id);

        int state = mapper.waitState(user_id);
        
        return state;
    }

    @RequestMapping("/wait")
    public Tabling wait(@RequestBody Map<String, String> requestData) {
        System.out.println("들어왔음");
        String user_id = requestData.get("user_id");
        String store_seq = requestData.get("store_seq");
        int people_num = Integer.parseInt(requestData.get("people_num"));
        String store_user = requestData.get("store_user");

        System.out.println(store_seq);

        int count = mapper.waitCount(store_seq);

        mapper.wait(user_id, store_seq, count, people_num); 

        Tabling waitinfo = mapper.waitInfo(user_id);

        count = mapper.waitCount(store_seq);

        Tabling wait = new Tabling(waitinfo.getTabling_seq(), waitinfo.getStore_seq(), waitinfo.getUser_id(), waitinfo.getWait_num(), waitinfo.getState(), waitinfo.getPeople_num(), waitinfo.getCreate_at(), count);

        mapper.tablingNoti(store_user);

        return wait;
         
    }

    @RequestMapping("/waitInfo")
    public Tabling waitInfo(@RequestBody Map<String, String> requestData) {
        System.out.println("들어왔음");
        String user_id = requestData.get("user_id");
        
        
        System.out.println(user_id);

        Tabling waitinfo = mapper.waitInfo(user_id);
        
        String store_seq = null;

        if(waitinfo != null){
            store_seq = waitinfo.getStore_seq()+"";
        }
        

        int count = mapper.waitCount(store_seq);

        Tabling wait = null;

        if(waitinfo != null){
            wait = new Tabling(waitinfo.getTabling_seq(), waitinfo.getStore_seq(), waitinfo.getUser_id(), waitinfo.getWait_num(), waitinfo.getState(), waitinfo.getPeople_num(), waitinfo.getCreate_at(), count);
        }


        return wait;
         
    }

    @RequestMapping("/getStoreName")
    public Store getStoreName(@RequestBody Map<String, String> requestData) {
        System.out.println("들어왔음");
        String store_seq = requestData.get("store_seq");

        Store store = mapper.getStore(store_seq);

        return store;
         
    }

    @RequestMapping("/waitDelet")
    public void waitDelet(@RequestBody Map<String, String> requestData) {
        System.out.println("들어왔음");
        String tabling_seq = requestData.get("tabling_seq");

       mapper.waitDel(tabling_seq);

        
         
    }

    @RequestMapping("/getCount")
    public int getCount(@RequestBody Map<String, String> requestData) {
        System.out.println("들어왔음");
        String store_seq = requestData.get("store_seq");

        int count = mapper.waitCount(store_seq);

        return count;
         
    }

    @RequestMapping("/getTable")
    public List<Table> getTable(@RequestBody Map<String, String> requestData) {
        System.out.println("들어왔음");
        String store_seq = requestData.get("store_seq");

        List<Table> tableList = mapper.getTable(store_seq);

        return tableList;
         
    }

    @RequestMapping("/payment")
    public void payment(@RequestBody StoreMenu requestData) {
        System.out.println("들어왔음");
        int store_seq = requestData.getStore_seq();
        String user_id = requestData.getUser_id();
        List<Order> orderDetails = requestData.getOrders();
        int selectedTable = requestData.getSelectedTable();
        String store_user = requestData.getStore_user();

        System.out.println(store_seq);
        System.out.println(user_id);
        
        System.out.println(orderDetails.get(0).toString());
        System.out.println(selectedTable);

        Integer result = mapper.getOrderNum(store_seq);

        int num = (result != null) ? result : 0;

        System.out.println(num);

        int totalAmount = 0;

       List<Table> selectTable =  mapper.getTableList(store_seq, selectedTable);
       System.out.println(selectTable.get(0).toString());

       int table_seq = selectTable.get(0).getTable_seq();
       mapper.selectTable(table_seq);

        for(Order order : orderDetails){
            int amount = mapper.getAmount(order.getMenu_seq());
            totalAmount+= amount*order.getQuantity();
        }
        for(Order order : orderDetails){
            int amount = mapper.getAmount(order.getMenu_seq());
            int pay_amount = amount*order.getQuantity();
            mapper.orderInsert(store_seq, user_id, order.getMenu_seq(), num+1, order.getQuantity(),totalAmount, 0, pay_amount, totalAmount, "card");
            

        }

        mapper.orderNoti(store_user);

    }

    @RequestMapping("/reservation")
    public void reservation(@RequestBody Reservation requestData) {
        
        String user_id = requestData.getUser_id();
        int store_seq = requestData.getStore_seq();
        String reserve_name = requestData.getReserve_name();
        String reserve_date = requestData.getReserve_date();
        String reserve_time = requestData.getReserve_time();
        int reserve_num = requestData.getReserve_num();
        String store_user = requestData.getStore_user();

        mapper.reservation(user_id, store_seq, reserve_name, reserve_date, reserve_time, reserve_num);
        mapper.reservaNoti(store_user);
        
    }
   
    @RequestMapping("/cancelReserve")
    public void cancelReserve(@RequestBody Reservation requestData) {
        
        String user_id = requestData.getUser_id();
        int store_seq = requestData.getStore_seq();
        String reserve_name = requestData.getReserve_name();
        String reserve_date = requestData.getReserve_date();
        String reserve_time = requestData.getReserve_time();
        int reserve_num = requestData.getReserve_num();

        System.out.println(requestData.toString());

        mapper.cancelReserve(user_id, store_seq, reserve_name, reserve_date, reserve_time, reserve_num);
        
    }


    @RequestMapping("/getReserv")
    public List<Reservation> getReserv(@RequestBody Map<String, String> requestData) {
        
        String store_seq = requestData.get("store_seq");

        System.out.println(store_seq);

        List<Reservation> reservInfo = mapper.getReser(store_seq);

        return reservInfo;
        
    }

    @RequestMapping("/reviewPage")
    public ArrayList<ReviewImgList> reviewPage(@RequestBody Map<String, String> requestData) {
        
        String store_seq = requestData.get("store_seq");

        System.out.println(store_seq);

        List<Review> reviewList = mapper.storeReview(store_seq);
        
        // List<ReviewImgList> reviewImgList = new List<ReviewImgList>();

        ArrayList<ReviewImgList> reviewImgList = new ArrayList<ReviewImgList>();

        for (Review r : reviewList){
            List<ReviewImg> img = mapper.reviewImgGet(r.getReview_seq());
            String user_nick = mapper.getNick(r.getUser_id());
            System.out.println(user_nick);
            ReviewImgList reviewimg = new ReviewImgList(r, img, user_nick);
            reviewImgList.add(reviewimg);
        }

        return reviewImgList;
        
    }
    

    @RequestMapping("/selectStore")
    public List<Store> selectStore(@RequestBody StoreMenu requestData) {
        System.out.println("들어왔음");
        String location = requestData.getLocation();
        List<String> nouns = requestData.getNouns();

        if(location.equals("수완지구")){
            location = "수완";
        }

        if(location.equals("동명동")){
            location = "동명";
        }
        
        for (String i : nouns){
            if(i.equals("충장로") || i.equals("광주")){
                location = i;
                nouns.remove(i);
            }
        }

        String keyword = "";
        if(nouns.size() > 0){
            keyword = nouns.get(0);
        }
        System.out.println(keyword);

       List<Store> storeList = mapper.searchStore(location, keyword);
        
        return storeList;
    }


    @RequestMapping("/reviewAlign")
    public ArrayList<ReviewImgList> reviewAlign(@RequestBody Map<String, String> requestData) {
        
        String store_seq = requestData.get("store_seq");
        String option = requestData.get("option");

        System.out.println(option);

        List<Review> reviewList = null;
        
        if(option.equals("최신순")){
            reviewList = mapper.storeReview(store_seq);
        }else if(option.equals("별점 높은순")){
            reviewList = mapper.reviewhigh(store_seq);
        }else{
            reviewList = mapper.reviewlow(store_seq);
        }

        ArrayList<ReviewImgList> reviewImgList = new ArrayList<ReviewImgList>();

        for (Review r : reviewList){
            List<ReviewImg> img = mapper.reviewImgGet(r.getReview_seq());
            String user_nick = mapper.getNick(r.getUser_id());
            ReviewImgList reviewimg = new ReviewImgList(r, img, user_nick);
            reviewImgList.add(reviewimg);
        }

        return reviewImgList;
        
    }


    @RequestMapping("/LikeTF")
    public boolean LikeTF(@RequestBody Map<String, String> requestData) {
        String id = requestData.get("user_id");
        String store_seq = requestData.get("store_seq");

        int count = mapper.likeTF(id, store_seq);
        System.out.println(count);

        if(count > 0){
            return true;
        } else{
            return false;
        }
        
    }

    @RequestMapping("/Like")
    public void Like(@RequestBody Map<String, String> requestData) {
        String id = requestData.get("user_id");
        String store_seq = requestData.get("store_seq");
        String like = requestData.get("like");

        System.out.println(like);

        if(like.equals("true")){
            mapper.delLike(id, store_seq);
        } else{
            mapper.insertLike(id, store_seq);
        }
        
        
    }


    @RequestMapping("/paycafe")
    public void paycafe(@RequestBody StoreMenu requestData) {
        System.out.println("들어왔음");
        int store_seq = requestData.getStore_seq();
        String user_id = requestData.getUser_id();
        List<Order> orderDetails = requestData.getOrders();
        String store_user = requestData.getStore_user();

        System.out.println(store_seq);
        System.out.println(user_id);
        
        System.out.println(orderDetails.get(0).toString());

        Integer result = mapper.getOrderNum(store_seq);

        int num = (result != null) ? result : 0;

        System.out.println(num);

        int totalAmount = 0;



        for(Order order : orderDetails){
            int amount = mapper.getAmount(order.getMenu_seq());
            totalAmount+= amount*order.getQuantity();
        }
        for(Order order : orderDetails){
            int amount = mapper.getAmount(order.getMenu_seq());
            int pay_amount = amount*order.getQuantity();
            mapper.orderInsert(store_seq, user_id, order.getMenu_seq(), num+1, order.getQuantity(),totalAmount, 0, pay_amount, totalAmount, "card");
            

        }

        mapper.orderNoti(store_user);

    }


    @RequestMapping("/test")
    public String test(){
        System.out.println("성공");
        return "성공";
    }

    @RequestMapping("/notiState")
    public boolean notiState(@RequestBody Map<String, String> requestData){
        String user_id = requestData.get("user_id");

        int count = mapper.getNoti(user_id);

        if(count >0){
            return true;
        }
        else{
            return false;
        }
        
    }
    @RequestMapping("/goNoti")
    public List<Notification> goNoti(@RequestBody Map<String, String> requestData){
        String user_id = requestData.get("user_id");
        System.out.println(user_id);
        List<Notification> notice = mapper.notiList(user_id);
        mapper.notiState(user_id);


        return notice;
        
    }




}
