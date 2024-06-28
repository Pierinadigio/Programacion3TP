package tpe;
import java.util.*;

import tpe.utils.CSVReader;

	/**Estrategia de Backtracking
	La estrategia de backtracking explora exhaustivamente todas las posibles asignaciones de tareas a los procesadores. 
	En cada paso, se verifica si una tarea puede ser asignada a un procesador sin violar las restricciones. 
	Si todas las tareas han sido asignadas, se evalúa la calidad de la solución obtenida y se actualiza la mejor asignación si es necesario. 
	El algoritmo continúa probando todas las combinaciones posibles, deshaciendo cada asignación (backtrack) para probar la siguiente posibilidad.
	Esta estrategia garantiza encontrar la solución óptima, pero puede ser computacionalmente costosa debido a la exploración exhaustiva de todas las combinaciones posibles, 
	especialmente cuando el número de tareas y procesadores es grande. 
	La métrica de "estados generados" proporciona una medida del costo computacional de la solución.
	**/

public class Backtracking {
	private List<MyTask> tareas;
    private List<Processor> procesadores;
    private Map<Processor, List<MyTask>> solucion;
    private int mejorTiempo;
    private int tiempoMaxNoRefrigerado;
    private int estadosGenerados;
    private int maxCriticas;
   
    public Backtracking(String pathProcesadores, String pathTareas, int tiempoMaxNoRefrigerado) {
    	CSVReader reader = new CSVReader();
		reader.readProcessors(pathProcesadores);
		reader.readTasks(pathTareas);
		this.tareas = new ArrayList<>(reader.getTasks().values());
		this.procesadores = new ArrayList<>(reader.getProcessors().values());
        this.tiempoMaxNoRefrigerado = tiempoMaxNoRefrigerado;
        this.mejorTiempo = Integer.MAX_VALUE;
        this.solucion = new HashMap<>();
        this.estadosGenerados = 0;
        this.maxCriticas = 2;
       
    }
    
    public Map<Processor, List<MyTask>> asignarTareasBacktraking() {
    	Map<Processor, List<MyTask>> asignacionActual = new HashMap<>();
    	for (Processor p : procesadores) {
        	asignacionActual.put(p, new ArrayList<>());
        }
       _asignarTareasBacktraking(asignacionActual, 0);
            return solucion;
    }

    private void _asignarTareasBacktraking(Map<Processor, List<MyTask>> asignacionActual, int index) {
        
        if (index == tareas.size()) {
            int tiempoActual = calcularTiempoMaximo(asignacionActual);
            if (tiempoActual < mejorTiempo) {
                mejorTiempo = tiempoActual;
                solucion = copiarAsignacion(asignacionActual);
            }
        } else {
            MyTask tarea = tareas.get(index);
            for (Processor p : procesadores) {
                if (esValida(tarea, asignacionActual, p) ){
                    asignacionActual.get(p).add(tarea);
                    estadosGenerados++; 
<<<<<<< HEAD
                    _asignarTareasBacktraking(asignacionActual, index + 1);
                    asignacionActual.get(p).remove(asignacionActual.get(p).size() - 1);
                }
=======
                    _asignarTareasBacktraking(asignacionActual, tareas);
                    asignacionActual.get(p).remove(asignacionActual.get(p).size() - 1);
                }
<<<<<<< HEAD
        int countCritica = 0;
        int tiempoTotal = 0;
        for (MyTask t : asignacion.get(p)) {
            if (t.isCritica()) {
                countCritica++;
            }
            tiempoTotal += t.getTiempoEjecucion();
        }
        if (tarea.isCritica() && countCritica >= maxCriticas ) {
            return false;
        }
            return false;
        }
        return true;
    }    
=======
        int tiempoTotal = p.getTiempoTotalEjecucion(asignacion.get(p));
    
         if (tarea.isCritica() && p.hasCriticalTask(maxCriticas) ) {
             return false;
         }
         if (!p.isRefrigerado() && (tiempoTotal + tarea.getTiempoEjecucion() > tiempoMaxNoRefrigerado)) {
             return false;
         }
         return true;
     }    
>>>>>>> 58d387803bde6e3ff4b11f589d820e5b76d782f7
    public int calcularTiempoMaximo(Map<Processor, List<MyTask>> asignacion) {
        int maxTiempo = 0;
        for (Map.Entry<Processor, List<MyTask>> entry : asignacion.entrySet()) {
           	int totalTiempo = entry.getKey().getTiempoTotalEjecucion(entry.getValue());
        	  if (totalTiempo > maxTiempo) {
            	maxTiempo = totalTiempo;
            }
        }
        return maxTiempo;
    }
 
    public int getEstadosGenerados() {
        return estadosGenerados;
    }

    public int getMejorTiempo() {
        return mejorTiempo;
    }
}

