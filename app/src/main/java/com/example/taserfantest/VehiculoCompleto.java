package com.example.taserfantest;

public abstract class VehiculoCompleto {
    private String matricula;
    private Double preciohora;
    private String marca;
    private String descripcion;
    private String color;
    private Double bateria;
    private String fecha;
    private String estado;
    private String tipocarnet;

    public VehiculoCompleto(String matricula, Double preciohora, String marca, String descripcion, String color, Double bateria, String fecha, String estado, String tipocarnet) {
        this.matricula = matricula;
        this.preciohora = preciohora;
        this.marca = marca;
        this.descripcion = descripcion;
        this.color = color;
        this.bateria = bateria;
        this.fecha = fecha;
        this.estado = estado;
        this.tipocarnet = tipocarnet;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Double getPreciohora() {
        return preciohora;
    }

    public void setPreciohora(Double preciohora) {
        this.preciohora = preciohora;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getBateria() {
        return bateria;
    }

    public void setBateria(Double bateria) {
        this.bateria = bateria;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipocarnet() {
        return tipocarnet;
    }

    public void setTipocarnet(String tipocarnet) {
        this.tipocarnet = tipocarnet;
    }
}
