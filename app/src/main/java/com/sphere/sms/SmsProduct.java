package com.sphere.sms;

/**
 * Created by Abhijeet on 11-12-2016.
 */
public class SmsProduct {
    private String startNum, date;
    int qty;

    SmsProduct(String AstartNum, String Adate, int Aqty){
        this.setDate(Adate);
        this.setQty(Aqty);
        this.setStartNum(AstartNum);
    }

    public String getStartNum() {
        return startNum;
    }

    public void setStartNum(String startNum) {
        this.startNum = startNum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
