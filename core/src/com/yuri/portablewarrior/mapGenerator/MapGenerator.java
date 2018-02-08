package com.yuri.portablewarrior.mapGenerator;

import com.badlogic.gdx.math.RandomXS128;

import java.util.Random;

public class MapGenerator {

    private static final int   BRITH_LIMIT     = 4;
    private static final int   DEATH_LIMIT     = 1;
    private static final int   NUMBER_OF_STEPS = 0;
    private static final float INITIAL_CHANCE  = 0.4f;

    public static boolean[][] generateMap(int width, int hight) {
        boolean[][] map = initialiseMap(width, hight);

        for(int i = 0; i < NUMBER_OF_STEPS; i++) {
           map = doSimulationSteps(map);
        }

        return map;
    }

    public static boolean[][] initialiseMap(int width, int hight) {
        boolean[][] map               = new boolean[width][hight];
        float       chanceToStarAlive = 0.2f;
        RandomXS128 random = new RandomXS128();
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < hight; y++) {
                float number = random.nextInt(100);

                //System.out.println(number);

                if(number < chanceToStarAlive) {
                    map[x][y] = true;
                }

            }
        }
        return map;
    }

    public static boolean[][] doSimulationSteps(boolean[][] oldMap) {
        boolean[][] newMap = new boolean[oldMap.length][oldMap.length];

        for(int x = 0; x < oldMap.length; x++) {
            for(int y = 0; y < oldMap.length; y++) {
                int nbs = countAliveNeigbours(oldMap, x, y);

                if(oldMap[x][y]) {
                    if(nbs < DEATH_LIMIT) {
                        newMap[x][y] = false;
                    } else {
                        newMap[x][y] = true;
                    }
                } else {
                    if(nbs > BRITH_LIMIT) {
                        newMap[x][y] = true;
                    } else {
                        newMap[x][y] = false;
                    }
                }
            }
        }

        return newMap;
    }

    private static int countAliveNeigbours(boolean[][] map, int x, int y) {
        int count = 0;

        for(int i =-1; i < 2; i++){
            for(int j = -1; j < 2; j++) {
                int neighBourd_x = x + i;
                int neighbourd_y = y + j;

                if(i == 0 && j == 0) {

                } else if(neighBourd_x < 0 || neighbourd_y < 0 ||
                        neighBourd_x >= map.length || neighbourd_y >= map[0].length ) {
                    count++;
                } else if(map[neighBourd_x][neighbourd_y]) {
                    count++;
                }
            }
        }
        return count;
    }
}
