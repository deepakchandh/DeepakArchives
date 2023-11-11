class Solution {
    public String shortestPalindrome(String s) {
        String temp = s + "#" + new StringBuilder(s).reverse().toString();
        int[] table = getTable(temp);
        for(int k: table){
            System.out.print(k +"_");
        }
        System.out.println();
        System.out.println(s.substring(table[table.length - 1]));
        return new StringBuilder(s.substring(table[table.length - 1])).reverse().toString() + s;
    }

    public int[] getTable(String s){ // abcd#dcba --> soln: dcbabcd
        int[] table = new int[s.length()];

        int index = 0;
        for(int i = 1; i < s.length(); ){
            if(s.charAt(index) == s.charAt(i)){
                table[i] = ++index;
                i++;
            } else {
                if(index > 0){
                    index = table[index-1];
                } else {
                    index = 0;
                    i ++;
                }
            }
        }
        return table;

    }
}

/*
private int[] computeKMPTable(String s) {
    int[] result = new int[s.length()];         // result[j] is the longest proper prefix that is also a suffix of s[0, j].
    result[0] = 0;   
    for (int i = 0, j = 1; j < s.length(); ++j) {
        if (s.charAt(i) == s.charAt(j)) {
            result[j] = ++i;   
        } else {
            while (i > 0 && s.charAt(i) != s.charAt(j)) {
                i = result[i - 1];
            }
            result[j] = s.charAt(i) == s.charAt(j) ? ++i : 0;
        }
    }
    return result;
}
*/