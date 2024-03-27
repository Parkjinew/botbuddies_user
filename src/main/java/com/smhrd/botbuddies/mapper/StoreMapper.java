package com.smhrd.botbuddies.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smhrd.botbuddies.entity.Menu;
import com.smhrd.botbuddies.entity.Reservation;
import com.smhrd.botbuddies.entity.Review;
import com.smhrd.botbuddies.entity.ReviewImg;
import com.smhrd.botbuddies.entity.Store;
import com.smhrd.botbuddies.entity.Table;
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

    public void waitDel(String tabling_seq);

    public List<Table> getTable(String store_seq);

    public Integer getOrderNum(int store_seq);

    public int getAmount(int menu_seq);

    public void orderInsert(int store_seq, String user_id, int menu_seq, int i, int quantity, int totalAmount, int j,
            int pay_amount, int totalAmount2, String method);

    public List<Table> getTableList(int store_seq, int selectedTable);

    public void selectTable(int table_seq);

    public void reservation(String user_id, int store_seq, String reserve_name, String reserve_date,
            String reserve_time, int reserve_num);

    public void cancelReserve(String user_id, int store_seq, String reserve_name, String reserve_date,
            String reserve_time, int reserve_num);

    public List<Reservation> getReser(String store_seq);

    public List<Review> storeReview(String store_seq);

    public List<ReviewImg> reviewImgGet(int review_seq);

    public List<Store> searchStore(String location, String keyword);

    public List<Review> reviewhigh(String store_seq);

    public List<Review> reviewlow(String store_seq);

    public int likeTF(String id, String store_seq);

    public void delLike(String id, String store_seq);

    public void insertLike(String id, String store_seq);

    
}
