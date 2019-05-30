package com.example.camilan_oop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class Transaction extends AppCompatActivity {
    Button btn_checkout;
    Button btn_cancel;
    TextView totalBelanja;
    EditText alamatPaket;
    Spinner spinner1;
    private RadioGroup radioBayar;
    private RadioButton radioBayarButton;
    final Bundle bundle2 = new Bundle();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        btn_checkout = (Button) findViewById(R.id.btn_checkout);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        totalBelanja = (TextView)findViewById(R.id.totalBelanja);
        radioBayar = (RadioGroup) findViewById(R.id.radioBayar);
        alamatPaket = (EditText) findViewById(R.id.alamatPaket);



        //get the spinner from the xml.
        //Spinner dropdown = findViewById(R.id.spinner1);
        //create a list of items for the spinner.
        //String[] items = new String[]{"JNE", "TIKI", "Go-Send"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        //ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        //dropdown.setAdapter(adapter);
        //String text = spinner1.getSelectedItem().toString();

        //String Text = spinner1.getSelectedItem().toString();
        //Get the bundle
        Bundle bundle = getIntent().getExtras();

        //Extract the data…
        String Total = bundle.getString("Total");
        totalBelanja.setText(Total);

        initializedSpinner();

        btn_checkout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // get selected radio button from radioGroup
                int selectedId = radioBayar.getCheckedRadioButtonId();



                // find the radiobutton by returned id
                radioBayarButton = (RadioButton) findViewById(selectedId);

                //Toast.makeText(Transaction.this, radioBayarButton.getText() +""+ totalBelanja.getText()+""+alamatPaket.getText(), Toast.LENGTH_SHORT).show();
                bundle2.putString("Total", String.valueOf(totalBelanja.getText()));
                bundle2.putString("Pembayaran", String.valueOf(radioBayarButton.getText()));
                bundle2.putString("Alamat", String.valueOf(alamatPaket.getText()));

                Intent myIntent = new Intent(Transaction.this,Invoice.class);
                myIntent.putExtras(bundle2);
                startActivity(myIntent);
            }


        });

    }

    private void initializedSpinner(){
        // Spinner element
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);

        // Spinner Drop down elements
        List<String> pengiriman = new ArrayList<String>();
        pengiriman.add("JNE");
        pengiriman.add("TIKI");
        pengiriman.add("Go-Send");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pengiriman);

        //spinner.setAdapter(dataAdapter);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner1.setAdapter(dataAdapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String searchItem = parent.getItemAtPosition(position).toString();
                bundle2.putString("Pengiriman", searchItem);
                Toast.makeText(parent.getContext(),searchItem + " Selected" , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Transaction.this, MainActivity.class));
            }
        });
    }

}