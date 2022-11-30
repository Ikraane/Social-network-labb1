package com.example.Socialnetwork.bo;


import com.example.Socialnetwork.db.PostDB;
import com.example.Socialnetwork.db.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService implements IPostService {


    @Autowired
    private PostRepository postRepository;

    @Override
    public PostDB savePost(Post post) throws IllegalArgumentException{

        if (post.getPoster() == null || post.getMessage() == null) throw new IllegalArgumentException();

        else {
            PostDB postDB = new PostDB();
            postDB.setPoster(post.getPoster());
            postDB.setMessage(post.getMessage());
            postDB.setDate(LocalDateTime.now());

            return postRepository.save(postDB);
        }

    }

    @Override
    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();

        for(PostDB postDB: postRepository.findAll()) {
            Post post = new Post();
            post.setPostID(postDB.getPostID());
            post.setPoster(postDB.getPoster());
            post.setMessage(postDB.getMessage());
            post.setPostDate(postDB.getDate());
            posts.add(post);

        }
        return posts;
    }

    @Override
    public List<Post> findPostsByPoster(String poster) {
        List<Post> posts = new ArrayList<>();

        for(PostDB postDB: postRepository.findPostsByPoster(poster)) {
            Post post = new Post();
            post.setPostID(postDB.getPostID());
            post.setPoster(postDB.getPoster());
            post.setMessage(postDB.getMessage());
            post.setPostDate(postDB.getDate());
            posts.add(post);

        }
        return posts;
    }
}
