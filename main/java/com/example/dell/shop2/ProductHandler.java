package com.example.dell.shop2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


public class ProductHandler {

    private SQLiteHelper dbHelper;

    public ProductHandler(Context context){dbHelper=new SQLiteHelper(context);}

    int addProduct(product_pojo pj)
    {
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues v=new ContentValues();
        v.put(dbHelper.id,pj.getId());
        v.put(dbHelper.name,pj.getName());
        v.put(dbHelper.price,pj.getPrice());
        v.put(dbHelper.unit,pj.getUnit());
        v.put(dbHelper.quantity,pj.getQuantity());
        v.put(dbHelper.description,pj.getDescription());

        long idE=db.insert(dbHelper.TABLE,null,v);

        db.close();

        return (int)idE;
    }

    product_pojo getProduct(int id)
    {
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Cursor c=db.query(dbHelper.TABLE,new String[]{dbHelper.id,dbHelper.name,dbHelper.price,
                           dbHelper.unit,dbHelper.quantity,dbHelper.description},dbHelper.id+"=?",new String[]{String.valueOf(id)},null,null,null,null);

        if(c!=null)
            c.moveToFirst();

        product_pojo pj=new product_pojo(Integer.parseInt(c.getString(0)),c.getString(1),
                Double.parseDouble(c.getString(2)),c.getString(3),Double.parseDouble(c.getString(4)),c.getString(5));

        return pj;
    }

    public int updateProduct(product_pojo pj)
    {
        SQLiteDatabase db=dbHelper.getWritableDatabase();

        ContentValues v=new ContentValues();
        v.put(dbHelper.id,pj.getId());
        v.put(dbHelper.name,pj.getName());
        v.put(dbHelper.price,pj.getPrice());
        v.put(dbHelper.unit,pj.getUnit());
        v.put(dbHelper.quantity,pj.getQuantity());
        v.put(dbHelper.description,pj.getDescription());

        return db.update(dbHelper.TABLE,v,dbHelper.id+" = ?",new String[]{String.valueOf(pj.getId())});

    }

    public void deleteProduct(product_pojo pj)
    {

        SQLiteDatabase db=dbHelper.getWritableDatabase();
        db.delete(dbHelper.TABLE,dbHelper.id+"= ?",new String[]{String.valueOf(pj.getId())});
        db.close();
    }

    public List<product_pojo> getAllProducts(){

        SQLiteDatabase db=dbHelper.getWritableDatabase();
        List<product_pojo> pjList=new ArrayList<product_pojo>();
        String selque="SELECT  * FROM " + dbHelper.TABLE;

        Cursor c=db.rawQuery(selque,null);

        if(c.moveToFirst())
        {
            do{
               product_pojo pj=new product_pojo();
                pj.setId(Integer.parseInt(c.getString(0)));
                pj.setName(c.getString(1));
                pj.setPrice(Double.parseDouble(c.getString(2)));
                pj.setUnit(c.getString(3));
                pj.setQuantity(Double.parseDouble(c.getString(4)));
                pj.setDescription(c.getString(5));


                pjList.add(pj);
            }while(c.moveToNext());
        }

        return pjList;

    }

    public int getProductsCount() {

        String countQuery = "SELECT  * FROM " + dbHelper.TABLE;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }




}
