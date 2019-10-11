package bsu.edu.cs222;

public class TicTacToeGameState {

    private int[] row1 = new int[3];
    private int[] row2 = new int[3];
    private int[] row3 = new int[3];

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
        if(checkThreeInARow(row1[0], row2[1], row3[2]) != 0){
            return row1[0];
        }

        if(checkThreeInARow(row1[2], row2[1], row3[0]) != 0){
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
}
