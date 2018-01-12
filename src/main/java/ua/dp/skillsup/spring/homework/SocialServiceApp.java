package ua.dp.skillsup.spring.homework;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class SocialServiceApp {
    List<PostProvider> providers;

    public static void main(String[] args) {
        //todo instantiate App with spring
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("socialApplicationContext.xml");

        SocialServiceApp socialServiceApp = (SocialServiceApp) context.getBean("socialServiceApp");
        socialServiceApp.run();
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


    public List<PostProvider> getProviders() {
        return providers;
    }

    public void setProviders(List<PostProvider> providers) {
        this.providers = providers;
    }
}
