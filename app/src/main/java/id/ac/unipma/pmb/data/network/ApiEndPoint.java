package id.ac.unipma.pmb.data.network;

import id.ac.unipma.pmb.BuildConfig;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

public class ApiEndPoint {

    public static final String API_URL = "https://pmb." + BuildConfig.BASE_URL + "/api";
    public static final String ENDPOINT_ANNOUNCEMENT = API_URL + "/news";
    public static final String ENDPOINT_SEARCH = "";
    public static final String ENDPOINT_PRESTATIONS = "https://gist.githubusercontent.com/winnerawan/6d3caefedfead7cba932f581d2438b2a/raw/f11c3d7c0667d2f087d337ff4f6554cf9b0b8e58/pres.json";
    public static final String ENDPOINT_INFOS = API_URL + "/infos";
    public static final String ENDPOINT_INFO_TRACK = API_URL + "/infos/track";
    public static final String ENDPOINT_INFO_COST = API_URL + "/infos/cost";
    public static final String ENDPOINT_INFO_SCHEDULE = API_URL + "/infos/schedule";
    public static final String ENDPOINT_INFO_STUDY = API_URL + "/infos/study";
}