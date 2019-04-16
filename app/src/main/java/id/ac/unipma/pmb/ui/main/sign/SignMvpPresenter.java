package id.ac.unipma.pmb.ui.main.sign;

import id.ac.unipma.pmb.ui.base.MvpPresenter;

public interface SignMvpPresenter<V extends SignView> extends MvpPresenter<V> {

    void login(String username, String password);
}
