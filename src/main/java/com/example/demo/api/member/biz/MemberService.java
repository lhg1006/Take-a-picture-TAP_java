package com.example.demo.api.member.biz;

import com.example.demo.api.common.CookieUtil;
import com.example.demo.api.member.vo.MemberInsParamVO;
import com.example.demo.mapper.master.MasterDataBase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MasterDataBase masterDataBase;
    private final CookieUtil cookieUtil;

    public int memberIns(MemberInsParamVO memberInsParamVO){
        int result = 0;
        try {
            String password = memberInsParamVO.getPassword();
            String chkPassword = memberInsParamVO.getChkPassword();
            if(password.equals(chkPassword)){
                result = masterDataBase.memberIns(memberInsParamVO);
            }else{
                result = -2; // 비밀번호 불일치
            }
        }catch (Exception e){
            log.error("MemberDataAction memberIns ERROR ===> {}", e);
        }
        return result;
    }

    public int emailChk(String email){
        int result = 0;
        try {
            result = masterDataBase.emailChk(email);
        }catch (Exception e){
            log.error("MemberDataAction emailChk ERROR ===> {}", e);
        }
        return result;
    }

    public int loginAuth(Map<String, Object> param, HttpServletResponse httpServletResponse){
        int result = 0;
        try {
            result = masterDataBase.memberAuth(param);
            if(result == 1) {
                cookieUtil.setCookie(httpServletResponse, param);
            }
        }catch (Exception e){
            log.error("MemberDataAction loginAuth ERROR ===> {}", e);
        }
        return result;
    }

    public int forgotPassword(String email, String phone){
        int result = 0;
        try {
            result = masterDataBase.forgotPassword(email, phone);
        }catch (Exception e){
            log.error("MemberDataAction forgotPassword ERROR ===> {}", e);
        }
        return result;
    }

    public int changePassword(Map<String,Object> param){
        int result = 0;
        try {
            result = masterDataBase.updPw(param);
        }catch (Exception e){
            log.error("MemberDataAction changePassword ERROR ===> {}", e);
        }
        return result;
    }

    public int logout(HttpServletRequest req, HttpServletResponse res){
        try {
            cookieUtil.deleteAllCookies(req, res);
            return 1;
        }catch (Exception e){
            log.error("MemberDataAction logout ERROR ===> {}", e);
        }
        return 0;
    }

}