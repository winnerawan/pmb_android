package id.ac.unipma.pmb.data.network;


import id.ac.unipma.pmb.data.network.model.*;
import com.rx2androidnetworking.Rx2AndroidNetworking;
import io.reactivex.Single;
import id.ac.unipma.pmb.data.network.model.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;


/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

@Singleton
public class AppApiHelper implements ApiHelper {

    @Inject
    public AppApiHelper() {

    }

    @Override
    public Single<List<Announcement>> getAnnouncements() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_ANNOUNCEMENT)
                .build()
                .getObjectListSingle(Announcement.class);
    }

    @Override
    public Single<List<Prestation>> getPrestations() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_PRESTATIONS)
                .build()
                .getObjectListSingle(Prestation.class);
    }

    @Override
    public Single<List<Selection>> search(String keyword) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_SEARCH)
                .build()
                .getObjectListSingle(Selection.class);
    }

    @Override
    public Single<List<Info>> getInfos() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_INFOS)
                .build()
                .getObjectListSingle(Info.class);
    }

    @Override
    public Single<ContentInfo> getCostInfo(String link) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_INFO_COST)
                .addQueryParameter("link", link)
                .build()
                .getObjectSingle(ContentInfo.class);
    }

    @Override
    public Single<ContentInfo> getTrackInfo(String link) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_INFO_TRACK)
                .addQueryParameter("link", link)
                .build()
                .getObjectSingle(ContentInfo.class);
    }

    @Override
    public Single<ContentInfo> getScheduleInfo(String link) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_INFO_SCHEDULE)
                .addQueryParameter("link", link)
                .build()
                .getObjectSingle(ContentInfo.class);
    }

    @Override
    public Single<ContentInfo> getStudyInfo(String link) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_INFO_STUDY)
                .addQueryParameter("link", link)
                .build()
                .getObjectSingle(ContentInfo.class);
    }
}
