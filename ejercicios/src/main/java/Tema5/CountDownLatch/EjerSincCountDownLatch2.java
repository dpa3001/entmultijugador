package Tema5.CountDownLatch;

import java.util.concurrent.CountDownLatch;

public class EjerSincCountDownLatch2 {

	private CountDownLatch sD = new CountDownLatch(2);
	private CountDownLatch sB = new CountDownLatch(1);
	private CountDownLatch sC = new CountDownLatch(1);
	private CountDownLatch sE = new CountDownLatch(1);
	private CountDownLatch sH = new CountDownLatch(1);
	private CountDownLatch sG = new CountDownLatch(1);

	private void println(String mensaje) {
		System.out.println(mensaje);
	}

	public void p1() {
		try {
			println("A");
			sB.await();

			println("B");
			sC.await();

			println("C");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void p2() {
		try {
			sD.await();
			println("D");
			sE.await();
			println("E");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void p3() {
		try {
			println("F");
			sG.await();
			
			println("G");
			
			sH.await();
			println("H");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void exec() {
		new Thread(() -> p1()).start();
		new Thread(() -> p2()).start();
		new Thread(() -> p3()).start();
	}

	public static void main(String[] args) {
		new EjerSincCountDownLatch2().exec();
	}

}
