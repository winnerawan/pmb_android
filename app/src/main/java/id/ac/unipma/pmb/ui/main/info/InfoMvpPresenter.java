package id.ac.unipma.pmb.ui.main.info;

import id.ac.unipma.pmb.ui.base.MvpPresenter;

public interface InfoMvpPresenter<V extends InfoView> extends MvpPresenter<V> {

    void getInfos();
}
