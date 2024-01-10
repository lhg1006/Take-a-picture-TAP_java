package com.example.demo.api.alim.vo;

import lombok.Data;

@Data
public class AlimVO {
    private int autoNo;
    private int myMemNo;
    private int yourMemNo;
    private int alimCode;    // 1: like, 2: comment, 3: follow
    private String insDate;
}
