package net.winnerawan.androidx.data.network;

import io.reactivex.Single;
import net.winnerawan.androidx.data.network.model.Announcement;
import net.winnerawan.androidx.data.network.model.Info;
import net.winnerawan.androidx.data.network.model.Prestation;
import net.winnerawan.androidx.data.network.model.Selection;

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
}
