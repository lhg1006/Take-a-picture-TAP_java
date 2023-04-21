package com.example.demo.api.newFeed.biz;

import com.example.demo.mapper.master.MasterDataBase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class NewFeedService {
    private final MasterDataBase masterDataBase;

    public Map<String, Object> getNewFeedList(){
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("postList", masterDataBase.getNewFeedList());
        return resMap;
    }

    public int addComment(Map<String, Object> param){
        return masterDataBase.addComment(param);
    }

    public int delComment(Map<String, Object> param){
        return masterDataBase.delComment(param);
    }

    public int addPost(Map<String, Object> param){
        return masterDataBase.addPost(param);
    }

    public int delPost(Map<String, Object> param){
        return masterDataBase.delPost(param);
    }
}
