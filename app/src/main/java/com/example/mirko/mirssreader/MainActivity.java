package com.example.mirko.mirssreader;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mirko.mirssreader.Adapter.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    RecyclerView listRecyclerView;

    public static Context baseContext;

    public static String RSS_link = "http://rss.nytimes.com/services/xml/rss/nyt/Science.xml";
    public static List<String> RSS_Name_List = new ArrayList<String>();
    public static List<String> RSS_Link_List = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseContext = getBaseContext();
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("News");
        setSupportActionBar(toolbar);

        listRecyclerView = (RecyclerView)findViewById(R.id.listrecyclerView);
        LinearLayoutManager listLinearLayoutManager  = new LinearLayoutManager(getBaseContext(),LinearLayoutManager.HORIZONTAL,false);
        listRecyclerView.setLayoutManager(listLinearLayoutManager);


        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager  = new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        load(recyclerView, RSS_link, getBaseContext());
    }

    public static void load(RecyclerView recyclerView, String RSS_link, Context context) {
        RssLoader loader = new RssLoader(recyclerView, RSS_link, baseContext);
        loader.loadRSS();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_refresh)
            load(recyclerView, RSS_link, getBaseContext());
            loadList();
        if(item.getItemId() == R.id.menu_add)
            startActivity(new Intent(MainActivity.this, AddPopUp.class ));
        return true;
    }

    private void loadList(){
        ListAdapter adapter = new ListAdapter(recyclerView, baseContext);
        listRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        Log.d("dddddd", "ssss:"+ RSS_Name_List.size());
    }
}
