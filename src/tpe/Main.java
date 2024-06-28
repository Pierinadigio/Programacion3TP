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
     
      
<<<<<<< HEAD
    //----------------------PRIMERA PARTE TP---------------------------------------------------------------//
      
      //Servicio4
      int tiempoMaxNoRefrigerado = 200;
      System.out.println("\nServicio 4: Backtracking: Solucion Obtenida");
      serv.servicio4(tiempoMaxNoRefrigerado);
	  

      
	 //Servicio5
	  System.out.println("\nServicio 5: Greedy: Solucion Obtenida");
	  serv.servicio5(tiempoMaxNoRefrigerado);
	 
     
    }
 //--------------------------PARA IMPRIMIR------------------------------------------------------------//
=======
     //------------------------SEGUNDA PARTE TP ---------------------------------------------------//
      int tiempoMaxNoRefrigerado = 60;

      Backtracking backtraking = new Backtracking("./src/tpe/datasets/Procesadores.csv","./src/tpe/datasets/Tareas.csv" , tiempoMaxNoRefrigerado);
      Map<Processor, List<MyTask>> resultadoBack = backtraking.asignarTareasBacktraking();
      
      System.out.println("\nBacktracking: Solucion Obtenida");
      imprimirAsignacion(resultadoBack);
      System.out.println("Tiempo máximo de ejecución Backtracking: " + backtraking.getMejorTiempo());
      System.out.println("Estados generados: " + backtraking.getEstadosGenerados());
      
      Greedy greedy = new Greedy ("./src/tpe/datasets/Procesadores.csv","./src/tpe/datasets/Tareas.csv" , tiempoMaxNoRefrigerado);
      Map<Processor, List<MyTask>> resultadogreedy = greedy.asignarTareasGreedy();
      
      System.out.println("\nGreedy: Solucion Obtenida");
      imprimirAsignacion(resultadogreedy);
      System.out.println("Tiempo máximo de ejecución Greedy: " + greedy.getMejorTiempo());
      System.out.println("Candidatos considerados: " + greedy.getCandidatosConsiderados());
   
    }
    
    
    //--------------------------PARA IMPRIMIR------------------------------------------------------------//
>>>>>>> 58d387803bde6e3ff4b11f589d820e5b76d782f7
    
    
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
    
    
<<<<<<< HEAD
=======
    public static void imprimirAsignacion(Map<Processor, List<MyTask>> asignacion) {
       for (Map.Entry<Processor, List<MyTask>> entry : asignacion.entrySet()) {
        Processor procesador = entry.getKey();
            List<MyTask> tareas = entry.getValue();
            System.out.println("Procesador: " + procesador.getId());
            
           if (tareas.isEmpty()) {
                System.out.println("  No tiene tareas asignadas.");
           } else {
               System.out.print("  Tareas asignadas: ");
               for (MyTask tarea : tareas) {
                    System.out.print(tarea.getId() + " ");
               }
                System.out.println(); 
            }
           System.out.println(); 
       }
   }
    
	
    
>>>>>>> 58d387803bde6e3ff4b11f589d820e5b76d782f7
}