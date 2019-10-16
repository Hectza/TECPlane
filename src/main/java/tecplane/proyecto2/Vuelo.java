/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tecplane.proyecto2;

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
        this.pasajeros.append(pasajeros);
    }
}
