package rych.gbook2;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v4.content.AsyncTaskLoader;
import android.util.JsonReader;
import android.util.Log;

//import com.google.common.io.BaseEncoding;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class BookLoader {


    public static ArrayList<Book> load(String string) {
        ArrayList<Book> al=new ArrayList<Book>();
        JsonReader reader=new JsonReader(new StringReader(string));
        String name;
        StringBuilder auth=new StringBuilder();
        Book tmpBook;
        try {
            reader.beginObject();
            if(reader.hasNext()) {
                if(BLreader.findName(reader, "items")) {
                    reader.beginArray();
                    while (reader.hasNext()) {
                        reader.beginObject();
                        BLreader.findName(reader, "volumeInfo");
                        reader.beginObject();
                        tmpBook = new Book();
                        while (reader.hasNext()) {
                                name = reader.nextName();
                                if (name.equals("title"))
                                    tmpBook.mTitle = reader.nextString();
                                else if (name.equals("authors")) {
                                    reader.beginArray();
                                    for (auth.append(reader.nextString()); reader.hasNext(); auth.append(reader.nextString())) {
                                        //auth.append(reader.nextString());
                                        auth.append(", ");
                                    }

                                    reader.endArray();
                                    tmpBook.mAuth = auth.toString();
                                    auth.setLength(0);
                                } else if (name.equals("description"))
                                    tmpBook.mDesc = reader.nextString();
                                else if (name.equals("imageLinks")) {
                                    reader.beginObject();
                                    while (reader.hasNext()) {
                                        name = reader.nextName();
                                        if (name.equals("thumbnail"))
                                            tmpBook.mPicAdress = reader.nextString();
                                        else
                                            reader.skipValue();
                                    }
                                    reader.endObject();
                                } else reader.skipValue();
                            }

                            reader.endObject();

                            if(BLreader.findName(reader, "accessInfo")){
                                reader.beginObject();
                                while (reader.hasNext()) {
                                    name = reader.nextName();
                                    if (name.equals("pdf")){
                                        reader.beginObject();
                                        while (reader.hasNext()) {
                                            name = reader.nextName();
                                            if (name.equals("downloadLink")) {
                                                tmpBook.mPdf = reader.nextString();
                                                //tmpBook.mPdf+="&key=AIzaSyAllD6YBKY-z6_hb6Z1zL5uTCMDzy7byKM";
                                            }
                                            else reader.skipValue();
                                        }
                                        reader.endObject();
                                    }
                                    else reader.skipValue();
                                }
                                reader.endObject();
                            }

                            while (reader.hasNext()) reader.skipValue();
                            reader.endObject();
                            al.add(tmpBook);
                        }
                        reader.endArray();
                    }
                }
            reader.endObject();
            reader.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
            return al;
        }


    /*public void getReqest(String str){
        mReq=str;
    }

    //Auth

    private String getSHA1(String packageName){
        try {
            Signature[] signatures = mCtx.getPackageManager().getPackageInfo(packageName, PackageManager.GET_SIGNATURES).signatures;
            for (Signature signature: signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA-1");
                md.update(signature.toByteArray());
                return BaseEncoding.base16().encode(md.digest());
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }*/
}
