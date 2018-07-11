package com.example.mirko.mirssreader;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mirko.mirssreader.Adapter.ListAdapter;
import com.example.mirko.mirssreader.Model.ListObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    static RecyclerView recyclerView;
    static RecyclerView listRecyclerView;

    public static Context baseContext;

    public static List<ListObject> Link_List = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData();
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
        loadList();
    }

    public static void load(RecyclerView recyclerView, String RSS_link, Context context) {
        RssLoader loader = new RssLoader(recyclerView, RSS_link, baseContext);
        loader.loadRSS();
    }
    public void saveData(){
        SharedPreferences sharedPrefs = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(Link_List);
        editor.putString("Link List", json);
        editor.apply();
    }

    public void loadData(){
        SharedPreferences sharedPrefs = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPrefs.getString("Link List", null);
        Type type = new TypeToken<ArrayList<ListObject>>(){}.getType();
        Link_List = gson.fromJson(json,type);

        if(Link_List == null){
            Link_List = new ArrayList<>();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_refresh)
            loadList();
        if(item.getItemId() == R.id.menu_add)
            startActivity(new Intent(MainActivity.this, AddPopUp.class ));
        return true;
    }
    public static void loadList(){
        ListAdapter adapter = new ListAdapter(recyclerView, baseContext);
        listRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
