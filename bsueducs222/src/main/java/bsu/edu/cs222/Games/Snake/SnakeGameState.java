package bsu.edu.cs222.Games.Snake;

import bsu.edu.cs222.Controller;

import java.util.ArrayList;
import java.util.Random;

public class SnakeGameState {

    private int[] cells = new int[400];
    ArrayList<Integer> snake = new ArrayList<>();
    private Controller mainController;
    public int food;
    public int direction;
    public int lastDirection;



    SnakeGameState(Controller mainController){
        this.mainController = mainController;
        snake.add(0);
        snake.add(1);
        snake.add(2);
        direction = 1;
        lastDirection = 1;
        placeFood();
    }

    public void reset(){
        while(snake.size() > 3){
            snake.remove(0);
        }
        placeFood();
    }

    public void placeFood(){
        int randomInt = (int)(Math.random() * 400);
        System.out.println(randomInt);
        if(snake.contains(randomInt)){
            placeFood();
            return;
        }
        food = randomInt;
    }

    public void moveSnake(){
        int snakeHead = snake.get(snake.size() - 1);
        int move = snakeHead;
        if(direction == 0) {
            move = snakeHead - 20;
            if(move < 0){
                move = move + 400;
            }
        }
        if(direction == 1) {
            move = snakeHead + 1;
            if(move % 20 == 0){
                move = move - 20;
            }
        }
        if(direction == 2) {
            move = snakeHead + 20;
            if(move > 399){
                move = move - 400;
            }
        }
        if(direction == 3) {
            move = snakeHead - 1;
            if(move % 20 == 19 || move == -1){
                move = move + 20;
            }
        }
        lastDirection = direction;
        if(snake.contains(move) && move != snake.get(0)){
            mainController.notifyLoss();
            reset();
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
