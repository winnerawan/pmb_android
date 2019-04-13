package id.ac.unipma.pmb.ui.detail;

import id.ac.unipma.pmb.ui.base.MvpPresenter;

public interface DetailMvpPresenter<V extends DetailView> extends MvpPresenter<V> {


    String getIntersId();
}
