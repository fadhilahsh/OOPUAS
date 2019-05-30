package com.example.camilan_oop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Invoice extends AppCompatActivity {
    Button btn_next;

    TextView pembayaranInvoice,pengirimanPaket,hargaProduk,tanggal, alamatPaket, totalPembayaran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy");
        String formattedDate = df.format(c);

        pembayaranInvoice = (TextView)findViewById(R.id.pembayaranInvoice);
        pengirimanPaket = (TextView)findViewById(R.id.pengirimanPaket);
        hargaProduk = (TextView)findViewById(R.id.hargaProduk);
        tanggal = (TextView)findViewById(R.id.tanggal);
        alamatPaket = (TextView) findViewById(R.id.alamatPaket);
        btn_next = (Button) findViewById(R.id.btn_next);



        Bundle bundle2 = getIntent().getExtras();
        String Pembayaran = bundle2.getString("Pembayaran");
        String Pengiriman = bundle2.getString("Pengiriman");
        String Total = bundle2.getString("Total");
        String Alamat = bundle2.getString("Alamat");

        tanggal.setText(formattedDate);
        pembayaranInvoice.setText(Pembayaran);
        pengirimanPaket.setText(Pengiriman);
        hargaProduk.setText(Total);
        alamatPaket.setText(Alamat);


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Invoice.this, EndActivity.class));
            }
        });

    }
}
