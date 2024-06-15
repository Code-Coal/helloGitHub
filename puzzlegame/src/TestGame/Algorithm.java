package TestGame;

import java.util.*;

import java.util.Random;

public class Algorithm {

    // 0: 无，1: 有雷，2: 标记雷，3: 无雷（已点开），>3: 周围雷的数量, -1: 显示所有雷, -3: 点到雷区域
    public static void algorithm(int[][] data, int x, int y, int m, int n) {
        // 如果点击的是标记的区域，直接返回
        if (data[x][y] == 2) return;

        // 已点击区域标记为3
        data[x][y] = 3;

        // 检查周围8个方向是否有雷
        int[][] directions = {{1,1}, {1,0}, {1,-1}, {0,1}, {0,-1}, {-1,1}, {-1,0}, {-1,-1}};
        boolean noBombsAround = true;

        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (newX > 0 && newX <= m && newY > 0 && newY <= n) {
                if (data[newX][newY] == 1 || data[newX][newY] == 2) {
                    noBombsAround = false;
                    break;
                }
            }
        }

        // 如果周围没有雷，则递归检查周围区域
        if (noBombsAround) {
            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];
                if (newX > 0 && newX <= m && newY > 0 && newY <= n && data[newX][newY] == 0) {
                    algorithm(data, newX, newY, m, n);
                }
            }
        } else {
            // 如果周围有雷，则计算雷的数量
            int bombCount = 0;
            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];
                if (newX > 0 && newX <= m && newY > 0 && newY <= n) {
                    if (data[newX][newY] == 1 || data[newX][newY] == 2) {
                        bombCount++;
                    }
                }
            }
            data[x][y] = 3 + bombCount;  // 将雷的数量存储在当前单元格
        }
    }



    public static void initBoom(int[][] data, int x, int y, int m, int n, int num) {
        // 先标记点击区域及其周围区域
        int[][] directions = {{0,0}, {1,1}, {1,0}, {1,-1}, {0,1}, {0,-1}, {-1,1}, {-1,0}, {-1,-1}};
        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (newX >= 0 && newX <= m && newY >= 0 && newY <= n) {
                data[newX][newY] = 12; // 临时标记
            }
        }

        // 创建一个包含所有可能放置雷的位置的列表
        List<int[]> possiblePositions = new ArrayList<>();
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (data[i][j] == 0) {
                    possiblePositions.add(new int[]{i, j});
                }
            }
        }

        // 随机打乱列表
        Collections.shuffle(possiblePositions);

        // 从打乱的列表中选择前num个位置放置雷
        for (int i = 0; i < num; i++) {
            int[] pos = possiblePositions.get(i);
            data[pos[0]][pos[1]] = 1;
        }

        // 取消临时标记
        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (newX >= 0 && newX <= m && newY >= 0 && newY <= n) {
                data[newX][newY] = 0;
            }
        }
    }
}

