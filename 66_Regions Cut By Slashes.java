class Solution {
    public int regionsBySlashes(String[] grid) {
        
        int m = grid.length, n = grid[0].length()+1;
        int[] parent = new int[n*n];
        int[] rank = new int[n*n];
        
        for(int i=0; i<parent.length; i++)
            parent[i] = i;
        
        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                if(i==0 || j==0 || i==n-1 || j==n-1){
                    union(0, i*n+j, parent, rank);
                }
        
        int count = 1;
        for(int i=0; i<m; i++)
            for(int j=0; j<m; j++){
                char ch = grid[i].charAt(j);
                if(ch == '/'){
                    int p1 = i*n + j+1;
                    int p2 = (i+1)*n + j;
                    
                    if(!union(p1, p2, parent, rank))   count++;
                }else if(ch == '\\'){
                    int p1 = i*n + j;
                    int p2 = (i+1)*n + j+1;
                    if(!union(p1, p2, parent, rank))   count++;
                }
            }
        return count;
    }
    
    public boolean union(int x, int y, int[] parent, int[] rank){
        int px = find(x, parent);
        int py = find(y, parent);
        
        if(px != py){
            if(rank[px] > rank[py])
                parent[py] = px;
            else if(rank[px] < rank[py])
                parent[px] = py;
            else{
                parent[px] = py;
                rank[py]++;
            }
            return true;
        }
        return false;
    }
    
    public int find(int x, int[] parent){
        if(x == parent[x])
            return x;
        
        return parent[x] = find(parent[x], parent);
    }
}
