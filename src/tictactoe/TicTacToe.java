package tictactoe;
import java.util.Scanner;

public class TicTacToe {
	 static char[] board = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
	 static char currentPlayer = 'X';

	public static void main(String[] args) {
		 Scanner sc = new Scanner(System.in);
	        boolean gameRunning = true;

	        System.out.println("Welcome to Tic Tac Toe!");
	        printBoard();

	        while (gameRunning) {
	            System.out.println("Player " + currentPlayer + ", enter position (1-9): ");
	            int position = sc.nextInt();

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
	                System.out.println("Player " + currentPlayer + " wins!");
	                gameRunning = false;
	            } else if (checkDraw()) {
	                System.out.println("It's a draw!");
	                gameRunning = false;
	            } else {
	                switchPlayer();
	            }
	        }

	        sc.close();

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

}
