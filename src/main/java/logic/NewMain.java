/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author hecto
 */
public class NewMain {
    public static LinkedList puertas = new LinkedList();
    public static Salida colaDeSalida = new Salida();
    public static int minTime;
    public static int maxTime;
    public static int priorityQueueType;
    public static Timer timer;
    public static LinkedList ingresosEspeciales = new LinkedList();
    public static float acumuladorSentimientos = 0;
    public static int cantidadOpiniones = 0;
    public static int asientosE = 0;
    public static int asientosP = 0;
    public static int asientosO = 0;
    public static int asientosD = 0;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        Analyzer google = new Analyzer();
        try {
            google.analizarOpinion("Prueba para inicializar API");
        } catch (Exception ex) {
            System.out.println("Error API Google");
        }
        GUI.Progra2 interfaz = new GUI.Progra2();
        interfaz.setVisible(true);
    }
    
    public void agregarVueloEnCurso(Vuelo pVuelo){
        colaDeSalida.enqueue(pVuelo);
    }
    
    public static void asignarPuertas(int cantidad){
        List<Country> countries = new ArrayList<Country>();

        // Get ISO countries, create Country object and
        // store in the collection.
        String[] isoCountries = Locale.getISOCountries();
        for (String country : isoCountries) {
            Locale locale = new Locale("es", country);
            String iso = locale.getISO3Country();
            String code = locale.getCountry();
            String name = locale.getDisplayCountry();

            if (!"".equals(iso) && !"".equals(code) && !"".equals(name)) {
                countries.add(new Country(iso, code, name));
            }
        }
        
        if(puertas != null){
            if(puertas.getSize() > 0){
                print("Ya existen puertas asignadas");
                return;
            }
            else{
                for(int i = 1; i <= cantidad; i++){
                Gate puertaNueva = new Gate(i);
                for(int j = 1; j < 5; j++){
                    int randintA = new Random().nextInt((99 - 1) + 1) + 1;
                    int randintB = new Random().nextInt((99 - 1) + 1) + 1;
                    String pOrigen = countries.get(randintA).getName();
                    String pDestino = countries.get(randintB).getName();
                    int pNumero = new Random().nextInt((99 - 1) + 1) + 1;
                    puertaNueva.vuelosPendientes.enqueue(new Vuelo(pNumero, pOrigen, pDestino));
                }
                puertaNueva.vueloActual = (Vuelo)puertaNueva.vuelosPendientes.dequeue();
                puertas.append(puertaNueva);
                }
            }
        }
    }
    
    public static void checkIn(String pNombre, String pNacionalidad, String pFechaNacimiento, long pPasaporte, String pLugarOrigen, String pLugarDestino, int pCondicion){
        Pasajero pasajeroNuevo = new Pasajero (pNombre, pNacionalidad, pFechaNacimiento, pPasaporte, pLugarOrigen, pLugarDestino, pCondicion);
        for(Node temp = puertas.getHead().getNext(); temp != null; temp = temp.getNext()){
            Gate puertaTemp = (Gate)temp.getElement();
            LQueue listaVuelosTemp = puertaTemp.getVuelosPendientes();
            Vuelo vueloActualTemp = puertaTemp.getVuelo();
            if (vueloActualTemp != null){
                if (pLugarOrigen.toLowerCase().equals(vueloActualTemp.getOrigen().toLowerCase()) && pLugarDestino.toLowerCase().equals(vueloActualTemp.getDestino().toLowerCase())){
                    puertaTemp.agregarPasajero(pasajeroNuevo);
                    JOptionPane.showMessageDialog(null, "¡Agregado con éxito! Su asiento es el " + pasajeroNuevo.getAsiento());
                    SMSSender sms = new SMSSender();
                    sms.enviarSMS("Su asiento es el " + pasajeroNuevo.getAsiento());
                    return;
                }
            }
                if (listaVuelosTemp.size() > 0){
                    Node vueloPendienteTemp = listaVuelosTemp.firstNode();
                    while (vueloPendienteTemp != null) {
                        Vuelo vueloPendiente = (Vuelo)vueloPendienteTemp.getElement();
                        if (pLugarOrigen.toLowerCase().equals(vueloPendiente.getOrigen().toLowerCase()) && pLugarDestino.toLowerCase().equals(vueloPendiente.getDestino().toLowerCase())) {
                            JOptionPane.showMessageDialog(null, ("El vuelo "  + vueloPendiente.getNumero() + " " + vueloPendiente.getOrigen() + " > " + vueloPendiente.getDestino() + " aún no se encuentra en la puerta"));
                            return;
                        }
                        vueloPendienteTemp = vueloPendienteTemp.getNext();
                    }
                }
        }
        JOptionPane.showMessageDialog(null, "No existe un vuelo asociado a ese origen y destino");
    }
    
    public static void agregarVuelo(int numeroVuelo, String origen, String destino, int numeroPuerta){
        Node temp = puertas.getHead().getNext();
        while (temp != null) {
            Gate puertaTemp = (Gate)temp.getElement();
            if (puertaTemp.getIdentificador() == numeroPuerta) {
                break;
            }
            temp = temp.getNext();
        }
        if (temp == null){
            JOptionPane.showMessageDialog(null, "No se encuentra la puerta");
            return;
        }
        Gate tempPuerta = (Gate)temp.getElement();
        if (tempPuerta.vueloActual == null){
            tempPuerta.vueloActual = new Vuelo(numeroVuelo, origen, destino);
        }
        else{
            tempPuerta.vuelosPendientes.enqueue(new Vuelo(numeroVuelo, origen, destino));
        }
    }
        
    public static void print(String string){
        System.out.println(string);
    }
    public static void print(int integer){
        System.out.println(integer);
    }
    
    public static void atender(int numeroPuerta){
        Node temp = puertas.getHead().getNext();
        while (temp != null){
            Gate puertaTemp = (Gate)temp.getElement();
            if(puertaTemp.getIdentificador() == numeroPuerta){
                if (puertaTemp.isBoarding()){
                    puertaTemp.atender();
                    return;
                }
                else{
                    JOptionPane.showMessageDialog(null, "La puerta " + puertaTemp.getIdentificador() + " aún no está abordando");
                    return;
                }
            }
            temp = temp.getNext();
        }
        JOptionPane.showMessageDialog(null, "La puerta no existe");
    }

    public static void siguienteVuelo(int numPuerta){
        Node temp = puertas.getHead().getNext();
        while (temp != null) {
            Gate puertaTemp = (Gate) temp.getElement();
            if (puertaTemp.getIdentificador() == numPuerta) {
                puertaTemp.asignarVuelo();
                return;
            }
            temp = temp.getNext();
        }
        System.out.println("La puerta no existe");
    }
    
    public static void abrirAbordaje(int numPuerta){
        Node temp = puertas.getHead().getNext();
        while (temp != null) {
            Gate puertaTemp = (Gate) temp.getElement();
            if (puertaTemp.getIdentificador() == numPuerta) {
                puertaTemp.isBoarding(true);
                return;
            }
            temp = temp.getNext();
        }
        JOptionPane.showMessageDialog(null, "La puerta no existe");
    }
    
    public static void cerrarPuerta(int numeroPuerta){
        Node temp = puertas.getHead().getNext();
        while (temp != null) {
            Gate puertaTemp = (Gate) temp.getElement();
            if (puertaTemp.getIdentificador() == numeroPuerta) {
                puertaTemp.cerrar();
                return;
            }
            temp = temp.getNext();
        }
        JOptionPane.showMessageDialog(null, "La puerta no existe");
    }
    
    public static Gate obtenerPuerta(int numPuerta){
        Node temp = puertas.getHead().getNext();
        while (temp != null) {
            Gate puertaTemp = (Gate) temp.getElement();
            if (puertaTemp.getIdentificador() == numPuerta) {
                return puertaTemp;
            }
            temp = temp.getNext();
        }
        JOptionPane.showMessageDialog(null, "La puerta no existe");
        return null;
    }
    
    public static void abrirSalida(int periodB, int periodA){
        timer = new Timer();
        try{
            timer.purge();
        } catch (Exception ie){
        }
        minTime = periodB;
        maxTime = periodA;
        int period = new Random().nextInt((periodA - periodB) + 1) + periodB;
        period = period*1000;
        timer.schedule(new MyTimeTask(), period, period);
    }
    
    private static class MyTimeTask extends TimerTask{
        
        private boolean isRunning = false;
        
        public void run(){
            this.isRunning = true;
            colaDeSalida.otorgarSalida();
            timer.cancel();
            timer = new Timer();
            int period = new Random().nextInt((maxTime - minTime) + 1) + minTime;
            period = period*1000;
            timer.schedule(new MyTimeTask(), period);
        }
    }
    
    public class notBoardingException extends Exception { 
        public notBoardingException(String errorMessage) {
            super(errorMessage);
        }
    }
    
    public class nonExistentException extends Exception { 
        public nonExistentException(String errorMessage) {
            super(errorMessage);
        }
    }
}

