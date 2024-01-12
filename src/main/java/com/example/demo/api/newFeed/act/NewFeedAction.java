package com.example.demo.api.newFeed.act;

import com.example.demo.api.newFeed.biz.NewFeedService;
import com.example.demo.api.newFeed.vo.FollowsVO;
import com.example.demo.api.newFeed.vo.LikeVO;
import com.example.demo.api.newFeed.vo.NewFeedVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/list/target")
    public Map<String, Object> targetFeedList(@RequestParam("userMail") String userMail){
        return newFeedService.getTargetFeedList(userMail);
    }

    @GetMapping("/list/single")
    public NewFeedVO singleFeed(@RequestParam("userMail") String userMail, @RequestParam("postNo") int postNo){
        return newFeedService.getSingleFeed(userMail, postNo);
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
    public int likeDel(@RequestBody Map<String,Object> param){
        return newFeedService.likeDel(param);
    }

    @GetMapping("/getLikeList")
    public List<LikeVO> likeList(@RequestParam("postNo") int postNo){
        return newFeedService.getLikeList(postNo);
    }

    @GetMapping("/getFollowList")
    public List<FollowsVO> followList(@RequestParam("email") String email, @RequestParam("type") String type){
        return newFeedService.followList(email, type);
    }

    @PostMapping("/followIns")
    public int addFollow(@RequestBody Map<String, Object> param){
        return newFeedService.addFollow(param);
    }

    @PostMapping("/followDel")
    public int delFollow(@RequestBody Map<String, Object> param){
        return newFeedService.delFollow(param);
    }

    @GetMapping("/isFollowed")
    public int isFollowed(@RequestParam Map<String,Object> param){
        return newFeedService.isFollowed(param);
    }
}
