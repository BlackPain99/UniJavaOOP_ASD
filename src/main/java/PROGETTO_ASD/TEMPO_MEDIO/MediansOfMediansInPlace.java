package PROGETTO_ASD.TEMPO_MEDIO;

public class MediansOfMediansInPlace extends Select {
    @Override
    public String getName() {
        return "MediansOfMediansInPlace";
    }

    @Override
    public int execute(int[] input, int k) {
        return mediansOfMediansInPlace(input, k);
    }


    public static int mediansOfMediansInPlace(int[] nums, int k){
        return nums[select(nums, 0, nums.length - 1, k)];
    }

    private static int select(int[] nums, int l, int h, int k){

        while(l < h){

            int pivotIndex = pivot(nums, l, h);
            int j = partition(nums, l, h, pivotIndex);

            if(j < k){
                l = j + 1;
            } else if(j > k){
                h = j - 1;
            } else {
                return j;
            }

        }
        return l;
    }

    private static int pivot(int[] list, int left, int right) {

        // for 5 or less elements just get median
        if (right - left < 5) {
            return partition5(list, left, right);
        }

        // otherwise move the medians of five-element subgroups to the first n/5 positions
        for (int i = left; i <= right; i += 5) {

            // get the median of the i'th five-element subgroup
            int subRight = i + 4;
            if (subRight > right) {
                subRight = right;
            }

            int median5 = partition5(list, i, subRight);

            exch(list, median5, left + ((i - left) / 5));
        }

        // compute the median of the n/5 medians-of-five
        return select(list, left, (int) (left + Math.ceil((right - left) / 5d) - 1), (int) (left + (right - left) / 10d));

    }

    private static int partition5(int[] list, int l, int h) {

        for (int i = l; i <= h; i++) {
            for (int j = i; j > l; j--) {

                if (less(list[j - 1], list[j])) {
                    exch(list, j, j - 1);
                }
            }
        }

        return (h + l) / 2;
    }

    private static int partition(int[] a, int l, int h, int pivotIndex){

        exch(a, l, pivotIndex);
        int i = l;
        int j = h + 1;
        int v = a[l];

        while(true){
            while(less(a[++i], v) && i != h){}
            while(less(v, a[--j]) && j != l){}
            if(j <= i) break;
            exch(a, i, j);
        }

        exch(a, j, l);
        return j;


    }

    private static void exch(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static boolean less(int v, int w){

        if(v < w){
            return true;
        }

        return false;
    }


}
