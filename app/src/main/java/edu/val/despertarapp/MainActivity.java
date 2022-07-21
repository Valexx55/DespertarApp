package edu.val.despertarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //?estoy un telefono XIAOMI?? (que tiene capado el inicio autómatico por defecto)
        //si es que sí, le saco la actividad de ajustes, para que el usuario me autorice.
        solicitarInicioAutomático();
    }

    private void solicitarInicioAutomático ()
    {
        String fabricante = Build.MANUFACTURER;//obtengo el fabricante

        if (fabricante.equalsIgnoreCase("xiaomi"))
        {
            //estoy en un telefono xiamoi
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity" ));
            startActivity (intent);
        }
    }
}