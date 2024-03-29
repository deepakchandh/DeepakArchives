class Solution {
    public static int maxProfit(int[] prices) {


        int profit =0, minVal = prices[0];
        for(int i=1;i<prices.length;i++){
            if(prices[i] < minVal){
                minVal = prices[i];
            }
            else if(prices[i] - minVal > profit){
                profit = prices[i] - minVal;
            }  
        }
        return profit;
        
    }

    public static void main(String[] args) {

    }
}

































/*
int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        
        for(int i=0;i<prices.length;i++){
            if(prices[i] < minPrice){
                minPrice = prices[i];
            }
            else if(prices[i] - minPrice > maxProfit){
                maxProfit = prices[i] - minPrice;
            }      
        }
        return maxProfit;
    }*/