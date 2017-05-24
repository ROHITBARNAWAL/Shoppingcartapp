package com.example.dell.shop2;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class ProductAdapter extends BaseAdapter {


    private Activity act;
    private LayoutInflater inflater;
    private List<product_pojo> proList;

    public ProductAdapter(Activity act,List<product_pojo> proList)
    {
        this.act=act;
        this.proList=proList;

    }

    @Override
    public int getCount(){return proList.size();}

    @Override
    public Object getItem(int position){return proList.get(position);}

    @Override
    public long getItemId(int position){return position;}

    @Override
    public View getView(int position, View v, ViewGroup parent)
    {
    if(inflater==null)
        inflater=(LayoutInflater)act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
     if(v==null)
         v=inflater.inflate(R.layout.pro_view_format,null);

        TextView p1= (TextView) v.findViewById(R.id.t1);
        TextView p2= (TextView) v.findViewById(R.id.t2);
        TextView p3= (TextView)v.findViewById(R.id.t3);
        TextView p4 = (TextView)v.findViewById(R.id.t4);
        Button b=(Button)v.findViewById(R.id.b1);

        product_pojo list=proList.get(position);

        p1.setText(String.valueOf(list.getId()));
        p2.setText(list.getName());
        p3.setText(String.valueOf(list.getPrice()));
        p4.setText(" /"+list.getUnit());

        b.setOnLongClickListener(new ListItemClickListener(position,list));


        return v;

    }

    private class ListItemClickListener implements View.OnLongClickListener{

        int position;
        product_pojo list;

        public ListItemClickListener(int position,product_pojo list)
        {
            this.position=position;
            this.list=list;
        }

        @Override
        public boolean onLongClick(final View v)
        {
            final Dialog d=new Dialog(v.getRootView().getContext());
            d.setContentView(R.layout.options);
            d.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            Button p1=(Button)d.findViewById(R.id.b1);
            Button p2=(Button)d.findViewById(R.id.b2);
            Button p3=(Button)d.findViewById(R.id.b3);
            p1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Dialog dd=new Dialog(v.getRootView().getContext());
                    dd.setContentView(R.layout.details);
                    dd.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                    TextView t=(TextView)dd.findViewById(R.id.t1);

                    product_pojo list=proList.get(position);
                    String n="Quantity: "+String.valueOf(list.getQuantity())+"\nDescription: "+list.getDescription();
                    t.setText(n);

                    dd.show();
                }
            });


            p2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Dialog k=new Dialog(v.getRootView().getContext());
                    k.setContentView(R.layout.update);
                    k.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);

                    final TextView p1=(TextView)k.findViewById(R.id.t1);
                    final TextView p2=(TextView)k.findViewById(R.id.t2);
                    final TextView p3=(TextView)k.findViewById(R.id.t3);
                    final TextView p4=(TextView)k.findViewById(R.id.t4);

                    final EditText o1=(EditText)k.findViewById(R.id.e1);
                    final EditText o2=(EditText)k.findViewById(R.id.e2);
                    final EditText o3=(EditText)k.findViewById(R.id.ee3);
                    final EditText o4=(EditText)k.findViewById(R.id.ee4);

                    final Button b=(Button)k.findViewById(R.id.b1);
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if(!(o1.getText().toString().equals("")) && !(o2.getText().toString().equals("")) && !(o3.getText().toString().equals("")) && !(o4.getText().toString().equals("")))
                            {
                                String nam, des;
                                Double cost, stock;
                                nam = o1.getText().toString();
                                cost = Double.parseDouble(o2.getText().toString());
                                stock = Double.parseDouble(o3.getText().toString());
                                des = o4.getText().toString();

                                product_pojo list=proList.get(position);
                                int idp=list.getId();
                                String un=list.getUnit();

                                ProductHandler db=new ProductHandler(act);
                                db.updateProduct(new product_pojo(idp, nam, cost, un, stock, des));
                                proList.set(position,list);

                                ProductAdapter ad=new ProductAdapter(act,proList);
                                notifyDataSetChanged();
                                ad.notifyDataSetChanged();


                                Toast.makeText(v.getRootView().getContext(),"Product Updated!!",Toast.LENGTH_LONG).show();

                                v.getContext().startActivity(new Intent(v.getRootView().getContext(),store.class));

                                k.dismiss();
                                d.dismiss();


                            }
                            else
                            {
                                Toast.makeText(v.getRootView().getContext(),"Wrong Input Or Field Empty",Toast.LENGTH_LONG).show();
                            }

                        }
                    });

                    k.show();

                    }
            });


            p3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    AlertDialog.Builder dd=new AlertDialog.Builder(v.getRootView().getContext());
                    dd.setMessage("Are you sure you want to delete this product from the list??");
                    dd.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            ProductHandler db=new ProductHandler(act);
                            db.deleteProduct(list);
                            proList.remove(position);
                            notifyDataSetChanged();
                            d.dismiss();
                            Toast.makeText(v.getRootView().getContext(),"PRODUCT DELETED FROM THE LIST!!",Toast.LENGTH_LONG).show();
                        }
                    });
                    dd.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            d.dismiss();
                        }
                    });
                    dd.show();

                }
            });
            d.show();

            return  false;
        }

    }

}