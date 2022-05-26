package com.example.taserfantest;

public class Coche extends VehiculoCompleto {
    private int numplazas;
    private int numpuertas;

    public Coche(String matricula, Double preciohora, String marca, String descripcion, String color, Double bateria, String fecha, String estado, String tipocarnet, int numplazas, int numpuertas) {
        super(matricula, preciohora, marca, descripcion, color, bateria, fecha, estado, tipocarnet);
        this.numplazas = numplazas;
        this.numpuertas = numpuertas;
    }

    public int getNumplazas() {
        return numplazas;
    }

    public void setNumplazas(int numplazas) {
        this.numplazas = numplazas;
    }

    public int getNumpuertas() {
        return numpuertas;
    }

    public void setNumpuertas(int numpuertas) {
        this.numpuertas = numpuertas;
    }
}
