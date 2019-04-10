package net.winnerawan.androidx.di.component;

import net.winnerawan.androidx.ui.account.AccountFragment;
import net.winnerawan.androidx.ui.detail.DetailActivity;
import net.winnerawan.androidx.ui.help.AboutFragment;
import net.winnerawan.androidx.ui.login.LoginActivity;
import net.winnerawan.androidx.ui.main.MainActivity;
import net.winnerawan.androidx.ui.main.detailinfo.DetailInfoFragment;
import net.winnerawan.androidx.ui.main.home.HomeFragment;
import net.winnerawan.androidx.ui.main.info.InfoFragment;
import net.winnerawan.androidx.ui.main.sign.SignFragment;
import net.winnerawan.androidx.ui.main.search.SearchActivity;
import net.winnerawan.androidx.ui.splash.SplashActivity;
import dagger.Component;
import net.winnerawan.androidx.di.PerActivity;
import net.winnerawan.androidx.di.module.ActivityModule;

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

    void inject(DetailInfoFragment detailInfoFragment);
}
