package com.example.deliveryboy;

import android.content.Context;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.deliveryboy.utils.Global;
import com.example.deliveryboy.utils.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Tab1Fragment extends Fragment {

    TextView pickup,delivery,shopname,productname,address;
    SessionManager sessionManager;


    private String URLline = Global.BASE_URL+"api_delivery_boy/del_boy_online_status/";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_tab1_fragment, container, false);

        pickup=view.findViewById(R.id.pickTv);

        delivery=view.findViewById(R.id.createdDate);

        shopname=view.findViewById(R.id.orderType);

        productname=view.findViewById(R.id.shop_name);

        address=view.findViewById(R.id.dateee);
        sessionManager = new SessionManager(view.getContext());


        callup();

        return view;
    }

    private void callup(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLline,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  dialog.dismiss();
                        //  Toast.makeText(Login.this,response,Toast.LENGTH_LONG).show();
                        //parseData(response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String ot = jsonObject.optString("message");
                            String status=jsonObject.optString("code");
                            String token=jsonObject.optString("data");







                            Log.d("otp","mm"+token);
                            Log.d("code","mm"+status);
                            if(status.equals("200")){
                                Toast.makeText(getContext(), "Successful", Toast.LENGTH_LONG).show();
                               /* Intent intent = new Intent(Login.this, MainUI.class);
                                startActivity(intent);*/
                            }
                            else{
                                Toast.makeText(getContext(), "Failed."+ot, Toast.LENGTH_LONG).show();


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //   Log.d("response","hhh"+response);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("online_status",sessionManager.getID());


                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                Map<String, String>  params = new HashMap<String, String>();
                params.put("Authorization","Token "+sessionManager.getTokens());
                Log.d("Tokenccccc","mm"+sessionManager.getTokens());
                return params;

            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);


    }
}
