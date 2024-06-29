package tpe;

import java.util.ArrayList;
import java.util.List;

public class ArbolTareas {
    private Nodo root;

    public ArbolTareas() {
        this.root = null;
    }

    public void insert(MyTask task) {
        root = insertRec(root, task);
    }

    private Nodo insertRec(Nodo root, MyTask task) {
        if (root == null) {
            root = new Nodo(task.getNivelPrioridad());
            root.addTask(task);
            return root;
        }

        if (task.getNivelPrioridad() < root.priority) {
            root.left = insertRec(root.left, task);
        } else if (task.getNivelPrioridad() > root.priority) {
            root.right = insertRec(root.right, task);
        } else {
            root.addTask(task);  
        }

        return root;
    }

    public List<MyTask> getTasksInPriorityRange(int low, int high) {
        List<MyTask> result = new ArrayList<>();
        getTasksInPriorityRangeRec(root, low, high, result);
        return result;
    }

    private void getTasksInPriorityRangeRec(Nodo root, int low, int high, List<MyTask> result) {
        if (root == null) {
            return;
        }

        if (low < root.priority) {
            getTasksInPriorityRangeRec(root.left, low, high, result);
        }

        if (low <= root.priority && high >= root.priority) {
            result.addAll(root.tasks);
        }

        if (high > root.priority) {
            getTasksInPriorityRangeRec(root.right, low, high, result);
        }
    }
    
    public List<MyTask> getTasksInPriorityOrder() {
    	List<MyTask> result = new ArrayList<>();
        getTasksInPriorityOrderRec(root, result);
        return result;
    }

    private void getTasksInPriorityOrderRec(Nodo node, List<MyTask> result) {
        if (node != null) {
            getTasksInPriorityOrderRec(node.right, result); 
            result.addAll(node.getTasks()); 
            getTasksInPriorityOrderRec(node.left, result); 
            
        }
    }
   
    
}
