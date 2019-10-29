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
public class Salida {
    LQueue colaEspecial = new LQueue();
    LQueue colaSalida = new LQueue();
    int puestosDeAtencion = 1;
    int atendidos = 0;
    long tiempoAtencion;
    long sumaTiempoAtencion;
    
    long tiempoEsperaE = 0;
    long atendidosE = 0;
    long tiempoEsperaP = 0;
    long atendidosP = 0;
    long tiempoEsperaO = 0;
    long atendidosO = 0;
    long tiempoEsperaD = 0;
    long atendidosD = 0;
    
    public void otorgarSalida(){
        for (int i = 0; i < this.puestosDeAtencion; i++){
            if(colaEspecial.size() > 0){
                colaEspecial.dequeue();
                GUI.Encuesta encuesta = new GUI.Encuesta();
                encuesta.setVisible(true);
                this.atendidos++;
                this.atendidosE++;
                this.tiempoEsperaE = this.tiempoEsperaE + (System.currentTimeMillis() - this.tiempoAtencion);
                this.sumaTiempoAtencion = this.sumaTiempoAtencion + (System.currentTimeMillis() - this.tiempoAtencion);
                this.tiempoAtencion = System.currentTimeMillis();
                return;
            }
            if(colaSalida.size() > 0){
                Pasajero pasajero = (Pasajero)colaSalida.dequeue();
                if (pasajero.getCondicion() == 1){
                    this.atendidosD++;
                    this.tiempoEsperaD = this.tiempoEsperaD + (System.currentTimeMillis() - this.tiempoAtencion);
                }
                else if(pasajero.getCondicion() == 2){
                    this.atendidosO++;
                    this.tiempoEsperaO = this.tiempoEsperaO + (System.currentTimeMillis() - this.tiempoAtencion);
                }
                else if(pasajero.getCondicion() == 3){
                    this.atendidosP++;
                    this.tiempoEsperaP = this.tiempoEsperaP + (System.currentTimeMillis() - this.tiempoAtencion);
                }
                GUI.Encuesta encuesta = new GUI.Encuesta();
                encuesta.setVisible(true);
                this.atendidos++;
                this.sumaTiempoAtencion = this.sumaTiempoAtencion + (System.currentTimeMillis() - this.tiempoAtencion);
                this.tiempoAtencion = System.currentTimeMillis();
                return;
            }
                System.out.println("No hay nadie en la cola de salida");
        }
    }
    
    public void enqueue(Vuelo vuelo){
        LinkedList listaPasajeros = vuelo.getPasajeros();
        for(Node temp = listaPasajeros.getHead().getNext(); temp != null; temp = temp.getNext()){
            Pasajero pasajeroTemp = (Pasajero)temp.getElement();
            if ( pasajeroTemp.getCondicion() == 4){
                colaEspecial.enqueue(pasajeroTemp);
            }
            else{
                colaSalida.enqueue(pasajeroTemp);
            }
        }
        this.tiempoAtencion = System.currentTimeMillis();
    }
    
    public void cambiarPuestos(int numPuestos){
        this.puestosDeAtencion = numPuestos;
    }
    
    public int getPuestos(){
        return this.puestosDeAtencion;
    }
    
    public LQueue getColaEspecial(){
        return this.colaEspecial;
    }
    
    public LQueue getColaSalida(){
        return this.colaSalida;
    }
    
    public int getAtendidos(){
        return this.atendidos;
    }
    
    public float getPromedioAtencion(){
        float promedio;
        if (atendidos == 0){
            return 0;
        }
        else{
            promedio = sumaTiempoAtencion / atendidos;
            return promedio/1000;
        }
    }
        
    public float getPromedioAtencionE(){
        float promedio;
        if (atendidosE == 0){
            return 0;
        }
        else{
            promedio = tiempoEsperaE / atendidosE;
            return promedio/1000;
        }
    }
    
    public float getPromedioAtencionP(){
        float promedio;
        if (atendidosP == 0){
            return 0;
        }
        else{
            promedio = tiempoEsperaP / atendidosP;
            return promedio/1000;
        }
    }
    
    public float getPromedioAtencionO(){
        float promedio;
        if (atendidosO == 0){
            return 0;
        }
        else{
            promedio = tiempoEsperaO / atendidosO;
            return promedio/1000;
        }
    }
    
    public float getPromedioAtencionD(){
        float promedio;
        if (atendidosD == 0){
            return 0;
        }
        else{
            promedio = tiempoEsperaD / atendidosD;
            return promedio/1000;
        }
    }
}
