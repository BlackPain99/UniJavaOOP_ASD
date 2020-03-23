package ESERCIZI_ASD_SECONDO_SEMESTRE.ESERCIZI_13_14;

import java.util.ArrayList;
import java.util.List;

 class Node {

    String key;
    Node left, right;

    Node(String key){
        this.key = key;
        left = null;
        right = null;
    }

     public String getKey() {
         return key;
     }

     public Node getLeft() {
         return left;
     }

     public Node getRight() {
         return right;
     }
 }


