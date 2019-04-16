package id.ac.unipma.pmb.ui.main.home;

import id.ac.unipma.pmb.ui.base.MvpPresenter;
import id.ac.unipma.pmb.ui.base.MvpView;

public interface HomeMvpPresenter<V extends MvpView> extends MvpPresenter<V> {

    void getAnnouncements();
    void getNews();
    void getMenuInfos();

    boolean getLoggedIn();
}
