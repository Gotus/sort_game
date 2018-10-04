package Gotus.com;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Component;

import Gotus.com.CellState;

class Cell {
    private int x;
    private int y;

    Cell(int x, int y) {

        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

public class Game extends Component{

    List<List<CellState>> field;
    private static final int FIELD_SIZE = 5;
    private static final int NUM_OF_GAME_CHIPS = 15;


    Game() {

        //Инициализация игрового поля
        field = new ArrayList<List<CellState>>(5);

        for (int i = 0; i < FIELD_SIZE; i++) {

            field.add(new ArrayList<CellState>(FIELD_SIZE));
            for (int j = 0; j < FIELD_SIZE; j++) {

                field.get(i).add(CellState.FREE);
            }
        }

        //Расстановка блоков
        field.get(0).set(1, CellState.BLOCKED);
        field.get(0).set(3, CellState.BLOCKED);
        field.get(2).set(1, CellState.BLOCKED);
        field.get(2).set(3, CellState.BLOCKED);
        field.get(4).set(1, CellState.BLOCKED);
        field.get(4).set(3, CellState.BLOCKED);
        /*
        for (int i = 0; i < FIELD_SIZE; i++) {

            for (int j = 0; j < FIELD_SIZE; j++) {
                System.out.print(field.get(i).get(j) + " ");
            }

            System.out.println();
        }
        */
        //Расстановка фишек
        placeGameChip();
    }

    private void placeGameChip() {
        int num_type_1 = 5;
        int num_type_2 = 5;
        int num_type_3 = 5;
        int value = 0;
        List<Cell> available_cells = new ArrayList<Cell>();
        Random rand = new Random();

        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j += 2) {

                    available_cells.add(new Cell(j, i));
            }
        }

        for (int i = 0; i < available_cells.size(); i++) {
            boolean is_not_generated = true;
            int x = 0;
            int y = 0;
            while (is_not_generated) {

                value = rand.nextInt(3) + 1;
                switch (value) {
                    case 1:
                        if (num_type_1 > 0) {
                            x = available_cells.get(i).getX();
                            y = available_cells.get(i).getY();
                            field.get(y).set(x, CellState.TYPE1);
                            num_type_1--;
                            is_not_generated = false;
                        }
                        break;
                    case 2:
                        if (num_type_2 > 0) {

                            x = available_cells.get(i).getX();
                            y = available_cells.get(i).getY();
                            field.get(y).set(x, CellState.TYPE2);
                            num_type_2--;
                            is_not_generated = false;
                        }
                        break;
                    case 3:
                        if (num_type_3 > 0) {

                            x = available_cells.get(i).getX();
                            y = available_cells.get(i).getY();
                            field.get(y).set(x, CellState.TYPE3);
                            num_type_3--;
                            is_not_generated = false;
                        }
                        break;
                }
            }
        }

        showField();
    }

    public void showField() {
        for (int i = 0; i < FIELD_SIZE; i++) {

            for (int j = 0; j < FIELD_SIZE; j++) {
                System.out.print(field.get(i).get(j) + " ");
            }

            System.out.println();
        }
    }


    public void move() {

    }
}


