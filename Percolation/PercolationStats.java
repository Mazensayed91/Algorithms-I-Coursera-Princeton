/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package project1_algorithms;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdStats;
import java.lang.IllegalArgumentException;


/**
 *
 * @author Lenovo
 */
public class PercolationStats {
    // perform independent trials on an n-by-n grid
    private double p_random_variables[];
    private int trials;
    public PercolationStats(int n, int trials)
    {
        if(n<=0 || trials<=0)
        {
            throw new IllegalArgumentException("n and trials must be greater than 0 !");

        }

        this.trials = trials;
        int random_row;
        int random_col;
        p_random_variables = new double[trials];
        for(int i = 0;i<trials;i++)
        {
            Percolation grid;
            grid = new Percolation(n);
            while(!grid.percolates())
            {
                random_row = StdRandom.uniform(n)+1;
                random_col = StdRandom.uniform(n)+1;
                grid.open(random_row, random_col);
            }
            p_random_variables[i] = (double)grid.numberOfOpenSites() / (n*n);
        }
    }
    
    // sample mean of percolation threshold
    public double mean()
    {
        return StdStats.mean(p_random_variables);
    }
    
    // sample standard deviation of percolation threshold
    public double stddev()
    {
        return StdStats.stddev(p_random_variables);
    }
    
    // low endpoint of 95% confidence interval
    public double confidenceLo()
    {
        return this.mean() - 1.96 * (this.stddev()/Math.sqrt(this.trials));
    }
    
    // high endpoint of 95% confidence interval
    public double confidenceHi()
    {
        return this.mean() + 1.96 * (this.stddev()/Math.sqrt(this.trials));
    }
    
    public static void main(String[] args)
    {
      
        
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
          
        
        PercolationStats estimate = new PercolationStats(n,trials);
        StdOut.println("mean = " + estimate.mean());
        StdOut.println("stddev = " + estimate.stddev());
        StdOut.println("95% confidence interval = " +"[" +estimate.confidenceLo()+", "+estimate.confidenceHi() + "]");          
    }

}
