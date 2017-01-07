package com.sphere.sms;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BackgroundTask extends AppCompatActivity {

    Context ctx = this;
    TextView totalView, CurrentView, totTime;
    int tot, one, totalNum;
    String phNum, msgSend, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background_task);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        String[] str = b.getStringArray("toPass");

//        Intent i = getIntent();
//        str = i.getStringArrayExtra("toPass");
        tot = Integer.parseInt(str[2]);
        one = Integer.parseInt(str[1]);
        phNum = str[0];
        msgSend = str[3];
        totalNum = tot*one;

        CurrentView = (TextView)findViewById(R.id.currentStatus);
        totalView = (TextView)findViewById(R.id.totalNum);
        totTime = (TextView)findViewById(R.id.totalTime);

        totalView.setText(String.valueOf(totalNum));
        totTime.setText(String.valueOf(((one*10)+(tot)*30)/60));

        Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        date = format.format(c.getTime());

        Alert a = new Alert(ctx);
        a.alertMsg();
    }

    public class Alert extends Activity {
        Context context;
        Alert(Context mContext){
            this.context = mContext;
        }
        public void alertMsg(){
            final AlertDialog.Builder msgcheck = new AlertDialog.Builder(ctx);
            msgcheck.setMessage("Starting Phone Number: "+phNum+
                    "   Message: " + msgSend);
            msgcheck.setTitle("CHECKPOST");
            msgcheck.setCancelable(false);
            msgcheck.setPositiveButton("GO ON", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    sms s = new sms();
                    s.execute();
//                    dialog.cancel();
                }
            });
            msgcheck.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intent);
//                    dialog.cancel();
                }
            });
            AlertDialog alertDialog = msgcheck.create();
            alertDialog.show();
        }
    }

    public class  sms extends AsyncTask<Void, Integer,Void>{
        @Override
        protected Void doInBackground(Void... params) {
            dbOperation dbo = new dbOperation(ctx);
            int k = 1;
            long ph = Long.parseLong(phNum);
            String phoneNumber = String.valueOf(ph);
            SQLiteDatabase sdb = dbo.getWritableDatabase();
            dbo.addInformation(sdb, phNum, date, totalNum);
            try{
                for(int i=0; i<tot; i++){
                    for(int j=0; j<one; j++){
                        try {
                            SmsManager.getDefault().sendTextMessage(phoneNumber, null, msgSend, null, null);
//                            Log.v("Phone Nunber", k+" ph "+ phoneNumber+"  "+msgSend);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        ph++;
                        phoneNumber = "+91"+String.valueOf(ph);
                        k++;
                        Thread.sleep(10000);
                        final int finalK = (k-1);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                CurrentView.setText(String.valueOf(finalK));
                            }
                        });
                    }
                    Thread.sleep(30000);
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            final AlertDialog.Builder msgcheck = new AlertDialog.Builder(ctx);
            msgcheck.setMessage("Messages were sent successfully !!!");
            msgcheck.setTitle("DONE");
            msgcheck.setCancelable(false);
            msgcheck.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(ctx, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    ctx.startActivity(intent);
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog = msgcheck.create();
            alertDialog.show();
        }
    }
}