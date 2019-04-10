package net.winnerawan.androidx.data.db.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class App {
    @SerializedName("banner")
    @Expose
    private String banner;
    @SerializedName("inters")
    @Expose
    private String inters;
    @SerializedName("reward")
    @Expose
    private String reward;

    public App(String banner, String inters, String reward) {
        this.banner = banner;
        this.inters = inters;
        this.reward = reward;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getInters() {
        return inters;
    }

    public void setInters(String inters) {
        this.inters = inters;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }
}
