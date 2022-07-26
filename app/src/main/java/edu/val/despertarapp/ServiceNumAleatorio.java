package edu.val.despertarapp;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class ServiceNumAleatorio extends Service {

    private int numero_aleatorio;


    public ServiceNumAleatorio() {
    }


    //ESTAMOS PROGRAMANDO UN STARTED SERVICE
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("ETIQUETA_LOG", "inicio servicio Num Aleatorio");
       // return super.onStartCommand(intent, flags, startId);
        Notification notification = Notificaciones.crearNotificacionSegundoPlano(this);//detalle Service hereda de context
        startForeground(66, notification);//saldrá el mensaje de aviso "comprobando mensajes"

        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);//patron singleton, existe sólo una instnacia posible de la clase
        FinServicioReceiver finServicioReceiver = new FinServicioReceiver();
        IntentFilter intentFilter = new IntentFilter("SERV_ALEATORIO_FINAL");//esta es la señal que emite el servicio a su finalización
        localBroadcastManager.registerReceiver(finServicioReceiver, intentFilter);//"programo" que el receptor esté escuchando la señal emitida por el servicio cuando este acabe


        ///simulo que mi servicio ejecuta una función
        try {
            //podría hacer un AsyncTask, para obtenr datos por http
            Thread.sleep(5000);//hacemos que el cálculo, dure 5 segundos, como simulando una interacción HTTP
            this.numero_aleatorio = (int)(Math.random()*100+1);//obtengo un número aleatorio entre 1 y 100

        }catch (Throwable throwable)
        {
            Log.e("ETIQUETA_LOG", "Fallo en la ejecución del servicio", throwable);
        }
        stopForeground(false);
        stopSelf();



        return START_NOT_STICKY;//en caso de eliminar Android este servicio, le decimos que no rearranque automáticamente
    }

    @Override
    public void onDestroy() {
        Log.d("ETIQUETA_LOG", "servicio Num Aleatorio finalizado");
        //emito la señal de fin
        Intent intent_finalizacion = new Intent("SERV_ALEATORIO_FINAL");
        intent_finalizacion.putExtra("NUM_ALEATORIO", this.numero_aleatorio);
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);//emite una señal dentro de los límites de aplicación
        localBroadcastManager.sendBroadcast(intent_finalizacion);

        super.onDestroy();
    }

    //BInded servive, fuera del temario
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }
}