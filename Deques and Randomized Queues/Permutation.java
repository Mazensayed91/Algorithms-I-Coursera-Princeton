/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package porject2_algorithms;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

/**
 *
 * @author Lenovo
 */
public class Permutation {
       public static void main(String[] args){
           
           int k = Integer.parseInt(args[0]);
           String element="";
           RandomizedQueue <String>rq = new RandomizedQueue<>();
           RandomizedQueue <String>small = new RandomizedQueue<>();
           
           while (!StdIn.isEmpty()) {
                String s = StdIn.readString();
                rq.enqueue(s);
       }
           for(int i = 0 ;i<k;i++)
           {
               element = rq.dequeue();
               small.enqueue(element);
               StdOut.println(element);
           }
           rq = small;

}
}
