package com.smhrd.botbuddies.controller;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.botbuddies.entity.Menu;
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



@RestController
public class StoreController {

    @Autowired
    private StoreMapper mapper;

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
        
        System.out.println(align);
        System.out.println(category);

        List<Store> storeList = null;

        if(category.equals("0")){
            if(align.equals("align")){
                storeList = mapper.storeListAll();

            } else if(align.equals("distance")){
                storeList = mapper.storeListAll();

            } else if(align.equals("rating")){
                storeList = mapper.storeListAllScore();

            } else{
                storeList = mapper.storeListAllReview();

            }

        } else{
            if(align.equals("align")){
                storeList = mapper.storeList(category);

            } else if(align.equals("distance")){
                storeList = mapper.storeList(category);

            } else if(align.equals("rating")){
                storeList = mapper.storeListScore(category);

            } else{
                storeList = mapper.storeListReview(category);
                
            }

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

        System.out.println(store_seq);

        int count = mapper.waitCount(store_seq);

        mapper.wait(user_id, store_seq, count, people_num); 

        Tabling waitinfo = mapper.waitInfo(user_id);

        count = mapper.waitCount(store_seq);

        Tabling wait = new Tabling(waitinfo.getTabling_seq(), waitinfo.getStore_seq(), waitinfo.getUser_id(), waitinfo.getWait_num(), waitinfo.getState(), waitinfo.getPeople_num(), waitinfo.getCreate_at(), count);


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

    }

    @RequestMapping("/reservation")
    public void reservation(@RequestBody Reservation requestData) {
        
        String user_id = requestData.getUser_id();
        int store_seq = requestData.getStore_seq();
        String reserve_name = requestData.getReserve_name();
        String reserve_date = requestData.getReserve_date();
        String reserve_time = requestData.getReserve_time();
        int reserve_num = requestData.getReserve_num();

        mapper.reservation(user_id, store_seq, reserve_name, reserve_date, reserve_time, reserve_num);
        
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
            ReviewImgList reviewimg = new ReviewImgList(r, img);
            reviewImgList.add(reviewimg);
        }

        return reviewImgList;
        
    }
    

    @RequestMapping("/selectStore")
    public void selectStore(@RequestBody StoreMenu requestData) {
        System.out.println("들어왔음");
        System.out.println(requestData.getLocation());
        System.out.println(requestData.getNouns());
       
    }







}
