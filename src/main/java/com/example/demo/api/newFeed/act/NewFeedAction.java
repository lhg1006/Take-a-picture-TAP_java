package com.example.demo.api.newFeed.act;

import com.example.demo.api.newFeed.biz.NewFeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/newFeed")
public class NewFeedAction {
    private final NewFeedService newFeedService;

    @GetMapping("/list")
    public Map<String, Object> newFeedList(){
        return newFeedService.getNewFeedList();
    }

    @PostMapping("/addComment")
    public int addComment(@RequestBody Map<String, Object> param){
        return newFeedService.addComment(param);
    }

    @PostMapping("/delComment")
    public int delComment(@RequestBody Map<String, Object> param){
        return newFeedService.delComment(param);
    }
    @PostMapping("/addPost")
    public int addPost(@RequestBody Map<String,Object> param){
        return newFeedService.addPost(param);
    }
    @PostMapping("/delPost")
    public int delPost(@RequestBody Map<String,Object> param){
        return newFeedService.delPost(param);
    }
}
