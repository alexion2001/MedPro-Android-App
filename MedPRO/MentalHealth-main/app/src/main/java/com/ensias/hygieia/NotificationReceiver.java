package com.ensias.hygieia;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.ensias.hygieia.DoctorAppointementActivity;
import com.ensias.hygieia.MainActivity;
import com.ensias.hygieia.R;

public class NotificationReceiver extends BroadcastReceiver {
    private static final String CHANNEL_ID = "channel_id";
    private static final String CHANNEL_NAME = "channel_name";

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent notificationIntent = new Intent(context, DoctorHomeActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context, 0, notificationIntent, 0
        );

        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                context, CHANNEL_ID
        );
        builder.setContentTitle("Consultation Reminder")
                .setContentText("You have a consultation in 2 minutes")
                .setSmallIcon(R.drawable.baseline_calendar_month_24)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(1, builder.build());
    }
}

