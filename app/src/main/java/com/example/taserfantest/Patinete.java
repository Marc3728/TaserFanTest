package com.example.taserfantest;

public class Patinete extends VehiculoCompleto {
    private String tipo;

    public Patinete(String matricula, Double preciohora, String marca, String descripcion, String color, Double bateria, String fecha, String estado, String tipocarnet, String tipo) {
        super(matricula, preciohora, marca, descripcion, color, bateria, fecha, estado, tipocarnet);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
