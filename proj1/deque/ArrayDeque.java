package deque;


import java.util.Deque;

public class ArrayDeque<T>{

    private Object[] items;
    private int capacity;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque(){
        items = new Object[8];
        capacity=8;
        size=0;
        nextFirst=4;
        nextLast=5;
    }

    @SuppressWarnings("unchecked")
    public void resize() {
        Object[] oldItems = items;
        items=new Object[capacity*2];

        int startFalling = (capacity*2)/4;
        int firstItem = (nextFirst+1)%capacity;
        for(int i=0; i < size;i=(i+1)%capacity){
            items[startFalling]=oldItems[firstItem];
            startFalling++;
            firstItem = (nextFirst+1)%capacity;
        }
        capacity=capacity*2;
        nextFirst = (capacity/4)-1;
        nextLast = nextFirst + size +1;
    }

    public void resizeDown() {
        Object[] oldItems = items;
        capacity = capacity / 2;
        items = new Object[capacity];

        int start = (nextFirst + 1) % oldItems.length;
        for (int i = 0; i < size; i++) {
            items[i] = oldItems[start];
            start = (start + 1) % oldItems.length;
        }

        nextFirst = capacity - 1;
        nextLast = size;
    }

    public void addFirst(T x){
        if(size == capacity) {
            resize();
        }
        items [nextFirst] = x;
        nextFirst = (nextFirst - 1 + capacity) % capacity;
        size++;
    }

    public void addLast(T x){
        if(size == capacity){
            resize();
        }
        items [nextLast] = x;
        nextLast = (nextLast + 1) % capacity;
        size++;
    }
    @SuppressWarnings("unchecked")
    public T removeFirst(){
        if(size==0){
            return null;
        }
        T removedElement = (T) items[(nextFirst+1)%capacity];
        items[(nextFirst+1)%capacity] = null;
        size--;
        nextFirst=(nextFirst+1)%capacity;
        return removedElement;
    }

    @SuppressWarnings("unchecked")
    public T removeLast(){
        if(size==0){
            return null;
        }
        T removedElement = (T) items[((nextLast-1)+capacity)%capacity];
        items[((nextLast-1)+capacity)%capacity] = null;
        size--;
        nextLast=((nextLast-1)+capacity)%capacity;
        if (size > 0 && size == capacity / 4 && capacity > 8) {
            resizeDown();
        }
        return removedElement;
    }

    @SuppressWarnings("unchecked")
    public T get(int index){
        if (index < 0 || index >= size) {
            return null;
        }
        int actualIndex = (nextFirst + 1 + index) % capacity;
        return (T) items[actualIndex];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty(){
        return (size==0);
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            int actualIndex = (nextFirst + 1 + i) % capacity;
            System.out.print(items[actualIndex] + " ");
        }
        System.out.println();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof ArrayDeque)) {
            return false;
        }

        ArrayDeque<?> otherDeque = (ArrayDeque<?>) o;
        if (this.size != otherDeque.size) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            int thisIndex = (nextFirst + 1 + i) % capacity;
            int otherIndex = (otherDeque.nextFirst + 1 + i) % otherDeque.capacity;

            T thisElement = (T) this.items[thisIndex];
            Object otherElement = otherDeque.items[otherIndex];

            if (!thisElement.equals(otherElement)) {
                return false;
            }
        }

        return true;
    }

}
