package com.example.mukhter.bakers.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by MUKHTER on 10/07/2017.
 */

public class BakingContract {




    public static final String AUTHORITY = "com.example.mukhter.bakingrecipe";

    public static final Uri BASE_URL = Uri.parse("content://" + AUTHORITY);
    public static final String PATH = "recipe";

    private BakingContract(){}

    public static final class BakingEntry implements BaseColumns{

        public static final Uri CONTENT_URI =
                BASE_URL.buildUpon().appendPath(PATH).build();

        public static final String TABLE_NAME = "baking";
        public static final String RECIPE = "recipe";
        public static final String INGREDIENTS = "ingredients";
        public static final String STEPS = "steps";

    }
}
