package edu.val.despertarapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GestorAlarma {

    public static void programarAlarma (Context context)
    {
        Calendar calendar = Calendar.getInstance();
        long tiempo = calendar.getTimeInMillis() + 10000; //hago que la alarma salte en 10 segundos

        AlarmManager alarmManager =  (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmaReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 65, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, tiempo, pendingIntent);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E dd/MM/yyy 'a las ' hh:mm:ss");
        String mensaje = "Alarma programada para " + simpleDateFormat.format(tiempo);

        Log.d("ETIQUETA_LOG", mensaje);
        Toast.makeText(context, mensaje, Toast.LENGTH_LONG).show();





    }
}
