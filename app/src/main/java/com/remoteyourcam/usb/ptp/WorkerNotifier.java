package com.remoteyourcam.usb.ptp;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;

import com.remoteyourcam.usb.R;
import com.remoteyourcam.usb.util.NotificationIds;

public class WorkerNotifier implements Camera.WorkerListener {

    private final NotificationManager notificationManager;
    private final Notification notification;
    private final int uniqueId;

    public WorkerNotifier(Context context) {
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(R.drawable.icon);
        builder.setTicker(context.getString(R.string.worker_ticker));
        builder.setContentTitle(context.getString(R.string.worker_ticker));
        notification = builder.build();
        uniqueId = NotificationIds.getInstance().getUniqueIdentifier(WorkerNotifier.class.getName() + ":running");
    }

    @Override
    public void onWorkerStarted() {
        notification.flags |= Notification.FLAG_NO_CLEAR;
        notificationManager.notify(uniqueId, notification);
    }

    @Override
    public void onWorkerEnded() {
        notificationManager.cancel(uniqueId);
    }

}
