package ui.yinwen;

import java.sql.SQLOutput;
import java.util.*;

public class Main {
    // Yinwen Jiang
    // NUID: 002193745


    public static void main(String[] args) {
	// write your code here
        // 75 Sort Colors
        int[] nums = {2,0,2,1,1,0};
        System.out.println(Arrays.toString(sortColors(nums)));

        // 229 Majority Element II
        int[] num = {3,2,3};
        System.out.println(majorityElement(num));

        // 274 H-Index
        int[] citations = {3,0,6,1,5};
        System.out.println((hIndex(citations)));

        // 349 Intersection of Two Arrays
        int[] nums1 = {1,2,2,1};
        int[] nums2 = {2,2};
        System.out.println(Arrays.toString(intersection(nums1, nums2)));

        // 658 Find K Closest Elements
        int[]arr = {1,2,3,4,5};
        int k = 4;
        int x = 3;
        System.out.println(findClosestElements(arr, k, x));

        // 767 Reorganize String
        String str = "aab";
        System.out.println(reorganizeString(str));

        // 791 Custom Sort String
        String order = "cba";
        String s = "abcd";
        System.out.println(customSortString(order, s));

        // 969 Pancake Sorting
        int[]pancake = {3,2,4,1};
        System.out.println(pancakeSort(pancake));

        // 1636 Sort Array by Increasing Frequency
        int[] freqNums = {1,1,2,2,2,3};
        System.out.println(Arrays.toString(frequencySort(freqNums)));

        // 692 Top K Frequent Words
        String[] words = {"i","love","leetcode","i","love","coding"};
        int topK = 2;
        System.out.println(topKFrequent(words, topK));


    }

    // 75 Sort Colors
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

    // 229 Majority Element II
    private static List<Integer> majorityElement(int[] nums) {
        if (nums == null || nums.length == 0)
            return new ArrayList<Integer>();
        List<Integer> result = new ArrayList<Integer>();
        int num1 = nums[0], num2 = nums[0], count1 = 0, count2 = 0, len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] == num1)
                count1++;
            else if (nums[i] == num2)
                count2++;
            else if (count1 == 0) {
                num1 = nums[i];
                count1 = 1;
            } else if (count2 == 0) {
                num2 = nums[i];
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] == num1)
                count1++;
            else if (nums[i] == num2)
                count2++;
        }
        if (count1 > len / 3)
            result.add(num1);
        if (count2 > len / 3)
            result.add(num2);
        return result;
    }

    // 274 H-Index
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

    // 349 Intersection of Two Arrays
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

    // 658 Find K Closest Elements
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

    // 767 Reorganize String
    private static String reorganizeString(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            int count = map.getOrDefault(c, 0) + 1;
            if (count > (s.length() + 1) / 2)
                return "";
            map.put(c, count);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);

        for (char c : map.keySet()) {
            pq.add(new int[] {c, map.get(c)});
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int[] first = pq.poll();
            if (sb.length() == 0 || first[0] != sb.charAt(sb.length() - 1)) {
                sb.append((char) first[0]);
                if (--first[1] > 0) {
                    pq.add(first);
                }
            } else {
                int[] second = pq.poll();
                sb.append((char) second[0]);
                if (--second[1] > 0) {
                    pq.add(second);
                }
                pq.add(first);
            }
        }
        return sb.toString();
    }


    // 791 Custom Sort String
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


    // 969 Pancake Sorting
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

    // 1636 Sort Array by Increasing Frequency
    private static int[] frequencySort(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Map.Entry<Integer, Integer>> list = new ArrayList(map.entrySet());
        Collections.sort(list, (a,b) -> a.getValue() == b.getValue() ? b.getKey() - a.getKey() : a.getValue() - b.getValue());
        int index = 0;
        int[] res = new int[nums.length];
        for (Map.Entry<Integer, Integer> entry : list) {

            int count = entry.getValue();
            int key = entry.getKey();

            for (int i=0; i<count; i++) {
                res[index++] = key;
            }

        }
        return res;
    }

    // 692 Top K Frequent Words
    private static List<String> topKFrequent(String[] words, int k) {
        List<String> res = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            if (map.containsKey(word)) map.put(word, map.get(word) + 1);
            else map.put(word, 1);
        }
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((a,b) -> a.getValue() == b.getValue() ? b.getKey().compareTo(a.getKey()) : (a.getValue() - b.getValue()));

        for(Map.Entry<String, Integer> entry: map.entrySet()){
            pq.offer(entry);
            if (pq.size() > k)
                pq.poll();
        }
        while (!pq.isEmpty()) res.add(0, pq.poll().getKey());
        return res;
    }
}
