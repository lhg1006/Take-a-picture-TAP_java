package com.example.demo.api.alim.biz;

import com.example.demo.api.alim.vo.AlimInsVO;
import com.example.demo.api.alim.vo.AlimVO;
import com.example.demo.mapper.tap.TapDataBase;
import com.example.demo.utils.DateFormatUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class AlimService {
    private final TapDataBase TAPDataBase;
    private final DateFormatUtil dateFormatUtil;

    public int sendAlim(AlimInsVO alimInsVO){
        int result = 0;

        try {
            result = TAPDataBase.sendAlim(alimInsVO);

        }catch (Exception e){
            log.error("AlimService sendAlim Error ===>>> {}", e);

        }

        return result;
    }

    public Map<String, Object> alimList(int memNo, int pageNo){
        Map<String, Object> resMap = new HashMap<>();
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("memNo", memNo);
            params.put("pagePerCnt", 20);
            params.put("offset", 20 * (pageNo - 1));
            List<AlimVO> alimVOList = TAPDataBase.alimList(params);
            boolean stopPaging = false;

            if(alimVOList != null && alimVOList.size() > 0){
                for (AlimVO alimVO : alimVOList) {
                    String insDate = alimVO.getInsDate();
                    if (!StringUtils.isEmpty(insDate)) {
                        String insDateKor = dateFormatUtil.insDateKorea(insDate);
                        alimVO.setInsDateKor(insDateKor);
                    }
                }
            }else{
                stopPaging = true;
            }

            resMap.put("alimList", alimVOList);
            resMap.put("stopPaging", stopPaging);

        }catch (Exception e){
            log.error("AlimService alimList Error ===>>> {}", e);
        }
        return resMap;
    }
}
