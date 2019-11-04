package ca.weizhi.fency;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.navnas.barcodereader.R;

import java.util.ArrayList;

public class CardAdapter extends ArrayAdapter {

    private final Activity context;
    private final ArrayList<Card> cardArrayList;
    public CardAdapter(Activity context,
                       ArrayList<Card> cardArrayList) {
        super(context, R.layout.card_layout);
        this.context = context;
        this.cardArrayList=cardArrayList;
    }


    @Override
    public int getCount() {
        return cardArrayList.size();
        //return 2;
    }
    @Override
    public Object getItem(int position) {
        return cardArrayList.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Log.i("adapter","start");


        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.card_layout, null, true);
        ImageView logoView = (ImageView) rowView.findViewById(R.id.logo_img);

        logoView.setImageBitmap(getBitmapByName(cardArrayList.get(position).getStoreName().toLowerCase()));


        ImageView barcodeView=(ImageView) rowView.findViewById(R.id.barcode_img);

        TextView addView=rowView.findViewById(R.id.addView);

        if(cardArrayList.get(position).getBarcode().equals("")){

            addView.setVisibility(View.VISIBLE);

            barcodeView.setVisibility(View.GONE);

            addView.setText("Add Card");

        }else{
            addView.setVisibility(View.GONE);

            barcodeView.setVisibility(View.VISIBLE);

            Bitmap bitm=null;
            Ecoad ecc=new Ecoad(120, 40);
            try {
                bitm=ecc.bitmap1(cardArrayList.get(position).getBarcode());
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            barcodeView.setImageBitmap(bitm);

        }





        //logoView.setImageResource(imageId[position]);

        return rowView;
    }

    public Bitmap getBitmapByName(String name) {
        ApplicationInfo appInfo = context.getApplicationInfo();
        int resID = context.getResources().getIdentifier(name, "drawable", appInfo.packageName);
        return BitmapFactory.decodeResource(context.getResources(), resID);
    }


}
