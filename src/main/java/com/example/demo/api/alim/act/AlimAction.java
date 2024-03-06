package com.example.demo.api.alim.act;

import com.example.demo.api.alim.biz.AlimService;
import com.example.demo.api.alim.vo.AlimInsVO;
import com.example.demo.api.alim.vo.AlimVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/alim")
public class AlimAction {
    private final AlimService alimService;

    @PostMapping("/send")
    public int alimSend(AlimInsVO alimInsVO){
        return alimService.sendAlim(alimInsVO);
    }

    @GetMapping("/list")
    public List<AlimVO> alimList(@RequestParam("myMemNo") String memNo){
        return alimService.alimList(memNo);
    }
}
