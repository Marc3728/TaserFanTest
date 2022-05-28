package com.example.taserfantest;

public class Patinete extends VehiculoCompleto {
    private double tamano;
    private int ruedas;

    public Patinete(String matricula, Double preciohora, String marca, String descripcion, String color, Double bateria, String fecha, String estado, String tipocarnet, double tamano, int ruedas) {
        super(matricula, preciohora, marca, descripcion, color, bateria, fecha, estado, tipocarnet);
        this.tamano = tamano;
        this.ruedas = ruedas;
    }

    public double getTamano() {
        return tamano;
    }

    public void setTamano(double tamano) {
        this.tamano = tamano;
    }

    public int getRuedas() {
        return ruedas;
    }

    public void setRuedas(int ruedas) {
        this.ruedas = ruedas;
    }
}
