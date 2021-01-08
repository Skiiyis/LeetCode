/**
 * 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 */
public class _42_TrapRain {

    public static void main(String[] args) {
        System.out.println(rain(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }

    public static int rain(int[] height) {
        int left = 0;
        int right = 0;
        int rainNum = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] > 0) {
                left = i;
                break;
            }
        }
        for (int i = height.length - 1; i >= 0; i--) {
            if (height[i] > 0) {
                right = i;
                break;
            }
        }
        if (left >= right) {
            return 0;
        }
        for (int i = left; i <= right; i++) {
            if (height[i] == 0) {
                rainNum++;
            } else {
                height[i] = height[i] - 1;
            }
        }
        return rainNum + rain(height);
    }
}
