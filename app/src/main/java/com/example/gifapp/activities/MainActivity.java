package com.example.gifapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.gifapp.classes.DataAdapter;
import com.example.gifapp.classes.GetData;
import com.example.gifapp.R;
import com.example.gifapp.classes.SingleObj;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements DataAdapter.OnItemClickListener {

    RecyclerView recView;
    ArrayList<GetData> dataModelArrayList = new ArrayList<>();
    DataAdapter dataAdapter;

    String url = "https://api.giphy.com/v1/gifs/trending?api_key=K17ARKLi1vhhCkCSwpFEgygvwm1pQWQB";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recView = findViewById(R.id.recyclerView);
        recView.setLayoutManager(new GridLayoutManager(this, 2));
        recView.setHasFixedSize(true);

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                JSONArray dataArray = response.getJSONArray("data");
                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject obj = dataArray.getJSONObject(i);

                    JSONObject obj1 = obj.getJSONObject("images");
                    JSONObject obj2 = obj1.getJSONObject("downsized_medium");

                    String sourceUrl = obj2.getString("url");
                    dataModelArrayList.add(new GetData(sourceUrl));
                }
                dataAdapter = new DataAdapter(MainActivity.this, dataModelArrayList);
                recView.setAdapter(dataAdapter);
                dataAdapter.setOnItemClickListener(MainActivity.this);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Toast.makeText(MainActivity.this, getString(R.string.errorMsg) + error.getMessage(), Toast.LENGTH_SHORT).show());
        SingleObj.getInstance(this).addToRequestQueue(objectRequest);
    }

    @Override
    public void onItemClick(int pos) {
        Intent fView = new Intent(this, SingleGifActivity.class);
        GetData clickedItem = dataModelArrayList.get(pos);
        fView.putExtra("imageUrl", clickedItem.getImageUrl());
        startActivity(fView);
    }
}