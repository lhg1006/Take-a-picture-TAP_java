package com.example.demo.api.test;

import com.example.demo.api.member.vo.MemberDataListVO;
import com.example.demo.mapper.master.MasterDataBase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestAction {

    private final MasterDataBase masterDataBase;

}
