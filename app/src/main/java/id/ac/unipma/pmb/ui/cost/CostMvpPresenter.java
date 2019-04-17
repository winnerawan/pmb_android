package id.ac.unipma.pmb.ui.cost;

import id.ac.unipma.pmb.ui.base.MvpPresenter;

public interface CostMvpPresenter<V extends CostView> extends MvpPresenter<V> {
    void getCosts();
}
