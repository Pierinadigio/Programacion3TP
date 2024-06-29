package tpe;
import java.util.*;

import tpe.utils.CSVReader;

	/**Estrategia de Backtracking
	La estrategia de backtracking explora todas las posibles combinaciones de asignación de tareas a procesadores, 
	utilizando el objeto Estado para mantener el estado actual de la asignación. 
	Se aplican condiciones de poda para evitar explorar soluciones que no pueden conducir a una mejora sobre la mejor solución 
	encontrada hasta el momento (mejorSolucion). El objetivo es encontrar la asignación de tareas que minimice el tiempo total de ejecución, 
	cumpliendo con las restricciones definidas..
	**/

public class Backtracking {
	private LinkedList<MyTask> tareas;
	private HashMap<String, Processor> processors;
    private Solucion mejorSolucion;
    private int tiempoMaxNoRefrigerado;
    private int maxCriticas;
   
    public Backtracking(HashMap<String, Processor> processors, HashMap<String, MyTask>tareas, Integer tiempoMaxNoRefrigerado,int maxCriticas) {
    	this.tareas = new LinkedList<MyTask>(tareas.values());
		this.processors = processors;
		this.tiempoMaxNoRefrigerado = tiempoMaxNoRefrigerado;
        this.mejorSolucion = null;;
        this.maxCriticas = maxCriticas;
       
    }
    
    public Solucion asignarTareasBacktraking() {
        Estado estadoInicial = new Estado(processors);
        asignarTareasBacktraking(estadoInicial, tareas);
        if (mejorSolucion == null) {
            return null;
        }
        this.mejorSolucion.setEstadosGenerados(estadoInicial.getEstadosGenerados());
        return mejorSolucion; 
    }

    private void asignarTareasBacktraking(Estado estado, LinkedList<MyTask> tareasDisponibles) {
        if(tareas.size()== 0){ //Es una posible solucion, ahora tengo que ir guardando la mejor solucion
        	if(this.mejorSolucion == null || estado.getTiempoFinalEjecucion()< this.mejorSolucion.getTiempoFinalEjecucion()) {
        		this.mejorSolucion = new Solucion(estado);
        	}
        }
        else{
            Iterator<String> it = estado.iteratorProcesadores();  
            while(it.hasNext()) {
            	String procesador = it.next(); 
            	MyTask t = tareas.removeFirst();             		
            	Integer tiempoFinalAnterior = estado.getTiempoFinalEjecucion(); 
				estado.addTarea(procesador,t); //Se actualiza el estado
				//Poda
				if((estado.countCriticas(procesador)<=maxCriticas) && 
                (this.mejorSolucion == null || (estado.getTiempoFinalEjecucion()< this.mejorSolucion.getTiempoFinalEjecucion())) && 
                ((!estado.esRefrigerado(procesador) && estado.getTiempoProcesador(procesador)<= tiempoMaxNoRefrigerado) || estado.esRefrigerado(procesador))) {
					estado.incrementarEstados();
					asignarTareasBacktraking(estado, tareasDisponibles); 
				}
				tareas.addFirst(t);
				estado.removeTarea(procesador,tiempoFinalAnterior);
            }
        }
    }
}

