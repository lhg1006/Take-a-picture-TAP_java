//package com.example.demo.api.feed.biz;
//
//import com.example.demo.api.feed.vo.CommentResultVO;
//import com.example.demo.api.feed.vo.FeedResultVO;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Service
//@Slf4j
//@RequiredArgsConstructor
//public class FeedService {
//
//    public Map<String,Object> feedList(){
//        Map<String,Object> resMap = new HashMap<>();
//        try {
//            List<FeedResultVO> feedList = masterDataBase.getFeedList();
//            List<CommentResultVO> commentList = masterDataBase.getCommentList();
//            resMap.put("feedList", feedList);
//            resMap.put("commentList", commentList);
//        }catch (Exception e){
//            log.error("FeedService feedList ERROR ===> {}", e);
//        }
//
//        return resMap;
//    }
//
//    public int commentIns(CommentResultVO param){
//        int result = 0;
//
//        if (param.getPostNo() == 0 || param.getRMemberEmail().equals("")) return result;
//
//        try {
//            result = masterDataBase.commentIns(param);
//        }catch (Exception e){
//            log.error("FeedService commentIns ERROR ===> {}", e);
//        }
//
//        return result;
//    }
//
//    public int commentDel(CommentResultVO param){
//        int result = 0;
//
//        if (param.getAutoNo() == 0 || param.getPostNo() == 0) return result;
//        try {
//            result = masterDataBase.commentDel(param);
//        }catch (Exception e){
//            log.error("FeedService commentDel ERROR ===> {}", e);
//        }
//
//        return result;
//    }
//}
