package id.ac.unipma.pmb.ui.login;

import id.ac.unipma.pmb.ui.base.MvpPresenter;

public interface LoginMvpPresenter<V extends LoginView> extends MvpPresenter<V> {

    void login(String username, String password);
    void setLoggedIn(boolean loggedIn);
    void setStep(int step);
}
