package tpe;

import java.util.ArrayList;
import java.util.List;

public class Nodo {
    int priority;
    List<MyTask> tasks;
    Nodo left, right;

    public Nodo(int priority) {
        this.priority = priority;
        this.tasks = new ArrayList<>();
        this.left = this.right = null;
    }

    public void addTask(MyTask task) {
        tasks.add(task);
    }

	public List<MyTask> getTasks() {
		return new ArrayList<>(tasks);
	}

	
    
}

