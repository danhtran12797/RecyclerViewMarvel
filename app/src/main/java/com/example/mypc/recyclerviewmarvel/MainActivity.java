package com.example.mypc.recyclerviewmarvel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mypc.recyclerviewmarvel.Marvel;
import com.example.mypc.recyclerviewmarvel.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    private final String URL_DATA="https://simplifiedcoding.net/demos/marvel/";
    private RecyclerView recyclerView;
    private MarvelAdapter adapter;
    private ArrayList<Marvel> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        loadData(URL_DATA);

    }

    private void loadData(String url_data) {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        RequestQueue requestQueue=Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url_data, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONArray array=new JSONArray(response);
                    for(int i=0;i<array.length();i++){
                        JSONObject object=array.getJSONObject(i);
                        listItems.add(new Marvel(object.getString("name"),object.getString("bio"),object.getString("imageurl")));
                    }
                    int curSize=adapter.getItemCount();
                    adapter.notifyItemRangeInserted(curSize,listItems.size());
                    //recyclerView.scrollToPosition(adapter.getItemCount()-1);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);
    }

    private void initView() {
        recyclerView=findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecorationvider=new DividerItemDecoration(this,linearLayoutManager.getOrientation());
        //Drawable drawable=ContextCompat.getDrawable(getApplicationContext(),R.drawable.custom_divider);
        //dividerItemDecorationvider.setDrawable(drawable);
        recyclerView.addItemDecoration(dividerItemDecorationvider);

        listItems=new ArrayList<>();

        adapter=new MarvelAdapter(listItems,getApplicationContext());
        recyclerView.setAdapter(adapter);

//        adapter.setOnItemClickListener(new MarvelAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                Marvel marvel=listItems.get(position);
//
//                Intent intent=new Intent(MainActivity.this,DetailActivity.class);
//                intent.putExtra("MARVEL",marvel);
//                startActivity(intent);
//            }
//        });


    }
}
