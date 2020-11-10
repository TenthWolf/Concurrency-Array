package create;

import java.util.Random;

public class Again extends Thread {

	/**
	 * class will calculate random numbers between 1 and 10
	 * 
	 * @param args - array of strings which stores arguments passed by command line
	 *             while starting a program
	 */
	public static void main(String[] args) {
		Random rand = new Random();
		int[] arr = new int[200000000];

		for (int i = 0; i < 11; i++) {
			arr[i] = rand.nextInt();
		}

		long time = System.currentTimeMillis();

		Transfer.single(arr);
		System.out.println("Single: " + (System.currentTimeMillis() - time));
		time = System.currentTimeMillis();
		System.out.println(time + "mls");

		System.out.println();

		Transfer.getSum(arr);
		System.out.println("Parallel: " + (System.currentTimeMillis() - time));
		System.out.println(time + "mls");

	}
}
