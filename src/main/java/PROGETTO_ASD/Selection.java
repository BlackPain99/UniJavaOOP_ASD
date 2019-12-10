package PROGETTO_ASD;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Selection {

    public static void main(String[] args) {

        List<Integer> items = new ArrayList<>();

        int k = Input.InputToList(items);

        int kSmallest = heapSelect(items, k);

        System.out.println(kSmallest);
    }

    public static int heapSelect(List<Integer> items, int k) {

        minHeap H1 = new minHeap(items);
        //minHeap H2 = new minHeap();

        PriorityQueue<Node> H2 = new PriorityQueue<>();

        H2.add(new Node(0, H1.getIntList().get(0)));

        for (int i = 0; i < k - 1; i++) {
            int j = H2.peek().getKey();

            H2.remove();
            int l = H1.left(j);
            int r = H1.right(j);

            if (l < H1.getHeapSize()) {
                H2.add(new Node(l, H1.getIntList().get(l)));
            }

            if (r < H1.getHeapSize()) {
                H2.add(new Node(r, H1.getIntList().get(r)));
            }
        }

        return H2.peek().getVal();

    }




}







