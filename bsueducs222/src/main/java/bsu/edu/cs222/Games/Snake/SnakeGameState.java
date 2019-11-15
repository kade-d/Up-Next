package bsu.edu.cs222.Games.Snake;

import java.util.ArrayList;
import java.util.Random;

public class SnakeGameState {

    private int[] cells = new int[400];
    ArrayList<Integer> snake = new ArrayList<>();
    public int food;



    SnakeGameState(){
        snake.add(3);
        snake.add(4);
        snake.add(5);
        placeFood();
    }

    public void placeFood(){
        int randomInt = (int)(Math.random() * 400 + 1);
        System.out.println(randomInt + "");
        System.out.println(snake.size());
        System.out.println(snake);
        if(snake.contains(randomInt)){
            placeFood();
            return;
        }
        food = randomInt;
    }

    public void moveSnake(int move){
        move = snake.get(snake.size() - 1) + 1;
        if(move == 401){
            move = 1;
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
