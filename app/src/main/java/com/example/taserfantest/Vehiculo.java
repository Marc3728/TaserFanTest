package com.example.taserfantest;

public class Vehiculo {
    private String modelo;
    private String matricula;
    private String estado;
    private String color;
    private TipoVehiculo tipo;

    public Vehiculo(String modelo, String matricula, String estado, String color, TipoVehiculo tipo) {
        this.modelo = modelo;
        this.matricula = matricula;
        this.estado = estado;
        this.color = color;
        this.tipo = tipo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public TipoVehiculo getTipo() {
        return tipo;
    }

    public void setTipo(TipoVehiculo tipo) {
        this.tipo = tipo;
    }
}
