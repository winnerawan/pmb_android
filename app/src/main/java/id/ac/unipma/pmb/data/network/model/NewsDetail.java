package id.ac.unipma.pmb.data.network.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsDetail {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("writer")
    @Expose
    private String writer;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("first")
    @Expose
    private String first;
    @SerializedName("center")
    @Expose
    private String center;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("link")
    @Expose
    private String link;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writter) {
        this.writer = writter;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
