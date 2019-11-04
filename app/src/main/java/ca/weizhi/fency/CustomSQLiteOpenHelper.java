package ca.weizhi.fency;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

import static android.content.ContentValues.TAG;

public class CustomSQLiteOpenHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;



    private static final String DATABASE_NAME = "fency.db";//数据库名字
    private static final int DATABASE_VERSION = 1;//数据库版本号
    private static final String CREATE_TABLE_CARDS = "create table CARDS ("
            + "id integer primary key,"

            + "STORE_NAME TEXT ,"
            + "BARCODE TEXT);";

    private static final String CREATE_TABLE_LOCATIONS = "create table LOCATIONS ("
            + "id integer primary key,"
            + "latitude real ,"
            + " longitude real ,"
            + "store_name text ,"
            + "active integer);";



    public CustomSQLiteOpenHelper(Context context) {
        this(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private CustomSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);//调用到SQLiteOpenHelper中
        Log.d(TAG,"New CustomSQLiteOpenHelper");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG,"onCreate");
        db.execSQL(CREATE_TABLE_CARDS);
        db.execSQL(CREATE_TABLE_LOCATIONS);
        String  userGUID = UUID.randomUUID().toString();
        Log.i("guid",userGUID+"");

        Cursor cursor= db.rawQuery("select * from CARDS; ",null);

        if(!cursor.moveToNext()) {
            db.execSQL("insert into cards values(1,'Metro','');");
            db.execSQL("insert into cards values(2,'IGA','');");





        }

        cursor= db.rawQuery("select * from LOCATIONS; ",null);

        if(!cursor.moveToNext()) {
            db.execSQL("insert into locations values(1,45.450180,-75.754488,'metro',1);");

            db.execSQL("insert into locations values(2,45.446529,-75.733623,'IGA',1);");




        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<Card> getCards(){

        db=getReadableDatabase();

        ArrayList<Card> cardArrayList=new ArrayList<>();

        Cursor cursor= db.rawQuery("select * from CARDS; ",null);

        while(cursor.moveToNext()){
            cardArrayList.add(new Card(cursor.getInt(0),cursor.getString(1),cursor.getString(2)));


        }
        return  cardArrayList;


    }

    public void insertBarcode(int storeId,String barcode){

        db=getWritableDatabase();

        db.execSQL("update cards set barcode='"+barcode+"' where id='"+storeId+"'");


    }

//    public String getGUID(){
//
//        Cursor cursor=db.rawQuery("select * from user ",null);
//
//        String userGUID="";
//
//        if(cursor.moveToNext()){
//
//            userGUID=cursor.getString(1);
//
//        }
//
//        return userGUID;
//
//    }
//
//    public String getUsername(){
//
//        Cursor cursor=db.rawQuery("select * from user ",null);
//
//        String username="";
//
//        if(cursor.moveToNext()){
//
//            username=cursor.getString(2);
//
//        }
//
//        return username;
//
//    }
}
