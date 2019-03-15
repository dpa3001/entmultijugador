package Tema5.FindDuplicates.TADs;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class FindDuplicates {

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
	
	public void exec() {
		new Thread(()->findDuplicates(new File("X:\\DirA"))).start();
		new Thread(()->findDuplicates(new File("X:\\DirB"))).start();
	}

	public static void main(String[] args) {
		new FindDuplicates().exec();
	}
}