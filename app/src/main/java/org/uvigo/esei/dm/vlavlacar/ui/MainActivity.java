package org.uvigo.esei.dm.vlavlacar.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import org.uvigo.esei.dm.vlavlacar.R;
import org.uvigo.esei.dm.vlavlacar.core.CalculadoraViaje;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.calculadoraViaje = new CalculadoraViaje();

        final ImageButton BT_CALCULA_TIEMPO = this.findViewById( R.id.btCalculaTiempo );
        final ImageButton BT_CALCULA_CONSUMO = this.findViewById( R.id.btCalculaConsumo );

        BT_CALCULA_TIEMPO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.calculaTiempo();
            }
        });

        BT_CALCULA_CONSUMO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.calculaConsumo();
            }
        });

        this.calculadoraViaje = new CalculadoraViaje();
    }

    private void calculaTiempo()
    {
        final EditText ED_HORAS = this.findViewById( R.id.edHoras );
        final EditText ED_MINUTOS = this.findViewById( R.id.edMinutos );

        this.pasaDatosACalculadora();

        int minutos = this.calculadoraViaje.calculaTiempo();
        ED_HORAS.setText( Integer.toString( minutos / 60  ) );
        ED_MINUTOS.setText( Integer.toString( minutos % 60 ) );

        this.calculadoraViaje.setMinutos( minutos );
        this.calculaConsumo();
    }

    private void calculaConsumo()
    {
        final EditText ED_CONSUMO = this.findViewById( R.id.edConsumo );

        this.pasaDatosACalculadora();
        ED_CONSUMO.setText( Double.toString( this.calculadoraViaje.calculaConsumoTotal() ) );
    }

    private void pasaDatosACalculadora()
    {
        final EditText ED_HORAS = this.findViewById( R.id.edHoras );
        final EditText ED_MINUTOS = this.findViewById( R.id.edMinutos );
        final EditText ED_KM = this.findViewById( R.id.edKm );
        final EditText ED_CARRETERA = this.findViewById( R.id.edCarretera );
        final EditText ED_CONSUMO = this.findViewById( R.id.edConsumo );
        String strHoras = ED_HORAS.getText().toString().trim();
        String strMinutos = ED_MINUTOS.getText().toString().trim();
        String strKm = ED_KM.getText().toString().trim();
        String strConsumo = ED_CONSUMO.getText().toString().trim();
        int km;
        int h;
        int m;
        double consumo;

        try {
            km = Integer.parseInt( strKm );
        } catch(NumberFormatException exc) {
            final String mensaje = "convirtiendo a km: " + strKm;

            Log.w( "DatosACalculadora", mensaje );
            Toast.makeText( this, mensaje, Toast.LENGTH_SHORT ).show();
            km = 1;
        }

        try {
            h = Integer.parseInt( strHoras );
        } catch(NumberFormatException exc) {
            final String mensaje = "convirtiendo a horas: " + strHoras;

            Log.w( "DatosACalculadora", mensaje );
            Toast.makeText( this, mensaje, Toast.LENGTH_SHORT ).show();
            h = 1;
        }

        try {
            m = Integer.parseInt( strMinutos );
        } catch(NumberFormatException exc) {
            final String mensaje = "convirtiendo a minutos: " + strMinutos;

            Log.w( "DatosACalculadora", mensaje );
            Toast.makeText( this, mensaje, Toast.LENGTH_SHORT ).show();
            m = 1;
        }

        try {
            consumo = Double.parseDouble( strConsumo );
        } catch(NumberFormatException exc) {
            final String mensaje = "convirtiendo a consumo: " + strConsumo;

            Log.w( "DatosACalculadora", mensaje );
            Toast.makeText( this, mensaje, Toast.LENGTH_SHORT ).show();
            consumo = 8.0;
        }

        this.calculadoraViaje.setTipoCarretera( ED_CARRETERA.getText().toString().trim().charAt( 0 ) );
        this.calculadoraViaje.setTiempo( h, m );
        this.calculadoraViaje.setKm( km );
        this.calculadoraViaje.setConsumo( consumo );
    }

    private CalculadoraViaje calculadoraViaje;
}
