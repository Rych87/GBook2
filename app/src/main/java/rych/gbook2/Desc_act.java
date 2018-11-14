package rych.gbook2;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

public class Desc_act extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc_act);
        LinearLayout ll=findViewById(R.id.content_act2);
        Intent intent=getIntent();
        //Bundle bundle=intent.getBundleExtra("rych.gbook.Launch");
        //Book mBook=bundle.getSerializable("Book");
        final Book mBook=(Book)intent.getSerializableExtra("Book");
        final TextView tw=new TextView(this);
        tw.setText(mBook.mPdf);
        ll.addView(tw);
        Button B2=(Button)findViewById(R.id.button2);
        final DownloadManager dm = (DownloadManager)this.getSystemService(DOWNLOAD_SERVICE);
        final Context ctx=this;
        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*String request=mBook.mPdf+"&key=AIzaSyAllD6YBKY-z6_hb6Z1zL5uTCMDzy7byKM";

                RequestQueue rq= Volley.newRequestQueue(getThis());
                StringRequest sr=new StringRequest(Request.Method.GET, mBook.mPdf, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        tw.setText(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        PrintStream ps = new PrintStream(baos);
                        error.printStackTrace(ps);
                        ps.close();
                        tw.setText(baos.toString());
                    }
                });

                rq.add(sr);*/

                //Uri uri=Uri.parse("https://books.sonatype.com/mvnref-book/pdf/mvnref-pdf.pdf");

                /*Uri uri=Uri.parse(request);
                DownloadManager.Request req=new DownloadManager.Request(uri);
                req.setDestinationInExternalPublicDir("/ass", "ass.pdf");
                dm.enqueue(req);
                req.setDestinationInExternalPublicDir("/ass", "ass.pdf");*/

                //new DownLoader().execute(mBook.mPdf);
                //new DownLoader().execute("http://maven.apache.org/maven-1.x/maven.pdf");
                Log.d("Test", "button push");
                DownLoader dl=new DownLoader();
                Log.d("Test", "new dl");
                dl.execute(mBook.mPdf);
                Log.d("Test", "dl exec");
                try {
                    String str=dl.get();
                    tw.setText(str);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                //con.setRequestMethod("GET");
                //con.setDoInput(true);
            }
        });
    }

    private AppCompatActivity getThis(){
        return this;
    }
}

