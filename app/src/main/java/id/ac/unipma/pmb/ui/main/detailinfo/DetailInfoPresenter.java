package id.ac.unipma.pmb.ui.main.detailinfo;

import id.ac.unipma.pmb.data.DataManager;
import id.ac.unipma.pmb.ui.base.BasePresenter;
import id.ac.unipma.pmb.utils.rx.SchedulerProvider;import com.androidnetworking.error.ANError;
import io.reactivex.disposables.CompositeDisposable;

import javax.inject.Inject;

public class DetailInfoPresenter<V extends DetailInfoView> extends BasePresenter<V> implements DetailInfoMvpPresenter<V> {

    @Inject
    public DetailInfoPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getContentCost(String link) {
        getCompositeDisposable().add(getDataManager().getCostInfo(link)
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .subscribe(content -> {
                    if (!isViewAttached()) return;
                    if (content==null) {
                        return;
                    }
                    getMvpView().showContentCost(content);
                }, throwable -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    getMvpView().hideLoading();


                    if (throwable instanceof ANError) {

                        ANError anError = (ANError) throwable;
                        handleApiError(anError);
                    }
                }));
    }

    @Override
    public void getContentSchedule(String link) {
        getCompositeDisposable().add(getDataManager().getScheduleInfo(link)
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .subscribe(content -> {
                    if (!isViewAttached()) return;
                    if (content==null) {
                        return;
                    }
                    getMvpView().showContentSchedule(content);
                }, throwable -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    getMvpView().hideLoading();


                    if (throwable instanceof ANError) {

                        ANError anError = (ANError) throwable;
                        handleApiError(anError);
                    }
                }));
    }

    @Override
    public void getContentTrack(String link) {
        getCompositeDisposable().add(getDataManager().getTrackInfo(link)
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .subscribe(content -> {
                    if (!isViewAttached()) return;
                    if (content==null) {
                        return;
                    }
                    getMvpView().showContentTrack(content);
                }, throwable -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    getMvpView().hideLoading();


                    if (throwable instanceof ANError) {

                        ANError anError = (ANError) throwable;
                        handleApiError(anError);
                    }
                }));
    }

    @Override
    public void getContentStudy(String link) {
        getCompositeDisposable().add(getDataManager().getStudyInfo(link)
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .subscribe(content -> {
                    if (!isViewAttached()) return;
                    if (content==null) {
                        return;
                    }
                    getMvpView().showContentStudy(content);
                }, throwable -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    getMvpView().hideLoading();


                    if (throwable instanceof ANError) {

                        ANError anError = (ANError) throwable;
                        handleApiError(anError);
                    }
                }));
    }
}
