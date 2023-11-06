class Solution {
    public int maxProfit(int[] prices) {

        int minVal = 100000, profit=0, i=0;
        while(i < prices.length){
            while(i < prices.length - 1 && prices[i+1] <= prices[i]) // minima
                i++;
            
            int min = prices[i++];
            while(i < prices.length - 1 && prices[i+1] >= prices[i]) // maxima
                i++;
            profit += i < prices.length ? prices[i++] - min : 0;
        }
        return profit;

        
        
    }
}
/*
int profit =0, i=0;
        while(i < prices.length){

            while(i < prices.length - 1 && prices[i+1] <= prices[i]) // finding local minimum
                i++;
            
            int min = prices[i++];
            while(i < prices.length - 1 && prices[i+1] >= prices[i]) // local maximum
                i++;
            
            profit += i < prices.length ? prices[i++] - min : 0;

        }
        return profit;*/
