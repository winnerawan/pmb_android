package net.winnerawan.androidx.ui.main.home;

import net.winnerawan.androidx.ui.base.MvpPresenter;
import net.winnerawan.androidx.ui.base.MvpView;

public interface HomeMvpPresenter<V extends MvpView> extends MvpPresenter<V> {

    void getAnnouncements();
    void getPrestations();
}
