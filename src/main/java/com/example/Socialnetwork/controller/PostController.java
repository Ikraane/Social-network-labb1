package com.example.Socialnetwork.controller;

import com.example.Socialnetwork.bo.IPostService;
import com.example.Socialnetwork.bo.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@CrossOrigin
public class PostController {

    @Autowired
    private IPostService iPostService;

    @PostMapping("/add")
    public String add(@RequestBody Post post) throws Exception {
        iPostService.savePost(post);
        return "New post is added";
    }

    @GetMapping("/getAll")
    public List<Post> getAllPosts() {
        return iPostService.getAllPosts();
    }

    @GetMapping("/getMyPosts")
    public List<Post> findPostsByPoster(@RequestParam(value = "poster", required = false) String poster) {
        return iPostService.findPostsByPoster(poster);
    }
}
