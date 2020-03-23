package ESERCIZI_ASD_SECONDO_SEMESTRE.ESERCIZI_13_14;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {

    Node root;
    static int preIndex = 0;

    public static void main(String[] args) {

        List<String> preOrderInput = new ArrayList<>();

        Input.inputToList(preOrderInput);

        ESERCIZI_ASD_SECONDO_SEMESTRE.ESERCIZI_13_14.BinaryTree tree = new ESERCIZI_ASD_SECONDO_SEMESTRE.ESERCIZI_13_14.BinaryTree();

        Node root = tree.buildTree(preOrderInput);

    }

    Node buildTree(List<String> inOrder, List<String> preOrder, int startInOrder, int endInOrder){

        if(startInOrder > endInOrder){
            return null;
        }

        Node x = new Node(preOrder.get(preIndex++));

        //Nodo senza figli
        if(startInOrder == endInOrder){
            return x;
        }

        int inOrderIndex = inOrder.indexOf(x.key);

        x.left = buildTree(inOrder, preOrder, startInOrder, inOrderIndex - 1);
        x.right = buildTree(inOrder, preOrder, inOrderIndex + 1, endInOrder);

        return x;
    }

    Node buildTree(List<String> preOrderInput){

        String key = preOrderInput.get(preIndex++);

        if( key.equals("NULL") ){
            return null;
        }

        Node x = new Node(key);

        if(preIndex == preOrderInput.size() - 1){
            return x;
        }

        x.left = buildTree(preOrderInput);
        x.right = buildTree(preOrderInput);

        return x;

    }


}
