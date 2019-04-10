package net.winnerawan.androidx.data.db;

import net.winnerawan.androidx.data.db.model.DaoMaster;
import net.winnerawan.androidx.data.db.model.DaoSession;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

@Singleton
public class AppDbHelper implements DbHelper {

    private final DaoSession mDaoSession;

    @Inject
    public AppDbHelper(DbOpenHelper dbOpenHelper) {
        mDaoSession = new DaoMaster(dbOpenHelper.getWritableDb()).newSession();
    }


}
