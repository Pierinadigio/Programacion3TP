package tpe;
import java.util.*;
import tpe.utils.CSVReader;

/**
 * La estrategia Greedy utilizada en este problema se basa en asignar cada tarea al procesador que causará el menor incremento 
 * en el tiempo total de ejecución, asegurando que se cumplan las restricciones de criticidad y refrigeración. 
 * El algoritmo intenta maximizar la eficiencia seleccionando la tarea más grande disponible y asignándola al procesador más adecuado en cada iteración, 
 * buscando así una solución rápida aunque no necesariamente óptima globalmente.
 * Este enfoque proporciona una solución rápida y razonablemente buena.
 */
public class Greedy {
    private LinkedList<MyTask> tareas;
    private HashMap<String, Processor> processors;
    private Integer tiempoMaxNoRefrigerado;
    private Solucion solucion;
    private int maxCriticas;

    public Greedy(HashMap<String, Processor> processors, List<MyTask> tareas, Integer tiempoMaxNoRefrigerado, int maxCriticas) {
        this.tareas= new LinkedList<>(tareas);
        this.tiempoMaxNoRefrigerado = tiempoMaxNoRefrigerado;
        this.solucion = null;
        this.processors = processors;
        this.maxCriticas = maxCriticas;
    }

    public Solucion asignarTareasGreedy() {
        Estado estadoInicial = new Estado(processors);
        return asignarTareasGreedy(estadoInicial, tareas);
    }
    
    private Solucion asignarTareasGreedy(Estado estado, List<MyTask> tareas){
        while(!tareas.isEmpty()){
            MyTask tarea = tareas.remove(0);
            Processor procesador = getBestProcessor(estado, tarea); 
            if (procesador == null){
                return null;
            }           
            estado.addTarea(procesador.getId(), tarea);
        }                                                 
        this.solucion = new Solucion(estado);
        this.solucion.setEstadosGenerados(estado.getEstadosGenerados());
        return solucion;
    }

    private Processor getBestProcessor(Estado estado, MyTask tarea) {
        HashMap<String,Processor> procesadores = estado.getProcesadores();
        Processor bestProcessor = null;
        Iterator<String> it = estado.iteratorProcesadores();
        while(it.hasNext()){
            String id = it.next();
            estado.incrementarEstados(); 
            Processor processor = procesadores.get(id);
            if (puedeProcesarTarea(estado, id, tarea)){
                if (bestProcessor == null || processor.getTiempoEjecucion() < bestProcessor.getTiempoEjecucion()){
                	bestProcessor = processor;
                }
            }
        }
        return bestProcessor;        
    }

    private boolean puedeProcesarTarea(Estado estado, String idProcesador, MyTask tarea){
    	int cant = estado.countCriticas(idProcesador);
        return (cant<maxCriticas || (!tarea.isCritica() && cant == maxCriticas)) && 
                    (estado.esRefrigerado(idProcesador) || 
                    (!estado.esRefrigerado(idProcesador) && estado.getTiempoProcesador(idProcesador) + tarea.getTiempoEjecucion()<= tiempoMaxNoRefrigerado ));
    }
}

