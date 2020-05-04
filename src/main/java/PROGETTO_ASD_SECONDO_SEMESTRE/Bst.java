package PROGETTO_ASD_SECONDO_SEMESTRE;


/**
 * Il programma dovrà essere di tipo interattivo: a ogni iterazione, l'utente potrà indicare una fra le operazioni sopra riportate.
 * In output verrà fornito eventualmente il risultato dell'operazione (ad es., "five" nel caso in cui l'operazione sia "find 5").
 * Il programma terminerà ogni qualvolta l'utente inserisca un'operazione non supportata (ad es., "exit").
 * Il contenuto di ogni nodo deve essere rappresentato nel formato "chiave:valore".
 * Un (sotto-)albero vuoto deve essere rappresentato dalla stringa "NULL".
 */
class Bst {

    private BSTNode root;

    public Bst(){
        root = null;
    }


    /**
     * inserisce un nuovo nodo nell'albero BST con chiave k e valore v.
     * (si assuma che l'albero non contenga già un nodo con chiave k)
     * @param k chiave di tipo intero
     * @param v valore di tipo stringa
     */
    void insert(int k, String v){

        BSTNode z = new BSTNode(k, v);
        BSTNode y = null;
        BSTNode x = root;

        while (x != null){
            if (x.key > z.key){
                y = x;
                x = x.left;
            } else {
                y = x;
                x = x.right;
            }
        }

        if(y == null){
            root = z;
        } else {
            z.parent = y;

            if(y.key > z.key){
                y.left = z;
            } else {
                y.right = z;
            }

        }

    }


    /**
     * trova nell'albero il nodo con chiave numerica k.
     * (si assuma che tale nodo esista sempre)
     * si appoggia al metodo findTree(BSTNode, int)
     * @param k chiave di tipo intero
     * @return il valore associato a tale nodo
     */
    String find(int k){
        return findTree(root, k);
    }


    private String findTree(BSTNode x, int k){

        if(x == null || x.key == k){
            return x.value;
        } else {

            if(k < x.key){
                return findTree(x.left, k);
            } else {
                return findTree(x.right, k);
            }

        }

    }

    /**
     * trova nell'albero il nodo con chiave numerica k.
     * (si assuma che tale nodo esista sempre)
     * si appoggia al metodo findTreeNode(BSTNode, int)
     * @param k chiave di tipo intero
     * @return nodo con chiave k
     */
    BSTNode findNode(int k){
        return findTreeNode(root, k);
    }


    private BSTNode findTreeNode(BSTNode x, int k){

        if(x == null || x.key == k){
            return x;
        } else {

            if(k < x.key){
                return findTreeNode(x.left, k);
            } else {
                return findTreeNode(x.right, k);
            }

        }

    }


    /**
     * rimuove tutti i nodi dall'albero, che diventerà vuoto.
     */
    void clear(){
        root = null;
    }


    /**
     * visualizza l'albero corrente (rappresentazione in forma polacca (notazione prefissa)).
     * Il contenuto di ogni nodo viene rappresentato nel formato "chiave:valore".
     * Un (sotto-)albero vuoto deve essere rappresentato dalla stringa "NULL".
     */
    void show(){
        showTree(root);
    }

    private void showTree(BSTNode x) {

        if(x == null){
            System.out.print("NULL ");
        } else {
            System.out.print(x.key + ":" + x.value + " ");
            showTree(x.left);
            showTree(x.right);
        }

    }

    /**
     * dato un nodo x con chiave k, trova il nodo che contiene la più piccola chiave più grande di k
     * @param x nodo di cui si vuole conoscere il successore
     * @return il nodo che contiene la più piccola chiave più grande di k
     */
    BSTNode successor(BSTNode x){

        if (x.right != null){
            return searchMin(x.right);
        } else {
            BSTNode y = x.parent;

            while (y != null && x != y.left){
                x = y;
                y = x.parent;
            }

            return y;
        }
    }


    /**
     * trova il nodo con chiave minima
     * @param x nodo di partenza da cui iniziare a cercare la chiave minima
     * @return il nodo con chiave minima
     */
    BSTNode searchMin(BSTNode x){
        if(x == null || x.left == null){
            return x;
        } else {
            return searchMin(x.left);
        }

    }

    /**
     * calcola l'altezza dell'albero corrente (per convenzione, l'altezza dell'albero vuoto è 0 e l'altezza di un albero singoletto è 1).
     * algortimo lineare
     * @return l'altezza dell'albero corrente
     */
    int height(){
        return height(root);
    }

    int height(BSTNode x){
        if(x == null){
            return 0;
        } else {
            return 1 + Math.max(height(x.left), height(x.right));
        }
    }


}
