package com.java.graphs;
//https://practice.geeksforgeeks.org/problems/find-whether-path-exist5238/1?page=1&category[]=Graph&curated[]=1&sortBy=difficulty
public class FindwhetherpathExist {

    public boolean is_Possible(int[][] grid)
    {
        // Code here
        int row = grid.length;
        int col = grid[0].length;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(grid[i][j]==1){
                    if(dfs(grid,i,j,row,col)==true){
                        return true;
                    }else{
                        return false;
                    }
                }
            }
        }
        return false;
    }
    static boolean dfs(int grid[][],int x,int y,int row,int col){
        if(x<0 || x>=row || y<0 || y>=col || grid[x][y]==0){
            return false;
        }
        if(grid[x][y]==2)
            return true;

        grid[x][y]=0;

        boolean u = dfs(grid,x-1,y,row,col);
        boolean d = dfs(grid,x+1,y,row,col);
        boolean l = dfs(grid,x,y-1,row,col);
        boolean r = dfs(grid,x,y+1,row,col);
        return u|d|l|r;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {3,0,3,0,0},
                {3,0,0,0,3},
                {3,3,3,3,3},
                {0,2,3,0,0},
                {3,0,0,1,3}
        };

        FindwhetherpathExist findwhetherpathExist = new FindwhetherpathExist();
        System.out.println(findwhetherpathExist.is_Possible(grid));
    }

}

