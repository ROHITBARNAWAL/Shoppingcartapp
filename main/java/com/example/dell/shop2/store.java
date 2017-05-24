package com.example.dell.shop2;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class store extends AppCompatActivity {

    private List<product_pojo> proList;
    private ListView lView;

    private ProductAdapter ad;
    ProductHandler db;


    EditText p1,p2,p3,p4,p5;
    CheckBox C1,C2;
    TextView o1,o2;
    Button b;

    int idp;
    String nam,un,des;
    Double cost,stock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        lView=(ListView)findViewById(R.id.pList);

        db=new ProductHandler(this);

        proList=db.getAllProducts();

        ad=new ProductAdapter(this,proList);
        lView.setAdapter(ad);


    }

    public void add(View view)
    {

       final Dialog d=new Dialog(store.this);
        d.setContentView(R.layout.add_product);
        d.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        p1=(EditText)d.findViewById(R.id.e1);p2=(EditText)d.findViewById(R.id.e2);
        p3=(EditText)d.findViewById(R.id.ee3);p4=(EditText)d.findViewById(R.id.ee4);
        p5=(EditText)d.findViewById(R.id.t7);

        C1=(CheckBox)d.findViewById(R.id.c1); C2=(CheckBox)d.findViewById(R.id.c2);

        o1=(TextView)d.findViewById(R.id.t1); o2=(TextView)d.findViewById(R.id.t2);

        b=(Button)d.findViewById(R.id.b1);

       C1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (C1.isChecked() == true) {
                    C2.setChecked(false);
                    o1.setText("/piece");
                    o2.setText("pieces");
                    un = "piece";
                    p3.setEnabled(true);
                    p4.setEnabled(true);

                }
                else
                {
                    p3.setEnabled(false);
                    p4.setEnabled(false);}
            }
        });

        C2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (C2.isChecked() == true) {
                    C1.setChecked(false);
                    o1.setText("/kg");
                    o2.setText("kg");
                    un = "kg";
                    p3.setEnabled(true);
                    p4.setEnabled(true);
                }
                else
                {
                    p3.setEnabled(false);
                    p4.setEnabled(false);}

            }
        });



        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(p1.getText().toString().equals("")) && !(p3.getText().toString().equals("")) && !(p4.getText().toString().equals("")) && !(p2.getText().toString().equals("")) && !(p5.getText().toString().equals(""))) {
                    idp = Integer.parseInt(p1.getText().toString());
                    nam = p2.getText().toString();
                    cost = Double.parseDouble(p3.getText().toString());
                    stock = Double.parseDouble(p4.getText().toString());
                    des = p5.getText().toString();


                    int p=db.addProduct(new product_pojo(idp,nam, cost, un, stock, des));

                    proList.add(new product_pojo(idp, nam, cost, un, stock, des));

                    ad.notifyDataSetChanged();


                    d.dismiss();
                    Toast.makeText(getApplicationContext(),"Product Added",Toast.LENGTH_LONG).show();

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Wrong Input Or Field Empty",Toast.LENGTH_LONG).show();

                }

            }
        });

        d.show();



    }

}
