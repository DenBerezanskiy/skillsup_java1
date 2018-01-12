package ua.dp.skillsup.spring.homework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sun.misc.Contended;
import ua.dp.skillsup.spring.homework.service.FacebookApi;
import ua.dp.skillsup.spring.homework.service.InstagramApi;
import ua.dp.skillsup.spring.homework.service.ServiceApi;
import ua.dp.skillsup.spring.homework.service.TwitterApi;

import java.util.ArrayList;
import java.util.List;
@Configuration
public class SocialServiceApp {
    @Autowired
    List<PostProvider> providers;

    @Bean
    @Autowired
   public PostProvider instagramPostProvider(@Value("${keyWord}") String keyWord ,
                                             InstagramApi instagramApi, PostFilter postFilter)
    {
        PostProvider postProvider = initializePostProvider(keyWord, instagramApi, postFilter);
        return postProvider;
    }

    @Bean
    @Autowired
    public PostProvider twitterPostProvider(@Value("${keyWord}") String keyWord ,
                                            TwitterApi twitterApi, PostFilter postFilter)
    {
        PostProvider postProvider = initializePostProvider(keyWord, twitterApi, postFilter);
        return postProvider;
    }
    @Bean
    @Autowired
    public PostProvider facebookPostProvider(@Value("${keyWord}") String keyWord ,
                                             FacebookApi facebookApi , PostFilter postFilter)
    {
        PostProvider postProvider = initializePostProvider(keyWord, facebookApi, postFilter);
        return postProvider;
    }


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
    private PostProvider initializePostProvider(String keyWord , ServiceApi serviceApi , PostFilter postFilter)
    {
        PostProvider postProvider = new PostProvider();
        postProvider.setServiceApi(serviceApi);
        postProvider.setPostFilter(postFilter);
        postProvider.setKeyWord(keyWord);
        return postProvider;
    }
}
