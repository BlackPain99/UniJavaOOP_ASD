package PROGETTO_ASD;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class HeapSelect {

    public static void main(String[] args) {

        List<Integer> items = new ArrayList<>();

        int k = Input.InputToList(items);

        int kSmallestV1 = heapSelectV1(items, k);
        int kSmallestV2 = heapSelectV2(items, k);

        System.out.println(kSmallestV1);
        System.out.println(kSmallestV2);

    }

    public static int heapSelectV1(List<Integer> items, int k) {

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

    public static int heapSelectV2(List<Integer> items, int k){

        HeapMin H1 = new HeapMin(items);

        HeapMin H2 = new HeapMin();
        H2.insert2(H1.getHeap().get(0));

        for (int i = 0; i < k-1 ; i++) {
            //int key = H2.getMinNode().getKey();

            Node deletedNode = H2.extract2();

            Node left = deletedNode.getLeft(H1.getHeap());
            Node right = deletedNode.getRight(H1.getHeap());

            if(left == null && right == null)
                break;

            if(left != null && left.getKey() < H1.getHeapsize()){
                H2.insert2(left);
            }

            if(right != null && right.getKey() < H1.getHeapsize()){
                H2.insert2(right);
            }
        }

        return H2.getMinNode().getVal();
    }




}







