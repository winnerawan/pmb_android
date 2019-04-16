package id.ac.unipma.pmb.di.component;

import id.ac.unipma.pmb.di.module.ActivityModule;
import id.ac.unipma.pmb.ui.account.AccountFragment;
import id.ac.unipma.pmb.ui.detail.DetailActivity;
import id.ac.unipma.pmb.ui.about.AboutFragment;
import id.ac.unipma.pmb.ui.login.LoginActivity;
import id.ac.unipma.pmb.ui.main.MainActivity;
import id.ac.unipma.pmb.ui.main.chart.ChartActivity;
import id.ac.unipma.pmb.ui.main.home.HomeFragment;
import id.ac.unipma.pmb.ui.main.info.InfoFragment;
import id.ac.unipma.pmb.ui.main.sign.SignFragment;
import id.ac.unipma.pmb.ui.search.SearchActivity;
import id.ac.unipma.pmb.ui.splash.SplashActivity;
import id.ac.unipma.pmb.ui.main.detailinfo.DetailInfoActivity;
import dagger.Component;
import id.ac.unipma.pmb.di.PerActivity;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SplashActivity activity);

    void inject(MainActivity mainActivity);

    void inject(DetailActivity detailActivity);

    void inject(LoginActivity loginActivity);

    void inject(HomeFragment homeFragment);

    void inject(AboutFragment helpFragment);

    void inject(AccountFragment accountFragment);

    void inject(SignFragment signFragment);

    void inject(SearchActivity searchActivity);

    void inject(InfoFragment infoFragment);

    void inject(DetailInfoActivity detailInfoActivity);

    void inject(ChartActivity chartActivity);
}
