package com.example.demo.api.newFeed.biz;

import com.example.demo.api.alim.biz.AlimService;
import com.example.demo.api.alim.vo.AlimInsVO;
import com.example.demo.api.newFeed.vo.FollowsVO;
import com.example.demo.api.newFeed.vo.LikeVO;
import com.example.demo.api.newFeed.vo.NewFeedVO;
import com.example.demo.mapper.tap.TapDataBase;
import com.example.demo.mapper.tap.ForTransaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class NewFeedService {
    private final TapDataBase TAPDataBase;
    private final ForTransaction forTransaction;
    private final AlimService alimService;

    public Map<String, Object> getNewFeedList(String userMail){
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("postList", TAPDataBase.getNewFeedList(userMail));
        return resMap;
    }

    public Map<String, Object> getTargetFeedList(String userMail){
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("postList", TAPDataBase.getTargetFeedList(userMail));
        return resMap;
    }

    public NewFeedVO getSingleFeed(String userMail, int postNo){
        NewFeedVO singleFeed;
        singleFeed = TAPDataBase.getSingleFeed(userMail, postNo);
        if(singleFeed == null){
            singleFeed = new NewFeedVO();
            singleFeed.setPostState(-99);
        }
        return singleFeed;
    }


    public int addComment(Map<String, Object> param){
        int commentInsRes = 0;

        if( ObjectUtils.isEmpty(param.get("sendMemNo")) || ObjectUtils.isEmpty(param.get("receiveMemNo")) ) {
            return commentInsRes;
        }

        int myNo = Integer.parseInt(param.get("sendMemNo").toString()) ;
        int yourNo = Integer.parseInt(param.get("receiveMemNo").toString());

        int postNo = 0;

        if( !ObjectUtils.isEmpty(param.get("postId")) ){
            postNo = Integer.parseInt(param.get("postId").toString());
        }

        commentInsRes = TAPDataBase.addComment(param);

        if(commentInsRes > 0 && (myNo != yourNo)){
            AlimInsVO alimInsVO = new AlimInsVO();
            alimInsVO.setSendMemNo(myNo);
            alimInsVO.setReceiveMemNo(yourNo);
            alimInsVO.setAlimCode(2);
            alimInsVO.setPostNo(postNo);

            alimService.sendAlim(alimInsVO);
        }
        return commentInsRes;
    }

    public int delComment(Map<String, Object> param){
        return TAPDataBase.delComment(param);
    }

    public int addPost(Map<String, Object> param){
        return TAPDataBase.addPost(param);
    }

    @Transactional
    public int delPost(Map<String, Object> param){
        try {
            String postNo = (String) param.get("postNo");
            String userMail = (String) param.get("userMail");
            forTransaction.deletePost(postNo, userMail);
            forTransaction.deleteLikes(postNo);
            forTransaction.deleteComments(postNo);

            return 1;
        }catch (Exception e){
            log.error("NewFeedService delPost ERROR ===> {}", e);
            return 0;
        }
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

        likeInsRes = TAPDataBase.likeIns(param);

        if(likeInsRes > 0 && (myNo != yourNo)){
            AlimInsVO alimInsVO = new AlimInsVO();
            alimInsVO.setSendMemNo(myNo);
            alimInsVO.setReceiveMemNo(yourNo);
            alimInsVO.setAlimCode(1);
            alimInsVO.setPostNo(postNo);

            alimService.sendAlim(alimInsVO);
        }
        return likeInsRes;
    }
    public int likeDel(Map<String, Object> param){
        return TAPDataBase.likeDel(param);
    }
    public List<LikeVO>getLikeList(int postNo){
        List<LikeVO> list = TAPDataBase.likeList(postNo);
        return list;
    }


    public List<FollowsVO> followList(String email, String type){
        List<FollowsVO> list = new ArrayList<>();
        try {
            if(type.equals("follow")){
                list = TAPDataBase.followList(email);
            }else{
                list = TAPDataBase.followerList(email);
            }
        }catch (Exception e){
            log.error("NewFeedService followList ERROR ===> ", e);
        }
        return list;
    }

    public int addFollow(Map<String, Object> param){
        int result = 0;
        try {
            if( ObjectUtils.isEmpty(param.get("userMemNo")) || ObjectUtils.isEmpty(param.get("followMemNo")) ) {
                return result;
            }

            int myNo = Integer.parseInt(param.get("userMemNo").toString()) ;
            int yourNo = Integer.parseInt(param.get("followMemNo").toString());

            int postNo = 0; // 팔로우는 그냥 0 넣음

            int chk = TAPDataBase.followCheck(param);

            if(chk == 0){
                result = TAPDataBase.addFollow(param);
            }

            if(result > 0 && (myNo != yourNo)){
                AlimInsVO alimInsVO = new AlimInsVO();
                alimInsVO.setSendMemNo(myNo);
                alimInsVO.setReceiveMemNo(yourNo);
                alimInsVO.setAlimCode(3);
                alimInsVO.setPostNo(postNo);

                alimService.sendAlim(alimInsVO);
            }
        }catch (Exception e){
            log.error("NewFeedService addFollow ERROR ===> ",e);
        }
        return result;
    }

    public int delFollow(Map<String, Object> param){
        int result = 0;
        try {
            result = TAPDataBase.delFollow(param);
        }catch (Exception e){
            log.error("NewFeedService delFollow ERROR ===> ",e);
        }
        return result;
    }

    public int isFollowed(Map<String, Object> param){
        return TAPDataBase.isFollowed(param);
    }
}
