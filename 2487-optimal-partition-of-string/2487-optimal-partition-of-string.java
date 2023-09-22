class Solution {
    public int partitionString(String s) {
        int hash[] = new int[26];
        int count = 0;
        for(int i = 0 ; i < s.length(); i ++){
            if(hash[s.charAt(i) - 'a'] == 1){
                count++;
                hash = new int[26];
            }
            hash[s.charAt(i)-'a']++;
        }
        return count+1;
    }
}