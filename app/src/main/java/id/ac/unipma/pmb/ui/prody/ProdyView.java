package id.ac.unipma.pmb.ui.prody;

import id.ac.unipma.pmb.data.network.model.Faculty;
import id.ac.unipma.pmb.data.network.model.Info;
import id.ac.unipma.pmb.ui.base.MvpView;

import java.util.List;

public interface ProdyView extends MvpView {
    void showProgramStudy(List<Faculty> faculties);
}
