package id.ac.unipma.pmb.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class LoginInfo {

    @SerializedName("username")
    @Expose
    private Integer username;
    @SerializedName("password")
    @Expose
    private Integer password;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("nama_mhs")
    private String name;

    public Integer getUsername() {
        return username;
    }

    public void setUsername(Integer username) {
        this.username = username;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
