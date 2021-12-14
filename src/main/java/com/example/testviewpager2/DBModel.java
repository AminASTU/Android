package com.example.testviewpager2;

import android.provider.BaseColumns;

public final class DBModel {
    private DBModel() {
    }

    public static class UserClass implements BaseColumns {
        public static final String TABLE_NAME = "Users";
        public static final String COL_NAME = "Name";
        public static final String COL_COURSE = "Course";
    }
}
