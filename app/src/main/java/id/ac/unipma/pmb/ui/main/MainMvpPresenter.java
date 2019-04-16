package id.ac.unipma.pmb.ui.main;

import id.ac.unipma.pmb.ui.base.MvpPresenter;

public interface MainMvpPresenter<V extends MainView> extends MvpPresenter<V> {

    boolean isLoggedIn();
}
