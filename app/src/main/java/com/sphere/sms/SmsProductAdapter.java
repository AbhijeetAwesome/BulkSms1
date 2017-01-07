package com.sphere.sms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abhijeet on 11-12-2016.
 */
public class SmsProductAdapter extends ArrayAdapter {
    List list = new ArrayList();
    public SmsProductAdapter(Context ctx, int resources){
        super(ctx, resources);
    }

    @Override
    public void add(Object object) {
        list.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        productHolder proHol;
        if(row== null){
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.display_sms_row, parent, false);
            proHol = new productHolder();
            proHol.tx_startnum = (TextView)row.findViewById(R.id.startNumber);
            proHol.tx_date = (TextView) row.findViewById(R.id.smsQty);
            proHol.tx_qty = (TextView)row.findViewById(R.id.sendDate);
            row.setTag(proHol);
        }
        else{
            proHol = (productHolder)row.getTag();
        }
        SmsProduct product = (SmsProduct)getItem(position);
        proHol.tx_startnum.setText(product.getStartNum().toString());
        proHol.tx_qty.setText(Integer.toString(product.getQty()));
        proHol.tx_date.setText(product.getDate().toString());
        return row;
    }
    static class productHolder{
        TextView tx_startnum, tx_date, tx_qty;
    }
}
