package id.ac.unipma.pmb.ui.search;

import id.ac.unipma.pmb.data.network.model.Selection;
import id.ac.unipma.pmb.ui.base.MvpView;

import java.io.File;
import java.util.List;

public interface SearchView extends MvpView {

    void showSelections(List<Selection> selections);

    void showKwitansi(File file);
}
