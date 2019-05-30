package com.example.camilan_oop;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MenuCamilanActivity extends AppCompatActivity {
    private static final String JSON_URL = "http://192.168.43.188/proyek_oop/listSemuaFnb5.php";

    TextView mealTotalText;
    ArrayList<MenuItem> orders;
    ListView listView;
    private List<MenuItem> playerItemList;
    Button btn_beli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_camilan);

        listView =  findViewById(R.id.listView);
        playerItemList = new ArrayList<>();

        btn_beli = (Button) findViewById(R.id.btn_beli);

        btn_beli.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // update login session ke FALSE dan mengosongkan nilai id dan username
                Intent intent = new Intent(MenuCamilanActivity.this, Transaction.class);
                Bundle bundle = new Bundle();
                bundle.putString("Total", "Rp " + String.valueOf(calculateMealTotal()));
                intent.putExtras(bundle);
                finish();
                startActivity(intent);

            }
        });

        loadPlayer();
        orders = getListItemData();
        mealTotalText = (TextView)findViewById(R.id.meal_total);

    }
    public int calculateMealTotal(){
        int mealTotal = 0;
        for(MenuItem order : playerItemList){
            mealTotal += Integer.valueOf(order.getHarga()) * order.getmQuantity();
        }
        return mealTotal;
    }

    public void setMealTotal(){
        mealTotalText.setText("Rp "+ calculateMealTotal());
    }

    DataSetObserver observer = new DataSetObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            setMealTotal();
        }
    };

    private void loadPlayer() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray playerArray = obj.getJSONArray("array");
                            if(playerArray.length() == 0){
                                Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                            }



                            for (int i = 0; i < playerArray.length(); i++) {

                                JSONObject playerObject = playerArray.getJSONObject(i);


                                MenuItem playerItem = new MenuItem(playerObject.getString("idFnb"),
                                        playerObject.getString("menu"),
                                        playerObject.getString("kategori"),
                                        playerObject.getString("harga"));

                                playerItemList.add(playerItem);
                            }

                            ListView storedOrders = (ListView)findViewById(R.id.listView);
                            ListViewAdapter adapter = new ListViewAdapter(playerItemList, getApplicationContext());


                            storedOrders.setAdapter(adapter);
                            adapter.registerDataSetObserver(observer);
                            listView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private ArrayList<MenuItem> getListItemData(){
        ArrayList<MenuItem> listViewItems = new ArrayList<MenuItem>();
        return listViewItems;
    }


}