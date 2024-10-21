package deque;

public class LinkedListDeque<T> /*implements Deque<T>, Iterable<T>*/  {
    private class Node {
        private Node prev;
        private T item;
        private Node next;

        private Node(T i, Node n) {
            item = i;
            next = n;
        }
    }
    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public void addFirst(T item) {
        Node firstNode = sentinel.next;
        firstNode.prev = new Node(item, firstNode);
        sentinel.next = firstNode.prev;
        firstNode.prev.prev = sentinel;
        size = size + 1;
    }

    public void addLast(T item) {
        Node lastNode = sentinel.prev;
        lastNode.next = new Node(item, sentinel);
        sentinel.prev = lastNode.next;
        lastNode.next.prev = lastNode;
        size = size + 1;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node rmNode = sentinel.next;
        T rmItem = rmNode.item;
        sentinel.next = rmNode.next;
        rmNode.next.prev = sentinel;
        rmNode.item = null;
        rmNode.next = null;
        rmNode.prev = null;
        size = size - 1;
        return rmItem;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node rmNode = sentinel.prev;
        T rmItem = rmNode.item;
        sentinel.prev = rmNode.prev;
        rmNode.prev.next = sentinel;
        rmNode.item = null;
        rmNode.prev = null;
        rmNode.next = null;
        size = size - 1;
        return rmItem;
    }

    public T get(int index) {
        if (index < 0) {
            return null;
        }
        int nodeInd = 0;
        for (Node p = sentinel.next; p.item != null; p = p.next) {
            if (nodeInd != index) {
                nodeInd += 1;
            } else {
                return p.item;
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty (){
        return (size == 0);
    }

    private T getrec(int index, int nodeInd, Node p) {
        if (p.item == null) {
            return null;
        }
        if (nodeInd == index) {
            return p.item;
        }
        return getrec(index, nodeInd + 1, p.next);
    }

    public T getRecursive(int index) {
        Node p = sentinel.next;
        if (index < 0) {
            return null;
        }
        int nodeInd = 0;

        return getrec(index, nodeInd, p);
    }

//    public Iterator<T> iterator() {
//        return new LinkedListIterator();
//    }
//
//    private class LinkedListIterator implements Iterator<T> {
//        private int wizPos;
//        private LinkedListIterator() {
//            wizPos = 0;
//        }
//
//        public boolean hasNext() {
//            return wizPos < size;
//        }
//
//        public T next() {
//            T item = get(wizPos);
//            wizPos += 1;
//            return item;
//        }
//    }

    public void printDeque() {
        for (Node p = sentinel.next; p.item != null; p = p.next) {
            System.out.print(p.item + " ");
        }
        System.out.println();
    }

    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (o == null) {
//            return false;
//        }
//        if (!(o instanceof Deque)) {
//            return false;
//        }
//        Deque<T> ol = (Deque<T>) o;
//        if (ol.size() != this.size()) {
//            return false;
//        }
//        for (int i = 0; i < size; i++) {
//            if (!(ol.get(i).equals(this.get(i)))) {
//                return false;
//            }
//        }
//        return true;
        if( o instanceof LinkedListDeque){
            if(((LinkedListDeque<T>) o).size() == this.size()) {
                for (int i = 0; i < this.size(); i++) {
                    if (((LinkedListDeque<T>) o).get(i) != this.get(i)) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        int n = 99;

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        for (int i = 0; i <= n; i++) {
            lld1.addLast(i);
        }

        LinkedListDeque<Integer> lld2 = new LinkedListDeque<>();
        for (int i = 0; i <= n-1; i++) {
            lld2.addLast(i);
        }

        System.out.println(lld1.equals(lld2));

    }
}