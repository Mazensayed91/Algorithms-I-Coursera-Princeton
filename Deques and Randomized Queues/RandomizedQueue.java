/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package porject2_algorithms;
import java.util.Iterator;

/**
 *
 * @author Lenovo
 */
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item>{
    private int head;
    private int tail;
    private Item[] arr;
    
    public RandomizedQueue(){
         arr = (Item[]) new Object[1];
         head = 0;
         tail = 0;
    }
    public boolean isEmpty(){
        return tail == 0;
    }
    public int size(){
        return tail;
        //return tail-head;
    }
    public void enqueue(Item item){
        if(item == null)
            throw new IllegalArgumentException("Null arguments are not accepted");
        if(arr.length == size())
            resize(2 * arr.length);
        arr[tail++] = item;
    }
    public Item dequeue(){
        if(tail == 0)
            throw new java.util.NoSuchElementException("queue is empty");
        int random_index = StdRandom.uniform(tail);
        Item random_item = arr[random_index];
        arr[random_index] = arr[--tail];
        arr[tail] = null;
        if (tail > 0 && tail== arr.length/4) resize(arr.length/2);
        return random_item;
    }
    public Item sample(){
        if(tail == 0)
            throw new java.util.NoSuchElementException("queue is empty");
        int random_index = StdRandom.uniform(tail);
        return arr[random_index];
    }
    private void resize(int cap){
        Item[] new_arr = (Item[]) new Object[cap];
        for(int i =0;i<tail;i++){
            new_arr[i] = arr[i];
        }
        arr = new_arr;
    }
    
    public Iterator<Item> iterator() { return new DequeIterator(); }
    
    private class DequeIterator implements Iterator<Item>{
        private int current = 0;
        private Item[] perm_arr;
        private DequeIterator(){
         perm_arr = (Item[]) new Object[tail];
        for(int i = 0;i<tail;i++)
            perm_arr[i] = arr[i];
        StdRandom.shuffle(perm_arr);
    }
        public boolean hasNext(){return current != tail ;}
        public Item next(){
            if(!hasNext())
                throw new NoSuchElementException("Deque is empty!");
            return perm_arr[current++];
        }
        public void remove()
        {
            throw new UnsupportedOperationException("remove is unsupported to prevent bugs");
        }
    }
    public static void main(String[] args){
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
          rq.enqueue(3);
          rq.enqueue(23);
          rq.enqueue(434);
          rq.enqueue(11);
          rq.enqueue(98);
          for(int s : rq)
              StdOut.println(s);
          
         StdOut.println(rq.dequeue());
         rq.enqueue(928);
         StdOut.println(rq.size());
         StdOut.println(rq.dequeue());
         StdOut.println(rq.size());
    }
}
