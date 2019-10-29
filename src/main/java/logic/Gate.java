/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import javax.swing.JOptionPane;

/**
 *
 * @author hecto
 */
public class Gate {
    public int identificador;
    public Vuelo vueloActual;
    public LQueue vuelosPendientes = new LQueue();
    private LQueue colaEconomica = new LQueue();
    private LQueue colaOro = new LQueue();
    private LQueue colaPlatino = new LQueue();
    private LQueue colaEspecial = new LQueue();
    private boolean boarding = false;
    private int atendidos = 0;
    public static int atendidosE = 0;
    public static int atendidosP = 0;
    public static int atendidosO = 0;
    public static int atendidosD = 0;
    private MaxHeap maxHeapE = new MaxHeap(99);
    private MaxHeap maxHeapP = new MaxHeap(99);
    private MaxHeap maxHeapO = new MaxHeap(99);
    private MaxHeap maxHeapD = new MaxHeap(99);
    public static String tipoEstructuraE = "Cola de Prioridad";
    public static String tipoEstructuraP = "Cola de Prioridad";
    public static String tipoEstructuraO = "Cola de Prioridad";
    public static String tipoEstructuraD = "Cola de Prioridad";

    public Gate(int identificador) {
        this.identificador = identificador;
    }
    
    private void agregarEconomica(Pasajero pasajero){
        this.colaEconomica.enqueue(pasajero);
        if (tipoEstructuraD.equals("Max Heap")){
            maxHeapD.insert(1);
        }
    }
    
    private void agregarOro(Pasajero pasajero){
        this.colaOro.enqueue(pasajero);
        if (tipoEstructuraO.equals("Max Heap")){
            maxHeapO.insert(2);
        }
    }
    
    private void agregarPlatino(Pasajero pasajero){
        this.colaPlatino.enqueue(pasajero);
        if (tipoEstructuraP.equals("Max Heap")){
            maxHeapP.insert(3);
        }
    }
    
    private void agregarEspecial(Pasajero pasajero){
        this.colaEspecial.enqueue(pasajero);
        if (tipoEstructuraE.equals("Max Heap")){
            maxHeapE.insert(4);
        }
    }
    
    public void agregarPasajero(Pasajero pasajero){
        switch (pasajero.getCondicion()) {
            case 1:
                agregarEconomica(pasajero);
                break;
            case 2:
                agregarOro(pasajero);
                break;
            case 3:
                agregarPlatino(pasajero);
                break;
            default:
                agregarEspecial(pasajero);
                break;
        }
    }

    public int getIdentificador() {
        return this.identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }
    
    public void asignarVuelo(){
        if (this.vuelosPendientes.size() > 0) {
            Vuelo porAgregar = (Vuelo)this.vuelosPendientes.dequeue();
            this.vueloActual = porAgregar;
        }
        else{
            System.out.println("No existe un vuelo para asignar ");
        }
    }
    
    public Vuelo getVuelo(){
        return this.vueloActual;
    }
    
    public LQueue getVuelosPendientes(){
        return this.vuelosPendientes;
    }
    
