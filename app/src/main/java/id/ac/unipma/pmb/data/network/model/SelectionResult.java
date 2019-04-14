package id.ac.unipma.pmb.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SelectionResult {

    @SerializedName("no_reg")
    @Expose
    private String noReg;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("study")
    @Expose
    private String study;
    @SerializedName("base64")
    @Expose
    private String base64;

    public String getNoReg() {
        return noReg;
    }

    public void setNoReg(String noReg) {
        this.noReg = noReg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudy() {
        return study;
    }

    public void setStudy(String study) {
        this.study = study;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }
}
