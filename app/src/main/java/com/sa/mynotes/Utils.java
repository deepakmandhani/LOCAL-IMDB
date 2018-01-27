package com.sa.mynotes;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by deepak on 24/01/18.
 */

public class Utils {

    public static String getMovieImdbLinkUrl(String movieCode) {
        return "http://www.imdb.com/title/" + movieCode + "/?ref_=adv_li_tt";
    }
}
