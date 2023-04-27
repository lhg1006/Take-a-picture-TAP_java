package com.example.demo.api.newFeed.biz;

import com.example.demo.api.newFeed.vo.FollowsVO;
import com.example.demo.api.newFeed.vo.LikeVO;
import com.example.demo.mapper.ggu.GguDataBase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class NewFeedService {
    private final GguDataBase gguDataBase;

    public Map<String, Object> getNewFeedList(String userMail){
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("postList", gguDataBase.getNewFeedList(userMail));
        return resMap;
    }

    public int addComment(Map<String, Object> param){
        return gguDataBase.addComment(param);
    }

    public int delComment(Map<String, Object> param){
        return gguDataBase.delComment(param);
    }

    public int addPost(Map<String, Object> param){
        return gguDataBase.addPost(param);
    }

    public int delPost(Map<String, Object> param){
        return gguDataBase.delPost(param);
    }

    public int likeIns(Map<String, Object> param){
        return gguDataBase.likeIns(param);
    }
    public int likeDel(Map<String, Object> param){
        return gguDataBase.likeDel(param);
    }
    public List<LikeVO>getLikeList(int postNo){
        List<LikeVO> list = gguDataBase.likeList(postNo);
        return list;
    }


    public List<FollowsVO> followList(String email){
        List<FollowsVO> list = new ArrayList<>();
        try {
            list = gguDataBase.followList(email);
        }catch (Exception e){
            log.error("NewFeedService followList ERROR ===> ", e);
        }
        return list;
    }

    public int addFollow(Map<String, Object> param){
        int result = 0;
        try {
            result = gguDataBase.addFollow(param);
        }catch (Exception e){
            log.error("NewFeedService addFollow ERROR ===> ",e);
        }
        return result;
    }

    public int delFollow(Map<String, Object> param){
        int result = 0;
        try {
            result = gguDataBase.delFollow(param);
        }catch (Exception e){
            log.error("NewFeedService delFollow ERROR ===> ",e);
        }
        return result;
    }
}
