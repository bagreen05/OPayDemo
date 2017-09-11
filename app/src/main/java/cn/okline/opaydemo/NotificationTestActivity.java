package cn.okline.opaydemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

public class NotificationTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_test);
        findViewById(R.id.notification0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notification.Builder builder = new Notification.Builder(NotificationTestActivity.this);

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("www.baidu.com"));
                PendingIntent pendingIntent = PendingIntent.getActivity(NotificationTestActivity.this, 0, intent, 0);
                builder.setContentIntent(pendingIntent).setSmallIcon(R.mipmap.ic_launcher_round).setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                    .setAutoCancel(true).setContentTitle("普通通知");
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    Notification notification = builder.build();
                    NotificationManager systemService = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    systemService.notify(0,notification);
                }
            }
        });
        findViewById(R.id.notification1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notification.Builder builder = new Notification.Builder(NotificationTestActivity.this);

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("www.baidu.com"));
                PendingIntent pendingIntent = PendingIntent.getActivity(NotificationTestActivity.this, 0, intent, 0);
                builder.setContentIntent(pendingIntent).setSmallIcon(R.mipmap.ic_launcher_round)
//                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                        .setAutoCancel(true).setContentTitle("折叠式通知");
                RemoteViews remoteViews = new RemoteViews(BuildConfig.APPLICATION_ID, R.layout.card_view_long);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        builder.setCustomBigContentView(remoteViews);
                    }
                    Notification notification = builder.build();
//                    notification.bigContentView = remoteViews;
                    NotificationManager systemService = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    systemService.notify(1,notification);
                }
            }
        });
        findViewById(R.id.notification2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    Notification.Builder builder = new Notification.Builder(NotificationTestActivity.this);
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("www.baidu.com"));
                    PendingIntent pendingIntent = PendingIntent.getActivity(NotificationTestActivity.this, 0, intent, 0);
                    builder.setContentIntent(pendingIntent).setSmallIcon(R.mipmap.ic_launcher_round).setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                            .setAutoCancel(true).setContentTitle("悬挂式通知");

                    Intent hangIntent = new Intent();
                    hangIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    hangIntent.setClass(NotificationTestActivity.this,NotificationTestActivity.class);
                    PendingIntent pendingIntent1 = PendingIntent.getActivity(NotificationTestActivity.this, 0, hangIntent, PendingIntent.FLAG_CANCEL_CURRENT);
                    builder.setFullScreenIntent(pendingIntent1,true);

                    Notification notification = null;
                    notification = builder.build();
                    NotificationManager systemService = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    systemService.notify(2,notification);
                } else {
                    //版本小于16的话另行安排通知方式
                }
            }
        });
    }
}
