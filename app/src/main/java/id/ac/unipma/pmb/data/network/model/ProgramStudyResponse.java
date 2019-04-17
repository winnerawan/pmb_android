package id.ac.unipma.pmb.data.network.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProgramStudyResponse {

    @SerializedName("program_study")
    @Expose
    private List<ProgramStudy> programStudy = null;

    public List<ProgramStudy> getProgramStudy() {
        return programStudy;
    }

    public void setProgramStudy(List<ProgramStudy> programStudy) {
        this.programStudy = programStudy;
    }
}
