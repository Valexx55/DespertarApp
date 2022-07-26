package edu.val.despertarapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class FinServicioReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("ETIQUETA_LOG", "Servicio finalizado");
        // TODO: obtener el número del servicio aleatorio
        //y si se ha generado un número mayor o igual que 60, lanzamos una notificación
        //si no, no lanzamos una notificación
        //en todo caso, reprograamos la alarma
        int numero_recibido = intent.getIntExtra("NUM_ALEATORIO", -1);
        Log.d("ETIQUETA_LOG", "Número recibido = " + numero_recibido);
        if (numero_recibido>=60)
        {
            Notificaciones.lanzarNotificacion(context);
            Log.d("ETIQUETA_LOG", "Lanzo notificación");
        } else {
            Log.d("ETIQUETA_LOG", "No Lanzo notificación");
        }

        GestorAlarma.programarAlarma(context);
        context.unregisterReceiver(this);


    }
}