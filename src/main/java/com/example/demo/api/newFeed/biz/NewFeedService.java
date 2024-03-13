package com.example.demo.api.newFeed.biz;

import com.example.demo.api.alim.biz.AlimService;
import com.example.demo.api.alim.vo.AlimInsVO;
import com.example.demo.api.newFeed.vo.CommentsVO;
import com.example.demo.api.newFeed.vo.FollowsVO;
import com.example.demo.api.newFeed.vo.LikeVO;
import com.example.demo.api.newFeed.vo.NewFeedVO;
import com.example.demo.mapper.tap.TapDataBase;
import com.example.demo.mapper.tap.ForTransaction;
import com.example.demo.utils.DateFormatUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class NewFeedService {
    private final TapDataBase tapDataBase;
    private final ForTransaction forTransaction;
    private final AlimService alimService;
    private final DateFormatUtil dateFormatUtil;

    public Map<String, Object> getNewFeedList(String userMail, int pageNo) throws JsonProcessingException {
        Map<String, Object> resMap = new HashMap<>();
        Map<String, Object> params = new HashMap<>();
        params.put("userMail", userMail);
        params.put("pagePerCnt", 10);
        params.put("offset", 10 * (pageNo - 1));

        int totalCnt = tapDataBase.getNewFeedTotalCnt();
        boolean stopPaging = totalCnt <= pageNo * 2;

        List<NewFeedVO> newFeedList = tapDataBase.getNewFeedList(params);

        if(newFeedList != null){
            for (NewFeedVO vo : newFeedList) {
                if(vo.getComments() != null){
                    setFeedCommentDateKor(vo);
                }
            }
        }

        resMap.put("totalCnt", totalCnt);
        resMap.put("stopPaging", stopPaging);
        resMap.put("postList", newFeedList);

        return resMap;
    }

    public Map<String, Object> getTargetFeedList(String userMail){
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("postList", tapDataBase.getTargetFeedList(userMail));
        return resMap;
    }

    public NewFeedVO getSingleFeed(String userMail, int postNo) throws JsonProcessingException {
        NewFeedVO singleFeed;
        singleFeed = tapDataBase.getSingleFeed(userMail, postNo);
        if(singleFeed == null){
            singleFeed = new NewFeedVO();
            singleFeed.setPostState(-99);
        }else{
            setFeedCommentDateKor(singleFeed);
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

        commentInsRes = tapDataBase.addComment(param);

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
        return tapDataBase.delComment(param);
    }

    public int addPost(Map<String, Object> param){
        return tapDataBase.addPost(param);
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

        likeInsRes = tapDataBase.likeIns(param);

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
        return tapDataBase.likeDel(param);
    }
    public List<LikeVO>getLikeList(int postNo){
        List<LikeVO> list = tapDataBase.likeList(postNo);
        return list;
    }


    public List<FollowsVO> followList(String email, String type){
        List<FollowsVO> list = new ArrayList<>();
        try {
            if(type.equals("follow")){
                list = tapDataBase.followList(email);
            }else{
                list = tapDataBase.followerList(email);
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

            int chk = tapDataBase.followCheck(param);

            if(chk == 0){
                result = tapDataBase.addFollow(param);
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
            result = tapDataBase.delFollow(param);
        }catch (Exception e){
            log.error("NewFeedService delFollow ERROR ===> ",e);
        }
        return result;
    }

    public int isFollowed(Map<String, Object> param){
        return tapDataBase.isFollowed(param);
    }

    public void setFeedCommentDateKor(NewFeedVO list) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        if(list.getComments() != null){
            // JSON 형식의 comments를 List로 변환
            List<CommentsVO> commentList = objectMapper.readValue(list.getComments(), new TypeReference<List<CommentsVO>>(){});

            // created_at 값을 insDateKor로 변환
            for (CommentsVO comment : commentList) {
                String insDateKor = dateFormatUtil.insDateKorea(comment.getCreated_at());
                comment.setInsDateKor(insDateKor);
            }
            // 변환된 commentList를 다시 JSON 형식으로 변환하여 vo에 설정
            try {
                String updatedCommentsJson = objectMapper.writeValueAsString(commentList);
                list.setComments(updatedCommentsJson);
            } catch (JsonProcessingException e) {
                log.error("NewFeedService setFeedCommentDateKor ERROR =====> {}", e);
            }
        }
    }
}

