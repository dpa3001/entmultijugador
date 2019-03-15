package Tema5.FindDuplicates.executor;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FindDuplicates_Executor4 {

	//Genaramos un Executor Service con la política fija
	private ExecutorService executor = Executors.newFixedThreadPool(5);
	
	private ConcurrentMap<String, String> duplicates = new ConcurrentHashMap<>();

	/*
	 * El problema actual es que solo existe una tarea
	 * No hay nada que genere una tarea nueva.
	 * 
	 * Debemos de mirar dónde se comprueba si hay subdirectorios.
	 * 
	 */
	public void findDuplicates(File root) {
		if (root.isDirectory()) {
			for (File file : root.listFiles()) {
				
				/*
				 * Aquí se comprueba si hay subdirectorio
				 * 
				 * Entonces...En lugar de hacer una llamada recursiva
				 * que se ejecute secuencialmente, deberemos de 
				 * cambiar la siguiente línea de código
				 * 
				 */
				if (file.isDirectory()) {
					//findDuplicates(file);
					this.executor.execute(()->findDuplicates(file));
					/*
					 * Antes se ejecutaba en el mismo hilo una llamada
					 * a método y ahora generamos una tarea nueva. Esta
					 * tarea nueva la colocamos en el executor para que
					 * la ejecute cuando PUEDA en función de su política.
					 */
				} else {
					String path = duplicates.putIfAbsent(file.getName(),file.getAbsolutePath());
					if (path != null) {
						System.out.println("Found duplicate file: " + file.getName());
						System.out.println("    " + path);
						System.out.println("    " + file.getAbsolutePath());
					}
				}
			}
		}
		
		/*
		 * ¿Qué falta ahora? Hay que parar los hilos (parar el executor) 
		 * cuando ya no queden tareas pendientes. 
		 * 
		 *  ¿Cómo podemos llevar la cuenta de las tareas pendientes? Hay diferencias estrategias
		 *  Una podía ser llamar al ExecutorService y sus métodos.
		 *  
		 *  PARECE QUE NO EXISTE NINGÚN MÉTODO QUE TE DIGA LOS HILOS PENDIENTES
		 *  
		 *  Tal vez, sería interesante llevar la cuenta de las tareas activas...¿Cómo?
		 */
	}
	
	/*
	 * Esto de aquí ya no tiene mucho sentido.
	 * 
	 * Ahora el executor debe de ser el encargado de generar los hilos
	 * y ver cómo gestionarlos
	 */
	public void exec() {
		this.executor.execute(()->findDuplicates(new File("X:\\"))); 
//		new Thread(()->findDuplicates(new File("X:\\DirA"))).start();
//		new Thread(()->findDuplicates(new File("X:\\DirB"))).start();
	}

	public static void main(String[] args) {
		new FindDuplicates_Executor4().exec();
	}
}