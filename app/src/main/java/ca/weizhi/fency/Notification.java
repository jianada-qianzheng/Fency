package ca.weizhi.fency;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.navnas.barcodereader.R;

import static android.content.Context.NOTIFICATION_SERVICE;

public class Notification {

    public static void pushNotification(String type, int id,  String message,Context mContext) {
        // Using RemoteViews to bind custom layouts into Notification
        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(),
                R.layout.custom_push);


        // Set Notification Title
        String strtitle = "title";
        // Set Notification Text
        String strtext = "text";

        // Open NotificationView Class on Notification Click
        Intent intent = new Intent(mContext.getApplicationContext(), MainActivity.class);
        // Send data to NotificationView Class
        intent.putExtra("title", strtitle);
        intent.putExtra("text", strtext);
        // Open NotificationView.java Activity
        PendingIntent pIntent = PendingIntent.getActivity(mContext.getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext.getApplicationContext(),"notify_001")
                // Set Icon
                .setSmallIcon(R.drawable.ic_launcher_background)
                // Set Ticker Message
                // Dismiss Notification
                .setAutoCancel(true)
                // Set PendingIntent into Notification
                .setContentIntent(pIntent)
                // Set RemoteViews into Notification
                .setContent(remoteViews);

        // Locate and set the Image into customnotificationtext.xml ImageViews
        remoteViews.setImageViewResource(R.id.imagenotileft,R.drawable.common_google_signin_btn_icon_dark_normal);
        // remoteViews.setImageViewResource(R.id.imagenotiright,R.drawable.common_full_open_on_phone);





        // Locate and set the Text into customnotificationtext.xml TextViews
        remoteViews.setTextViewText(R.id.title,"text1");
        remoteViews.setTextViewText(R.id.text,"text2");

        //remoteViews.setTextViewText(R.id.textView,longitude+"/"+latitude);

        Bitmap bitm=null;
        BarcodeGenerator ecc=new BarcodeGenerator(120, 40);
        try {
            bitm=ecc.bitmap1(message);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        remoteViews.setImageViewBitmap(R.id.imagenotileft,bitm);

        remoteViews.setImageViewResource (R.id.imageView3,R.drawable.ic_metro);






        // Create Notification Manager
        NotificationManager notificationmanager = (NotificationManager) mContext.getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("notify_001",
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setLockscreenVisibility(android.app.Notification.VISIBILITY_PUBLIC);
            notificationmanager.createNotificationChannel(channel);
        }
        // Build Notification with Notification Manager
        notificationmanager.notify(0, builder.build());

    }
}
