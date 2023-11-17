package com.example.demo.mapper.demoInsta;

import com.example.demo.api.newFeed.vo.CommentResultVO;
import com.example.demo.api.newFeed.vo.FeedResultVO;
import com.example.demo.api.member.vo.MemberFollowsVO;
import com.example.demo.api.member.vo.MemberInsParamVO;
import com.example.demo.api.member.vo.MemberDataVO;
import com.example.demo.api.member.vo.MemberPostsVO;
import com.example.demo.api.newFeed.vo.FollowsVO;
import com.example.demo.api.newFeed.vo.LikeVO;
import com.example.demo.api.newFeed.vo.NewFeedVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DemoInstaDataBase {
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

    List<NewFeedVO> getTargetFeedList(String userMail);

    int addComment(Map<String,Object> param);

    int delComment(Map<String,Object> param);

    int addPost(Map<String,Object> param);

    int delPost(Map<String,Object> param);

    int likeIns(Map<String,Object> param);

    int likeDel(Map<String,Object> param);

    List<LikeVO> likeList(int postNo);

    int memberProfilePhotoIns(Map<String,Object> param);

    List<FollowsVO> followList(String email);

    List<FollowsVO> followerList(String email);

    int followCheck(Map<String,Object> param);
    int addFollow(Map<String,Object> param);

    int delFollow(Map<String,Object> param);

    MemberDataVO memberProfile(String email);

    List<MemberPostsVO> memberPosts(String email);

    List<MemberFollowsVO> memberFollows(String email);

    int getFollowCnt (String email);
    int getFollowerCnt (String email);

    int isFollowed (Map<String,Object> param);
}
