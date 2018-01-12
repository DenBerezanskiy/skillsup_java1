package ua.dp.skillsup.spring.homework;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class PostFilter {

    public List<Post> filterByKeyword(List<Post> posts, String keyWord){
        return posts.stream().filter((Post post) ->
                post.getText().toLowerCase().contains(keyWord.toLowerCase()))
                .collect(Collectors.<Post>toList());
    }
}
