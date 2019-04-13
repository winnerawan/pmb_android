package id.ac.unipma.pmb.ui.main.detailinfo;

import id.ac.unipma.pmb.ui.base.MvpPresenter;


public interface DetailInfoMvpPresenter<V extends DetailInfoView> extends MvpPresenter<V> {

    void getContentCost(String link);
    void getContentSchedule(String link);
    void getContentTrack(String link);
    void getContentStudy(String link);
}
