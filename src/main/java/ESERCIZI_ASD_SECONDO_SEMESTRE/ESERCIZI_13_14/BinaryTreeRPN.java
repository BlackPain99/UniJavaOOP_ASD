package ESERCIZI_ASD_SECONDO_SEMESTRE.ESERCIZI_13_14;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class BinaryTreeRPN {

    public static void main(String[] args) {

        List<String> preOrderInput = new ArrayList<>();

        Input.inputToList(preOrderInput);

        int result = test(preOrderInput);

        System.out.println(result);


    }

    static List<String> inOrderRPN(List<String> tree){

        if(tree.isEmpty()){
            throw new NullPointerException("errore, l'albero è vuoto");
        }

        List<String> inOrderList = new ArrayList<>();
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < tree.size(); i++) {

            String key = tree.get(i);

            if (key.equals("NULL")){

                if(i == tree.size() - 1){
                    break;
                }

                inOrderList.add(stack.pop());

            } else {
                stack.push(key);
            }

        }

        return inOrderList;

    }


    static List<String> cleanTreeFromNull(List<String> preOrderInput ){

        List<String> preOrder = new ArrayList<>();

        for (String key : preOrderInput) {

            if (!key.equals("NULL")) {
                preOrder.add(key);
            }

        }

        return preOrder;
    }

    static int getHeightOfTree(Node x){

        int height = -1;

        if(x != null) {
           height = 1 + Math.max(getHeightOfTree(x.left), getHeightOfTree(x.right));
        }

        return height;
    }

    static int test(List<String> preOrderInput){

        List<String> inOrderList = inOrderRPN(preOrderInput);
        List<String> preOrder = cleanTreeFromNull(preOrderInput);

        if(!testBST(inOrderList)){
            return 0;
        } else {

            BinaryTree tree = new BinaryTree();

            Node root = tree.buildTree(inOrderList, preOrder, 0, inOrderList.size() - 1);

            if (testAVL(root)){
                return 2;
            } else {
                return 1;
            }

        }

    }


    static boolean testBST(List<String> inOrderList ){

        for (int i = 0; i <= inOrderList.size() - 2 ; i++) {

            String key1 = inOrderList.get(i);
            String key2 = inOrderList.get(i + 1);

            int intKey1 = Integer.parseInt(key1);
            int intKey2 = Integer.parseInt(key2);

            if (intKey1 > intKey2){
                return false;
            }

        }

        return true;
    }

    static boolean testAVL(Node root){

        boolean avl = true;

        if(root != null){

            int leftTreeHeight = getHeightOfTree(root.left);
            int rightTreeHeight = getHeightOfTree(root.right);

            if(Math.abs(leftTreeHeight - rightTreeHeight) >= 2 ){
                return false;
            } else {
                avl = testAVL(root.left) && testAVL(root.right);
            }
        }

        return avl;
    }



}
