/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tecplane.proyecto2;

import java.util.TimerTask;

/**
 *
 * @author hecto
 */
public class Main {
    public static LinkedList puertas = new LinkedList();
    public static LQueue vuelosEnCurso = new LQueue();
    public int minTime;
    public int maxTime;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Pasajero pasajero = new Pasajero("Hector", "07/07/99", 305280832, "CR", "SLV", 3);
        System.out.println(pasajero.getNumAsiento());
    }
    
    public static void agregarVueloEnCurso(Vuelo pVuelo){
        vuelosEnCurso.enqueue(pVuelo);
    }
    
    public void asignarPuertas(int cantidad){
        if(puertas != null){
            if(puertas.getSize() > 0){
                print("Ya existen puertas asignadas");
                return;
            }
            else{
                for(int i = 1; i <= cantidad; i++){
                puertas.append(new Gate(i));
                }
            }
        }
    }
    
    public void agregarVuelo(int numeroVuelo, String origen, String destino, int numeroPuerta){
        Node temp = puertas.getHead();
        while (temp != null) {
            Gate puertaTemp = (Gate) temp.getElement();
            if (puertaTemp.getIdentificador() == numeroPuerta) {
                break;
            }
            temp = temp.getNext();
        }
        Gate tempPuerta = (Gate)temp.getElement();
        tempPuerta.vuelosPendientes.enqueue(new Vuelo(numeroVuelo, origen, destino));
    }
    
    public void defTiempoSalida(int pMinTime, int pMaxTime){
        minTime = pMinTime;
        maxTime = pMaxTime;
    }
    
    public void print(String string){
        System.out.println(string);
    }
    public void print(int integer){
        System.out.println(integer);
    }
    
    public void atender(int numeroPuerta){
        Node temp = puertas.getHead();
        while (temp != null){
            Gate puertaTemp = (Gate)temp.getElement();
            if(puertaTemp.getIdentificador() == numeroPuerta){
                puertaTemp.atender();
                return;
            }
            temp = temp.getNext();
        }
        System.out.println("La puerta no existe");
    }
    
    public void cerrarPuerta(int numeroPuerta){
        Node temp = puertas.getHead();
        while (temp != null) {
            Gate puertaTemp = (Gate) temp.getElement();
            if (puertaTemp.getIdentificador() == numeroPuerta) {
                puertaTemp.cerrar();
                return;
            }
            temp = temp.getNext();
        }
        System.out.println("La puerta no existe");
    }
        
    private static class MyTimeTask extends TimerTask{
        @Override
        public void run()
        {
            //write your code here
        }
    }
}

