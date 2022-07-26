package edu.val.despertarapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

public class AlarmaReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");

        Log.d("ETIQUETA_LOG", "La alarma ha sonado ");
        Intent intent_service = new Intent(context, ServiceNumAleatorio.class);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            context.startForegroundService(intent_service);
        } else {
            context.startService(intent_service);
        }
        Log.d("ETIQUETA_LOG", "Servicio Lanzado ");


    }
}