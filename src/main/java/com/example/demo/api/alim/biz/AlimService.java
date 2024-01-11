package com.example.demo.api.alim.biz;

import com.example.demo.api.alim.vo.AlimInsVO;
import com.example.demo.api.alim.vo.AlimVO;
import com.example.demo.mapper.demoInsta.DemoInstaDataBase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class AlimService {
    private final DemoInstaDataBase demoInstaDataBase;

    public int sendAlim(AlimInsVO alimInsVO){
        int result = 0;

        try {
            result = demoInstaDataBase.sendAlim(alimInsVO);

        }catch (Exception e){
            log.error("AlimService sendAlim Error ===>>> {}", e);

        }

        return result;
    }

    public List<AlimVO> alimList(String memNo){
        List<AlimVO> alimVOList = new ArrayList<>();
        try {
            alimVOList = demoInstaDataBase.alimList(memNo);
        }catch (Exception e){
            log.error("AlimService alimList Error ===>>> {}", e);
        }
        return alimVOList;
    }
}
