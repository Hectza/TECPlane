/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author hecto
 */
public class Vuelo {
    private int numero;
    private String origen;
    private String destino;
    private LinkedList pasajeros = new LinkedList();

    public Vuelo(int numero, String origen, String destino) {
        this.numero = numero;
        this.origen = origen;
        this.destino = destino;
    }
    
    public void agregarPasajero(Pasajero pasajero){
        this.pasajeros.append(pasajero);
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LinkedList getPasajeros() {
        return this.pasajeros;
    }

    public void setPasajeros(LinkedList pasajeros) {
        this.pasajeros = pasajeros;
    }
    
    
}
