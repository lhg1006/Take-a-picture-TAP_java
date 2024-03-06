package com.example.demo.api.member.biz;

import com.example.demo.api.common.CookieUtil;
import com.example.demo.api.member.vo.*;
import com.example.demo.mapper.demoInsta.DemoInstaDataBase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final CookieUtil cookieUtil;
    private final DemoInstaDataBase demoInstaDataBase;

    public int memberIns(MemberInsParamVO memberInsParamVO){
        int result = 0;
        try {
            String password = memberInsParamVO.getPassword();
            String chkPassword = memberInsParamVO.getChkPassword();
            if(password.equals(chkPassword)){
                result = demoInstaDataBase.memberIns(memberInsParamVO);
            }else{
                result = -2; // 비밀번호 불일치
            }
        }catch (Exception e){
            log.error("MemberService memberIns ERROR ===> ", e);
        }
        return result;
    }

    public int emailChk(String email){
        int result = 0;
        try {
            result = demoInstaDataBase.emailChk(email);
        }catch (Exception e){
            log.error("MemberService emailChk ERROR ===> ", e);
        }
        return result;
    }

    public int loginAuth(Map<String, Object> param, HttpServletResponse httpServletResponse){
        int result = 0;
        try {
            AuthVO authVO = demoInstaDataBase.memberAuth(param);

            if(authVO != null  && authVO.getExistsFlag() == 1) {
                param.put("memberNo", authVO.getMemberNo());
                cookieUtil.setCookie(httpServletResponse, param);
                result = 1;
            }else{
                return result;
            }
        }catch (Exception e){
            log.error("MemberService loginAuth ERROR ===> ", e);
        }
        return result;
    }

    public int forgotPassword(String email, String phone){
        int result = 0;
        try {
            result = demoInstaDataBase.forgotPassword(email, phone);
        }catch (Exception e){
            log.error("MemberService forgotPassword ERROR ===> ", e);
        }
        return result;
    }

    public int changePassword(Map<String,Object> param){
        int result = 0;
        try {
            result = demoInstaDataBase.updPw(param);
        }catch (Exception e){
            log.error("MemberService changePassword ERROR ===> ", e);
        }
        return result;
    }

    public int logout(HttpServletRequest req, HttpServletResponse res){
        try {
            cookieUtil.deleteAllCookies(req, res);
            return 1;
        }catch (Exception e){
            log.error("MemberService logout ERROR ===> ", e);
        }
        return 0;
    }

    public int memberProfilePhotoIns(Map<String, Object> param){
        int result = 0;
        try {
            result = demoInstaDataBase.memberProfilePhotoIns(param);
        }catch (Exception e){
            log.error("MemberService memberProfilePhotoIns ERROR ===> ",e);
        }
        return result;
    }

    public MemberDataVO memberSelect(String email){
        MemberDataVO result = new MemberDataVO();
        try {
            result = demoInstaDataBase.memberProfile(email);
        }catch (Exception e){
            log.error("MemberService memberProfilePhotoIns ERROR ===> ",e);
        }
        return result;
    }

    public List<MemberPostsVO> memberPosts(String email){
        List<MemberPostsVO> result = new ArrayList<>();
        try {
            result = demoInstaDataBase.memberPosts(email);
        }catch (Exception e){
            log.error("MemberService memberPosts ERROR ===> ",e);
        }
        return result;
    }

    public List<MemberFollowsVO> memberFollows(String email){
        List<MemberFollowsVO> result = new ArrayList<>();
        try {
            result = demoInstaDataBase.memberFollows(email);
        }catch (Exception e){
            log.error("MemberService memberFollows ERROR ===> ", e);
        }
        return result;
    }

    public Map<String,Object> myPageData(String email){
        Map<String, Object> resMap = new HashMap<>();
        try {
            MemberDataVO memberSelectVO = memberSelect(email);
            List<MemberPostsVO> postList = memberPosts(email);
            List<MemberFollowsVO> followList = memberFollows(email);

            int followCnt = 0;
            int followerCnt = 0;
            int postCnt = 0;
            followCnt = demoInstaDataBase.getFollowCnt(email);
            followerCnt = demoInstaDataBase.getFollowerCnt(email);
            postCnt = postList.size();

            resMap.put("followCnt", followCnt);
            resMap.put("followerCnt", followerCnt);
            resMap.put("postCnt", postCnt);

            resMap.put("profile", memberSelectVO);
            resMap.put("posts", postList);
            resMap.put("follows", followList);
        }catch (Exception e){
            log.error("MemberService myPageData ERROR ===> ", e);
        }
        return resMap;
    }

}