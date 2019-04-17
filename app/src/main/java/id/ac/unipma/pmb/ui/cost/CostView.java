package id.ac.unipma.pmb.ui.cost;

import id.ac.unipma.pmb.data.network.model.Cost;
import id.ac.unipma.pmb.ui.base.MvpView;

import java.util.List;

public interface CostView extends MvpView {
    void showCosts(List<Cost> costs);
}
