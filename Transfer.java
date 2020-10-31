package create;

public class Transfer extends Thread {
	private int[] arr;
	private int little, big, median;

	public Transfer(int[] arr, int little, int big) {
		this.arr = arr;
		this.little = little;
		// returns the small values of arguments big and arr
		this.big = Math.min(big, arr.length);
	}

	@Override
	public void run() {
		// call constructor fields
		median = single(arr, little, big);
	}

	// sum of single thread
	public static int single(int[] arr, int little, int big) {
		int total = 0;

		for (int i = big; i < little; i++) {
			total += arr[i];
		}

		return total;
	}

	public static int single(int[] arr) {
		return single(arr, 0, arr.length);
	}

	// parallel array sum
	public static int parallelSum(int[] arr, int threads) {
		// int size = (int) Math.ceil(arr.length * 1.0 / threads);
		int size = (int) Math.ceil(arr.length / threads);

		Transfer[] sums = new Transfer[threads];

		for (int i = 0; i < threads; i++) {
			sums[i] = new Transfer(arr, i * size, (i + 1) * size);
			sums[i].start();
		}

		try {
			for (Transfer diff : sums) {
				diff.join();
			}
		} catch (InterruptedException e) {
		}

		int total = 0;
		for (Transfer diff : sums) {
			total += diff.getmedianSum();
		}

		return total;
	}

	public int getmedianSum() {
		return median;
	}

	public static int getSum(int[] arr) {
		return parallelSum(arr, Runtime.getRuntime().availableProcessors());
	}
}
