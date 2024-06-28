package tpe;
import java.util.*;
import tpe.utils.CSVReader;

/**
 * La estrategia Greedy utilizada en este problema se basa en asignar cada tarea al procesador que causará el menor incremento 
 * en el tiempo total de ejecución, asegurando que se cumplan las restricciones de criticidad y refrigeración. 
 * Este enfoque proporciona una solución rápida y razonablemente buena, aunque no garantiza la solución óptima global.

 */
public class Greedy {
    private List<MyTask> tareas;
    private List<Processor> procesadores;
    private Map<Processor, List<MyTask>> solucion;
    private int tiempoMaxNoRefrigerado;
    private int candidatosConsiderados;
    private int mejorTiempo;
<<<<<<< HEAD
=======
    private int maxCriticas;
>>>>>>> 58d387803bde6e3ff4b11f589d820e5b76d782f7

    public Greedy(String pathProcesadores, String pathTareas, int tiempoMaxNoRefrigerado) {
        CSVReader reader = new CSVReader();
        reader.readProcessors(pathProcesadores);
        reader.readTasks(pathTareas);
        this.procesadores = new ArrayList<>(reader.getProcessors().values());
        this.tareas = reader.getTareasPorPrioridad().getTasksInPriorityOrder();
        this.tiempoMaxNoRefrigerado = tiempoMaxNoRefrigerado;
        this.candidatosConsiderados = 0;
<<<<<<< HEAD
=======
        this.maxCriticas = 2;
>>>>>>> 58d387803bde6e3ff4b11f589d820e5b76d782f7
        this.mejorTiempo = Integer.MAX_VALUE;
        this.solucion = new HashMap<>();
        for (Processor p : procesadores) {
            solucion.put(p, new ArrayList<>());
        }
    }

    public Map<Processor, List<MyTask>> asignarTareasGreedy() {
        for (MyTask task : tareas) {
            Processor bestProcessor = null;
            int minIncrement = Integer.MAX_VALUE;

          for (Processor processor : procesadores) {
            	if (esValida(processor, task, tiempoMaxNoRefrigerado)) {
                    int originalTime = processor.getTiempoTotalEjecucion();
                    processor.addTask(task);
                    int newTime = processor.getTiempoTotalEjecucion();
                    processor.removeTask(task);

                    int increment = newTime - originalTime;
                    if (increment < minIncrement) {
                        minIncrement = increment;
                        bestProcessor = processor;
                    }
                }
            }

            if (bestProcessor != null) {
                bestProcessor.addTask(task);
                solucion.get(bestProcessor).add(task);
                candidatosConsiderados++;
            }
        }

        int maxExecutionTime = calculateMaxExecutionTime(solucion);
        if (maxExecutionTime < mejorTiempo) {
            mejorTiempo = maxExecutionTime;
            solucion = cloneProcessors(solucion);
        }

        return solucion;
    }

    private boolean esValida(Processor processor, MyTask task, int X) {
        if (!processor.isRefrigerado() && processor.getTiempoTotalEjecucion() + task.getTiempoEjecucion() > X) {
            return false;
        }
<<<<<<< HEAD
        if (task.isCritica() && processor.hasCriticalTask()) {
=======
        if (task.isCritica() && processor.hasCriticalTask(maxCriticas)) {
>>>>>>> 58d387803bde6e3ff4b11f589d820e5b76d782f7
            return false;
        }
        return true;
    }

    private int calculateMaxExecutionTime(Map<Processor, List<MyTask>> asignacion) {
        int maxTime = 0;
        for (Processor processor : asignacion.keySet()) {
            int tiempo = 0;
            for (MyTask task : asignacion.get(processor)) {
                tiempo += task.getTiempoEjecucion();
            }
            if (tiempo > maxTime) {
                maxTime = tiempo;
            }
        }
        return maxTime;
    }

    private Map<Processor, List<MyTask>> cloneProcessors(Map<Processor, List<MyTask>> processors) {
        Map<Processor, List<MyTask>> clonedProcessors = new HashMap<>();
        for (Processor processor : processors.keySet()) {
            List<MyTask> clonedTasks = new ArrayList<>(processors.get(processor));
            clonedProcessors.put(processor, clonedTasks);
        }
        return clonedProcessors;
    }

    public int getCandidatosConsiderados() {
        return candidatosConsiderados;
    }

    public int getMejorTiempo() {
        return mejorTiempo;
    }
}
