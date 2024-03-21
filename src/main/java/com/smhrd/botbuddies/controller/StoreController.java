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




}
