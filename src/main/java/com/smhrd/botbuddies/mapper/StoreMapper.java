package com.smhrd.botbuddies.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smhrd.botbuddies.entity.Menu;
import com.smhrd.botbuddies.entity.Store;
import com.smhrd.botbuddies.entity.Tabling;

@Mapper
public interface StoreMapper {
    public List<Store> storeListAll();

    public List<Store> storeList(String id);

    public List<Store> searchResult(String searchQuery);

    public Store storeInfo(String id);

    public List<Menu> menuList(String id);

    public List<Store> storeListAllScore();

    public List<Store> storeListScore(String id);

    public List<Store> storeListAllReview();

    public List<Store> storeListReview(String id);

    // 현재 유저가 줄서는 중인지 확인
    public int waitState(String user_id);

    // 줄서기 신청
    public int wait(String user_id, String store_seq, int Count , int people_num);

    // 현재 매장에 줄서고 있는 수
    public int waitCount(String store_seq);

    // 줄서기 정보 
    public Tabling waitInfo(String user_id);

    public Store getStore(String store_seq);
}
