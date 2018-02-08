package com.yuri.portablewarrior.mapGenerator;

import com.badlogic.gdx.math.RandomXS128;

import java.util.Random;

public class MapGenerator {

    public static boolean[][] initialiseMap(int width, int hight) {
        boolean[][] map               = new boolean[width][hight];
        float       chanceToStarAlive = 0.45f;
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


}
