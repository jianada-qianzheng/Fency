package ca.weizhi.fency;

import android.app.Activity;
import android.graphics.Bitmap;
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

        ImageView barcodeView=(ImageView) rowView.findViewById(R.id.barcode_img);

        if(cardArrayList.get(position).getBarcode().equals("")){

            Bitmap bitm=null;
            Ecoad ecc=new Ecoad(120, 40);
            try {
                bitm=ecc.bitmap1("012345");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            barcodeView.setImageBitmap(bitm);

        }else{


            Bitmap bitm=null;
            Ecoad ecc=new Ecoad(120, 40);
            try {
                bitm=ecc.bitmap1("012345");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            barcodeView.setImageBitmap(bitm);

        }

        //logoView.setImageResource(imageId[position]);

        return rowView;
    }

}
