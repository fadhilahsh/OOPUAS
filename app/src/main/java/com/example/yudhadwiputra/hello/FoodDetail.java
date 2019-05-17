package com.example.yudhadwiputra.hello;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

public class FoodDetail extends AppCompatActivity {

    TextView food_name, food_price, food_description;
    ImageView food_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    floatingActionButton btnCart;
    ElegantNumberButton numberButton;

    //String foodId="";

    FirebaseDatabase database;
    DatabaseReference foods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        database = FirebaseDatabase.getInstance();
        foods = database.getReference("Foods");

        numberButton = (ElegantNumberButton) findViewById(R.id.number_botton);
        btnCart = (FloatingActionButton) findViewById(R.id.btnCart);

        food_description = (TextView) findViewById(R.id.food_description);
        food_name = (TextView) findViewById(R.id.food_name);
        food_price = (TextView) findViewById(R.id.food_price);
        food_image = (ImageView) findViewById(R.id.img_food);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);

        if (getIntent() !=null)
            FoodId = getIntent().getStringExtra("FoodId");
        if(!FoodId.isEmpty())
        {
            getDetailFood(FoodId);
        }

        private void getDetailFood(String FoodId)
        {
            foods.child(foodId).addValueEventListener(new ValueEventListener()
            {
                @Override
                public void onDataChange (DataSnapshot dataSnapshot)
                {
                    food food = dataSnapshot.getValue(Food.class);
                    Picasso.with(getBaseContext()).load(food.getImage()).into(food_image);
                    collapsingToolbarLayout.setTitle(food.getName());
                    food_price.setText(food.getPrice());
                    food_name.setText(food.getName());
                    food_description.setText(food.getDescription());
                }
                @Override
                public void onCancelled(DatabaseError databaseError)
                {

                }
            });
        }


    }

}
