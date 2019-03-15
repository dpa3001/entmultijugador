package Tema5.FindDuplicates.executor;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class FindDuplicates_Executor5 {

	//Genaramos un Executor Service con la política fija
	private ExecutorService executor = Executors.newFixedThreadPool(5);
	
	/*
	 * Hay que utilizar un AtomicInteger para llevar la cuenta de las tareas activas
	 * Podemos hacer que empiece en 1, porque siempre vamos a tener una tarea
	 * this.executor.execute(()->findDuplicates(new File("X:\\"))); 
	 */
	private AtomicInteger numTareasActivas = new AtomicInteger(1);
	
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
					//Incrementamos el número de tareas activas
					this.numTareasActivas.incrementAndGet();
					
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
			/*
			 * Cuando salgamos comprobamos si hay tareas activas
			 * Peroooo antes disminuimos la cuenta de numTareasActivas
			 */
			if (this.numTareasActivas.decrementAndGet()==0){
				this.executor.shutdown();
				System.out.println("Terminando...");
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
		this.executor.execute(()->findDuplicates(new File("C:\\\\Users\\\\DPA\\\\OneDrive - Universidad Rey Juan Carlos\\\\2018-19\\\\Entornos Multijugador\\\\ws"))); 
//		new Thread(()->findDuplicates(new File("X:\\DirA"))).start();
//		new Thread(()->findDuplicates(new File("X:\\DirB"))).start();
	}

	public static void main(String[] args) {
		new FindDuplicates_Executor5().exec();
	}
}