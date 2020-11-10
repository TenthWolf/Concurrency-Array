package create;

/**
 * class will calculate a parallel array sum
 * 
 * @author Rodolfo Puig
 * @since 2020 - 11 - 10
 */
public class Transfer extends Thread {
	private int[] arr;
	private int little, big, median;

	/**
	 * create constructor
	 * 
	 * @param arr    - nickname for array
	 * @param little - get the smallest value
	 * @param big    - get the largest value
	 */
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

	/**
	 * 
	 * 
	 * @param arr    - will be used to count for total variable
	 * @param little - will be less than big
	 * @param big    - has to equal high value
	 * @return sum of single thread method
	 */
	public static int single(int[] arr, int little, int big) {
		int total = 0;

		for (int i = big; i < little; i++) {
			total += arr[i];
		}

		return total;
	}

	/**
	 * 
	 * @param arr - gets the length to count the single method
	 * @return - calls the single method
	 */
	public static int single(int[] arr) {
		return single(arr, 0, arr.length);
	}

	/**
	 * 
	 * @param arr     - returns the value of the length inside size
	 * @param threads - used as an integer
	 * @return - parallel array sum
	 */
	//
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

	/**
	 * 
	 * @return - uses diff to find the median
	 */
	public int getmedianSum() {
		return median;
	}

	/**
	 * 
	 * @param arr - used for counting values on parallelSum method
	 * @return - returns the method parallelSum
	 */
	public static int getSum(int[] arr) {
		return parallelSum(arr, Runtime.getRuntime().availableProcessors());
	}
}
