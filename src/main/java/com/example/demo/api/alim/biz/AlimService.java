package com.example.demo.api.alim.biz;

import com.example.demo.api.alim.vo.AlimInsVO;
import com.example.demo.api.alim.vo.AlimVO;
import com.example.demo.mapper.demoInsta.DemoInstaDataBase;
import com.example.demo.utils.DateFormatUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class AlimService {
    private final DemoInstaDataBase demoInstaDataBase;
    private final DateFormatUtil dateFormatUtil;

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

            if(alimVOList != null){
                for (AlimVO alimVO : alimVOList) {
                    String insDate = alimVO.getInsDate();
                    if (!StringUtils.isEmpty(insDate)) {
                        String insDateKor = dateFormatUtil.insDateKorea(insDate);
                        alimVO.setInsDateKor(insDateKor);
                    }
                }
            }

        }catch (Exception e){
            log.error("AlimService alimList Error ===>>> {}", e);
        }
        return alimVOList;
    }
}
