package com.java.practice;

import java.util.*;

public class SubDomainVisitCount {

    public static List<String> subdomainVisits(String[] cpdomains) {
        Map<String,Integer> map=new HashMap<>();
        for(int i=0;i<cpdomains.length;i++){
            String s=cpdomains[i];

            String[] arrS=s.split(" ");
            int num=Integer.parseInt(arrS[0]);
            String[] arrD=arrS[1].split("\\.");

            StringBuilder sb=new StringBuilder();
            for(int j=arrD.length-1;j>=0;j--){
                if(j==arrD.length-1){
                    sb.append(arrD[j]);
                }else {
                    sb.insert(0,".");
                    sb.insert(0,arrD[j]);
                }
                String val=sb.toString();
                if(!map.containsKey(val)){
                    map.put(val,num);
                }else {
                    map.put(val,map.get(val)+num);
                }
            }
        }
        ArrayList<String> ans =new ArrayList<>();

        for(String mp:map.keySet()){
            StringBuilder sb=new StringBuilder();
            sb.append(map.get(mp));
            sb.append(" ");
            sb.append(mp);
            ans.add(sb.toString());
        }
        return ans;
    }
    public static void main(String[] args) {
        System.out.println(subdomainVisits(new String[]{"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"}));
    }
}
