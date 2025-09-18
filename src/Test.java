import java.util.Stack;

public class Test {

    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int arr[] = new int[n+m+1];

        int len1 = nums1.length;
        int len2 = nums2.length;

        int i=0, j=0, l=0;
        for(i=0;i<len1 && j<len2; ){
            if(nums1[i]<nums2[j]){
                arr[l++] = nums1[i++];
            }
            else{
                arr[l++] = nums2[j++];
            }
        }

        System.out.println("");


    }

    public static void main(String[] args)
    {
        Test d = new Test();
        d.merge(new int[]{1,2,3,0,0,0}, 3, new int[]{2,5,6}, 3);
    }
}
