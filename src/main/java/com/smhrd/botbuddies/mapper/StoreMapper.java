package com.smhrd.botbuddies.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smhrd.botbuddies.entity.Menu;
import com.smhrd.botbuddies.entity.Store;

@Mapper
public interface StoreMapper {
    public List<Store> storeListAll();

    public List<Store> storeList(String id);

    public List<Store> searchResult(String searchQuery);

    public Store storeInfo(String id);

    public List<Menu> menuList(String id);
}
