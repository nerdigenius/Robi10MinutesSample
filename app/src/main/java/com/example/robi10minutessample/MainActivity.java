package com.example.robi10minutessample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private myAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<SampleItem> exampleList= new ArrayList<>();


        recyclerView=findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        mAdapter = new myAdapter(parseJson());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);



    }

    public ArrayList<SampleItem> parseJson(){
        ArrayList<SampleItem> sampleList= new ArrayList<>();
        String json;
        SampleItem sampleItem= null;
     try {
         InputStream is = getAssets().open("jsonArray.json");
         int size =is.available();
         byte[] buffer = new byte[size];
         is.read(buffer);
         is.close();
         json= new String(buffer,"UTF-8");
         JSONArray jsonArray = new JSONArray(json);

         for (int i=0;i<jsonArray.length();i++){
             JSONObject jsonObject= jsonArray.getJSONObject(i);
             if(jsonObject.getString("type").equals("video")){
                  sampleItem = new SampleItem(R.drawable.ic_video,jsonObject.getString("type"),jsonObject.getString("name"),false,null);
                 sampleList.add(sampleItem);
             }

             else if(jsonObject.getString("type").equals("document")){
                  sampleItem = new SampleItem(R.drawable.ic_document,jsonObject.getString("type"),jsonObject.getString("name"),false,null,jsonObject.getString("author"));
                 sampleList.add(sampleItem);
             }

             else if(jsonObject.getString("type").equals("audio")){
                 sampleItem = new SampleItem(R.drawable.ic_audio,jsonObject.getString("type"),jsonObject.getString("name"),false,null);
                 sampleList.add(sampleItem);
             }

             else if(jsonObject.getString("type").equals("chapter")){
                 ArrayList<ChildItems> childItemsArrayList= new ArrayList<>();
                 JSONArray jsonArray1 =jsonObject.getJSONArray("childItems");
                 for(int j=0;j<jsonArray1.length();j++){
                     JSONObject jsonObject1=jsonArray1.getJSONObject(j);
                     childItemsArrayList.add(new ChildItems(jsonObject1.getString("name"),jsonObject1.getString("type")));

                 }


                 sampleItem = new SampleItem(R.drawable.ic_chapter,jsonObject.getString("type"),jsonObject.getString("name"),true,childItemsArrayList);
                 sampleList.add(sampleItem);
             }

         }

     }
     catch (IOException | JSONException e) {
         e.printStackTrace();
     }

     return sampleList;
    }
}