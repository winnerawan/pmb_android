package net.winnerawan.androidx.data;

import net.winnerawan.androidx.data.db.DbHelper;
import net.winnerawan.androidx.data.network.ApiHelper;
import net.winnerawan.androidx.data.prefs.PreferencesHelper;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

public interface DataManager extends DbHelper, ApiHelper, PreferencesHelper {


}
