class Test {
    long sum(int[] num) {
        long sum = 0;
        for (int i = 0; i < num.length; i++) {
            sum += num[i];
        }
        return sum;
    }
}