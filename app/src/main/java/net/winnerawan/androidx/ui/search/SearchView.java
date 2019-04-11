package net.winnerawan.androidx.ui.search;

import net.winnerawan.androidx.data.network.model.Selection;
import net.winnerawan.androidx.ui.base.MvpView;

import java.util.List;

public interface SearchView extends MvpView {

    void showSelections(List<Selection> selections);
}
