/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package porject2_algorithms;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 *
 * @author Lenovo
 */
public class Deque<Item> implements Iterable<Item>{
    
    private Node first;
    private Node last;
    private int size;
    private class Node
    {
        Node next;
        Node previous;
        Item data;
    }
    public Deque(){
        first = null;
        last = null;
        size = 0;
    }
    public boolean isEmpty(){
        return first == null && last == null;
    }
    
    public int size(){
        return size;
    }
    
    public void addFirst(Item item){
        if (item == null)
            throw new IllegalArgumentException("Null is not a valid object");
        boolean flag = isEmpty();
        size++;
        Node old_first = first;
        first = new Node();
        first.data = item;
        first.previous = null;
        first.next = old_first;

        if(!flag) 
            old_first.previous = first;
        else
            last = first;
       
    }
    public void addLast(Item item){
        if (item == null)
            throw new IllegalArgumentException("Null is not a valid object");
        boolean flag = isEmpty();
        size++;
        Node old_last = last;
        last = new Node();
        last.data = item;
        last.previous = old_last;
        last.next = null;

        if(!flag)
            old_last.next = last;
        else
            first = last;

    }
    public Item removeFirst()
    {
        if(isEmpty())
                throw new NoSuchElementException("Deque is empty!");
        size--;
        Item item = first.data;
        first = first.next;
        if(first != null)            
            first.previous = null;
        else
            last=null;

        return item;
    }
    public Item removeLast()
    {
        if(isEmpty())
                throw new NoSuchElementException("Deque is empty!");
        size--;
        Item item = last.data;
        last = last.previous;
        if(last != null)
            last.next = null;
        else
            first=null;
        return item;
    }
    
    //Making iterator
    public Iterator<Item> iterator() { return new DequeIterator(); }
    
    private class DequeIterator implements Iterator<Item>
    {
        private Node current = first;
        @Override
        public boolean hasNext(){return current != null;}
        @Override
        public Item next()
        {
            if(!hasNext())
                throw new NoSuchElementException("Deque is empty!");
            Item item = current.data;
            current = current.next;
            return item;
        }
        public void remove()
        {
            throw new UnsupportedOperationException("remove is unsupported to prevent bugs");
        }
    }
        public static void main(String[] args)
        {
            Deque <Integer>dq = new Deque<>();
            dq.addFirst(3);
            dq.addLast(24);
            StdOut.println(dq.removeFirst());
            StdOut.println(dq.removeLast());
            StdOut.println("size = " + dq.size());
           
        }

}
