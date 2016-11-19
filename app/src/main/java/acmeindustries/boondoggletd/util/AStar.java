package acmeindustries.boondoggletd.util;

import java.util.Comparator;
import java.util.PriorityQueue;

public class AStar {

    // lots of this taken from http://www.codebytes.in/2015/02/a-shortest-path-finding-algorithm.html

    public static final int V_H_COST = 10;

    Cell[][] grid;
    PriorityQueue<Cell> open;

    boolean closed[][];
    int width, height, startX, startY, endX, endY;
    private Object[][] blocked;

    private float[][] finalPath;


    private static Comparator<Cell> comparator = new Comparator<Cell>() {
        @Override
        public int compare(Cell o1, Cell o2) {
            return o1.getFinalCost()<o2.getFinalCost()?-1:
                    o1.getFinalCost()>o2.getFinalCost()?1:0;
        }
    };

    public AStar(int x, int y, int startX, int startY, int endX, int endY, Object[][] blocked){

        this.width = x;
        this.height = y;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.blocked = blocked;
        resetPath();
    }

    public void resetPath(){
        grid = new Cell[height][width];
        closed = new boolean[height][width];
        open = new PriorityQueue<>(24,comparator);
        setStartCell(startX, startY);
        setEndCell(endX, endY);

        for(int i=0;i<width;++i){
            for(int j=0;j<height;++j){
                grid[j][i] = new Cell(i, j);
                grid[j][i].setHeuristicCost(Math.abs(i-endX)+Math.abs(j-endY));
            }
        }
        grid[startY][startX].setFinalCost(0);
        for(int i=0;i<blocked[0].length;++i){
            for(int j=0;j<blocked.length;++j){
                if(blocked[j][i] != null){
                    setBlocked(i, j);
                }
            }
        }
    }

    public void setBlocked(int x, int y){
        grid[y][x] = null;
    }

    public void setStartCell(int x, int y){
        startX = x;
        startY = y;
    }

    public void setEndCell(int x, int y){
        endX = x;
        endY = y;
    }

    void checkAndUpdateCost(Cell current, Cell t, int cost){
        if (t == null || closed[t.getY()][t.getX()]) return;
        int t_final_cost = t.getHeuristicCost()+cost;

        boolean inOpen = open.contains(t);
        if( !inOpen || t_final_cost  < t.getFinalCost()){
            t.setFinalCost(t_final_cost);
            t.setParent(current);
            if(!inOpen) open.add(t);
        }

    }

    public boolean findPath(){
        resetPath();

        open.add(grid[startY][startX]);

        Cell current;

        while(true) {

            current = open.poll();
            if(current==null) break;
            closed[current.getY()][current.getX()]=true;

            if(current.equals(grid[endY][endX])) break;

            Cell t;
            if(current.getX()-1>=0) {
                t = grid[current.getY()][current.getX() - 1];
                checkAndUpdateCost(current, t, current.getFinalCost()+V_H_COST);
            }
            if(current.getX()+1<grid[0].length) {
                t = grid[current.getY()][current.getX() + 1];
                checkAndUpdateCost(current, t, current.getFinalCost()+V_H_COST);
            }
            if(current.getY()-1>=0){
                t = grid[current.getY()-1][current.getX()];
                checkAndUpdateCost(current, t, current.getFinalCost()+V_H_COST);
            }
            if(current.getY()+1<grid.length){
                t = grid[current.getY()+1][current.getX()];
                checkAndUpdateCost(current, t, current.getFinalCost()+V_H_COST);
            }
        }
        if(closed[endY][endX]){
            // walk list twice, consider a better implementation
            int count = 0;
            current = grid[endY][endX];
            while(current.getParent()!=null){
                count++;
                current = current.getParent();
            }
            finalPath = new float[count+1][2];
            current = grid[endY][endX];
            finalPath[count][0] = current.getX();
            finalPath[count][1] = current.getY();
            while(current.getParent()!=null){
                count--;
                current = current.getParent();
                finalPath[count][0] = current.getX();
                finalPath[count][1] = current.getY();
            }
            // path found!!!
            return true;
        }else{
            // no path found
            return false;
        }
    }

    public float[][] getFinalPath(){
        return finalPath;
    }
}
