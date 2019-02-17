package lesson_4;
import javax.print.attribute.Size2DSyntax;
import java.util.Random;
import java.util.Scanner;

public class HW_4 {
    public static char[][] map;
    public static final int SIZE = 4;
    //public static final int DOTS_TO_WIN = 4;
    public static final char DOT_EMPTY = '.';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();

    public static void main(String[] args){
        initmap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Победил человек");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("Победил Искуственный Интеллект");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра закончена");
    }
    public static void initmap(){
        map = new char[SIZE][SIZE];
        for (int i=0; i < SIZE; i++){
            for (int j=0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void humanTurn() {
        int x;
        int y;
        do {
            System.out.println("Введите координаты в формате X");
            x = sc.nextInt() - 1;
            System.out.println("Введите координаты в формате Y");
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[x][y] = DOT_X;
    }
    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        if (map[x][y] == DOT_EMPTY) return true;
        return false;
    }
    public static void aiTurn() {
        int x, y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isCellValid(x, y));
        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        map[y][x] = DOT_O;
    }
    public static boolean checkWin(char symb) {

        boolean correctCondition = false;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == symb) {
                    correctCondition = true;
                }   else {
                    correctCondition = false;
                    break;
                }
            }
            if (correctCondition) {
                return true;
            }
        }

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[j][i] == symb) {
                    correctCondition = true;
                }   else {
                    correctCondition = false;
                    break;
                }
            }
            if (correctCondition) {
                return true;
            }
        }

        for (int i = 0; i < SIZE; i++) {
            if (map[i][i] == symb) {
                correctCondition = true;
            }   else {
                correctCondition = false;
                break;
            }
        }

        if (correctCondition) {
            return true;
        }

        for (int i = 0; i < SIZE; i++) {
            if (map[SIZE - 1 - i][i] == symb) {
                correctCondition = true;
            }   else {
                correctCondition = false;
                break;
            }
        }

        return correctCondition;
    }
    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }
}
