package com.sphere.sms;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Context ctx = this;
    EditText phoneNum, oneBatch, totalBatch, msg, pass;
    String  a_phone, a_msg, a_oneBatch, a_totalBatch;
    int a_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendBtn(View view){
        phoneNum = (EditText)findViewById(R.id.phoneNum);
        oneBatch = (EditText)findViewById(R.id.oneBatch);
        totalBatch = (EditText)findViewById(R.id.totalBatch);
        msg = (EditText)findViewById(R.id.messageToSend);
        pass = (EditText)findViewById(R.id.password);

        a_phone = phoneNum.getText().toString();
        a_oneBatch = oneBatch.getText().toString();
        a_totalBatch = totalBatch.getText().toString();
        a_msg = msg.getText().toString();
        String str = pass.getText().toString();
        a_pass = Integer.parseInt(str);

        String[] toPass = {a_phone,a_oneBatch, a_totalBatch, a_msg};
        Bundle b = new Bundle();
        b.putStringArray("toPass", toPass);

        if(a_pass == 1234){
            pass.setText("");
            Intent intent = new Intent(ctx,BackgroundTask.class)
                    .putExtras(b);
            startActivity(intent);
        }
        else{
            pass.setText("");
            Toast.makeText(ctx,"Try Again Wrong Passcode", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
//            Intent i = new Intent(getApplication(), SettingsActivity.class);
//            startActivity(i);
            return true;
        }
        else if (id == R.id.viewData) {
            Intent i = new Intent(getApplication(), DisplayDatabase.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
