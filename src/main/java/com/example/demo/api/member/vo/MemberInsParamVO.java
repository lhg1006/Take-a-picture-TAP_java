package com.example.demo.api.member.vo;

import lombok.Data;

@Data
public class MemberInsParamVO {
    private String email;
    private String password;
    private String chkPassword;
    private String name;
    private String phone;
}
