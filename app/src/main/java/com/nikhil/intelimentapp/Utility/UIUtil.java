package com.nikhil.intelimentapp.Utility;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by Nikhil on 08-03-2016.
 */
public class UIUtil {
    private static ProgressDialog mProgressDialog;
    private static Object mObject = new Object();
    private static String APP_TAG = "Inteliment App";

    public static void startProgressDialog(Context context, String message) {
        synchronized (mObject) {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(context, "", message);
                mProgressDialog.setIndeterminate(true);
            }
        }
    }

    public static void stopProgressDialog(Context context) {
        synchronized (mObject) {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
                mProgressDialog = null;
            }
        }
    }

    public static boolean isInternetAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    public static void ShowToast(Context context, String message) {
        Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show();
    }

    public static void ShowToastCenter(Context context, String message) {
        Toast toast = Toast.makeText(context, "" + message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void AppLog(String message) {
        Log.d(APP_TAG, message);
    }

    public static void AppErrorLog(String message) {
        Log.e(APP_TAG, message);
    }


}
