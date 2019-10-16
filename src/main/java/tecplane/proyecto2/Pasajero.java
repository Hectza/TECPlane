/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tecplane.proyecto2;

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
    private String opinion;

    public Pasajero(String nombre, String fechaNacimiento, long pasaporte, String lugarOrigen, String lugarDestino, int condicion) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.pasaporte = pasaporte;
        this.lugarOrigen = lugarOrigen;
        this.lugarDestino = lugarDestino;
        this.condicion = condicion;
    }
    
    public void darOpinion(String pOpinion){
        this.opinion = pOpinion;
    }
    
    public String getNumAsiento(){
        String codigo = "";
        switch (this.condicion) {
            case 1:
                codigo = codigo + "D";
                break;
            case 2:
                codigo = codigo + "O";
                break;
            case 3:
                codigo = codigo + "P";
                break;
            default:
                codigo = codigo + "E";
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
    
}
