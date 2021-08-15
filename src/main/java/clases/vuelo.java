package clases;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class vuelo {
    String idVuelo;
    int asientosTotales = 180;
    String origen;
    String destino;
    int tarifa;
    String dia;
    String horario;
    estadoState estado;
    List<asiento> asientos = new ArrayList<>();
    int delay;


    public vuelo(String id, int asientosTotales, String origen, String destino,int tarifa, String dia, String horario,  int delay){
        this.idVuelo = id;
        this.asientosTotales=asientosTotales;
        this.origen=origen;
        this.destino=destino;
        this.tarifa=tarifa;
        this.dia=dia;
        this.horario=horario;
        this.estado = this.setearEstado();
        this.crearAsientos(asientosTotales);
        this.delay = delay;
    }

    public estadoState setearEstado(){
        estadoState estado;
        if(delay >0){
            estado = new delayed(this);
        }else{
            estado = new onTime(this);
        }
        return estado;
    }

    public void crearAsientos(int asientosTotales){
        for(int i = 1 ; i <= asientosTotales ; i++){
            asiento asiento = new asiento(i);
            asientos.add(asiento);
        }
    }

    public String getHorario(){
        return this.horario;
    }

    public String getFecha(){
        return this.dia;
    }

    public asiento buscarAsiento(int numAsiento){
        return asientos.stream().filter(unIt -> unIt.getNumAsiento() == numAsiento).collect(Collectors.toList()).get(0);
    }

    public List<asiento> asientosDisponibles(){
        return asientos.stream().filter(unAsiento -> unAsiento.estaDisponible()).collect(Collectors.toList());
    }

    public estadoState validarEstado(){
        return estado;
    }

    public void cambiarEstado(estadoState nuevoEstado){this.estado = nuevoEstado;
    }

    /*
    public void restarAsientos(int cantidad){
        asientosOcupados -= cantidad;
    }

    public void sumarAsientos(int cantidad){
        asientosOcupados += cantidad;
    }
*/

    public void resetAsientos(){
        asientos.forEach(unA -> unA.desocuparAsiento());
    }

    public boolean cumpleDestinos(String destinoInicial, String destinoFinal){
        return (origen == destinoInicial) && (destino == destinoFinal);
    }

    public String getIdVuelo() {
        return idVuelo;
    }

    public estadoState getEstado(){
        return estado;
    }
}