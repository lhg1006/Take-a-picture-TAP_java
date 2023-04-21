package com.example.demo.api.common;

import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Service
public class CookieUtil {

    public void setCookie(HttpServletResponse res, Map<String,Object> param){
        Cookie cookie = new Cookie("isLogin", "true");
        cookie.setPath("/");
        Cookie cookie1 = new Cookie("memberEmail", (String) param.get("email"));
        cookie1.setPath("/");
        res.addCookie(cookie);
        res.addCookie(cookie1);
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
