package com.sphere.sms;

/**
 * Created by Abhijeet on 11-12-2016.
 */
public final class SmsProductContract {
    SmsProductContract(){
    }

    public static abstract class SmsProductEntry{
        public static final String DATE = "date";
        public static final String STARTNUM = "startNum";
        public static final String TOTALNUM = "totalNum";
        public static final String TABLE_NAME = "Sms_table";
    }
}
