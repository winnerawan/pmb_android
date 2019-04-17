package id.ac.unipma.pmb.data.network.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProgramStudy {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("faculty")
    @Expose
    private Faculty faculty;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
}
