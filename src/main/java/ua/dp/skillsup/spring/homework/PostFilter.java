package ua.dp.skillsup.spring.homework;

import java.util.List;
import java.util.stream.Collectors;

public class PostFilter {

    public List<Post> filterByKeyword(List<Post> posts, String keyWord){
        return posts.stream().filter((Post post) ->
                post.getText().toLowerCase().contains(keyWord.toLowerCase()))
                .collect(Collectors.<Post>toList());
    }
}
