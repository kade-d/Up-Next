package bsu.edu.cs222;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MinesweeperGameState {

    public int[] cells = new int[81];
    public boolean[] bombCells = new boolean[81];
    public boolean[] shownCells = new boolean[81];
    public boolean[] flaggedCells = new boolean[81];

    public void reset(){
        cells = new int[81];
        bombCells = new boolean[81];
        shownCells = new boolean[81];
        flaggedCells = new boolean[81];
    }

    public void revealCell(int index){
        shownCells[index] = true;
    }

    public void flagCell(int index){
        flaggedCells[index] = true;
    }

    public void unflagCell(int index){
        flaggedCells[index] = false;
    }

    public void makeNewBoard(){
        addBombs();
    }

    public void addBombs(){
        for(int i = 0; i < 9; i++){
            cells[i] = -1;
        }
        randomizeBoard();
        placeNumbers();
        findBombs();
    }

    public void randomizeBoard(){
        Random random = ThreadLocalRandom.current();
        for (int i = cells.length - 1; i > 0; i--){
            int randomIndex = random.nextInt(i + 1);
            int randomCell = cells[randomIndex];
            cells[randomIndex] = cells[i];
            cells[i] = randomCell;
        }
    }

    public void placeNumbers(){
        for(int i = 0; i < cells.length; i++){
            int cell = cells[i];
            int bombCount = 0;
            if(cell != -1){
                if(i == 0){ //top left
                    if(cells[i + 1] == -1){
                        bombCount++;
                    }
                    if(cells[i + 9] == -1){
                        bombCount++;
                    }
                    if(cells[i + 10] == -1){
                        bombCount++;
                    }
                }
                else if(i < 8){ //top middle
                    if(cells[i - 1] == -1){
                        bombCount++;
                    }
                    if(cells[i + 1] == -1){
                        bombCount++;
                    }
                    if(cells[i + 8] == -1){
                        bombCount++;
                    }
                    if(cells[i + 9] == -1){
                        bombCount++;
                    }
                    if(cells[i + 10] == -1){
                        bombCount++;
                    }
                }
                else if(i == 8){ //top right
                    if(cells[i - 1] == -1){
                        bombCount++;
                    }
                    if(cells[i + 8] == -1){
                        bombCount++;
                    }
                    if(cells[i + 9] == -1){
                        bombCount++;
                    }
                }
                else if(i == 72){ // bottom left
                    if(cells[i - 9] == -1){
                        bombCount++;
                    }
                    if(cells[i - 8] == -1){
                        bombCount++;
                    }
                    if(cells[i + 1] == -1){
                        bombCount++;
                    }
                }
                else if(i > 72 && i < 80){ // bottom middle
                    if(cells[i - 10] == -1){
                        bombCount++;
                    }
                    if(cells[i - 9] == -1){
                        bombCount++;
                    }
                    if(cells[i - 8] == -1){
                        bombCount++;
                    }
                    if(cells[i - 1] == -1){
                        bombCount++;
                    }
                    if(cells[i + 1] == -1){
                        bombCount++;
                    }
                }
                else if(i == 80){ // bottom left
                    if(cells[i - 10] == -1){
                        bombCount++;
                    }
                    if(cells[i - 9] == -1){
                        bombCount++;
                    }
                    if(cells[i - 1] == -1){
                        bombCount++;
                    }
                }
                else if(i % 9 == 0){ // left middle
                    if(cells[i - 9] == -1){
                        bombCount++;
                    }
                    if(cells[i - 8] == -1){
                        bombCount++;
                    }
                    if(cells[i + 1] == -1){
                        bombCount++;
                    }
                    if(cells[i + 9] == -1){
                        bombCount++;
                    }
                    if(cells[i + 10] == -1){
                        bombCount++;
                    }
                }
                else if((i + 1) % 9 == 0){ // right middle
                    if(cells[i - 10] == -1){
                        bombCount++;
                    }
                    if(cells[i - 9] == -1){
                        bombCount++;
                    }
                    if(cells[i - 1] == -1){
                        bombCount++;
                    }
                    if(cells[i + 8] == -1){
                        bombCount++;
                    }
                    if(cells[i + 9] == -1){
                        bombCount++;
                    }
                }
                else{ //center
                    if(cells[i - 10] == -1){
                        bombCount++;
                    }
                    if(cells[i - 9] == -1){
                        bombCount++;
                    }
                    if(cells[i - 8] == -1){
                        bombCount++;
                    }
                    if(cells[i - 1] == -1){
                        bombCount++;
                    }
                    if(cells[i + 1] == -1){
                        bombCount++;
                    }
                    if(cells[i + 8] == -1){
                        bombCount++;
                    }
                    if(cells[i + 9] == -1){
                        bombCount++;
                    }
                    if(cells[i + 10] == -1){
                        bombCount++;
                    }
                }
                cells[i] = bombCount;
            }
        }
    }

    private void findBombs(){
        for(int i = 0; i < cells.length; i++){
            if(cells[i] == -1){
                bombCells[i] = true;
            }
        }
    }
}
