package com.company;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by shwet.shashank on 20/09/14.
 */
public class ConstraintPropagation {
    private int[][] grid;
    private static ArrayList<Integer> allNumbers;

    static {
        allNumbers = new ArrayList<Integer>();
        allNumbers.add(new Integer(1));allNumbers.add(new Integer(2));allNumbers.add(new Integer(3));allNumbers.add(new Integer(4));allNumbers.add(new Integer(5));allNumbers.add(new Integer(6));allNumbers.add(new Integer(7));allNumbers.add(new Integer(8));allNumbers.add(new Integer(9));
    }

    public ConstraintPropagation(int[][] grid) {
        this.grid = grid;
    }

    private HashMap<Integer, ArrayList<Integer>> generatePossibleMap(int[][] grid) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
        for(int i=0;i<81;i++) {
            ArrayList<Integer> tmp = new ArrayList<Integer>();
            tmp.addAll(allNumbers);
            map.put(i, tmp);
        }
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if (grid[i][j] != 0){
                    map.put(i*9+j, new ArrayList<Integer>());
                    propagate(i, j, grid, map);
                }
            }
        }
        return map;
    }

    private void propagate(int row, int col, int[][] grid, HashMap<Integer,ArrayList<Integer>> map) {
        int number = grid[row][col];
        for(int i=0;i<9; i++) {
            ArrayList<Integer> tmp = map.get(i*9 + col);
            if (tmp.contains(new Integer(number))) tmp.remove(new Integer(number));
            map.put(i*9+col, tmp);
        }

        for(int i=0;i<9;i++) {
            ArrayList<Integer> tmp = map.get(row*9 + i);
            if (tmp.contains(new Integer(number))) tmp.remove(new Integer(number));
            map.put(row*9+i, tmp);
        }

        int top = row/3;
        int left = col/3;
        for(int i=top*3; i< (top+1)*3; i++) {
            for(int j=left*3; j<(left+1)*3; j++){
                ArrayList<Integer> tmp = map.get(i*9+j);
                if (tmp.contains(new Integer(number))) tmp.remove(new Integer(number));
                map.put(i*9+j, tmp);
            }
        }
    }

    private int findWithLeastPossibility(HashMap<Integer, ArrayList<Integer>> map){
        int min = 10000;
        int minIndex = -1;
        for(Integer k: map.keySet()){
            if(map.get(k).size() > 0 && map.get(k).size() < min) {
                min = map.get(k).size();
                minIndex = k;
            }
        }
        return minIndex;
    }

    public boolean solve() {
        return solve(this.grid, generatePossibleMap(this.grid));
    }

    private boolean noClash(int[][] grid, HashMap<Integer,ArrayList<Integer>> possibleMap) {
        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++) {
                if(grid[i][j] == 0 && possibleMap.get(i*9+j).size()==0) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean solve(int[][] grid, HashMap<Integer, ArrayList<Integer>> map) {
        int k = findWithLeastPossibility(map);
        if (k==-1) return true;
        ArrayList<Integer> possibilities = map.get(k);
        for(Integer possible: possibilities) {
          grid[k/9][k%9] = possible;
          HashMap<Integer, ArrayList<Integer>> possibleMap = generatePossibleMap(grid);
          if(noClash(grid, possibleMap) && solve(grid, possibleMap)) return true;
        }
        grid[k/9][k%9] = 0;
        return false;
    }

    public void display() {
        for(int i=0;i<9;i++) {
            for(int j=0;j<9;j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
            System.out.println();
        }
    }

    private void displayMap(HashMap<Integer, ArrayList<Integer>> map) {
        for(Integer k: map.keySet()){
            System.out.print(k + " : ");

            for(Integer j: map.get(k)) {
                System.out.print(j + " ");
            }
            System.out.println();
            System.out.println();
        }
    }


}
