package vttp.batch5.ssf.noticeboard.models;

import java.util.Arrays;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Notice {
@Size(min =3 , max = 128)
    private String title;

    @NotNull
    @Email
    private String poster;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String postDate;

    @NotNull
    private List<String> categories = Arrays.asList("public-notice", "sport", "meeting", "garage-sale","others");

    @NotNull
    private String text;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

  

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
    
}
