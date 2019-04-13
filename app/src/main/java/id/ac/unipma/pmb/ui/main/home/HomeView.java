package id.ac.unipma.pmb.ui.main.home;

import id.ac.unipma.pmb.data.network.model.Announcement;
import id.ac.unipma.pmb.data.network.model.Prestation;
import id.ac.unipma.pmb.ui.base.MvpView;

import java.util.List;

public interface HomeView extends MvpView {
    void showAnnouncements(List<Announcement> announcements);

    void showPrestations(List<Prestation> prestations);

    void stopShimmer();
}
