package net.winnerawan.androidx.ui.main.info;

import net.winnerawan.androidx.data.network.model.Info;
import net.winnerawan.androidx.ui.base.MvpView;

import java.util.List;

public interface InfoView extends MvpView {
    void showInfos(List<Info> infos);
}
