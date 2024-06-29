package tpe;
import java.util.*;

public class Processor {
    private String id;
    private String codigo;
    private boolean refrigerado;
    private Integer anio;
    private Integer tiempoEjecucion;
    private LinkedList<MyTask> tareas;


    public Processor(String id, String codigo, boolean refrigerado, Integer anio) {
        this.id = id;
        this.codigo = codigo;
        this.refrigerado = refrigerado;
        this.anio = anio;
        this.tiempoEjecucion = 0;
        this.tareas = new LinkedList<>();
    }

    public Processor(Processor procesador) {
        this.id = procesador.id;
        this.codigo = procesador.codigo;
        this.refrigerado = procesador.refrigerado;
        this.anio = procesador.anio;
        this.tiempoEjecucion = procesador.tiempoEjecucion;
        this.tareas = new LinkedList<>(procesador.tareas);
    }
    
    public Iterator<MyTask> iterarTareas(){
    	return tareas.iterator();
    }
    public LinkedList<MyTask>getTareas(){
    	return new LinkedList<MyTask>(tareas);
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean isRefrigerado() {
        return refrigerado;
    }

    public void setRefrigerado(boolean refrigerado) {
        this.refrigerado = refrigerado;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }
   
    public int cantidadCriticas() {
    	int cant = 0;
    	for(int i = 0; i<tareas.size();i++) {
    		MyTask t = tareas.get(i);
    		if(t.isCritica()) {
    			cant++;	
    		}
    	}
    	return cant;
    }

    public void asignarTarea(MyTask tarea){
        tareas.add(tarea);
        tiempoEjecucion += tarea.getTiempoEjecucion();
    }

    public Integer getTiempoEjecucion() {
        return tiempoEjecucion;
    }


    public void removeLastTarea(){
        MyTask eliminada = tareas.removeLast(); 
        tiempoEjecucion -= eliminada.getTiempoEjecucion();
    }
}
