package com.example.taserfantest;

public class Moto extends VehiculoCompleto {
    private double velocidadmax;
    private double cilindrada;

    public Moto(String matricula, Double preciohora, String marca, String descripcion, String color, Double bateria, String fecha, String estado, String tipocarnet, double velocidadmax, double cilindrada) {
        super(matricula, preciohora, marca, descripcion, color, bateria, fecha, estado, tipocarnet);
        this.velocidadmax = velocidadmax;
        this.cilindrada = cilindrada;
    }

    public double getVelocidadmax() {
        return velocidadmax;
    }

    public void setVelocidadmax(double velocidadmax) {
        this.velocidadmax = velocidadmax;
    }

    public double getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(double cilindrada) {
        this.cilindrada = cilindrada;
    }
}
