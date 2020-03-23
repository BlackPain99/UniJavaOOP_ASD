package PROGETTO_ASD_PRIMO_SEMESTRE.MEDIAN_OF_MEDIANS;

import PROGETTO_ASD_PRIMO_SEMESTRE.Input;

import java.util.ArrayList;
import java.util.List;

public class MediansOfMediansInPlace {

    public static void main(String[] args) {
        List<Integer> listItems = new ArrayList<>();
        int key = Input.InputToList(listItems);

        Integer[] items = new Integer[listItems.size()];

        for (int i = 0; i < listItems.size(); i++){
            items[i] = listItems.get(i);
        }

        int result = (Integer) MediansOfMediansInPlace.mediansOfMediansInPlace(items, key-1);

        System.out.println(result);

    }

    public static Comparable mediansOfMediansInPlace(Comparable[] nums, int k){
        return nums[select(nums, 0, nums.length - 1, k)];
    }

    private static int select(Comparable[] nums, int l, int h, int k){

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

    private static int pivot(Comparable[] list, int left, int right) {

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

    private static int partition5(Comparable[] list, int l, int h) {

        for (int i = l; i <= h; i++) {
            for (int j = i; j > l; j--) {

                if (less(list[j - 1], list[j])) {
                    exch(list, j, j - 1);
                }
            }
        }

        return (h + l) / 2;
    }

    private static int partition(Comparable[] a, int l, int h, int pivotIndex){

        exch(a, l, pivotIndex);
        int i = l;
        int j = h + 1;
        Comparable v = a[l];

        while(true){
            while(less(a[++i], v) && i != h){}
            while(less(v, a[--j]) && j != l){}
            if(j <= i) break;
            exch(a, i, j);
        }

        exch(a, j, l);
        return j;


    }

    private static void exch(Comparable[] nums, int i, int j){
        Comparable temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static boolean less(Comparable v, Comparable w){

        if(v.compareTo(w) < 0){
            return true;
        }

        return false;
    }





}
