package tpe;

import java.util.List;

public class MyTask implements Comparable<MyTask>{
	    private String id;
	    private String nombre;
	    private int tiempoEjecucion;
	    private boolean critica;
	    private int nivelPrioridad;

	    public MyTask(String id, String nombre, int tiempoEjecucion, boolean critica, int nivelPrioridad) {
	        this.id = id;
	        this.nombre = nombre;
	        this.tiempoEjecucion = tiempoEjecucion;
	        this.critica = critica;
	        this.nivelPrioridad = nivelPrioridad;
	    }

	    public String getId() {
	        return id;
	    }

	    public boolean isCritica() {
	        return critica;
	    }

	    public int getNivelPrioridad() {
	        return nivelPrioridad;
	    }

		public int getTiempoEjecucion() {
			return tiempoEjecucion ;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		
		public String getDatosTarea() {
	        return "ID: " + id + ", Nombre: " + nombre + ", Tiempo de Ejecución: " + tiempoEjecucion + 
	               ", Crítica: " + critica + ", Nivel de Prioridad: " + nivelPrioridad;
	    }
		public String obtenerListadoDeTareas(List<MyTask> lista) {
	        StringBuilder listado = new StringBuilder();

	        listado.append("Listado Tareas:\n");
	        for (MyTask tarea : lista) {
	            listado.append(tarea.getDatosTarea()).append("\n");
	        }

	        return listado.toString();
	    }
		@Override
	    public int compareTo(MyTask other) {
	        return Integer.compare(this.nivelPrioridad, other.nivelPrioridad);
	    }
		
		

	}