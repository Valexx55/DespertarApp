package edu.val.despertarapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class Notificaciones {

    public static final String ID_CANAL_NOTIFICACION = "canal_id1";
    public static final String NOMBRE_CANAL = "nombre canal 1";


    public static void lanzarNotificacion (Context context)
    {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        //dentro de este método, vamos a lanzar la notificación
        Log.d("ETIQUETA_LOG", "Lanzando noticación ...");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            Log.d("ETIQUETA_LOG", "Necesitamos crear un canal ...");
            NotificationChannel notificationChannel = crearCanalNotificacion (context, ID_CANAL_NOTIFICACION, NOMBRE_CANAL);
            notificationManager.createNotificationChannel(notificationChannel);
            Log.d("ETIQUETA_LOG", "Canal Creado ...");
        }



        NotificationCompat.Builder nb = new NotificationCompat.Builder(context, ID_CANAL_NOTIFICACION);
       // NotificationCompat.Builder nb1 = new NotificationCompat.Builder(context);

        nb.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        nb.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        //el icono de la notificación debe ser blanco y de fondo transparente
        nb.setSmallIcon(R.drawable.ic_baseline_bus_alert_24);
        Bitmap bitmap_icono_largo = BitmapFactory.decodeResource(context.getResources(), R.drawable.emoticono_risa);
        nb.setLargeIcon(bitmap_icono_largo);
        //Patrón "builder" - EL MÉTODO modifica una propeidad y me devuelve el propio objeto modificado
        nb.setContentTitle("BUENOS DÍAS").setSubText("aviso diario").setContentText("Eres un campeón");
        nb.setAutoCancel(true);//consigo que cuando se toque, desaparezca
        nb.setDefaults(Notification.DEFAULT_ALL);

        Log.d("ETIQUETA_LOG", "Notificación construída...");

        //CUANDO TOQUE TOQUE LA NOTIFICACIÓN, QUIERO IR A MAIN ACTIVITY.class
        //para ello, normalmente, necesitaría un intent
        //pero al hacerlo "desde fuera" - la notificación aparece en la panta inicial"
        //necesito un PendingIntent
        Intent intent_noti = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 775, intent_noti, PendingIntent.FLAG_ONE_SHOT);
        nb.setContentIntent(pendingIntent);

        Log.d("ETIQUETA_LOG", "Notificación con Pending Intent...");

        Notification notification = nb.build();
        Log.d("ETIQUETA_LOG", "Notificación Lanzada...");
        notificationManager.notify(345, notification);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static NotificationChannel crearCanalNotificacion (Context context, String id_canal, String nombre_canal)
    {
        NotificationChannel notificationChannel = null;

            notificationChannel = new NotificationChannel(id_canal, nombre_canal, NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.enableLights(true);
            notificationChannel.enableVibration(true);
            notificationChannel.setLightColor(context.getResources().getColor(R.color.purple_700));

            //audio
            AudioAttributes audioAttributes = new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).setUsage(AudioAttributes.USAGE_NOTIFICATION).build();

            //TODO PROBAR CON SONIDO POR DEFECTO
            //notificationChannel.setSound(Settings.System.DEFAULT_NOTIFICATION_URI, audioAttributes);

            Uri uri_sonido_personalizado = Uri.parse("android.resource://"+ context.getPackageName()+"/" + R.raw.sonido_noti);

            notificationChannel.setSound(uri_sonido_personalizado, audioAttributes);

            //puedo indicar el patrón de vibracoión {tiempo silencio, tiempo vibración, tiempo silencio..
            long [] patron_vibracion_silencio = new long[] {500, 500, 500, 500, 500, 500};
            notificationChannel.setVibrationPattern(patron_vibracion_silencio);

            //indico si la notificación es visible con la pantalla bloqueda
            notificationChannel.setLockscreenVisibility(NotificationCompat.VISIBILITY_PUBLIC);



        return notificationChannel;

    }

    public static Notification crearNotificacionSegundoPlano (Context context)
    {
        Notification segundoplano = null;

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        //dentro de este método, vamos a lanzar la notificación
        Log.d("ETIQUETA_LOG", "Lanzando noticación ...");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            Log.d("ETIQUETA_LOG", "Necesitamos crear un canal ...");
            NotificationChannel notificationChannel = crearCanalNotificacion (context, ID_CANAL_NOTIFICACION, NOMBRE_CANAL);
            notificationManager.createNotificationChannel(notificationChannel);
            Log.d("ETIQUETA_LOG", "Canal Creado ...");
        }



        NotificationCompat.Builder nb = new NotificationCompat.Builder(context, ID_CANAL_NOTIFICACION);
        // NotificationCompat.Builder nb1 = new NotificationCompat.Builder(context);

        nb.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        nb.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        //el icono de la notificación debe ser blanco y de fondo transparente
        nb.setSmallIcon(R.drawable.ic_baseline_bus_alert_24);
        //Patrón "builder" - EL MÉTODO modifica una propeidad y me devuelve el propio objeto modificado
        nb.setContentTitle("Comprobando si hay mensajes");//.setSubText("aviso diario").setContentText("Eres un campeón");
        nb.setAutoCancel(true);//consigo que cuando se toque, desaparezca
        nb.setDefaults(Notification.DEFAULT_ALL);
        nb.setTimeoutAfter(5000);//la notificación se quita sola a los 5 segundos

        Log.d("ETIQUETA_LOG", "Notificación construída...");

        segundoplano = nb.build();
        Log.d("ETIQUETA_LOG", "Notificación Lanzada...");
        //notificationManager.notify(345, notification);

        return  segundoplano;//esta notificación será visible mientras el servicio se ejecute en segundo plano. tipo "comprobando si hay mensajes de whatsapp"
    }
}
