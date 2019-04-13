package id.ac.unipma.pmb.di.module;

import id.ac.unipma.pmb.ui.account.AccountMvpPresenter;
import id.ac.unipma.pmb.ui.account.AccountPresenter;
import id.ac.unipma.pmb.ui.adapter.AnnouncementAdapter;
import id.ac.unipma.pmb.ui.adapter.SearchAdapter;
import id.ac.unipma.pmb.ui.detail.DetailMvpPresenter;
import id.ac.unipma.pmb.ui.detail.DetailPresenter;
import id.ac.unipma.pmb.ui.help.AboutMvpPresenter;
import id.ac.unipma.pmb.ui.help.AboutPresenter;
import id.ac.unipma.pmb.ui.main.MainMvpPresenter;
import id.ac.unipma.pmb.ui.main.MainPresenter;
import id.ac.unipma.pmb.ui.main.chart.ChartMvpPresenter;
import id.ac.unipma.pmb.ui.main.chart.ChartPresenter;
import id.ac.unipma.pmb.ui.main.home.HomeMvpPresenter;
import id.ac.unipma.pmb.ui.main.home.HomePresenter;
import id.ac.unipma.pmb.ui.main.info.InfoMvpPresenter;
import id.ac.unipma.pmb.ui.main.info.InfoPresenter;
import id.ac.unipma.pmb.ui.main.sign.SignMvpPresenter;
import id.ac.unipma.pmb.ui.main.sign.SignPresenter;
import id.ac.unipma.pmb.ui.search.SearchMvpPresenter;
import id.ac.unipma.pmb.ui.search.SearchPresenter;
import id.ac.unipma.pmb.ui.splash.SplashMvpPresenter;
import id.ac.unipma.pmb.ui.splash.SplashPresenter;
import id.ac.unipma.pmb.utils.rx.AppSchedulerProvider;
import id.ac.unipma.pmb.utils.rx.SchedulerProvider;
import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import id.ac.unipma.pmb.di.PerActivity;
import id.ac.unipma.pmb.ui.account.AccountView;
import id.ac.unipma.pmb.ui.adapter.InfoAdapter;
import id.ac.unipma.pmb.ui.adapter.PrestationAdapter;
import id.ac.unipma.pmb.ui.main.chart.ChartView;
import id.ac.unipma.pmb.ui.help.AboutView;
import id.ac.unipma.pmb.ui.login.LoginMvpPresenter;
import id.ac.unipma.pmb.ui.login.LoginPresenter;
import id.ac.unipma.pmb.ui.login.LoginView;
import id.ac.unipma.pmb.ui.detail.DetailView;
import id.ac.unipma.pmb.ui.main.MainView;
import dagger.Module;
import dagger.Provides;

import id.ac.unipma.pmb.di.ActivityContext;
import id.ac.unipma.pmb.ui.main.detailinfo.DetailInfoMvpPresenter;
import id.ac.unipma.pmb.ui.main.detailinfo.DetailInfoPresenter;
import id.ac.unipma.pmb.ui.main.detailinfo.DetailInfoView;
import id.ac.unipma.pmb.ui.main.home.HomeView;
import id.ac.unipma.pmb.ui.main.info.InfoView;
import id.ac.unipma.pmb.ui.search.SearchView;
import id.ac.unipma.pmb.ui.main.sign.SignView;
import id.ac.unipma.pmb.ui.splash.SplashView;

import io.reactivex.disposables.CompositeDisposable;

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
    @PerActivity
    ChartMvpPresenter<ChartView> provideChoroplethChartPresenter(
            ChartPresenter<ChartView> presenter) {
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
    @PerActivity
    DetailInfoMvpPresenter<DetailInfoView> provideDetailInfoPresenter(
            DetailInfoPresenter<DetailInfoView> presenter) {
        return presenter;
    }
}