package com.example.taserfantest;

public class Empleado {
    private String DNI;
    private String nombre;
    private String apellidos;
    private String email;
    private String password;

    public Empleado(String DNI, String nombre, String apellidos,String email,String password) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getDNI() {
        return DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                ", DNI='" + DNI + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                '}';
    }
}
