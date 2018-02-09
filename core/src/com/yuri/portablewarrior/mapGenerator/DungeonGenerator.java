package com.yuri.portablewarrior.mapGenerator;

import com.badlogic.gdx.math.Vector2;

public class DungeonGenerator {
    private int dimensions  = 20;
    private int maxTunnels  = 50;
    private int maxLenght   = 8;

    private int[][] map;

    public DungeonGenerator() {
        map = new int[dimensions][dimensions];

        for(int x = 0; x < dimensions; x++) {
            for(int y = 0; y < dimensions; y++) {
                map[x][y] = 1;
            }
        }

        createMap();
    }

    public DungeonGenerator(int dimensions, int maxTunnels, int maxLenght) {
        this.dimensions = dimensions;
        this.maxTunnels = maxTunnels;
        this.maxLenght  = maxLenght;
        this.map        = new int [dimensions][dimensions];

        for(int x = 0; x < dimensions; x++) {
            for(int y = 0; y < dimensions; y++) {
                map[x][y] = 1;
            }
        }
        createMap();
    }

    private void createMap() {
        int  tempDimision     = dimensions;
        int  tempMaxTunnels   = maxTunnels;
        int  tempLenght       = maxLenght;

        float curretRow       = (float) Math.floor(Math.random() * dimensions);
        float currentColumn   = (float) Math.floor(Math.random() * dimensions);

        Vector2[] directions = new Vector2[] {new Vector2(-1, 1),
                                              new Vector2(1,0),
                                              new Vector2(0, -1),
                                              new Vector2(0,1)};

        Vector2 lastDirection = new Vector2(1,1);
        Vector2 randomDirection = new Vector2();

        while(tempDimision > 0 && tempLenght > 0 && tempMaxTunnels > 0) {

            do {

                randomDirection.x = (float) Math.floor(Math.random() * directions.length);

            }while ((randomDirection.x == -lastDirection.x && randomDirection.y == -lastDirection.y) ||
                    (randomDirection.x == -randomDirection.x && randomDirection.y == -lastDirection.y));

            float randomLenght = (float) Math.ceil(Math.random() * maxLenght);
            int   tunelLenght  = 0;

            while (tunelLenght < randomLenght) {

               // if((curretRow == 0))

            }
        }
    }

    public int[][] getMap() {
        return map;
    }
}
