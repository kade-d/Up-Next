package bsu.edu.cs222.Games.Snake;

import bsu.edu.cs222.Controller;

import java.util.ArrayList;

public class SnakeGameState {

    public ArrayList<Integer> snake = new ArrayList<>();
    private Controller mainController;
    int food;
    int direction;
    int lastDirection;
    private int boardWidth;
    private int cellCount;

    public SnakeGameState(Controller mainController, int boardWidth, int boardHeight){
        this.mainController = mainController;
        this.boardWidth = boardWidth;
        this.cellCount = boardWidth * boardHeight;
        snake.add(0);
        snake.add(1);
        snake.add(2);
        direction = 1;
        lastDirection = 1;
        placeFood();
    }

    void restart(){
        snake.clear();
        snake.add(0);
        snake.add(1);
        snake.add(2);
        direction = 1;
        lastDirection = 1;
        placeFood();
    }

    private void placeFood(){
        int randomInt = (int)(Math.random() * cellCount);
        if(snake.contains(randomInt)){
            placeFood();
            return;
        }
        food = randomInt;
    }

    void moveSnake(){
        int snakeHead = snake.get(snake.size() - 1);
        int move = snakeHead;
        if(direction == 0) {
            move = snakeHead - boardWidth;
            if(move < 0){
                restart();
                return;
            }
        }
        if(direction == 1) {
            move = snakeHead + 1;
            if(move % boardWidth == 0){
                restart();
                return;
            }
        }
        if(direction == 2) {
            move = snakeHead + boardWidth;
            if(move > cellCount - 1){
                restart();
                return;
            }
        }
        if(direction == 3) {
            move = snakeHead - 1;
            if(move % boardWidth == boardWidth - 1 || move == -1){
                restart();
                return;
            }
        }
        lastDirection = direction;
        if(snake.contains(move) && move != snake.get(0)){
            mainController.notifyLoss();
            restart();
            return;
        }
        if(move == food){
            snake.add(move);
            placeFood();
        }
        else{
            snake.add(move);
            snake.remove(0);
        }
    }
}
