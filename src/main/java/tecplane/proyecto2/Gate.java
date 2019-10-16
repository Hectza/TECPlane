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
public class Gate {
    public int identificador;
    Vuelo vueloActual;
    public LQueue vuelosPendientes = new LQueue();
    private LQueue colaEconomica = new LQueue();
    private LQueue colaOro = new LQueue();
    private LQueue colaPlatino = new LQueue();
    private LQueue colaEspecial = new LQueue();

    public Gate(int identificador) {
        this.identificador = identificador;
    }
    
    public void agregarEconomica(Pasajero pasajero){
        this.colaEconomica.enqueue(pasajero);
    }
    
    public void agregarOro(Pasajero pasajero){
        this.colaOro.enqueue(pasajero);
    }
    
    public void agregarPlatino(Pasajero pasajero){
        this.colaPlatino.enqueue(pasajero);
    }
    
    public void agregarEspecial(Pasajero pasajero){
        this.colaEspecial.enqueue(pasajero);
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }
    
    public void asignarVuelo(){
        if (this.vuelosPendientes.size() > 0) {
            this.vueloActual = (Vuelo)this.vuelosPendientes.dequeue();
        }
        else{
            System.out.println("No existe un vuelo para asignar ");
        }
    }
    
    public void atender(){
        if (this.vueloActual == null){
            System.out.println("No hay vuelo asociado a la puerta");
        }
        else{
            if (colaEspecial.size() > 0){
                vueloActual.agregarPasajero((Pasajero) colaEspecial.dequeue());
            }
            else if (colaPlatino.size() > 0) {
                vueloActual.agregarPasajero((Pasajero) colaPlatino.dequeue());
            }
            else if (colaOro.size() > 0) {
                vueloActual.agregarPasajero((Pasajero) colaOro.dequeue());
            }
            else if (colaEconomica.size() > 0) {
                vueloActual.agregarPasajero((Pasajero) colaEconomica.dequeue());
            }
            else{
                System.out.println("Vuelo listo para cerrar");
            }
        }
    }
    
    public void cerrar(){
        Main.agregarVueloEnCurso(this.vueloActual);
        if (this.vuelosPendientes.size() > 0){
            this.vueloActual = (Vuelo)this.vuelosPendientes.dequeue();
        }
        else{
            this.vueloActual = null;
        }
    }
}
