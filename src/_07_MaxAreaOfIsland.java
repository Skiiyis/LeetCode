import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个包含了一些 0 和 1的非空二维数组 grid , 一个 岛屿 是由四个方向 (水平或垂直) 的 1 (代表土地) 构成的组合。你可以假设二维矩阵的四个边缘都被水包围着。
 * <p>
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0。)
 * <p>
 * 示例 1:
 * <p>
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,1,1,0,1,0,0,0,0,0,0,0,0],
 * [0,1,0,0,1,1,0,0,1,0,1,0,0],
 * [0,1,0,0,1,1,0,0,1,1,1,0,0],
 * [0,0,0,0,0,0,0,0,0,0,1,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 对于上面这个给定矩阵应返回 6。注意答案不应该是11，因为岛屿只能包含水平或垂直的四个方向的‘1’。
 * <p>
 * 示例 2:
 * <p>
 * [[0,0,0,0,0,0,0,0]]
 * 对于上面这个给定的矩阵, 返回 0。
 * <p>
 * 注意: 给定的矩阵grid 的长度和宽度都不超过 50。
 */
public class _07_MaxAreaOfIsland {
    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        };
        System.out.println("maxArea:" + maxAreaOfIsland(grid));
    }

    public static int maxAreaOfIsland(int[][] grid) {
        int height = grid.length;
        int width = grid[0].length;
        int maxArea = 0;
        List<Integer> points = new ArrayList<>();
        for (int point = 0; point < height * width; point++) {
            if (points.contains(point)) {
                continue;
            }
            int x = point / width;
            int y = point % width;
            int pointValue = grid[x][y];
            if (pointValue == 0) {
                continue;
            }
            List<Integer> linkedPoints = new ArrayList<>();
            searchLinkedPoint(linkedPoints, grid, x, y);
            maxArea = Math.max(maxArea, linkedPoints.size());
            System.out.println("area:" + linkedPoints.size());
            points.addAll(linkedPoints);
        }
        return maxArea;
    }

    public static void searchLinkedPoint(List<Integer> linkedPoints, int[][] grid, int x,
                                         int y) {
        int height = grid.length;
        int width = grid[0].length;
        int point = width * x + y;

        int pointValue = grid[x][y];
        if (pointValue == 0) {
            return;
        }
        if (linkedPoints.contains(point)) {
            return;
        }
        linkedPoints.add(point);
        /*
                       x,y-1
                x-1,y   x,y    x+1,y
                       x,y+1
         */
        if (x - 1 >= 0) {
            searchLinkedPoint(linkedPoints, grid, x - 1, y);
        }
        if (y - 1 >= 0) {
            searchLinkedPoint(linkedPoints, grid, x, y - 1);
        }
        if (x + 1 < height) {
            searchLinkedPoint(linkedPoints, grid, x + 1, y);
        }
        if (y + 1 < width) {
            searchLinkedPoint(linkedPoints, grid, x, y + 1);
        }
    }
}
