package net.winnerawan.androidx.ui.main.search;


import net.winnerawan.androidx.ui.base.MvpPresenter;

public interface SearchMvpPresenter<V extends SearchView> extends MvpPresenter<V> {
    void search(String keyword);
}
