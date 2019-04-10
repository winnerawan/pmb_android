package net.winnerawan.androidx.ui.detail;

import net.winnerawan.androidx.ui.base.MvpPresenter;

public interface DetailMvpPresenter<V extends DetailView> extends MvpPresenter<V> {


    String getIntersId();
}
