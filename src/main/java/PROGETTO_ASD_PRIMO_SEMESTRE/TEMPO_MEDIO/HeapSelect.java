package PROGETTO_ASD_PRIMO_SEMESTRE.TEMPO_MEDIO;


import java.util.*;

public class HeapSelect extends Select {

    @Override
    public String getName() {
        return "HeapSelect";
    }

    @Override
    public int execute(int[] input, int k) {

        return heapSelectV1(input, k);
    }

    static int heapSelectV1(int[] input, int k) {

        List<Integer> items = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            items.add(input[i]);
        }

        MinHeap H1 = new MinHeap(items);

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
