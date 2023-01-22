import java.util.Scanner;

public class TicTacToe {

  public static void main(String[] args) {
    Scanner scnr = new Scanner(System.in);
    boolean play = true;
    int xWins = 0;
    int oWins = 0;
    System.out.println("Welcome to TicTacToe!\nBy: Nithilan Elangovan\n======================\n");
    while (play) {
      int turn = 0;
      char[][] board = createBoard();
      if (turn % 2 == 0) {
        System.out.println("Player O's turn:");
      } else {
        System.out.println("Player X's turn:");
      }
      displayBoard(board);
      while (hasWinner(board) == false && isFilled(board) == false) {
        int colChoice = readValidInt(scnr, "Choose the COLUMN of spot (1-3): ", 1, 3);
        int rowChoice = readValidInt(scnr, "Choose the ROW of spot (1-3): ", 1, 3);
        if (isValidMove(board, colChoice, rowChoice)) {
          turn++;
          board = boardMark(board, colChoice, rowChoice, turn);
        }
        if (turn % 2 == 0) {
          System.out.println("\nPlayer O's turn:");
        } else {
          System.out.println("\nPlayer X's turn:");
        }
        displayBoard(board);
      }
      if (isFilled(board) || hasWinner(board)) {
        if (turn % 2 == 0 && hasWinner(board)) {
          System.out.println("Player X Wins!");
          xWins++;
        } else if (turn % 2 == 1 && hasWinner(board)) {
          System.out.println("Player O Wins!");
          oWins++;
        } else {
          System.out.println("It's a draw!");
        }

      }
      System.out.println("\nWins:\nPlayer X: " + xWins + ", Player O: " + oWins);
      if (readValidInt(scnr, "\nPlay again? (yes: 1, no: 2) ", 1, 2) == 2) {
        play = false;
        System.out.println("\nThanks for playing TicTacToe!");
      } else {
        System.out.println();
      }
    }
    scnr.close();
  }

  public static int readValidInt(Scanner scnr, String prompt, int min, int max) {
    int validNum = 0;
    System.out.print(prompt);
    validNum = scnr.nextInt();
    while (validNum < min || validNum > max) {
      System.out.print("Number out of bounds, try again\n" + prompt + " ");
      validNum = scnr.nextInt();
    }
    return validNum;
  }

  public static char[][] createBoard() {
    char[][] board = new char[3][3];
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        board[i][j] = '-';
      }
    }
    return board;
  }


  public static char[][] boardMark(char[][] board, int col, int row, int turn) {
    if (turn % 2 == 0) {
      board[row - 1][col - 1] = 'X';
    } else {
      board[row - 1][col - 1] = 'O';
    }
    return board;
  }

  public static boolean isValidMove(char[][] board, int col, int row) {
    if (board[row - 1][col - 1] == '-') {
      return true;
    } else {
      System.out.println("That is not a valid move! Try again!");
      return false;
    }
  }

  public static boolean isFilled(char[][] board) {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] == '-') {
          return false;
        }
      }
    }
    return true;
  }

  public static void displayBoard(char[][] board) {
    System.out.print("  ");
    for (int i = 1; i <= board[0].length; i++) {
      System.out.print(i + " ");
    }
    System.out.println();
    for (int i = 0; i < board.length; i++) {
      System.out.print(i + 1 + " ");
      for (int j = 0; j < board[i].length; j++) {
        System.out.print(board[i][j] + " ");
      }
      System.out.println();
    }
  }

  public static boolean hasWinner(char[][] board) {
    for (int i = 0; i < 3; i++) {
      if ((board[i][0] == 'X' || board[i][0] == 'O') && board[i][0] == board[i][1]
          && board[i][1] == board[i][2]) {
        return true;
      }
      if ((board[0][i] == 'X' || board[0][i] == 'O') && board[0][i] == board[1][i]
          && board[1][i] == board[2][i]) {
        return true;
      }
    }
    if ((board[0][0] == 'X' || board[0][0] == 'O') && board[0][0] == board[1][1]
        && board[1][1] == board[2][2]) {
      return true;
    }
    if ((board[2][0] == 'X' || board[2][0] == 'O') && board[2][0] == board[1][1]
        && board[1][1] == board[0][2]) {
      return true;
    }
    return false;
  }
}
