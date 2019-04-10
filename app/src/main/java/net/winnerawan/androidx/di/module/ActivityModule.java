package net.winnerawan.androidx.di.module;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import net.winnerawan.androidx.di.PerActivity;
import net.winnerawan.androidx.ui.account.AccountMvpPresenter;
import net.winnerawan.androidx.ui.account.AccountPresenter;
import net.winnerawan.androidx.ui.account.AccountView;
import net.winnerawan.androidx.ui.adapter.AnnouncementAdapter;
import net.winnerawan.androidx.ui.adapter.InfoAdapter;
import net.winnerawan.androidx.ui.adapter.PrestationAdapter;
import net.winnerawan.androidx.ui.adapter.SearchAdapter;
import net.winnerawan.androidx.ui.detail.DetailMvpPresenter;
import net.winnerawan.androidx.ui.help.AboutMvpPresenter;
import net.winnerawan.androidx.ui.help.AboutPresenter;
import net.winnerawan.androidx.ui.help.AboutView;
import net.winnerawan.androidx.ui.login.LoginMvpPresenter;
import net.winnerawan.androidx.ui.login.LoginPresenter;
import net.winnerawan.androidx.ui.login.LoginView;
import net.winnerawan.androidx.ui.main.MainPresenter;
import net.winnerawan.androidx.ui.detail.DetailView;
import net.winnerawan.androidx.ui.main.MainView;
import dagger.Module;
import dagger.Provides;

import net.winnerawan.androidx.di.ActivityContext;
import net.winnerawan.androidx.ui.main.detailinfo.DetailInfoMvpPresenter;
import net.winnerawan.androidx.ui.main.detailinfo.DetailInfoPresenter;
import net.winnerawan.androidx.ui.main.detailinfo.DetailInfoView;
import net.winnerawan.androidx.ui.main.home.AnnouncementSliderAdapter;
import net.winnerawan.androidx.ui.main.home.HomeMvpPresenter;
import net.winnerawan.androidx.ui.main.home.HomePresenter;
import net.winnerawan.androidx.ui.main.home.HomeView;
import net.winnerawan.androidx.ui.main.info.InfoMvpPresenter;
import net.winnerawan.androidx.ui.main.info.InfoPresenter;
import net.winnerawan.androidx.ui.main.info.InfoView;
import net.winnerawan.androidx.ui.main.search.SearchMvpPresenter;
import net.winnerawan.androidx.ui.main.search.SearchPresenter;
import net.winnerawan.androidx.ui.main.search.SearchView;
import net.winnerawan.androidx.ui.main.sign.SignMvpPresenter;
import net.winnerawan.androidx.ui.main.sign.SignPresenter;
import net.winnerawan.androidx.ui.main.sign.SignView;
import net.winnerawan.androidx.ui.splash.SplashMvpPresenter;
import net.winnerawan.androidx.ui.splash.SplashView;
import net.winnerawan.androidx.utils.rx.AppSchedulerProvider;
import net.winnerawan.androidx.utils.rx.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;
import net.winnerawan.androidx.ui.detail.DetailPresenter;
import net.winnerawan.androidx.ui.main.MainMvpPresenter;
import net.winnerawan.androidx.ui.splash.SplashPresenter;

import java.util.ArrayList;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }


    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }

    @Provides
    @PerActivity
    SplashMvpPresenter<SplashView> provideSplashPresenter(
            SplashPresenter<SplashView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    MainMvpPresenter<MainView> provideMainPresenter(
            MainPresenter<MainView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    DetailMvpPresenter<DetailView> provideDetailPresenter(
            DetailPresenter<DetailView> presenter) {
        return presenter;
    }

    @Provides
    HomeMvpPresenter<HomeView> provideHomePresenter(
            HomePresenter<HomeView> presenter) {
        return presenter;
    }

    @Provides
    InfoMvpPresenter<InfoView> provideInfoPresenter(
            InfoPresenter<InfoView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    LoginMvpPresenter<LoginView> provideLoginMvpPresenter(
            LoginPresenter<LoginView> presenter) {
        return presenter;
    }

    @Provides
    AnnouncementAdapter provideAnnouncementAdapter() {
        return new AnnouncementAdapter(new ArrayList<>());
    }

    @Provides
    AboutMvpPresenter<AboutView> provideAboutPresenter(
            AboutPresenter<AboutView> presenter) {
        return presenter;
    }

    @Provides
    AccountMvpPresenter<AccountView> provideAccountPresenter(
            AccountPresenter<AccountView> presenter) {
        return presenter;
    }

    @Provides
    SignMvpPresenter<SignView> provideSignPresenter(
            SignPresenter<SignView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    SearchMvpPresenter<SearchView> provideSearchPresenter(
            SearchPresenter<SearchView> presenter) {
        return presenter;
    }

    @Provides
    PrestationAdapter providePrestationAdapter() {
        return new PrestationAdapter(new ArrayList<>());
    }

    @Provides
    SearchAdapter provideSearchAdapter() {
        return new SearchAdapter(new ArrayList<>());
    }

    @Provides
    InfoAdapter provideInfoAdapter() {
        return new InfoAdapter(new ArrayList<>());
    }

    @Provides
    DetailInfoMvpPresenter<DetailInfoView> provideDetailInfoPresenter(
            DetailInfoPresenter<DetailInfoView> presenter) {
        return presenter;
    }
}
