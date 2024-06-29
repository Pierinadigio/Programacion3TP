package tpe;

import java.util.*;
import tpe.utils.*;;


public class Servicios {
	private HashMap<String, MyTask> tasks;
	private List<MyTask> criticalTasks;
    private List<MyTask> nonCriticalTasks;
	private ArbolTareas tareasPorPrioridad;
	private HashMap<String, Processor> processors;
	private List<MyTask> tareas;
	
	private final int maxCriticas = 2;
		
	public Servicios(String pathProcesadores, String pathTareas) {
		CSVReader reader = new CSVReader();
		reader.readProcessors(pathProcesadores);
		reader.readTasks(pathTareas);
		this.tasks = reader.getTasks();
		this.criticalTasks = reader.getCriticalTasks();
        this.nonCriticalTasks = reader.getNonCriticalTasks();
		this.tareasPorPrioridad = reader.getTareasPorPrioridad();
		this.processors = reader.getProcessors();
		this.tareas = reader.getTareasPorPrioridad().getTasksInPriorityOrder();
		
		
	}
  /**---------------------------------CONSTRUCTOR-----------------------------------------------*//**
 * Lectura de archivos CSV: La complejidad temporal de la lectura de archivos depende del tamaño de los archivos y del rendimiento de la operación de lectura de archivos 
  * en el sistema operativo y en la biblioteca utilizada. En términos generales, si consideramos que la lectura de los archivos tiene una complejidad lineal con respecto 
  * al tamaño de los archivos, podríamos considerar la complejidad como O(m + n), donde 'm' es el tamaño del archivo de procesadores y 'n' es el tamaño del archivo de tareas.

   Sumando todas las complejidades, el constructor realiza las siguientes operaciones:
   * Instanciar CSVReader: O(1)
   * Leer procesadores: O(m)
   * Leer tareas: O(n)
   * Copiar tasks: O(n)
   * Copiar criticalTasks: O(n)
   * Copiar nonCriticalTasks: O(n)
   * Asignar tareasPorPrioridad: O(1)
   * Por lo tanto, la complejidad total del constructor de Servicios es O(m+n), donde m es el número de procesadores y n es el número de tareas.
    
 */
	
	/**-------------------------------SERVICIO 1-----------------------------------------------*/
	 
	public MyTask servicio1(String ID) {
	 	return tasks.get(ID);
	 }
	 /**
	  * Devuelve una tarea basada en su ID. La complejidad temporal de esta operación es O(1), 
	  * ya que, dado el ID, la búsqueda en un HashMap tiene complejidad constante.
	  */
	 
	 
	 /**-------------------------------SERVICIO 2-----------------------------------------------*/
	 
	 public List<MyTask> servicio2(boolean esCritica) {
	        return new ArrayList<>(esCritica ? this.criticalTasks : this.nonCriticalTasks);
	    }
	 
	  /**
	  *El método servicio2 tiene una complejidad computacional O(n), donde n es el número de elementos 
	  *en la lista criticalTasks o nonCriticalTasks.
	  *La operación esCritica ? this.criticalTasks : this.nonCriticalTasks simplemente selecciona una referencia a una de las dos listas, 
	  *lo cual es una operación de tiempo constante, O(1).
	  */
	 
	 /**-------------------------------SERVICIO 3-----------------------------------------------*/
	  	 
	 public List<MyTask> servicio3(int prioridadInferior, int prioridadSuperior) {
	        return tareasPorPrioridad.getTasksInPriorityRange(prioridadInferior, prioridadSuperior);
	    }
	 /**
	  * Para buscar un rango en un arbol, la complejidad de encontrar los nodos con valores dentro de un rango específico puede ser 
	  * O(h+k), donde h es la altura del árbol y k es el número de nodos en el rango.
	  * En el peor caso, si el árbol está desbalanceado, la altura h puede ser O(n), donde n es el número de nodos en el árbol.
	  * En el mejor caso, si el árbol está perfectamente balanceado, la altura h es O(logn).
	  */
	 	  
	 
	 /**-------------------------------SERVICIO 4-----------------------------------------------*/  
	 
	 public void servicio4(int tiempoMaxNoRefrigerado){   //Servicio que se encarga de asignar las tareas a los procesadores usando backtracking
			
			if(servicio2(true).size() > processors.size() * maxCriticas) { //Si la cantidad de tareas criticas es mayor a la cantidad de procesadores, no se puede asignar
				System.out.println("No se puede asignar las tareas a los procesadores");
			}
			else {
				Backtracking back = new Backtracking(processors, tasks, tiempoMaxNoRefrigerado, maxCriticas);

				Solucion solucion = back.asignarTareasBacktraking();	
				if(solucion == null)	//si viene null, hay alguna tarea que no se pudo asignar a ningún procesador (por ejemplo, todos procesadores no refrigerados y tarea con tiempoEj > tiempo dado por el usuario)
					System.out.println("No hay Solucion");
				else
				System.out.println(solucion);
			}
		}
	 
	 /**-------------------------------SERVICIO 5-----------------------------------------------*/
	 public void servicio5(int tiempoMaxNoRefrigerado){   //Servicio que se encarga de asignar las tareas a los procesadores usando greedy
			if(servicio2(true).size() > processors.size() * maxCriticas) { //Si la cantidad de tareas criticas es mayor a la cantidad de procesadores, no se puede asignar
				System.out.println("No se puede asignar las tareas a los procesadores");
			}
			else {
				Greedy greedy = new Greedy(processors, tareas, tiempoMaxNoRefrigerado, maxCriticas);
				Solucion solucion = greedy.asignarTareasGreedy();
				if(solucion == null)  //si viene null, hay alguna tarea que no se pudo asignar a ningún procesador (por ejemplo, todos procesadores no refrigerados y tarea con tiempoEj > tiempo dado por el usuario)
					System.out.println("No hay Solucion");
				else
				System.out.println(solucion);
			}
		}


}
	
