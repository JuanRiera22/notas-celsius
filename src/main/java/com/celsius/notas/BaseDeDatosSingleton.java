package com.celsius.notas;

public class BaseDeDatosSingleton {
    private static BaseDeDatosSingleton instancia;
    private IBaseDeDatos bd;

    private BaseDeDatosSingleton() {
        bd = new basededatossql();
    }

    public static BaseDeDatosSingleton getInstancia() {
        if (instancia == null) {
            instancia = new BaseDeDatosSingleton();
        }

        return instancia;
    }

    public IBaseDeDatos getBd() {
        return bd;
    }
}
