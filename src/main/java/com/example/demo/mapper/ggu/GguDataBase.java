package com.example.demo.mapper.ggu;

import com.example.demo.api.feed.vo.CommentResultVO;
import com.example.demo.api.feed.vo.FeedResultVO;
import com.example.demo.api.member.vo.MemberInsParamVO;
import com.example.demo.api.newFeed.vo.LikeVO;
import com.example.demo.api.newFeed.vo.NewFeedVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GguDataBase {
    int emailChk(@Param("email") String email);

    int memberIns(MemberInsParamVO memberInsParamVO);

    int memberAuth(Map<String,Object> param);

    int forgotPassword(@Param("email")String email, @Param("phone")String phone);

    int updPw(Map<String,Object> param);

    List<FeedResultVO> getFeedList();

    List<CommentResultVO> getCommentList();

    int commentIns(CommentResultVO param);

    int commentDel(CommentResultVO param);


    //여기서부터 뉴피드
    List<NewFeedVO> getNewFeedList(String userMail);

    int addComment(Map<String,Object> param);

    int delComment(Map<String,Object> param);

    int addPost(Map<String,Object> param);

    int delPost(Map<String,Object> param);

    int likeIns(Map<String,Object> param);

    int likeDel(Map<String,Object> param);

    List<LikeVO> likeList(int postNo);

    int memberProfilePhotoIns(Map<String, Object> param);
}
