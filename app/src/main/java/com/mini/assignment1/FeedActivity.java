package com.mini.assignment1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FeedActivity extends AppCompatActivity {


    int j=0 ,k=10,l=0;
    private String url;
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;

    private Button button;
    private Button back;

    private List<Item> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        recyclerView= findViewById(R.id.recyclerview);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        requestQueue=VolleySingleton.getmInstance(this).getRequestQueue();

        button=findViewById(R.id.refresh);
        back=findViewById(R.id.back2);

        mList=new ArrayList<>();

        String url="https://pixabay.com/api/?key=38075518-764e41914bbb597fcf041adbe&q=animal&image_type=photo&pretty=true";
        fetchData(j,k,url);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FeedActivity.this,HomeActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Home Page", Toast.LENGTH_SHORT).show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    mList.clear();
                    PostAdapter adapter1 = new PostAdapter(FeedActivity.this, mList);
                    recyclerView.setAdapter(adapter1);
                    adapter1.notifyDataSetChanged();
                    switch (l) {
                        case 1:
                            fetchData(10, 20, url);
                            break;

                        case 2:
                            String  url1="https://pixabay.com/api/?key=38075518-764e41914bbb597fcf041adbe&q=fruits&image_type=photo&pretty=true";
                            fetchData(10,20,url1);
                            break;

                        case 3:
                            String  url2="https://pixabay.com/api/?key=38075518-764e41914bbb597fcf041adbe&q=fruits&image_type=photo&pretty=true";
                            fetchData(0,10,url2);
                            break;

                        case 4:
                            String  url3="https://pixabay.com/api/?key=38075518-764e41914bbb597fcf041adbe&q=food&image_type=photo&pretty=true";
                            fetchData(10,20,url3);
                            break;
                        case 5:
                            String  url4="https://pixabay.com/api/?key=38075518-764e41914bbb597fcf041adbe&q=food&image_type=photo&pretty=true";
                            fetchData(0,10,url4);
                            break;
                        case 6:
                            String  url5="https://pixabay.com/api/?key=38075518-764e41914bbb597fcf041adbe&q=quotes&image_type=photo&pretty=true";
                            fetchData(10,20,url5);
                            break;
                        case 7:
                            String  url6="https://pixabay.com/api/?key=38075518-764e41914bbb597fcf041adbe&q=quotes&image_type=photo&pretty=true";
                            fetchData(0,10,url6);
                            break;

                        case 8:
                            String  url7="https://pixabay.com/api/?key=38075518-764e41914bbb597fcf041adbe&q=butterfly%20garden&image_type=photo&pretty=true";
                            fetchData(0,10,url7);
                            break;

                        case 9:
                            String  url8="https://pixabay.com/api/?key=38075518-764e41914bbb597fcf041adbe&q=butterfly%20garden&image_type=photo&pretty=true";
                            fetchData(10,20,url8);
                            break;


                        default :
                            Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_LONG).show();


                    }



            }
        });




    }

    private void fetchData(int j,int k,String url1){

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url1, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    Toast.makeText(getApplicationContext(), "Feed Refresh", Toast.LENGTH_SHORT).show();
                        JSONArray jsonArray = response.getJSONArray("hits");
                        for (int i=j ; i < k; i++) {

                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String imageUrl = jsonObject.getString("webformatURL");
                            int likes = jsonObject.getInt("likes");
                            String tags = jsonObject.getString("tags");
                            Item post = new Item(imageUrl, tags, likes);
                            mList.add(post);


                        }

                        l++;
                    PostAdapter adapter=new PostAdapter(FeedActivity.this,mList);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(FeedActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequest);

    }
}