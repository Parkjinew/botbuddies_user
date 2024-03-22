package com.smhrd.botbuddies.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.botbuddies.entity.Menu;
import com.smhrd.botbuddies.entity.Store;
import com.smhrd.botbuddies.entity.StoreMenu;
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
        for(Store i : storeList){
            System.out.println(i.toString());
        }

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

        String store_seq = waitinfo.getStore_seq()+"";

        int count = mapper.waitCount(store_seq);

        Tabling wait = new Tabling(waitinfo.getTabling_seq(), waitinfo.getStore_seq(), waitinfo.getUser_id(), waitinfo.getWait_num(), waitinfo.getState(), waitinfo.getPeople_num(), waitinfo.getCreate_at(), count);


        return wait;
         
    }

    @RequestMapping("/getStoreName")
    public Store getStoreName(@RequestBody Map<String, String> requestData) {
        System.out.println("들어왔음");
        String store_seq = requestData.get("store_seq");

        Store store = mapper.getStore(store_seq);
        System.out.println(store.toString());

        return store;
         
    }




}
