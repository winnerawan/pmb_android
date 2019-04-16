package id.ac.unipma.pmb.ui.detail;

import id.ac.unipma.pmb.data.network.model.NewsDetail;
import id.ac.unipma.pmb.ui.base.MvpView;

public interface DetailView extends MvpView {
    void showNewsDetail(NewsDetail news);
    void stopShimmer();
}
