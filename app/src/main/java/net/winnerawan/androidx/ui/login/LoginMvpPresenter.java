package net.winnerawan.androidx.ui.login;

import net.winnerawan.androidx.ui.base.MvpPresenter;

public interface LoginMvpPresenter<V extends LoginView> extends MvpPresenter<V> {

    void login(String username, String password);
}
