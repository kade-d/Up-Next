package bsu.edu.cs222;

import java.util.ArrayList;

public class TicTacToe {

    //TODO Author: Adam

    public TicTacToeGameState gameState = new TicTacToeGameState();
    public String victor = null;

    public void playerMove(int cell){
        gameState.addMove(cell, 1);
        checkVictory();
    }

    public int computerMove(){
        ArrayList<int[]> lines = gameState.lines;
        int lineIndex = -1;
        int cellIndex = -1;
        for(int i = 0; i < lines.size(); i++) {
            if(findCellToBeMarked(lines.get(i)) != -1){
                lineIndex = i;
                cellIndex = findCellToBeMarked(lines.get(i));
            }
        }
        if(lineIndex == -1){
            return -1;
        }
        else {
            int cell = findCellIndexFromLineIndex(lineIndex, cellIndex);
            gameState.addMove(cell, 2);
            checkVictory();
            return cell;
        }
    }


    public int findCellToBeMarked(int[] line){
        int totalXCells = 0;
        int totalOCells = 0;
        int xCellsInARow = 0;
        int oCellsInARow = 0;
        for(int i = 0; i < 3; i++){
            if(line[i] == 2){
                oCellsInARow++;
                totalOCells++;
                xCellsInARow = 0;
            }
            if(line[i] == 1){
                xCellsInARow++;
                totalXCells++;
                oCellsInARow = 0;
            }
            if(oCellsInARow == 2 && totalXCells == 0){
                return findEmptyCell(line);
            }
            if(xCellsInARow == 2 && totalOCells == 0){
                return findEmptyCell(line);
            }
        }
        return -1;
    }

    public int findEmptyCell(int[] line){
        for(int i = 0; i < line.length; i++){
            int cellValue = line[i];
            if(cellValue == 0){
                return i;
            }
        }
        return -1;
    }

    public int findCellIndexFromLineIndex(int lineIndex, int cellIndex){
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

    public int extractRow1(int cellIndex){
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

    public int extractRow2(int cellIndex){
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

    public int extractRow3(int cellIndex){
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

    public int extractCol1(int cellIndex){
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

    public int extractCol2(int cellIndex){
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

    public int extractCol3(int cellIndex){
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

    public int extractDiag1(int cellIndex){
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

    public int extractDiag2(int cellIndex){
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

    public void checkVictory(){
        if(gameState.checkBoard() != 0){
            declareVictory(gameState.checkBoard());
        }
    }

    private void declareVictory(int victor){
        if(victor == 1) {
            System.out.println("Player wins!");
            this.victor = "Player";
        }
        else if(victor == 2) {
            System.out.println("Computer wins!");
            this.victor = "Computer";
        }
    }
}
