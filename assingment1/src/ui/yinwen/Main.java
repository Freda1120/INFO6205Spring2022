package ui.yinwen;

import java.sql.SQLOutput;
import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int[] nums = {2,0,2,1,1,0};
        System.out.println(Arrays.toString(sortColors(nums)));

        int[] citations = {3,0,6,1,5};
        System.out.println((hIndex(citations)));

        int[] nums1 = {1,2,2,1};
        int[] nums2 = {2,2};
        System.out.println(Arrays.toString(intersection(nums1, nums2)));

        int[]arr = {1,2,3,4,5};
        int k = 4;
        int x = 3;
        System.out.println(findClosestElements(arr, k, x));

        String order = "cba";
        String s = "abcd";
        System.out.println(customSortString(order, s));

        int[]pancake = {3,2,4,1};
        System.out.println(pancakeSort(pancake));

        String[] words = {"i","love","leetcode","i","love","coding"};
        int topK = 2;
        System.out.println(topKFrequent(words, topK));
    }

    // sortColors
    private static int[] sortColors(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int cur = 0;
        while (cur <= right){
            if (nums[cur] == 2){
                swap(nums, cur, right);
                right --;
            }
            else if (nums[cur] == 0){
                swap(nums, cur, left);
                cur ++;
                left ++;
            }
            else{
                cur ++;
            }
        }
        return nums;

    }
    private static void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // H-index
    private static int hIndex(int[] citations) {
        Arrays.sort(citations);
        int len = citations.length;
        for (int citation : citations)
            if (citation >= len)
                return len;
            else
                len--;
        return len;
    }

    // intersection
    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length){
            if (nums1[i] < nums2[j]){
                i++;
            }else if (nums1[i] > nums2[j]){
                j++;
            }else{
                set.add(nums1[i]);
                i++;
                j++;
            }

        }
        int[] res = new int[set.size()];
        int k = 0;
        for (Integer num: set){
            res[k++] = num;
        }
        return res;

    }

    // findClosestElements
    private static List<Integer> findClosestElements(int[] arr, int k, int x) {
        int low = 0;
        int high = arr.length - k;
        while (low < high){
            int mid = (low + high) / 2;
            if (x - arr[mid] > arr[mid+k] - x)
                low = mid + 1;
            else
                high = mid;
        }
        List<Integer> result = new ArrayList<>();
        for (int i = low; i < low + k; i++) result.add(arr[i]);
        return result;
    }


    // customSortString
    private static String customSortString(String order, String s) {
        int[] count = new int[26];
        for(char c: s.toCharArray()){
            count[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for(char c: order.toCharArray()){
            for(int i = 0; count[c - 'a'] > i; i++){
                sb.append(c);
            }
            count[c - 'a'] = 0;
        }
        for(char i = 'a'; i <= 'z';i++){
            for(int j  = 0; j < count[i - 'a']; j++){
                sb.append(i);
            }
        }
        return sb.toString();
    }


    // pancakeSort
    private static List<Integer> pancakeSort(int[] arr) {
        List<Integer> res = new ArrayList<>();
        for (int x = arr.length, i; x > 0; x--) {
            for (i = 0; arr[i] != x; i++);
            reverse(arr, i + 1);
            res.add(i + 1);
            reverse(arr, x);
            res.add(x);
        }
        return res;
    }
    private static void reverse(int[] A, int k){
        for (int i = 0, j = k - 1; i < j; i++, j--) {
            int tmp = A[i];
            A[i] = A[j];
            A[j] = tmp;
        }
    }


    // topKFrequent
    private static List<String> topKFrequent(String[] words, int k) {
        List<String> res = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            if (map.containsKey(word)) map.put(word, map.get(word) + 1);
            else map.put(word, 1);
        }
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((a,b) -> Objects.equals(a.getValue(), b.getValue()) ? b.getKey().compareTo(a.getKey()) : (a.getValue() - b.getValue()));

        for(Map.Entry<String, Integer> entry: map.entrySet()){
            pq.offer(entry);
            if (pq.size() > k)
                pq.poll();
        }
        while (!pq.isEmpty()) res.add(0, pq.poll().getKey());
        return res;
    }

}
