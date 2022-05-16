package com.example.taserfantest;

public class Vehicle {
    private String modelo;
    private String matricula;
    private String estado;
    private String color;

    public Vehicle(String modelo, String matricula, String estado, String color) {
        this.modelo = modelo;
        this.matricula = matricula;
        this.estado = estado;
        this.color = color;
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
}
