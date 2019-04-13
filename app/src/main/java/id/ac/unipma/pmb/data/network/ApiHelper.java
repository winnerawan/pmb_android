package id.ac.unipma.pmb.data.network;

import id.ac.unipma.pmb.data.network.model.*;
import io.reactivex.Single;
import id.ac.unipma.pmb.data.network.model.*;

import java.util.List;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

public interface ApiHelper {

    Single<List<Announcement>> getAnnouncements();
    Single<List<Prestation>> getPrestations();
    Single<List<Selection>> search(String keyword);
    Single<List<Info>> getInfos();
    Single<ContentInfo> getCostInfo(String link);
    Single<ContentInfo> getTrackInfo(String link);
    Single<ContentInfo> getScheduleInfo(String link);
    Single<ContentInfo> getStudyInfo(String link);

}
