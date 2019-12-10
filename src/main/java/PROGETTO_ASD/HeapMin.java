package PROGETTO_ASD;

import java.util.ArrayList;
import java.util.List;

public class HeapMin {

    private List<Node> heap;
    private int heapsize;

    private class Node implements Comparable<Node> {

        private int key; //posizione
        private int val; //valore

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }

        public int getKey() {
            return key;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public String toString() {
            return "" + val;
        }

        public Node getParent() {
            if (key == 0) {
                return null;
            }
            int parentKey = (key + 1) / 2 - 1;
            return heap.get(parentKey);
        }

        public Node getLeft() {
            int leftKey = 2 * (key + 1) - 1;
            if (leftKey >= heap.size()) {
                return null;
            }
            return heap.get(leftKey);
        }

        public Node getRight() {
            int rightKey = 2 * (key + 1);
            if (rightKey >= heap.size()) {
                return null;
            }
            return heap.get(rightKey);
        }

        @Override
        public int compareTo(Node o) {

            if (this.getVal() < o.getVal()) {
                return -1;
            } else if (this.getVal() == o.getVal()) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    public HeapMin(int[] values){
        heap = new ArrayList<>();
        for (int i = 0; i < values.length; i++){
            heap.add(new Node(i, values[i]));
        }
        heapsize = heap.size();
    }

    public void heapify(int i){

    }




}
