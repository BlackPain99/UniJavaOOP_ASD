package PROGETTO_ASD;

import java.util.ArrayList;
import java.util.List;

public class QuickSelect {

    public static void main(String[] args) {

        List<Integer> listItems = new ArrayList<>();
        int key = Input.InputToList(listItems);

        int[] items = new int[listItems.size()];

        for (int i = 0; i < listItems.size(); i++){
            items[i] = listItems.get(i);
        }

        int result = quickSelect(items, 0, items.length - 1, key);

        System.out.println(result);
    }

    public static int quickSelect(int[] items, int low, int high, int key){

        if(low < high){

            int r = partition(items, low, high);

            if(r == key-1) {
                return items[r];
            } else if(r > key-1){
                return quickSelect(items, low, r-1, key);
            } else {
                return quickSelect(items, r+1, high, key);
            }
        }

        return items[low];
    }




    public static int partition(int[] items, int low, int high){

        int pivot = items[high];
        int i = (low - 1);

        for(int j = low; j <= high; j++){
            if(items[j] <= pivot){
                i++;
                swap(items, i, j);
            }
        }

        return i;
    }

    private static void swap(int[] items, int i, int j) {
        int temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

}
