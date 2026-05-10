package tictactoe;

import java.util.Scanner;
import java.util.Random;

public class TicTacToe {

    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();

    static char[] board = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
    static char currentPlayer = 'X';
    static boolean Computer = false;

    public static void main(String[] args) {
        boolean playAgain = true;

        chooseGameMode();

        while (playAgain) {
            resetBoard();
            playGame();
            playAgain = askPlayAgain();
        }

        System.out.println("You chose to exit!!!");
        sc.close();
    }

    static void chooseGameMode() {
        while (true) {
            System.out.println("Choose Game Mode:");
            System.out.println("1. Human vs Human");
            System.out.println("2. Human vs Computer");
            System.out.print("Enter your option: ");

            if (!sc.hasNextInt()) {
                System.out.println("Please enter a valid number!");
                sc.next();
                continue;
            }

            int option = sc.nextInt();

            if (option == 1) {
                Computer = false;
                break;
            } else if (option == 2) {
                Computer = true;
                break;
            } else {
                System.out.println("Invalid option! Choose 1 or 2.");
            }
        }
    }

    static void playGame() {
        boolean gameRunning = true;

        System.out.println("Welcome to Tic Tac Toe!");
        printBoard();

        while (gameRunning) {
            int position;

            if (Computer && currentPlayer == 'O') {
                position = computerMove();
                System.out.println("Computer chose position: " + position);
            } else {
                System.out.println("Player " + currentPlayer + ", enter position (1-9): ");

                if (!sc.hasNextInt()) {
                    System.out.println("Please enter a valid number!");
                    sc.next();
                    continue;
                }

                position = sc.nextInt();
            }

            if (position < 1 || position > 9) {
                System.out.println("Invalid position! Choose between 1 and 9.");
                continue;
            }

            if (board[position - 1] != ' ') {
                System.out.println("That position is already taken. Try again.");
                continue;
            }

            board[position - 1] = currentPlayer;
            printBoard();

            if (checkWin()) {
                if (Computer && currentPlayer == 'O') {
                    System.out.println("Computer wins!");
                } else {
                    System.out.println("Player " + currentPlayer + " wins!");
                }
                gameRunning = false;
            } else if (checkDraw()) {
                System.out.println("It's a draw!");
                gameRunning = false;
            } else {
                switchPlayer();
            }
        }
    }

    static int computerMove() {
        int position;

        while (true) {
            position = random.nextInt(9) + 1;

            if (board[position - 1] == ' ') {
                return position;
            }
        }
    }

    static void printBoard() {
        System.out.println();
        System.out.println(" " + display(0) + " | " + display(1) + " | " + display(2));
        System.out.println("---+---+---");
        System.out.println(" " + display(3) + " | " + display(4) + " | " + display(5));
        System.out.println("---+---+---");
        System.out.println(" " + display(6) + " | " + display(7) + " | " + display(8));
        System.out.println();
    }

    static char display(int index) {
        if (board[index] == ' ') {
            return (char) ('1' + index);
        }
        return board[index];
    }

    static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    static boolean checkDraw() {
        for (char cell : board) {
            if (cell == ' ') {
                return false;
            }
        }
        return true;
    }

    static boolean checkWin() {
        int[][] winConditions = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6}
        };

        for (int[] condition : winConditions) {
            if (board[condition[0]] == currentPlayer &&
                board[condition[1]] == currentPlayer &&
                board[condition[2]] == currentPlayer) {
                return true;
            }
        }

        return false;
    }

    static void resetBoard() {
        for (int i = 0; i < board.length; i++) {
            board[i] = ' ';
        }
        currentPlayer = 'X';
    }

    static boolean askPlayAgain() {
        while (true) {
            System.out.println("Do You Want To Play Again --> 1.YES  2.NO");

            if (!sc.hasNextInt()) {
                System.out.println("Please enter a valid number!");
                sc.next();
                continue;
            }

            int option = sc.nextInt();

            if (option == 1) {
                return true;
            }

            if (option == 2) {
                return false;
            }

            System.out.println("Please enter a valid number!");
        }
    }
}