package net.winnerawan.androidx.ui.main.home;

import net.winnerawan.androidx.data.network.model.Announcement;
import net.winnerawan.androidx.data.network.model.Prestation;
import net.winnerawan.androidx.ui.base.MvpView;

import java.util.List;

public interface HomeView extends MvpView {
    void showAnnouncements(List<Announcement> announcements);

    void showPrestations(List<Prestation> prestations);

    void stopShimmer();
}
