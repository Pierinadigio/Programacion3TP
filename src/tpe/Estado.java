package tpe;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.HashMap;

public class Estado {
    private HashMap<String, Processor> procesadores;
    private Integer tiempoFinalEjecucion;
    private Integer estadosgenerados;
    
    public Estado(HashMap<String, Processor> procesadores){
        this.procesadores = procesadores;
        this.tiempoFinalEjecucion = null;
        this.estadosgenerados = null;
    }
    
    //Constructor para poder guardar la mejor solucion y evitar que los cambios realizados en el estado que se pasa como parametro, modifique
    //la mejor solución.
    
    public Estado(Estado estado) {
        this.procesadores = new HashMap<>();
        for (Entry<String, Processor> entry : estado.procesadores.entrySet()) {
            this.procesadores.put(entry.getKey(), new Processor(entry.getValue()));
        }
        this.tiempoFinalEjecucion = estado.getTiempoFinalEjecucion();  
    }
    
    
    public Integer getEstadosGenerados() {
		return estadosgenerados;
	}
	public void incrementarEstados() {
		if(this.estadosgenerados == null) {
			this.estadosgenerados = 1;
		}else {
		this.estadosgenerados++;
		}
	}
	
    public Integer getTiempoFinalEjecucion() {
		return tiempoFinalEjecucion;
	}

	public void setTiempoFinalEjecucion(Integer tiempoFinalEjecucion) {
		this.tiempoFinalEjecucion = tiempoFinalEjecucion;
	}

	public HashMap<String, Processor> getProcesadores() {
		return new HashMap<String, Processor>(procesadores);
	}

	public Iterator<String> iteratorProcesadores(){
        return procesadores.keySet().iterator();        
    }

    public void addTarea(String idProcesador, MyTask tarea){
        procesadores.get(idProcesador).asignarTarea(tarea);
        Integer tiempoProcesador = procesadores.get(idProcesador).getTiempoEjecucion();
        if (this.tiempoFinalEjecucion == null || tiempoProcesador > this.tiempoFinalEjecucion){
            this.tiempoFinalEjecucion = tiempoProcesador;
        }
    }

    public void removeTarea(String idProcesador, Integer tiempoFinalAnterior){
        procesadores.get(idProcesador).removeLastTarea();
        this.tiempoFinalEjecucion = tiempoFinalAnterior ;
    }

    public int countCriticas(String procesador) { 
    	Processor p = procesadores.get(procesador);
    	return p.cantidadCriticas();  
    }
    
    public boolean esRefrigerado(String procesador) {
    	Processor p = procesadores.get(procesador);
    	return p.isRefrigerado();
    }
    
    public int getTiempoProcesador(String procesador) {
    	Processor p = procesadores.get(procesador);
    	return p.getTiempoEjecucion();
    }
    
}

