package ua.dp.skillsup.spring.homework;

public class Post {
    private String text;
    private String imageUrl;

    public Post(String text, String imageUrl) {
        this.text = text;
        this.imageUrl = imageUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Post{" +
                "text='" + text + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

}
