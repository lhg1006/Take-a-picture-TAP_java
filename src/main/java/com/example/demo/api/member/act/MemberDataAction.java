package com.example.demo.api.member.act;

import com.example.demo.api.member.biz.MemberService;
import com.example.demo.api.member.vo.MemberInsParamVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MemberDataAction {
    private final MemberService memberService;

    @GetMapping("/api/email/duplication")
    public int emailChk(@RequestParam("email") String email){
        return memberService.emailChk(email);
    }

    @PostMapping("/api/signup/memberIns")
    public int memberIns(@RequestBody MemberInsParamVO memberInsParamVO){
        return memberService.memberIns(memberInsParamVO);
    }

    @GetMapping("/api/login/auth")
    public int login(@RequestParam Map<String,Object> param, HttpServletResponse httpServletResponse){
        return memberService.loginAuth(param, httpServletResponse);
    }

    @GetMapping("/api/forgot/password")
    public int forgotPassword(@RequestParam("email")String email, @RequestParam("phone")String phone){
        return memberService.forgotPassword(email, phone);
    }

    @PostMapping("/api/change/password")
    public int changePassword(@RequestBody Map<String,Object> param){
        return memberService.changePassword(param);
    }

    @PostMapping("/api/logout")
    public int logout(HttpServletRequest req, HttpServletResponse res){
        return memberService.logout(req, res);
    }

    @PostMapping("/api/member/profile/photoIns")
    public int memberProfilePhotoIns(@RequestBody Map<String, Object> param){
        return memberService.memberProfilePhotoIns(param);
    }

}