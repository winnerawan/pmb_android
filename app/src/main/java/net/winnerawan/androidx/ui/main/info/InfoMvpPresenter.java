package net.winnerawan.androidx.ui.main.info;

import net.winnerawan.androidx.ui.base.MvpPresenter;

public interface InfoMvpPresenter<V extends InfoView> extends MvpPresenter<V> {

    void getInfos();
}
