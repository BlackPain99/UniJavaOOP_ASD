package ESERCIZI_ASD_SECONDO_SEMESTRE.ESERCIZIO_17;

/**
 * Si richiede l'implementazione di un BST, in cui ciascun nodo contiene una chiave numerica (di tipo intero) e un valore alfanumerico (di tipo stringa),
 * che supporti, oltre alle operazioni classiche insert k v, remove k, find k, clear, show, anche la seguente operazione:
 *
 * - height: restituisce l'altezza dell'albero corrente (per convenzione, l'altezza dell'albero vuoto è 0 e l'altezza di un albero singoletto è 1).
 *
 * Si richiede che le operazioni classiche insert k v, remove k, e find k prendano al più tempo proporzionale all'altezza del BST,
 * mentre l'operazione height prenda tempo costante, indipendente quindi dalla dimensione o dall'altezza dell'albero.
 */
class BstAumentato {

    private BSTAumentatiNode root;

    BstAumentato(){
        this.root = null;
    }

    /**
     * inserisce un nuovo nodo nell'albero BST con chiave k e valore v.
     * (si assuma che l'albero non contenga già un nodo con chiave k)
     * @param k chiave di tipo intero
     * @param v valore di tipo stringa
     */
    void insert(int k, String v){

        BSTAumentatiNode z = new BSTAumentatiNode(k, v);
        BSTAumentatiNode y = null;
        BSTAumentatiNode x = root;

        while (x != null){
            if (x.getKey() > z.getKey()){
                y = x;
                x = x.getLeft();
            } else {
                y = x;
                x = x.getRight();
            }
        }

        //inserimento in un BST vuoto
        if(y == null){
            root = z;
        } else {

            z.setParent(y);

            if(y.getKey() > z.getKey()){
                y.setLeft(z);
            } else {
                y.setRight(z);
            }

            //aggiornare le altezze dei nodi a partire dal padre del nodo inserito, y, fino alla radice
            updateHeights(y);
        }

    }

    /**
     * aggiorna le altezze dei nodi a partire dal nodo passato in input fino alla radice
     * @param y nodo da cui partire per aggiornare le altezze
     */
    private void updateHeights(BSTAumentatiNode y) {

        while(y != null){

            y.setHeight(maxHeight(y.getLeft(), y.getRight()) + 1);

            y = y.getParent();
        }

    }

    /**
     * determina altezza più lunga tra due nodi in input
     * @param left primo nodo (figlio sx)
     * @param right secondo nodo (figlio dx)
     * @return l'altezza più lunga
     */
    private int maxHeight(BSTAumentatiNode left, BSTAumentatiNode right) {
        int h1, h2;

        if(left == null)
            h1 = 0;
        else
            h1 = left.getHeight();

        if(right == null)
            h2 = 0;
        else
            h2 = right.getHeight();

        return Math.max(h1, h2);
    }

    /**
     * rimuove dall'albero il nodo che contiene la chiave numerica k.
     * (si assuma che tale nodo esista sempre)
     * @param k chiave di tipo intero
     */
    void remove(int k){
        BSTAumentatiNode z = findNode(k);

        //nodo non trovato
        if(z == null){
            return;
        }

        removeTree(z);
    }


    private void removeTree(BSTAumentatiNode z){

        //x è il nodo effettivamente da eliminare (può essere il nodo stesso o il suo successore)
        BSTAumentatiNode x, v;

        if(z.getLeft() == null || z.getRight() == null){
            x = z;
        } else {
            x = successor(z);
        }

        //determino il figlio di x che lo andrà a sostituire
        if(x.getLeft() != null){
            v = x.getLeft();
        } else {
            v = x.getRight();
        }

        //aggiorno il parent di v al parent di x (ovviamente se v non è null)
        if(v != null){
            v.setParent(x.getParent());
        }

        BSTAumentatiNode parent = x.getParent();

        //se x è la radice, allora v sarà la nuova radice (il parent è già assegnato a null dall'if precedente)
        if(x.getParent() == null){
            root = v;
        } else {

            //adesso mi occupo di aggiornare i puntatori del padre di x
            if( x == x.getParent().getLeft()){
                x.getParent().setLeft(v);
            } else {
                x.getParent().setRight(v);
            }

        }

        if(x != z){
            z.setKey(x.getKey());
            z.setValue(x.getValue());
        }

        updateHeights(parent);

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

    private String findTree(BSTAumentatiNode x, int k){

        if(x == null || x.getKey() == k){
            assert x != null;
            return x.getValue();
        } else {

            if(k < x.getKey()){
                return findTree(x.getLeft(), k);
            } else {
                return findTree(x.getRight(), k);
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
    BSTAumentatiNode findNode(int k){
        return findTreeNode(root, k);
    }


    private BSTAumentatiNode findTreeNode(BSTAumentatiNode x, int k){

        if(x == null || x.getKey() == k){
            return x;
        } else {

            if(k < x.getKey()){
                return findTreeNode(x.getLeft(), k);
            } else {
                return findTreeNode(x.getRight(), k);
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

    private void showTree(BSTAumentatiNode x) {

        if(x == null){
            System.out.print("NULL ");
        } else {
            System.out.print(x.getKey() + ":" + x.getValue() + ":" + x.getHeight() + " ");
            showTree(x.getLeft());
            showTree(x.getRight());
        }

    }

    /**
     * dato un nodo x con chiave k, trova il nodo che contiene la più piccola chiave più grande di k
     * @param x nodo di cui si vuole conoscere il successore
     * @return il nodo che contiene la più piccola chiave più grande di k
     */
    BSTAumentatiNode successor(BSTAumentatiNode x){

        if (x.getRight() != null){
            return searchMin(x.getRight());
        } else {
            BSTAumentatiNode y = x.getParent();

            while (y != null && x != y.getLeft()){
                x = y;
                y = x.getParent();
            }

            return y;
        }
    }

    /**
     * trova il nodo con chiave minima
     * @param x nodo di partenza da cui iniziare a cercare la chiave minima
     * @return il nodo con chiave minima
     */
    BSTAumentatiNode searchMin(BSTAumentatiNode x){
        if(x == null || x.getLeft() == null){
            return x;
        } else {
            return searchMin(x.getLeft());
        }

    }

    /**
     * controlla la correttezza dei puntatori
     * @return true se è un albero binario valido, false altrimenti
     */
    boolean isValid(){
        return isTree(root);
    }

    boolean isTree(BSTAumentatiNode x){

        if(x == null){
            return true;
        }

        if(x == root && x.getParent() != null){
            return false;
        }

        if(x.getLeft() != null && x.getLeft().getParent() != x){
            return false;
        }

        if(x.getRight() != null && x.getRight().getParent() != x){
            return false;
        }

        return isTree(x.getLeft()) && isTree(x.getRight());
    }


    /**
     * altezza dell'albero
     * @return altezza della radice dell'albero
     */
    int height(){
        if(root == null)
            return 0;

        return root.getHeight();
    }





}
