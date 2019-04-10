package net.winnerawan.androidx.data.prefs;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

public interface PreferencesHelper {

    boolean isLoggedIn();
    void setLoggedIn(boolean loggedIn);

    boolean isFirstTime();
    void setFirstTime(boolean isFirstTime);


}
