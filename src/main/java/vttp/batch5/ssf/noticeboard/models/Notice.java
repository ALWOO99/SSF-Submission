package vttp.batch5.ssf.noticeboard.models;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Notice {
@Size(min =3 , max = 128)
    public String title;

    @NotNull
    @Email(message = "must be a well-formed email address")
    public String poster;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDateTime postDate;

    @NotNull
    public List<String> categories;

    @NotNull
    public String text;

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

    public @NotNull LocalDateTime getPostDate() {
        return postDate;
    }

    public void setPostDate(@NotNull LocalDateTime postDate) {
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
