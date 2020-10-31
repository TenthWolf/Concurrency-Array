package create;

import java.util.Random;

public class Again extends Thread {

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
