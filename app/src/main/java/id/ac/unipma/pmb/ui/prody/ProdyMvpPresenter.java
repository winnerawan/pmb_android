package id.ac.unipma.pmb.ui.prody;

import id.ac.unipma.pmb.ui.base.MvpPresenter;

public interface ProdyMvpPresenter<V extends ProdyView> extends MvpPresenter<V> {

    void getProgramStudy();
}
