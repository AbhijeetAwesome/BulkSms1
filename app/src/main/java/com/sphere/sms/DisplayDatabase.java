package com.sphere.sms;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class DisplayDatabase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_database);

        backgroundTask bt = new backgroundTask(this);
        bt.execute();
    }

    public class backgroundTask extends AsyncTask<String, SmsProduct, String> {
        Context ctx;
        SmsProductAdapter proAdp;
        Activity activity;
        ListView lv;
        backgroundTask(Context c){
            this.ctx = c;
            activity =(Activity)ctx;
        }

        @Override
        protected String doInBackground(String... params) {
            dbOperation dbo = new dbOperation(ctx);
            lv = (ListView)activity.findViewById(R.id.display_listview);
            SQLiteDatabase sdb = dbo.getReadableDatabase();
            Cursor curosr = dbo.getInfo(sdb);
            proAdp = new SmsProductAdapter(ctx, R.layout.display_sms_row);
            String startNum, Date;
            int qty;
            curosr.moveToLast();
            while(curosr.moveToPrevious()){
                startNum = curosr.getString(curosr.getColumnIndex(SmsProductContract.SmsProductEntry.STARTNUM));
                Date = curosr.getString(curosr.getColumnIndex(SmsProductContract.SmsProductEntry.DATE));
                qty = curosr.getInt(curosr.getColumnIndex(SmsProductContract.SmsProductEntry.TOTALNUM));
                SmsProduct product = new SmsProduct(startNum, Date, qty);
                publishProgress(product);
            }
            return "get_info";
        }

        @Override
        protected void onProgressUpdate(SmsProduct... values) {
            proAdp.add(values[0]);
        }

        @Override
        protected void onPostExecute(String aVoid) {
            lv.setAdapter(proAdp);
        }
    }
}
