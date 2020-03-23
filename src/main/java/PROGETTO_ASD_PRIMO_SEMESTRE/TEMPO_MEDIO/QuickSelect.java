package PROGETTO_ASD_PRIMO_SEMESTRE.TEMPO_MEDIO;

public class QuickSelect extends Select {

    @Override
    public String getName() {
        return "QuickSelect";
    }

    @Override
    public int execute(int[] input, int k) {
        return quickSelect(input, 0, input.length - 1, k);
    }



    private static int quickSelect(int[] items, int low, int high, int key){

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


    private static int partition(int[] items, int low, int high){

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

    /**
    private static void swap(int[] items, int i, int j) {
        int temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }
     **/
}
