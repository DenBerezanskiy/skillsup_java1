package ua.dp.skillsup.spring.homework;

import ua.dp.skillsup.spring.homework.service.ServiceApi;

import java.util.List;

public class PostProvider {
    private ServiceApi serviceApi;
    private PostFilter postFilter;
    private String keyWord;

    public List<Post> getPosts(){
        return postFilter.filterByKeyword(serviceApi.getPosts(), keyWord);
    }

    public void setFacebookServiceApi(ua.dp.skillsup.spring.homework.service.FacebookApi facebookServiceApi) {
    }

    public void setPostFilter(PostFilter postFilter) {
    }

    public void setKeyWord(String keyWord) {
    }

    public void setInstagramServiceApi(ua.dp.skillsup.spring.homework.service.InstagramApi instagramServiceApi) {
    }

    public void setTwitterServiceApi(ua.dp.skillsup.spring.homework.service.FacebookApi twitterServiceApi) {
    }
}
