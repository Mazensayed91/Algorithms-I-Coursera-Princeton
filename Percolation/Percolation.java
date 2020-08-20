import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean open[];
    private int opensites = 0;
    private int n;
    private WeightedQuickUnionUF weighted_union;
    private WeightedQuickUnionUF weighted_union_2;
    
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n)
    {
        this.n = n;
        if(n<=0){throw new IllegalArgumentException();}
        
        weighted_union = new WeightedQuickUnionUF(n*n+2);
        weighted_union_2 = new WeightedQuickUnionUF(n*n+1);
        open = new boolean [n*n];
        for(int i=0;i<n*n;i++)
        {
            open[i] = false; //keep track of connected sites;intialized with zeros because all are blocked initially
        }
    }
    
    // opens the site (row, col) if it is not open already
    public void open(int row, int col)
    {
        row--; col--; // Decrement row and col to deal with index
        // Determine position in the 2d gird from our 1d array
                
        checkIllegalArguments(row,col);     
        int position = row * n + col + 1;
        int up_ward;
        int down_ward;
        int right;
        int left;
        
        // Making connections with the virtual 2 sites
        if(row == 0)
        {
            weighted_union.union(position, 0);
            weighted_union_2.union(position, 0);
        }
        if(row == n-1)
        {
            weighted_union.union(position, open.length+1);
        }
        
        // Check if we are in any edge of the grid and open connections
        
        if(row != n-1){
            up_ward = position + n;
            if(open[up_ward-1] == true)
            {
                weighted_union.union(up_ward, position);
                weighted_union_2.union(up_ward, position);
            }
        }

        if(row != 0){
            down_ward = position - n;
            if(open[down_ward-1] == true)
            {
                weighted_union.union(down_ward, position);
                weighted_union_2.union(down_ward, position);
            }
        }

        if(col != n-1){
            right = position + 1;
            if(open[right-1] == true)
            {
                weighted_union.union(right, position);
                weighted_union_2.union(right, position);
            }
        }

        if(col != 0){
            left = position - 1;
            if(open[left-1] == true)
            {
                weighted_union.union(left, position);
                weighted_union_2.union(left, position);
            }
        }

        if(open[position-1] == false)
        {
            open[position-1] = true;
            opensites++;
        }
        
    }
    // is the site (row, col) open?
    public boolean isOpen(int row, int col)
    {
        row--;col--;
        checkIllegalArguments(row,col);
        int position = (row * n + col);
        
        return open[position];
    }
    
    
    // is the site (row, col) full?
    public boolean isFull(int row, int col)
    {
        row--; col--;
        checkIllegalArguments(row,col);
        int position = row * n + col;
        
        int p = weighted_union_2.find(0);
        int q = weighted_union_2.find(position+1);
        return p==q;
    }   
    // returns the number of open sites
    public int numberOfOpenSites(){return opensites;}
    private void checkIllegalArguments(int row,int col)
    {
        if(row >=n || row<0 || col>=n || col<0)
        {
            throw new IllegalArgumentException("You entered row or col that is out of the range,, range is n*n sites");

        }
    }
    
    // does the system percolate?
    public boolean percolates()
    {
        return weighted_union.find(0) == weighted_union.find(open.length+1);
    }
}
