package com.example.taserfantest;

public class LoggedInUserRepository {
    private static Empleado empleado;
    private static LoggedInUserRepository loggedInUserRepository;


    public static LoggedInUserRepository getInstance(){
        if (loggedInUserRepository==null)
            loggedInUserRepository = new LoggedInUserRepository();
        return loggedInUserRepository;
    }
    public void login(Empleado empleado){
        LoggedInUserRepository.empleado = empleado;
    }

    public static Empleado getLogedEmpleado(){
        return empleado;
    }

    public void logout(){
        empleado = null;
    }
}
