package com.example.demo.api.common;

import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Service
public class CookieUtil {

    public void setCookie(HttpServletResponse res, Map<String,Object> param){
        Cookie cookieLogin = new Cookie("isLogin", "true");
        cookieLogin.setPath("/");
        Cookie cookieEmail = new Cookie("memberEmail", (String) param.get("email"));
        cookieEmail.setPath("/");
        Cookie cookieMemNo = new Cookie("memberNo", (String) param.get("memberNo"));
        cookieMemNo.setPath("/");
        res.addCookie(cookieLogin);
        res.addCookie(cookieEmail);
        res.addCookie(cookieMemNo);
    }

    public String getCookie(HttpServletRequest req, String cookieName){
        Cookie[] cookies=req.getCookies();
        if(cookies!=null){
            for (Cookie c : cookies) {
                String name = c.getName();
                String value = c.getValue();
                if (name.equals(cookieName)) {
                    return value;
                }
            }
        }
        return null;
    }

    public void deleteCookie(HttpServletResponse res, String cookieId){
        Cookie cookie = new Cookie(cookieId, null);
        cookie.setMaxAge(0);
        res.addCookie(cookie);
    }

    public void deleteAllCookies(HttpServletRequest req,HttpServletResponse res) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName());
                cookie.setPath("/");
                cookie.setMaxAge(0);
                res.addCookie(cookie);
            }
        }
    }

}
