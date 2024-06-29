package tpe;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Solucion {

	private HashMap<String, Processor> processors;
	private Integer tiempoFinalEjecucion;
	private Integer estadosgenerados;
	    
	    public Solucion(Estado estado) {
	    	this.processors = new HashMap<>();
	        for (Entry<String, Processor> entry : estado.getProcesadores().entrySet()) {
	            this.processors.put(entry.getKey(), new Processor(entry.getValue()));
	        }
	        this.tiempoFinalEjecucion = estado.getTiempoFinalEjecucion(); 
			this.estadosgenerados = null;
	    }
	    
	    public Integer getTiempoFinalEjecucion() {
	    	return tiempoFinalEjecucion;
	    }
		
		
		
	    public void setEstadosGenerados(Integer estadosgenerados) {
			this.estadosgenerados = estadosgenerados;
		}

		public String toString() {
	    	String res =  "Tiempo final ejecución: " + tiempoFinalEjecucion + "\nCosto de la solucion: \nEstados/Candidatos: " + estadosgenerados + 
	    				  "\nTareas asignadas:\n";
	    	Iterator<String> it = processors.keySet().iterator();
	    	while(it.hasNext()) {
	    		String idProcesador = it.next();
	    		Processor p = processors.get(idProcesador);
	    		res += " Procesador " + p.getId() + "[ " ;
	    		Iterator<MyTask> itTareas = p.iterarTareas();
	    		while(itTareas.hasNext()) {
	    			MyTask t = itTareas.next();
	    			res += t.getId() + " ";
	    		}
	    		res += "]\n ";
	    	}	
	    	 return res;
	    }
}