package com.example.camilan_oop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<MenuItem> {

    TextView currentFoodName,
            currentCost,
            quantityText,
            addMeal,
            subtractMeal,
            removeMeal;

    private List<MenuItem> playerItemList;

    private Context context;

    public ListViewAdapter(List<MenuItem> playerItemList, Context context) {
        super(context, R.layout.list_item, playerItemList);
        this.playerItemList = playerItemList;
        this.context = context;
    }



    @Override
    public View getView(final int kategori, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);

        View listViewItem = inflater.inflate(R.layout.list_item, null, true);

        TextView textViewIdFnb = listViewItem.findViewById(R.id.textViewIdFnb);
        TextView textViewMenu = listViewItem.findViewById(R.id.textViewMenu);
        TextView textViewKategori = listViewItem.findViewById(R.id.textViewKategori);
        TextView textViewHarga = listViewItem.findViewById(R.id.textViewHarga);
        TextView addMeal = listViewItem.findViewById(R.id.plus_meal);
        TextView subtractMeal = listViewItem.findViewById(R.id.minus_meal);
        currentCost = (TextView)listViewItem.findViewById(R.id.selected_food_amount);
        quantityText = (TextView)listViewItem.findViewById(R.id.quantity);
        //removeMeal = (TextView)listViewItem.findViewById(R.id.delete_item);


        final MenuItem playerItem = playerItemList.get(kategori);

        textViewIdFnb.setText(playerItem.getIdFnb());
        textViewMenu.setText(playerItem.getMenu());
        textViewKategori.setText(playerItem.getKategori());
        textViewHarga.setText(playerItem.getHarga());

        quantityText.setText("x "+ playerItem.getmQuantity());

        //OnClick listeners for all the buttons on the ListView Item
        addMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerItem.addToQuantity();
                quantityText.setText("x "+ playerItem.getmQuantity());
                currentCost.setText("GH"+"\u20B5"+" "+ (Integer.valueOf(playerItem.getHarga()) * playerItem.getmQuantity()));
                notifyDataSetChanged();
            }
        });

        subtractMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerItem.removeFromQuantity();
                quantityText.setText("x "+playerItem.getmQuantity());
                currentCost.setText("GH"+"\u20B5"+" "+ (Integer.valueOf(playerItem.getHarga()) * playerItem.getmQuantity()));
                notifyDataSetChanged();
            }
        });

//        removeMeal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                playerItemList.remove(kategori);
//                notifyDataSetChanged();
//            }
//        });


        return listViewItem;
    }
}