package ui.yinwen;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        // 35 Search Insert Position
        int[]insertNums = {1,3,5,6};
        int target = 5;
        System.out.println(searchInsert(insertNums, target));

        // 540 Single Element in a Sorted Array
        int[] nums = {1,1,2,3,3,4,4,8,8};
        System.out.println(singleNonDuplicate(nums));

        // 153 Find Minimum in Rotated Sorted Array
        int[] rotatedNums = {3,4,5,1,2};
        System.out.println(findMin(rotatedNums));

        // 253 Meeting Rooms II
        int[][] intervals = {{0,30},{5,10},{15,20}};
        System.out.println(minMeetingRooms(intervals));

        // 347 Top K Frequent Elements
        int[] selectNums = {1,1,1,2,2,3};
        int k = 2;
        System.out.println(Arrays.toString(topKFrequent(selectNums, k)));

        // 16 3Sum Closest
        int[] threeNums = {-1,2,1,-4};
        int threeTarget = 1;
        System.out.println(threeSumClosest(threeNums, threeTarget));


        // 57 Insert Interval
        int[][] insertIntervals = {{1,3},{6,9}};
        int[] newInterval = {2,5};
        System.out.println(Arrays.deepToString(insert(insertIntervals, newInterval)));

        // 435 Non-overlapping Intervals
        int[][] nonOverlappingIntervals = {{1,2},{2,3},{3,4},{1,3}};
        System.out.println(eraseOverlapIntervals(nonOverlappingIntervals));

        // 986 Interval List Intersections
        int[][] firstList = {{0,2},{5,10},{13,23},{24,25}};
        int[][] secondList = {{1,5},{8,12},{15,24},{25,26}};
        System.out.println(Arrays.deepToString(intervalIntersection(firstList, secondList)));

        // 18 4Sum
        int[] fourNums = {1,0,-1,0,-2,2};
        int fourSumTarget = 0;
        System.out.println(fourSum(fourNums, fourSumTarget));

    }


    // 35 Search Insert Position
    private static int searchInsert(int[] nums, int target){
        int left = 0, right = nums.length-1;
        while(left <= right){
            int mid = (left+right)/2;
            if(nums[mid] == target)
                return mid;
            else if(nums[mid] > target)
                right = mid-1;
            else
                left = mid+1;
        }
        return left;
    }

    // 540 Single Element in a Sorted Array
    private static int singleNonDuplicate(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right){
            int mid = (left + right) / 2;
            if( (mid % 2 == 0 && nums[mid] == nums[mid +1]) || (mid % 2 == 1 && nums[mid] == nums[mid - 1]))
                left = mid + 1;
            else
                right = mid;
        }
        return nums[left];
    }


    // 153 Find Minimum in Rotated Sorted Array
    private static int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right){
            int mid = (left + right) / 2;
            if (nums[mid] > nums[right])
                left = mid + 1;
            else
                right = mid;
        }
        return nums[left];
    }

    // 253 Meeting Rooms II
    private static int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        PriorityQueue<Integer> pq = new PriorityQueue();
        for(int[] interval : intervals){
            if(!pq.isEmpty() && interval[0] >= pq.peek()){pq.poll();}
            pq.add(interval[1]);
        }
        return pq.size();
    }


    // 347 Top K Frequent Elements
    private static int[] topKFrequent(int[] nums, int k) {

        if (k == nums.length) {
            return nums;
        }

        Map<Integer, Integer> count = new HashMap();
        for (int n: nums) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }

        Queue<Integer> heap = new PriorityQueue<>((n1, n2) -> count.get(n1) - count.get(n2));

        for (int n: count.keySet()) {
            heap.add(n);
            if (heap.size() > k) heap.poll();
        }

        int[] top = new int[k];
        for(int i = k - 1; i >= 0; --i) {
            top[i] = heap.poll();
        }
        return top;
    }


    // 16 3Sum Closest
    private static int threeSumClosest(int[] num, int target) {
        int result = num[0] + num[1] + num[num.length - 1];
        Arrays.sort(num);
        for (int i = 0; i < num.length - 2; i++) {
            int start = i + 1, end = num.length - 1;
            while (start < end) {
                int sum = num[i] + num[start] + num[end];
                if (sum > target) {
                    end--;
                } else {
                    start++;
                }
                if (Math.abs(sum - target) < Math.abs(result - target)) {
                    result = sum;
                }
            }
        }
        return result;
    }


    // 57 Insert Interval
    private static int[][] insert(int[][] intervals, int[] newInterval) {
        int count = 0;
        int[][] result = new int[intervals.length + 1][2];
        for(int i = 0; i < intervals.length; i++){
            if(newInterval == null || intervals[i][1] < newInterval[0]){
                result[count++] = intervals[i];
            }
            else if(intervals[i][0] > newInterval[1]){
                result[count++] = newInterval;
                result[count++] = intervals[i];
                newInterval = null;
            } else{
                newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
                newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            }
        }
        if(newInterval != null){
            result[count++] = (newInterval);
        }
        int[][] output = new int[count][2];
        for(int i =0; i < count; i++){
            output[i] = result[i];
        }
        return output;
    }


    // 435 Non-overlapping Intervals
    private static int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length == 0 || intervals == null)
            return 0;
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        int count = 0;
        int prevEnd = intervals[0][1];
        for(int i = 1;  i < intervals.length; i++){
            if(intervals[i][0] < prevEnd)
                count++;
            else{
                prevEnd = intervals[i][1];
            }
        }
        return count;
    }


    // 986 Interval List Intersections
    private static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        if(firstList.length==0 || secondList.length==0) return new int[0][0];
        int i = 0;
        int j = 0;
        int startMax = 0, endMin = 0;
        List<int[]> ans = new ArrayList<>();

        while(i<firstList.length && j<secondList.length){
            startMax = Math.max(firstList[i][0],secondList[j][0]);
            endMin = Math.min(firstList[i][1],secondList[j][1]);
            if(endMin>=startMax){
                ans.add(new int[]{startMax,endMin});
            }
            if(endMin == firstList[i][1]) i++;
            if(endMin == secondList[j][1]) j++;
        }

        return ans.toArray(new int[ans.size()][2]);
    }


    // 18 4Sum
    private static List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums.length < 4)
            return new LinkedList<>();
        Arrays.sort(nums);
        List<List<Integer>> ans = new LinkedList<>();
        for (int i = 0; i < nums.length - 3; i++) {
            if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target)
                return ans;
            if ((long) nums[i] + nums[nums.length - 3] + nums[nums.length - 2] + nums[nums.length - 1] < target)
                continue;
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue;
                int tmp = target - nums[i] - nums[j];
                int m = nums.length - 1;
                for (int k = j + 1; k < nums.length - 1; k++) {
                    if (k > j + 1 && nums[k] == nums[k - 1])
                        continue;
                    while (k < m && nums[k] + nums[m] > tmp) {
                        m--;
                    }
                    if (k < m && nums[k] + nums[m] == tmp) {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[k], nums[m]));
                    }
                }
            }
        }
        return ans;
    }
}
