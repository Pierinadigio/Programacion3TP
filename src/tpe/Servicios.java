package tpe;

import java.util.*;
import tpe.utils.*;;


public class Servicios {
	private HashMap<String, MyTask> tasks;
	private List<MyTask> criticalTasks;
    private List<MyTask> nonCriticalTasks;
	private ArbolTareas tareasPorPrioridad;
		
	public Servicios(String pathProcesadores, String pathTareas) {
		CSVReader reader = new CSVReader();
		reader.readProcessors(pathProcesadores);
		reader.readTasks(pathTareas);
		this.tasks = reader.getTasks();
		this.criticalTasks = reader.getCriticalTasks();
        this.nonCriticalTasks = reader.getNonCriticalTasks();
		this.tareasPorPrioridad = reader.getTareasPorPrioridad();
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
	 	  
	 
		  
	
}
	
