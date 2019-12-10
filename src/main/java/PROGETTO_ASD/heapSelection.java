package PROGETTO_ASD;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class heapSelection {

    public static void main(String[] args) {

        List<Integer> items = new ArrayList<>();

        Input.InputToList(items);

        minHeap H1 = new minHeap(items);
        minHeap H2 = new minHeap();

        int k = 0;

        H2.insert(H1.getMin());

        for (int i = 0; i < k-1 ; i++) {
            H2.extract();


        }

        H1.printMinHeap();
        System.out.println(H1.getHeapSize());



    }
}
