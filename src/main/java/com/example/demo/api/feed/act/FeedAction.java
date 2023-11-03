//package com.example.demo.api.feed.act;
//
//import com.example.demo.api.feed.biz.FeedService;
//import com.example.demo.api.feed.vo.CommentResultVO;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/feed")
//public class FeedAction {
//
//    private final FeedService feedService;
//
//    @GetMapping("/getList")
//    public Map<String,Object> getFeedList(){
//        return feedService.feedList();
//    }
//
//    @PostMapping("/comment/ins")
//    public int commentIns(@RequestBody CommentResultVO param){
//        return feedService.commentIns(param);
//    }
//
//    @PostMapping("/comment/del")
//    public int commentDel(@RequestBody CommentResultVO param){
//        return feedService.commentDel(param);
//    }
//}
