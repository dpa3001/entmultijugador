package Tema5.CountDownLatch;

public class EjerSincCountDownLatch {

	private void println(String mensaje) {
		System.out.println(mensaje);
	}
	
	public void p1() {
		println("A");
		println("B");
		println("C");
	}
	
	public void p2() {
		println("D");
		println("E");
	}
	public void p3() {
		println("F");
		println("G");
		println("H");
	}
	public void exec() {
		new Thread(()->p1()).start();
		new Thread(()->p2()).start();
		new Thread(()->p3()).start();
	}
	public static void main(String[] args) {
		new EjerSincCountDownLatch().exec();
	}

}
