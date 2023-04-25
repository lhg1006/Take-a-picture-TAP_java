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
    public Map<String, Object> newFeedList(@RequestParam("userMail") String userMail){
        return newFeedService.getNewFeedList(userMail);
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
    @PostMapping("/likeIns")
    public int likeIns(@RequestBody Map<String,Object> param){
        return newFeedService.likeIns(param);
    }
    @PostMapping("/likeDel")
    public int LikeDel(@RequestBody Map<String,Object> param){
        return newFeedService.likeDel(param);
    }
}
