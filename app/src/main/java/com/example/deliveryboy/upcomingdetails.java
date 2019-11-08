package com.example.deliveryboy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.deliveryboy.utils.Global;
import com.squareup.picasso.Picasso;

public class upcomingdetails extends AppCompatActivity {

    TextView pick,delivery,product,deliveryamount,canamount,totalamo,shop,payment;
    ImageView imageView;
    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE); // will hide the title
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcomingdetails);

        pick=findViewById(R.id.pick);
        delivery=findViewById(R.id.del);

        shop=findViewById(R.id.shopname);
        product=findViewById(R.id.product);


        deliveryamount=findViewById(R.id.deliveryp);

        totalamo=findViewById(R.id.totalamount);

        payment=findViewById(R.id.payment);

        imageView=findViewById(R.id.img);

        Bundle bundle = getIntent().getExtras();

        String pickn = bundle.getString("pick");

        pick.setText(pickn);

        String del=bundle.getString("delivery");
        delivery.setText(del);

        String sh=bundle.getString("shopname");
        shop.setText(sh);

        String pro=bundle.getString("product");
        product.setText(pro);

        String delc=bundle.getString("delcharge");
        deliveryamount.setText(delc);

        String tot=bundle.getString("totcharge");
        totalamo.setText(tot);

        String pay=bundle.getString("payment");
        payment.setText(pay);

        String sp=bundle.getString("image");
        Picasso.with(context).load(sp).into(imageView);





    }
}
