import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Vishal_Mukta on 12/14/2018.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Enter numbers:");
        Scanner reader = new Scanner(System.in);
        String line = reader.nextLine();
        String[] words = line.split(" ");
        int[] nums = new int[words.length];
        System.out.println("Numbers entered:");
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                nums[i] = Integer.valueOf(words[i]);
                System.out.println(nums[i]);
            }
        }
        System.out.println("Enter target:");
        int target = reader.nextInt();
        int[] result = twoSum(nums, target);
        System.out.printf("Result: [%d, %d]", result[0], result[1]);
    }

    private static int[] twoSum(int[] nums, int target) {
        // Brute force solution
/*
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
*/
        // Optimal solution
        int x = nums.length;
        int y = 0;
        int l = 0;
        int res[] = {-1, -1};
        int unsorted[] = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            unsorted[i] = nums[i];
        }
        Arrays.sort(nums);
        for (x = 0; x < nums.length; x++) {
            y = find(nums, target - nums[x], x + 1, nums.length - 1);
            if (y != -1) {
                break;
            }
        }
        if (x >= nums.length) {
            return res;
        }
        boolean foundX = false;
        boolean foundY = false;
        for (int k = 0; k < unsorted.length; k++) {
            if (!foundX && (unsorted[k] == nums[x])) {
                res[l++] = k;
                foundX = true;
                continue;
            }
            if (!foundY && (unsorted[k] == nums[y])) {
                res[l++] = k;
                foundY = true;
            }
            if (foundX && foundY) {
                break;
            }
        }
        Arrays.sort(res);
        return res;
    }

    private static int find(int[] nums, int n, int first, int last) {
        if (first > last) {
            return -1;
        }
        int i = first + ((last - first) / 2);
        if (nums[i] == n) {
            return i;
        } else if (nums[i] < n) {
            return find(nums, n, i + 1, last);
        }
        return find(nums, n, first, i - 1);
    }
}
