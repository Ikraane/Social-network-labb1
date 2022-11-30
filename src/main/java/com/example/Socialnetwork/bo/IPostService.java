package com.example.Socialnetwork.bo;

import com.example.Socialnetwork.db.PostDB;

import java.util.List;

public interface IPostService {

    public PostDB savePost(Post post) throws Exception;
    public List<Post> getAllPosts();
    public List<Post> findPostsByPoster(String poster);
}
