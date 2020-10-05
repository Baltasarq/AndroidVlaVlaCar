package org.uvigo.esei.dm.vlavlacar.core;

import android.util.Log;

public class CalculadoraViaje {
    public enum TipoCarretera { Autopista, Nacional, Comarcal };
    private final char[] StrTipoCarretera = { 'A', 'N', 'C' };
    private final int[] VelocidadTipoCarretera = { 100, 80, 50 };

    public CalculadoraViaje()
    {
        this.minutos = 0;
        this.km = 0;
        this.consumo = 8.0;
        this.tipoCarretera = TipoCarretera.Autopista;
    }

    public int getMinutos()
    {
        return minutos;
    }

    public void setMinutos(int minutos)
    {
        this.minutos = minutos;
    }

    public void setTiempo(int horas, int minutos)
    {
        this.setMinutos( ( horas * 60 ) + minutos );
    }

    public int getKm()
    {
        return km;
    }

    public void setKm(int km)
    {
        this.km = km;
    }

    public TipoCarretera getTipoCarretera()
    {
        return tipoCarretera;
    }

    public void setTipoCarretera(TipoCarretera tipoCarretera)
    {
        this.tipoCarretera = tipoCarretera;
    }

    public void setTipoCarretera(char tipo) throws IllegalArgumentException
    {
        int numTipo = 0;

        tipo = Character.toUpperCase( tipo );

        for(; numTipo < StrTipoCarretera.length; ++numTipo) {
            if ( StrTipoCarretera[ numTipo ] == tipo ) {
                break;
            }
        }

        if ( numTipo >= StrTipoCarretera.length ) {
            String mensaje = "tipo de carretera no encontrado: " + tipo;

            Log.e( "CalculadoraViaje", mensaje );
            throw new IllegalArgumentException( mensaje );
        }

        this.tipoCarretera = TipoCarretera.values()[ numTipo ];
    }

    public double getConsumo()
    {
        return consumo;
    }

    public void setConsumo(double consumo)
    {
        this.consumo = consumo;
    }

    public double calculaConsumoTotal()
    {
        return ( this.getKm() / 100 ) * this.getConsumo();
    }

    public int calculaTiempo()
    {
        double velocidad = VelocidadTipoCarretera[ this.tipoCarretera.ordinal() ];

        return (int) Math.round( ( this.getKm() / velocidad ) * 60.0 );
    }

    private int minutos;
    private int km;
    private double consumo;
    private TipoCarretera tipoCarretera;
}
