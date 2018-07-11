package com.example.mirko.mirssreader;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mirko.mirssreader.Model.ListObject;

public class AddPopUp extends Activity {

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int heigth = dm.heightPixels;

        getWindow().setLayout((int)(width * 0.8), (int)(heigth * 0.8));
        fetchLink();
    }
    private void fetchLink(){
        final EditText linkText = (EditText) findViewById(R.id.link_text);
        final EditText nameText = (EditText) findViewById(R.id.name_text);

        Button button= (Button) findViewById(R.id.add_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //if(URLUtil.isValidUrl(linktext.getText().toString()))
                //{
                ListObject object = new ListObject();
                object.link = linkText.getText().toString();
                object.name = nameText.getText().toString();
                MainActivity.Link_List.add(object);
                finish();
                //}
            }
        });
    }
}
