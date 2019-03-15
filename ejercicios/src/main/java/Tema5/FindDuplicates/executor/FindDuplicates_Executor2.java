package Tema5.FindDuplicates.executor;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FindDuplicates_Executor2 {

	//Genaramos un Executor Service con la política fija
	private ExecutorService executor = Executors.newFixedThreadPool(5);
	
	private ConcurrentMap<String, String> duplicates = new ConcurrentHashMap<>();

	public void findDuplicates(File root) {
		if (root.isDirectory()) {
			for (File file : root.listFiles()) {
				if (file.isDirectory()) {
					findDuplicates(file);
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
		new FindDuplicates_Executor2().exec();
	}
}