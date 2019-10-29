/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.Random;

/**
 *
 * @author hecto
 */
public class Pasajero {
    private String nombre;
    private String fechaNacimiento;
    private long pasaporte;
    private String lugarOrigen;
    private String lugarDestino;
    private int condicion;
    private String condicionString;
    private String opinion;
    private String asiento;
    private String nacionalidad;

    public Pasajero(String nombre, String nacionalidad, String fechaNacimiento, long pasaporte, String lugarOrigen, String lugarDestino, int condicion) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.pasaporte = pasaporte;
        this.lugarOrigen = lugarOrigen;
        this.lugarDestino = lugarDestino;
        if (condicion == 1){
            boolean decision = new Random().nextBoolean();
            if(decision){
                this.condicion = 4;
            }
            else{
                this.condicion = condicion;
            }
        }
        else{
            this.condicion = condicion;
        }
        this.asiento = getNumAsiento();
        defCondicionString();
    }
    
    public void darOpinion(String pOpinion){
        this.opinion = pOpinion;
    }
    
    private void defCondicionString(){
        switch (this.condicion) {
            case 1:
                this.condicionString = "Económico";
                break;
            case 2:
                this.condicionString = "Oro";
                break;
            case 3:
                this.condicionString = "Platino";
                break;
            default:
                this.condicionString = "Especial";
                break;
        }
    }
    
    private String getNumAsiento(){
        String codigo = "";
        switch (this.condicion) {
            case 1:
                codigo = codigo + "D";
                NewMain.asientosD++;
                break;
            case 2:
                codigo = codigo + "O";
                NewMain.asientosO++;
                break;
            case 3:
                codigo = codigo + "P";
                NewMain.asientosP++;
                break;
            default:
                codigo = codigo + "E";
                NewMain.asientosE++;
                break;
        }
        
        int espacio = new Random().nextInt((3 - 1) + 1) + 1;
        
        switch (espacio) {
            case 1:
                codigo = codigo + "V";
                break;
            case 2:
                codigo = codigo + "C";
                break;
            default:
                codigo = codigo + "P";
                break;
        }
        
        int pos = new Random().nextInt((99 - 1) + 1) + 1;
        
        codigo = codigo + pos;
        
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public long getPasaporte() {
        return pasaporte;
    }

    public String getLugarOrigen() {
        return lugarOrigen;
    }

    public String getLugarDestino() {
        return lugarDestino;
    }

    public int getCondicion() {
        return condicion;
    }

    public String getOpinion() {
        return opinion;
    }

    public String getCondicionString() {
        return condicionString;
    }
    
    public String getAsiento(){
        return this.asiento;
    }
}
