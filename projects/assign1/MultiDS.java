import java.util.Random;


public class MultiDS<T> implements MyQ<T>, Shufflable{
    private T [] theQueue;
    private static final int default_capacity = 10;
    private int front;
    private int back;
    private int capacity;
    private int size;

    //set initial capacity
    public MultiDS (int defaultCapacity){
        if(defaultCapacity<=0){
            this.capacity = default_capacity;

        }else{
            this.capacity = defaultCapacity;
            theQueue = (T[]) new Object[this.capacity];
            front = 0;
            back = -1;
            size =0;
        }
    }
//add item to the queue
    public void enqueue(T newEntry) {
        if (!contains(newEntry)) {
            if (size == capacity) {
                resizeQ();
            }
            //System.out.println("Adding item: " + newEntry.toString());
            back = (back + 1) % capacity;
            theQueue[back] = newEntry;
            size++;
        }
    }
//check if repetitive 
    private boolean contains(T item) {
        for (int i = front; i != (back + 1) % capacity; i = (i + 1) % capacity) {
            if (theQueue[i] != null && theQueue[i].equals(item)) {
                return true;
            }
        }
        return false;
    }
    //resize capacity
    private void resizeQ(){
        int newCap = capacity*2;
        T[] newQueue = (T[]) new Object[newCap];
        int i =0;
        for(int j = front; j!= (back+1)%capacity; j = (i+1)%capacity){
            newQueue[i++] = theQueue[j];//copy to newqueue
        }
        newQueue[i] = theQueue[back];
        front =0;
        back = i;
        theQueue= newQueue;
        capacity = newCap;//set new cap
    }

//dequeue
    public T dequeue() {
        if (isEmpty()){
            throw new EmptyQueueException();}
            T removed = theQueue[front];
            theQueue[front] = null;
            front = (front+1)%capacity;
            size --;
            return removed;
    }


//get front
    public T getFront() {
    if (isEmpty()){
        throw new EmptyQueueException();
    }
    return theQueue[front];
    }


//check if queue is empty
    public boolean isEmpty() {
        return front == (back+1)%capacity;
    }

//clear all reasult

    public void clear() {
        while (!isEmpty()){
            dequeue();
        }
    }


//shuffle the queue
    public void shuffle() {
        Random rand = new Random();
        for (int i = size - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            // Swap elements at indices i and j
            T temp = theQueue[(front + i) % theQueue.length];
            theQueue[(front + i) % theQueue.length] = theQueue[(front + j) % theQueue.length];
            theQueue[(front + j) % theQueue.length] = temp;
        }
    }
//return the size of queue
    public int size() {
        if (isEmpty()){
            return 0;
        }else if (back>=front){
            return back-front+1;
        }else{
            return capacity-front+back+1;
        }
    }
    //return capacity of queue
    public int capacity() {
        return capacity;
    }
//tostring
    public String toString(){
        StringBuilder string = new StringBuilder();
        for(int i = front; i != (back+1)%theQueue.length; i = (i+1)%theQueue.length){
            string.append(theQueue[i]).append(" ");
        }
        return string.toString();
    }
}