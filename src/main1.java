class main1 {
	public static void main(String[] args) {
		int sum = 0;
		int start = -100;
		int end = 25;

		int i = start;

		while (i <= end) {
			sum += i;
			i++;
		}

		System.out.println("sum : " + sum);
	}
}