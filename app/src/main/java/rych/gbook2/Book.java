package rych.gbook2;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Book implements Serializable {
    public String mTitle;
    public String mAuth;
    public String mDesc;
    public String mPicAdress;
    public String mPdf;

    public Book(String tit, String aut, String i){
        mTitle=tit;
        mAuth=aut;
    }

    public Book(){
        mTitle="";
        mAuth="";
        mDesc="";
        mPicAdress="";
        mPdf="";
    }
}
