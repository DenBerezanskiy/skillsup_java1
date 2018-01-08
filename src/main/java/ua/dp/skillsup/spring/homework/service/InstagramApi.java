package ua.dp.skillsup.spring.homework.service;

import ua.dp.skillsup.spring.homework.Post;

import java.util.Arrays;
import java.util.List;

public class InstagramApi implements ServiceApi{
    private String appName;
    private String appSecret;

    @Override
    public List<Post> getPosts() {
        System.out.println("Reading posts from Instagram with following credentials: " + appName + "/" + appSecret);
        //some fancy implementation here
        return Arrays.asList(
                new Post("What a beautiful sunset! #sunset #evening #photo #potato #iphone #camera", "http://instagram.com/sunset.jpg"),
                new Post("Look at my dog!", "http://instagram.com/verygoodboy.jpg"),
                new Post("I'm such a hot chick! #selfie #kfc", "http://instagram.com/selfie.jpg")
        );
    }
}
