package edu.val.despertarapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class InicioMovilReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.d("ETIQUETA_LOG", "DISPOISITO INICIADO");
        Intent intent1 = new Intent(context, MainActivity.class);
        Log.d("ETIQUETA_LOG", "Lanzo la actividad inicial");
        Notificaciones.lanzarNotificacion(context);
    }
}