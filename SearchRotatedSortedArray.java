import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * 
 */
public class SearchRotatedSortedArray {


    /**
     * Given the array nums after the rotation and an integer target, 
     * return the index of target if it is in nums, 
     * or -1 if it is not in nums.
     * 
     * Runtime: 0 ms, faster than 100.00% of Java online submissions.
     * Memory Usage: 38.3 MB, less than 61.12% of Java online submissions.
     */
    static int search0(int[] nums, int target) {

        // **** sanity check(s) ****
        if (nums.length == 1 && target == nums[0]) 
            return 0;

        // **** initialization ****
        int left        = 0;
        int right       = nums.length - 1;
     
        // **** find start or pivot ****
        while (left < right) {

            // **** compute mid point ****
            int mid = left + (right - left) / 2;

            // **** check if target was found ****
            if (nums[mid] == target)
                return mid;

            // **** decide which way to go ****
            if (nums[mid] > nums[right])
                left = mid + 1;         // go right
            else
                right = mid;            // go left
        }

        // **** ****
        int start   = left;
        left        = 0;
        right       = nums.length - 1;

        // ???? ????
        System.out.println("<<< start: " + start);

        // **** decide which part to search on ****
        if (target >= nums[start] && target <= nums[right])
            left = start;
        else
            right = start;

        // ???? ????
        // System.out.println("<<< left: " + left + " right: " + right);

        // **** regular binary search (<=) ****
        while (left <= right) {

            // **** ****
            int mid = left + (right - left) / 2;

            // **** ****
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                left = mid + 1;         // go right
            else
                right = mid - 1;        // go left
        }

        // **** target NOT found ****
        return -1;
    }


    /**
     * Given the array nums after the rotation and an integer target, 
     * return the index of target if it is in nums, 
     * or -1 if it is not in nums.
     * 
     * Runtime: 0 ms, faster than 100.00% of Java online submissions.
     * Memory Usage: 38.3 MB, less than 61.12% of Java online submissions.
     */
    static int search(int[] nums, int target) {

        // **** sanity check(s) ****
        if (nums.length == 1 && target == nums[0]) 
            return 0;

        // **** initialization ****
        int left    = 0;
        int right   = nums.length - 1;

        // **** ****
        while (left <= right) {

            // **** compute mid ****
            int mid = (left + right) / 2;

            // **** check if target was found ****
            if (nums[mid] == target)
                return mid;

            // **** left sorted portion ****
            if (nums[left] <= nums[mid]) {
                if (target > nums[mid] || target < nums[left])
                    left = mid + 1;     // go left
                else 
                    right = mid - 1;    // go right
            }

            // **** right sorted portion ****
            else {
                if (target < nums[mid] || target > nums[right])
                    right = mid - 1;    // go right
                else 
                    left = mid + 1;     // go left
            }
        }

        // **** target NOT found ****
        return -1;
    }

    
    /**
      * Test scaffold.
      * NOT PART OF SOLUTION
      * @throws IOException
      */
    public static void main(String[] args) throws IOException {
          
        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // **** read and create array of int[] ****
        int[] nums = Arrays.stream(br.readLine().trim().split(","))
                        .mapToInt(Integer::parseInt)
                        .toArray();

        // **** read target ****
        int target = Integer.parseInt(br.readLine().trim());

        // **** close buffered reader ****
        br.close();

        // ???? ????
        System.out.println("main <<< nums: " + Arrays.toString(nums));
        System.out.println("main <<< target: " + target);

        // **** call method of interest and display result ****
        System.out.println("main <<< output: " + search0(nums, target));

        // **** call method of interest and display result ****
        System.out.println("main <<< output: " + search(nums, target)); 
    }
}