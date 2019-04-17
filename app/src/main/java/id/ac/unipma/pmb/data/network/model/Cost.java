package id.ac.unipma.pmb.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cost {

    @SerializedName("program_study")
    @Expose
    private String programStudy;
    @SerializedName("biaya_semester")
    @Expose
    private String biayaSemester;
    @SerializedName("pkkmb")
    @Expose
    private String pkkmb;
    @SerializedName("pbi_gel1")
    @Expose
    private String pbiGel1;
    @SerializedName("pbi_gel2")
    @Expose
    private String pbiGel2;
    @SerializedName("pbi_gel3")
    @Expose
    private String pbiGel3;

    public String getProgramStudy() {
        return programStudy;
    }

    public void setProgramStudy(String programStudy) {
        this.programStudy = programStudy;
    }

    public String getBiayaSemester() {
        return biayaSemester;
    }

    public void setBiayaSemester(String biayaSemester) {
        this.biayaSemester = biayaSemester;
    }

    public String getPkkmb() {
        return pkkmb;
    }

    public void setPkkmb(String pkkmb) {
        this.pkkmb = pkkmb;
    }

    public String getPbiGel1() {
        return pbiGel1;
    }

    public void setPbiGel1(String pbiGel1) {
        this.pbiGel1 = pbiGel1;
    }

    public String getPbiGel2() {
        return pbiGel2;
    }

    public void setPbiGel2(String pbiGel2) {
        this.pbiGel2 = pbiGel2;
    }

    public String getPbiGel3() {
        return pbiGel3;
    }

    public void setPbiGel3(String pbiGel3) {
        this.pbiGel3 = pbiGel3;
    }

}
