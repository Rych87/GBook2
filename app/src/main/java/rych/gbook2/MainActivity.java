package rych.gbook2;


import android.app.DownloadManager;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.SearchView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;

/*import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;*/

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String mHttp="http";
    private ArrayList<Book> mBookList;
    private String searchInput;
    private StringBuilder mFullReq;
    private RecyclerView mRecView;
    private GbAdapter mGbA;

    private TextView mTw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecView=findViewById(R.id.recycler_view);
        mRecView.setLayoutManager(new LinearLayoutManager(this));
        mTw=findViewById(R.id.textView4);

        //getSupportLoaderManager().initLoader(0,null,this).forceLoad();
        //mBookList=null;


        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //fab.setOnClickListener(new View.OnClickListener() {
           // @Override
           // public void onClick(View view) {
            //    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            //            .setAction("Action", null).show();
           // }
      //  });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem Si=menu.findItem(R.id.app_bar_search);
        SearchView sw=(SearchView)Si.getActionView();
        //final TextView tw=findViewById(R.id.tw);
        //final TextView tw2=findViewById(R.id.textView);
        sw.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchInput=s;
                mFullReq=new StringBuilder("https://www.googleapis.com/books/v1/volumes?q=");
                mFullReq.append(s);
                mFullReq.append("&maxResults=40&projection=lite&filter=free-ebooks&key=AIzaSyAllD6YBKY-z6_hb6Z1zL5uTCMDzy7byKM");
                RequestQueue rq= Volley.newRequestQueue(getThis());
                StringRequest sr=new StringRequest(Request.Method.GET, mFullReq.toString(), new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //mTw.setText(response);
                        mGbA=new GbAdapter(BookLoader.load(response),getThis());
                        mRecView.setAdapter(mGbA);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        PrintStream ps = new PrintStream(baos);
                        error.printStackTrace(ps);
                        ps.close();
                        mTw.setText(baos.toString());
                    }
                });
                rq.add(sr);
                //getSupportLoaderManager().restartLoader(0,null,MainActivity.this).forceLoad();
                //tw.setText(mBookList.get(0).mTitle);
                //tw2.setText(mBookList.get(0).mAuth);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
            
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        /*int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }

    /*@Override
    public Loader<ArrayList<Book>> onCreateLoader(int i, Bundle bundle) {
        BookLoader bl=new BookLoader(this,searchInput);
        //bl.getReqest(searchInput);
        return bl;
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Book>> loader, ArrayList<Book> s) {
       mGbA=new GbAdapter(s,this);
       mRecView.setAdapter(mGbA);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Book>> loader) {

    }*/

    private AppCompatActivity getThis(){
        return this;
    }
}
