package com.moovapp.rider;

/**
 * Created by Lijo Mathew Theckanal on 05-Dec-17.
 */

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.moovapp.rider.utils.AppPreferences;
import com.moovapp.rider.utils.Constants;

import java.util.List;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    public AppPreferences appPrefes;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //Displaying data in log
        //It is optional
        appPrefes = new AppPreferences(this, getResources().getString(R.string.app_name));
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        try {
            Log.d(TAG, "Notification Message Body: " + remoteMessage.getData().get("nmessage"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Calling method to generate notification
        try {
            checkUser(remoteMessage.getData().get("nmessage"), remoteMessage.getData().get("ntype"), remoteMessage.getData().get("njobid"), remoteMessage.getData().get("nhtml"));
        } catch (Exception e) {
            e.printStackTrace();
            checkUser(remoteMessage.getData().get("nmessage"), remoteMessage.getData().get("ntype"), "", remoteMessage.getData().get("nhtml"));
        }

    }

    private void checkUser(String nmessage, String ntype, String jobId, String htmlMsg) {
//        if (appPrefes.getDataBoolean(Constants.IS_USER_LOGGED_IN)) {
//            if (appPrefes.getData(Constants.LOGGED_USER_TYPE).equals("Job Provider")) {
//                if (ntype.equals("seekerCancelled") || ntype.equals("seekerConfirmed") || ntype.equals("seekerapply") || ntype.equals("adminMessage")) {
//                    sendNotification(nmessage, ntype, jobId, htmlMsg);
//                }
//            } else {
//                if (ntype.equals("notify") || ntype.equals("providerSelect") || ntype.equals("providerRejected") || ntype.equals("providerRate") || ntype.equals("jobCompleted") || ntype.equals("providerDeclineConfirmed") || ntype.equals("adminMessage")) {
//                    sendNotification(nmessage, ntype, jobId, htmlMsg);
//                }
//            }
//        }
    }

    //This method is only generating push notification
    //It is same as we did in earlier posts
    private void sendNotification(String messageBody, String type, String jobId, String htmlMsg) {
//        Intent intent;
//        if(appPrefes.getData(Constants.LOGGED_USER_TYPE).equals("Job Provider")){
//            intent = new Intent(this, JobProviderHomeActivity.class);
//        }else{
//            intent = new Intent(this, JobProviderHomeActivity.class);
//        }
//
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
//                PendingIntent.FLAG_ONE_SHOT);
//
//        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
////        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
////                .setSmallIcon(R.mipmap.ic_launcher)
////                .setContentTitle("ExtraHourz")
////                .setContentText(messageBody)
////                .setAutoCancel(true)
////                .setSound(defaultSoundUri)
////                .setContentIntent(pendingIntent);
//
//
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
//        builder.setSmallIcon(R.mipmap.app_logo_white_bg)
//                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.app_logo_white_bg))
//                .setColor(getResources().getColor(R.color.colorPrimary))
//                .setContentTitle("ExtraHourz")
//                .setContentIntent(pendingIntent)
//                .setContentText(messageBody)
//                .setSound(defaultSoundUri)
//                .setAutoCancel(true)
//                .setDefaults(Notification.DEFAULT_ALL)
//                .setPriority(NotificationManager.IMPORTANCE_HIGH);
//
//        NotificationManager notificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//        notificationManager.notify(0, builder.build());
//
//        ActivityManager activityManager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
//        List<ActivityManager.RunningTaskInfo> services = activityManager
//                .getRunningTasks(Integer.MAX_VALUE);
//        boolean isActivityFound = false;
//
//        if (services.get(0).topActivity.getPackageName().toString()
//                .equalsIgnoreCase(getApplicationContext().getPackageName().toString())) {
//            isActivityFound = true;
//        }
//
//        if (isActivityFound) {
//            Intent resultIntent = new Intent(getApplicationContext(), AlertDialogNotification.class);
//            resultIntent.putExtra("Message", htmlMsg);
//            resultIntent.putExtra("Type", "" + type);
//            resultIntent.putExtra("JobId", "" + jobId);
//            resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            getApplicationContext().startActivity(resultIntent);
//        }
    }
}