package id.ac.unipma.pmb.ui.main.detailinfo;

import id.ac.unipma.pmb.data.network.model.ContentInfo;
import id.ac.unipma.pmb.ui.base.MvpView;

public interface DetailInfoView extends MvpView {
    void showContentCost(ContentInfo content);
    void showContentTrack(ContentInfo content);
    void showContentStudy(ContentInfo content);
    void showContentSchedule(ContentInfo content);
}
