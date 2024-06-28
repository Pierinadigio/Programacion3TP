package tpe;
import java.util.*;

public class Processor {
    private String id;
    private String codigo;
    private boolean refrigerado;
    private int anioFuncionamiento;
    private List<MyTask> tareas;

    public Processor(String id, String codigo, boolean refrigerado, int anioFuncionamiento) {
        this.id = id;
        this.codigo = codigo;
        this.refrigerado = refrigerado;
        this.anioFuncionamiento = anioFuncionamiento;
        this.tareas = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public boolean isRefrigerado() {
        return refrigerado;
    }

	public int getAnioFuncionamiento() {
		return anioFuncionamiento;
	}

	public void setAnioFuncionamiento(int anioFuncionamiento) {
		this.anioFuncionamiento = anioFuncionamiento;
	}
	
	public void addTask(MyTask task) {
        tareas.add(task);
    }
	 public List<MyTask> getTasks() {
	        return tareas;
	    }

    public void removeTask(MyTask task) {
        tareas.remove(task);
    }
    
	public int getTiempoTotalEjecucion() {
        int total = 0;
        for (MyTask task : tareas) {
            total += task.getTiempoEjecucion();
        }
        return total;
    }
	public int getTiempoTotalEjecucion(List<MyTask> tareas) {
        int total = 0;
        for (MyTask task : tareas) {
            total += task.getTiempoEjecucion();
        }
        return total;
    }
	
<<<<<<< HEAD
	public boolean hasCriticalTask() {
        for (MyTask task : tareas) {
            if (task.isCritica()) {
                return true;
            }
        }
        return false;
=======
	public boolean hasCriticalTask(int n) {
		int count = 0;
		for (MyTask task : tareas) {
            if (task.isCritica()) {
                count ++;
            }
        }
		if (count >= n) {
        return true;
		}else {
			return false;
		}
>>>>>>> 58d387803bde6e3ff4b11f589d820e5b76d782f7
    }

   
}