    public void atender(){
        Pasajero atendido;
        if (this.vueloActual == null){
            JOptionPane.showMessageDialog(null, "No hay vuelo asociado a la puerta");
        }
        else{
            if (tipoEstructuraE.equals("Cola de Prioridad")){
                if (colaEspecial.size() > 0){
                    this.atendidos++;
                    atendidosE++;
                    atendido = (Pasajero) colaEspecial.dequeue();
                    this.vueloActual.agregarPasajero(atendido);
                    JOptionPane.showMessageDialog(null, "Ingresa " + atendido.getNombre() + " al asiento " + atendido.getAsiento() + " para el vuelo " + this.vueloActual.getNumero() + " con destino a " + this.vueloActual.getDestino());
                    NewMain.ingresosEspeciales.append(atendido);
                    return;
                }
            }
            else{
                if (colaEspecial.size() > 0){
                    this.atendidos++;
                    atendidosE++;
                    atendido = (Pasajero) colaEspecial.dequeue();
                    int max = this.maxHeapE.extractMax();
                    if (max != 0){
                        this.vueloActual.agregarPasajero(atendido);
                        JOptionPane.showMessageDialog(null, "Ingresa " + atendido.getNombre() + " al asiento " + atendido.getAsiento() + " para el vuelo " + this.vueloActual.getNumero() + " con destino a " + this.vueloActual.getDestino());
                        NewMain.ingresosEspeciales.append(atendido);
                        return;
                    }
                    else{
                        return;
                    }
                }
            }
            if (tipoEstructuraP.equals("Cola de Prioridad")){
                if (colaPlatino.size() > 0) {
                    this.atendidos++;
                    atendidosP++;
                    atendido = (Pasajero) colaPlatino.dequeue();
                    this.vueloActual.agregarPasajero(atendido);
                    JOptionPane.showMessageDialog(null, "Ingresa " + atendido.getNombre() + " al asiento " + atendido.getAsiento() + " para el vuelo " + this.vueloActual.getNumero() + " con destino a " + this.vueloActual.getDestino());
                    return;
                }
            }
            else{
                if (colaPlatino.size() > 0){
                    this.atendidos++;
                    atendidosP++;
                    atendido = (Pasajero) colaPlatino.dequeue();
                    int max = this.maxHeapP.extractMax();
                    if (max != 0){
                        this.vueloActual.agregarPasajero(atendido);
                        JOptionPane.showMessageDialog(null, "Ingresa " + atendido.getNombre() + " al asiento " + atendido.getAsiento() + " para el vuelo " + this.vueloActual.getNumero() + " con destino a " + this.vueloActual.getDestino());
                        return;
                    }
                    else{
                        return;
                    }
                }
            }
            if (tipoEstructuraO.equals("Cola de Prioridad")){
                if (colaOro.size() > 0) {
                    this.atendidos++;
                    atendidosO++;
                    atendido = (Pasajero) colaOro.dequeue();
                    this.vueloActual.agregarPasajero(atendido);
                    JOptionPane.showMessageDialog(null, "Ingresa " + atendido.getNombre() + " al asiento " + atendido.getAsiento() + " para el vuelo " + this.vueloActual.getNumero() + " con destino a " + this.vueloActual.getDestino());
                }
            }
            else{
                if (colaOro.size() > 0){
                    this.atendidos++;
                    atendidosO++;
                    atendido = (Pasajero) colaOro.dequeue();
                    int max = this.maxHeapO.extractMax();
                    if (max != 0){
                        this.vueloActual.agregarPasajero(atendido);
                        JOptionPane.showMessageDialog(null, "Ingresa " + atendido.getNombre() + " al asiento " + atendido.getAsiento() + " para el vuelo " + this.vueloActual.getNumero() + " con destino a " + this.vueloActual.getDestino());
                        return;
                    }
                    else{
                        return;
                    }
                }
            }
            if (tipoEstructuraD.equals("Cola de Prioridad")){
                if (colaEconomica.size() > 0) {
                    this.atendidos++;
                    atendidosD++;
                    atendido = (Pasajero) colaEconomica.dequeue();
                    this.vueloActual.agregarPasajero(atendido);
                    JOptionPane.showMessageDialog(null, "Ingresa " + atendido.getNombre() + " al asiento " + atendido.getAsiento() + " para el vuelo " + this.vueloActual.getNumero() + " con destino a " + this.vueloActual.getDestino());
                }
            }
            else{
                if (colaEconomica.size() > 0){
                    this.atendidos++;
                    atendidosD++;
                    atendido = (Pasajero) colaEconomica.dequeue();
                    int max = this.maxHeapD.extractMax();
                    if (max != 0){
                        this.vueloActual.agregarPasajero(atendido);
                        JOptionPane.showMessageDialog(null, "Ingresa " + atendido.getNombre() + " al asiento " + atendido.getAsiento() + " para el vuelo " + this.vueloActual.getNumero() + " con destino a " + this.vueloActual.getDestino());
                        return;
                    }
                    else{
                        return;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Vuelo listo para cerrar");
        }
    }
    
    public boolean isBoarding(){
        return this.boarding;
    }
    
    public void isBoarding(boolean isIt){
        this.boarding = isIt;
    }
    
    public int getAtendidos(){
        return this.atendidos;
    }
    
    public void cerrar(){
        NewMain.colaDeSalida.enqueue(this.vueloActual);
        
        this.colaEconomica.clear();
        this.colaEspecial.clear();
        this.colaOro.clear();
        this.colaPlatino.clear();
        this.boarding = false;
        
        if (this.vuelosPendientes.size() > 0){
            this.vueloActual = (Vuelo)this.vuelosPendientes.dequeue();
        }
        else{
            this.vueloActual = null;
        }
    }

    public LQueue getColaEconomica() {
        return colaEconomica;
    }

    public LQueue getColaOro() {
        return colaOro;
    }

    public LQueue getColaPlatino() {
        return colaPlatino;
    }

    public LQueue getColaEspecial() {
        return colaEspecial;
    }
    
    public void setEstructuars(int E, int P, int O, int D){
        if (E == 1){
            tipoEstructuraE = "Cola de Prioridad";
        }
        else{
            tipoEstructuraE = "Max Heap";
        }
        if (P == 1){
            tipoEstructuraP = "Cola de Prioridad";
        }
        else{
            tipoEstructuraP = "Max Heap";
        }
        if (O == 1){
            tipoEstructuraO = "Cola de Prioridad";
        }
        else{
            tipoEstructuraO = "Max Heap";
        }
        if (D == 1){
            tipoEstructuraD = "Cola de Prioridad";
        }
        else{
            tipoEstructuraD = "Max Heap";
        }
    }

    public String getTipoEstructuraE() {
        return tipoEstructuraE;
    }

    public String getTipoEstructuraP() {
        return tipoEstructuraP;
    }

    public String getTipoEstructuraO() {
        return tipoEstructuraO;
    }

    public String getTipoEstructuraD() {
        return tipoEstructuraD;
    }
    
    public String getNextClient(){
        if (this.colaEspecial.size() > 0){
            Pasajero pasajero = (Pasajero)this.colaEspecial.firstNode().getElement();
            return pasajero.getNombre();
        }
        else if (this.colaPlatino.size() > 0){
            Pasajero pasajero = (Pasajero)this.colaPlatino.firstNode().getElement();
            return pasajero.getNombre();
        }
        else if (this.colaOro.size() > 0){
            Pasajero pasajero = (Pasajero)this.colaOro.firstNode().getElement();
            return pasajero.getNombre();
        }
        else if (this.colaEconomica.size() > 0){
            Pasajero pasajero = (Pasajero)this.colaEconomica.firstNode().getElement();
            return pasajero.getNombre();
        }
        else{
            return "Nadie";
        }
    }
    
    public String getAsientos(){
        String asientos = "";
        if (this.colaEspecial.size() > 0){
            Node temp = this.colaEspecial.firstNode();
            while(temp != null){
                Pasajero pasajero = (Pasajero)temp.getElement();
                if(!asientos.equals("")){
                    asientos = asientos + ", " + pasajero.getAsiento();
                }
                else{
                    asientos = pasajero.getAsiento();
                }
                temp = temp.getNext();
            }
        }
        if (this.colaPlatino.size() > 0){
            Node temp = this.colaPlatino.firstNode();
            while(temp != null){
                Pasajero pasajero = (Pasajero)temp.getElement();
                if(!asientos.equals("")){
                    asientos = asientos + ", " + pasajero.getAsiento();
                }
                else{
                    asientos = pasajero.getAsiento();
                }
                temp = temp.getNext();
            }
        }
        if (this.colaOro.size() > 0){
            Node temp = this.colaOro.firstNode();
            while(temp != null){
                Pasajero pasajero = (Pasajero)temp.getElement();
                if(!asientos.equals("")){
                    asientos = asientos + ", " + pasajero.getAsiento();
                }
                else{
                    asientos = pasajero.getAsiento();
                }
                temp = temp.getNext();
            }
        }
        if (this.colaEconomica.size() > 0){
            Node temp = this.colaEconomica.firstNode();
            while(temp != null){
                Pasajero pasajero = (Pasajero)temp.getElement();
                if(!asientos.equals("")){
                    asientos = asientos + ", " + pasajero.getAsiento();
                }
                else{
                    asientos = pasajero.getAsiento();
                }
                temp = temp.getNext();
            }
        }
        if (asientos.equals("")){
            return "No hay personas en la cola";
        }
        return asientos;
    }

    public static int getAtendidosE() {
        return atendidosE;
    }

    public static int getAtendidosP() {
        return atendidosP;
    }

    public static int getAtendidosO() {
        return atendidosO;
    }

    public static int getAtendidosD() {
        return atendidosD;
    }
    
    
}
