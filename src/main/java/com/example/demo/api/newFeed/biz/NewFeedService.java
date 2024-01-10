package com.example.demo.api.newFeed.biz;

import com.example.demo.api.alim.biz.AlimService;
import com.example.demo.api.alim.vo.AlimInsVO;
import com.example.demo.api.newFeed.vo.FollowsVO;
import com.example.demo.api.newFeed.vo.LikeVO;
import com.example.demo.mapper.demoInsta.DemoInstaDataBase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class NewFeedService {
    private final DemoInstaDataBase demoInstaDataBase;
    private final AlimService alimService;

    public Map<String, Object> getNewFeedList(String userMail){
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("postList", demoInstaDataBase.getNewFeedList(userMail));
        return resMap;
    }

    public Map<String, Object> getTargetFeedList(String userMail){
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("postList", demoInstaDataBase.getTargetFeedList(userMail));
        return resMap;
    }


    public int addComment(Map<String, Object> param){
        return demoInstaDataBase.addComment(param);
    }

    public int delComment(Map<String, Object> param){
        return demoInstaDataBase.delComment(param);
    }

    public int addPost(Map<String, Object> param){
        return demoInstaDataBase.addPost(param);
    }

    public int delPost(Map<String, Object> param){
        return demoInstaDataBase.delPost(param);
    }

    public int likeIns(Map<String, Object> param){
        int likeInsRes = 0;

        if( ObjectUtils.isEmpty(param.get("myMemNo")) || ObjectUtils.isEmpty(param.get("postMemNo")) ) {
            return likeInsRes;
        }

        int myNo = Integer.parseInt(param.get("myMemNo").toString()) ;
        int yourNo = Integer.parseInt(param.get("postMemNo").toString());

        int postNo = 0;

        if( !ObjectUtils.isEmpty(param.get("postNo")) ){
            postNo = Integer.parseInt(param.get("postNo").toString());
        }

        likeInsRes = demoInstaDataBase.likeIns(param);

        if(likeInsRes > 0 && (myNo != yourNo)){
            AlimInsVO alimInsVO = new AlimInsVO();
            alimInsVO.setMyMemNo(myNo);
            alimInsVO.setYourMemNo(yourNo);
            alimInsVO.setAlimCode(1);
            alimInsVO.setPostNo(postNo);

            alimService.sendAlim(alimInsVO);
        }
        return likeInsRes;
    }
    public int likeDel(Map<String, Object> param){
        return demoInstaDataBase.likeDel(param);
    }
    public List<LikeVO>getLikeList(int postNo){
        List<LikeVO> list = demoInstaDataBase.likeList(postNo);
        return list;
    }


    public List<FollowsVO> followList(String email, String type){
        List<FollowsVO> list = new ArrayList<>();
        try {
            if(type.equals("follow")){
                list = demoInstaDataBase.followList(email);
            }else{
                list = demoInstaDataBase.followerList(email);
            }
        }catch (Exception e){
            log.error("NewFeedService followList ERROR ===> ", e);
        }
        return list;
    }

    public int addFollow(Map<String, Object> param){
        int result = 0;
        try {
            int chk = demoInstaDataBase.followCheck(param);

            if(chk == 0){
                result = demoInstaDataBase.addFollow(param);
            }
        }catch (Exception e){
            log.error("NewFeedService addFollow ERROR ===> ",e);
        }
        return result;
    }

    public int delFollow(Map<String, Object> param){
        int result = 0;
        try {
            result = demoInstaDataBase.delFollow(param);
        }catch (Exception e){
            log.error("NewFeedService delFollow ERROR ===> ",e);
        }
        return result;
    }

    public int isFollowed(Map<String, Object> param){
        return demoInstaDataBase.isFollowed(param);
    }
}
