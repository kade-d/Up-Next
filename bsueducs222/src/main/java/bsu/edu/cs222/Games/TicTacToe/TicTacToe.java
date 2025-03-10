package bsu.edu.cs222.Games.TicTacToe;

import java.util.ArrayList;

public class TicTacToe {

    public final TicTacToeGameState gameState = new TicTacToeGameState();
    public String victor = null;

    public void playerMove(int cell){
        gameState.addMove(cell, 1);
        checkVictory();
    }

    int computerMove() {
        ArrayList<int[]> lines = gameState.lines;
        int lineIndex = -1;
        int cellIndex = -1;
        int priorityLineIndex = -1;
        int priorityCellIndex = -1;
        for(int i = 0; i < lines.size(); i++) {
            if(findCellToBeMarked(lines.get(i)) != -1){
                if(findCellToBeMarked(lines.get(i)) > 2){
                    priorityLineIndex = i;
                    priorityCellIndex = findCellToBeMarked(lines.get(i)) - 3;
                }
                else {
                    lineIndex = i;
                    cellIndex = findCellToBeMarked(lines.get(i));
                }
            }
        }
        if(priorityCellIndex != -1) {
            int cell = findCellIndexFromLineIndex(priorityLineIndex, priorityCellIndex);
            gameState.addMove(cell, 2);
            checkVictory();
            return cell;
        }
        else if(lineIndex == -1){
            if(gameState.cells[4] == 0 && Math.floor(Math.random() * 2) == 0){
                return 4;
            }
            return -1;
        }
        else{
            int cell = findCellIndexFromLineIndex(lineIndex, cellIndex);
            gameState.addMove(cell, 2);
            checkVictory();
            return cell;
        }
    }


    private int findCellToBeMarked(int[] line) {
        int totalXCells = 0;
        int totalOCells = 0;
        for(int i = 0; i < 3; i++){
            if(line[i] == 2){
                totalOCells++;
            }
            if(line[i] == 1){
                totalXCells++;
            }
            if(totalOCells == 2 && totalXCells == 0){
                if(findEmptyCell(line) != -1) {
                    return findEmptyCell(line) + 3;
                }
            }
            if(totalXCells == 2 && totalOCells == 0){
                return findEmptyCell(line);
            }
        }
        return -1;
    }

    private int findEmptyCell(int[] line) {
        for(int i = 0; i < line.length; i++){
            int cellValue = line[i];
            if(cellValue == 0){
                return i;
            }
        }
        return -1;
    }

    private int findCellIndexFromLineIndex(int lineIndex, int cellIndex) {
        switch (lineIndex){
            case 0: //row1
                return extractRow1(cellIndex);

            case 1: //row2
                return extractRow2(cellIndex);

            case 2: //row3
                return extractRow3(cellIndex);

            case 3: //col1
                return extractCol1(cellIndex);

            case 4: //col2
                return extractCol2(cellIndex);

            case 5: //col3
                return extractCol3(cellIndex);

            case 6: //diag1
                return extractDiag1(cellIndex);

            case 7: //diag2
                return extractDiag2(cellIndex);
        }
        return -1;
    }

    private int extractRow1(int cellIndex) {
        if(cellIndex == 0){
            return 0;
        }
        if(cellIndex == 1){
            return 1;
        }
        if(cellIndex == 2){
            return 2;
        }
        return -1;
    }

    private int extractRow2(int cellIndex) {
        if(cellIndex == 0){
            return 3;
        }
        if(cellIndex == 1){
            return 4;
        }
        if(cellIndex == 2){
            return 5;
        }
        return -1;
    }

    private int extractRow3(int cellIndex) {
        if(cellIndex == 0){
            return 6;
        }
        if(cellIndex == 1){
            return 7;
        }
        if(cellIndex == 2){
            return 8;
        }
        return -1;
    }

    private int extractCol1(int cellIndex) {
        if(cellIndex == 0){
            return 0;
        }
        if(cellIndex == 1){
            return 3;
        }
        if(cellIndex == 2){
            return 6;
        }
        return -1;
    }

    private int extractCol2(int cellIndex) {
        if(cellIndex == 0){
            return 1;
        }
        if(cellIndex == 1){
            return 4;
        }
        if(cellIndex == 2){
            return 7;
        }
        return -1;
    }

    private int extractCol3(int cellIndex) {
        if(cellIndex == 0){
            return 2;
        }
        if(cellIndex == 1){
            return 5;
        }
        if(cellIndex == 2){
            return 8;
        }
        return -1;
    }

    private int extractDiag1(int cellIndex) {
        if(cellIndex == 0){
            return 0;
        }
        if(cellIndex == 1){
            return 4;
        }
        if(cellIndex == 2){
            return 8;
        }
        return -1;
    }

    private int extractDiag2(int cellIndex) {
        if(cellIndex == 0){
            return 2;
        }
        if(cellIndex == 1){
            return 4;
        }
        if(cellIndex == 2){
            return 6;
        }
        return -1;
    }

    private void checkVictory() {
        if(gameState.checkBoard() != 0){
            declareVictory(gameState.checkBoard());
        }
    }

    private void declareVictory(int victor){
        if(victor == 1) {
            this.victor = "Player";
        }
        else if(victor == 2) {
            this.victor = "Computer";
        }
    }
}
