package id.ac.unipma.pmb.ui.search;


import id.ac.unipma.pmb.ui.base.MvpPresenter;

public interface SearchMvpPresenter<V extends SearchView> extends MvpPresenter<V> {
    void search(String keyword);
}
