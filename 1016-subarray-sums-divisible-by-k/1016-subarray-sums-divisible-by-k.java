class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        count.put(0,1);
        int prefix = 0,  res = 0;
        for(int ele: nums){
            prefix= (prefix+ (ele%k)+ k) % k;
            res += count.getOrDefault(prefix,0);
            count.put(prefix, count.getOrDefault(prefix, 0) + 1);
        }
        return res;
    }
}