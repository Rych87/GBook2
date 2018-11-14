package rych.gbook2;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class DownLoader extends AsyncTask<String,Void,String> {

    private String LOG = "Test ";


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String[] a_url) {

        Log.d(LOG,"Start Asynctask");

        URL url = null;
        try {
            url = new URL(a_url[0]);
            Log.d(LOG,"new URL");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection con= null;
        try {
            con = (HttpURLConnection)url.openConnection();
            Log.d(LOG,"new connection");
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream is=con.getErrorStream();
        Log.d(LOG,"new errorstream");
        StringWriter writer = new StringWriter();
        Log.d(LOG,"new writer");
        try {
            IOUtils.copy(is, writer, Charset.defaultCharset());
            Log.d(LOG,"copy stream to writer");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
} 
