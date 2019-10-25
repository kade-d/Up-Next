package bsu.edu.cs222;

import java.util.ArrayList;

public class TicTacToeGameState {

    public int[] cells = new int[9];
    public int[] row1 = new int[3];
    public int[] row2 = new int[3];
    public int[] row3 = new int[3];
    public int[] col1 = new int[3];
    public int[] col2 = new int[3];
    public int[] col3 = new int[3];
    public int[] diag1 = new int[3];
    public int[] diag2 = new int[3];
    public ArrayList<int[]> lines = new ArrayList<>();

    public TicTacToeGameState() {
        updateLines();
    }

    public void updateLines(){
        lines.clear();
        lines.add(row1);
        lines.add(row2);
        lines.add(row3);
        lines.add(col1);
        lines.add(col2);
        lines.add(col3);
        lines.add(diag1);
        lines.add(diag2);
    }

    public void reset(){
        cells = new int[9];
        row1 = new int[3];
        row2 = new int[3];
        row3 = new int[3];
        col1 = new int[3];
        col2 = new int[3];
        col3 = new int[3];
        diag1 = new int[3];
        diag2 = new int[3];
        updateLines();
    }

    public void addMove(int cell, int player){
        if(cells[cell] == 0){
            cells[cell] = player;
            addMoveToRow(cell, player);
            addMoveToColumn(cell, player);
            addMoveToDiagonal(cell, player);
            updateLines();
        }
    }

    private void addMoveToRow(int cell, int player){
        if(cell <= 2){
            row1[cell] = player;
        }
        else if(cell <= 5){
            row2[cell - 3] = player;
        }
        else if(cell <= 8){
            row3[cell - 6] = player;
        }
    }

    private void addMoveToColumn(int cell, int player){
        if(cell % 3 == 0){
            col1[cell / 3] = player;
        }
        else if(cell % 3 == 1){
            col2[cell / 3] = player;
        }
        else if(cell % 3 == 2){
            col3[cell / 3] = player;
        }
    }

    private void addMoveToDiagonal(int cell, int player){
        if(cell == 0){
            diag1[0] = player;
        }
        if(cell == 2){
            diag2[0] = player;
        }
        if(cell == 4){
            diag1[1] = player;
            diag2[1] = player;
        }
        if(cell == 6){
            diag2[2] = player;
        }
        if(cell == 8){
            diag1[2] = player;
        }
    }

    public int checkBoard(){
        if(checkHorizontals() != 0){
            return checkHorizontals();
        }

        if(checkVerticals() != 0){
            return checkVerticals();
        }

        if(checkDiagonals() != 0){
            return checkDiagonals();
        }

        return 0;
    }

    private int checkHorizontals(){
        if(checkThreeInARow(row1[0], row1[1], row1[2]) != 0){
            return row1[0];
        }

        if(checkThreeInARow(row2[0], row2[1], row2[2]) != 0){
            return row2[0];
        }

        if(checkThreeInARow(row3[0], row3[1], row3[2]) != 0){
            return row3[0];
        }

        return 0;
    }

    private int checkVerticals(){
        if(checkThreeInARow(row1[0], row2[0], row3[0]) != 0){
            return row1[0];
        }

        if(checkThreeInARow(row1[1], row2[1], row3[1]) != 0){
            return row1[1];
        }

        if(checkThreeInARow(row1[2], row2[2], row3[2]) != 0){
            return row1[2];
        }

        return 0;
    }

    private int checkDiagonals(){
        if(checkThreeInARow(diag1[0], diag1[1], diag1[2]) != 0){
            return row1[0];
        }

        if(checkThreeInARow(diag2[0], diag2[1], diag2[2]) != 0){
            return row1[2];
        }

        return 0;
    }

    private int checkThreeInARow(int num1, int num2, int num3){
        if(cellIsEmpty(num1)){
            return 0;
        }
        else if(num1 == num2 && num2 == num3){
            return num1;
        }
        return 0;
    }

    private boolean cellIsEmpty(int num){
        return (num == 0);
    }

    public ArrayList<Integer> findOpenCells(){
        ArrayList<Integer> openCells = new ArrayList<>();

        for(int i = 0; i < 9; i++){
            if(cells[i] == 0){
                openCells.add(i);
            }
        }

        return openCells;
    }
}
