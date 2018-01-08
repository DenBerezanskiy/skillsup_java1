package ua.dp.skillsup.spring.homework;

import java.util.ArrayList;
import java.util.List;

public class SocialServiceApp {
    List<PostProvider> providers;

    public static void main(String[] args) {
        //todo instantiate App with spring
        new SocialServiceApp().run();
    }

    public void run(){
        List<Post> posts = new ArrayList<>();
        for (PostProvider provider : providers) {
            posts.addAll(provider.getPosts());
        }
        System.out.println("Filtered posts:");
        for (Post post : posts) {
            System.out.println(post);
        }
    }
}
