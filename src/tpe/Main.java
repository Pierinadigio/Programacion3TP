package tpe;
import java.util.*;


public class Main {

    public static void main(String[] args) {
    //----------------------PRIMERA PARTE TP---------------------------------------------------------------//	
       Servicios serv = new Servicios ("./src/tpe/datasets/Procesadores.csv","./src/tpe/datasets/Tareas.csv" );
      
       //Servicio1
       MyTask tarea5 = serv.servicio1("T5");
       System.out.println("\nServicio1:   Tarea:  " + datosTarea(tarea5));
   
       
       //Servicio2
      List<MyTask> lista = serv.servicio2(true);
      System.out.println("\nServicio2: Lista de tareas Criticas/No Criticas\n" + listadoTareas(lista));
      
      //Servicio3
      List<MyTask> tareasPorPrioridad = serv.servicio3(50,80);
      System.out.println("\nServicio3: Tareas por PRIORIDAD \n" + listadoTareas(tareasPorPrioridad));
     
      
    //----------------------PRIMERA PARTE TP---------------------------------------------------------------//
      
      //Servicio4
      int tiempoMaxNoRefrigerado = 200;
      System.out.println("\nServicio 4: Backtracking: Solucion Obtenida");
      serv.servicio4(tiempoMaxNoRefrigerado);
	  

      
	 //Servicio5
	  System.out.println("\nServicio 5: Greedy: Solucion Obtenida");
	  serv.servicio5(tiempoMaxNoRefrigerado);
	 
     
    }
 //--------------------------PARA IMPRIMIR---------------------git---------------------------------------//
    
    
    public static String datosTarea (MyTask tarea) {
	 	return tarea.getDatosTarea();
	 }
    
    
    public static String listadoTareas (List<MyTask> lista){
	 StringBuilder listado = new StringBuilder();
	 	for (MyTask tarea : lista) {
            listado.append(tarea.getDatosTarea()).append("\n");
        }

        return listado.toString();
	 }
    
    
}