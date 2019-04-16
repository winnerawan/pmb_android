package id.ac.unipma.pmb.ui.main.home;

import id.ac.unipma.pmb.data.network.model.Announcement;
import id.ac.unipma.pmb.data.network.model.MenuInfo;
import id.ac.unipma.pmb.data.network.model.News;
import id.ac.unipma.pmb.data.network.model.Prestation;
import id.ac.unipma.pmb.ui.base.MvpView;

import java.util.List;

public interface HomeView extends MvpView {
    void showAnnouncements(List<Announcement> announcements);

    void showNews(List<News> news);

    void showMenuInfos(List<MenuInfo> menuInfos);
    void stopShimmer2();
    void stopShimmer1();
    void stopShimmer0();
}
