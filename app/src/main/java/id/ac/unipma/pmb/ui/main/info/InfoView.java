package id.ac.unipma.pmb.ui.main.info;

import id.ac.unipma.pmb.data.network.model.Info;
import id.ac.unipma.pmb.ui.base.MvpView;

import java.util.List;

public interface InfoView extends MvpView {
    void showInfos(List<Info> infos);
}
