import java.util.Random;

public class BingoGame {
    private int[][] rawboard;
    private char[][] checkboard;
    private boolean isBoardOver;

    public BingoGame() {
        rawboard = new int[5][5]; 
        checkboard = new char[5][5]; 
        isBoardOver = false;
        initializeBoardCheck();
        initializeBoardRaw();
    }

    private void initializeBoardCheck() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                checkboard[i][j] = '-'; // Empty cell
            }
        }
        checkboard[2][2]='c';
    }
    private void initializeBoardRaw() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                        Random rand = new Random();
        
     
               int randomNum = rand.nextInt(99) + 1;
                rawboard[i][j] = randomNum; // Empty cell
            }
        }
    }

    public boolean makeMove(int chip, char Wincon) {
        if(isBoardOver){
            return false;
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                    if (rawboard[i][j]==chip) {
                        checkboard[i][j] = 'c';
                    }
            }
        }
    
    if(checkWin(Wincon)){
        System.out.print("Bingo on Board!");
        System.out.println();
        printBoard();
        System.out.println();
        isBoardOver = true;
        return true;
    }
    return false;




}

    public boolean checkWin(char wincon) {
        // Check rows, columns, and diagonals for a win
     //   "Input game type: Regular-R, Postage-P, X, T, Corners-C, Blackout-B: "
     switch (wincon) {
        case 'R':
            return checkBingo();
        case 'P':
        if (checkboard[0][3] == 'c'&&checkboard[0][4] == 'c'&&checkboard[1][4] == 'c'&&checkboard[1][3] == 'c') {
            return true;
        } else {
            return false;
        }
        case 'X':
        if (checkboard[0][0] == 'c'&&checkboard[0][4] == 'c'&&checkboard[1][1] == 'c'&&checkboard[1][3] == 'c'&&checkboard[3][1] == 'c'&&checkboard[3][3] == 'c'&&checkboard[4][0] == 'c'&&checkboard[4][4] == 'c') {
            return true;
        } else {
            return false;
        }
        case 'T':
        if (checkboard[0][0] == 'c'&&checkboard[0][1] == 'c'&&checkboard[0][2] == 'c'&&checkboard[0][3] == 'c'&&checkboard[0][4] == 'c'&&checkboard[1][2] == 'c'&&checkboard[2][2] == 'c'&&checkboard[3][2] == 'c'&&checkboard[4][2] == 'c') {
            return true;
        } else {
            return false;
        }
        case 'C':
        if (checkboard[0][0] == 'c'&&checkboard[4][4] == 'c'&&checkboard[0][4] == 'c'&&checkboard[4][0] == 'c') {
            return true;
        } else {
            return false;
        }
        case 'B':
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                    if (rawboard[i][j]=='-') {
                        return false;
                    }
            }
        }
        return true;
        default:
            System.out.println("Invalid Win Case");
            return false;
    }

    }

    public void printBoard() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(checkboard[i][j] + " ");
            }
            System.out.println();
        }
    }



public boolean checkBingo() {
    return checkRows() || checkColumns() || checkDiagonals();
}

private boolean checkRows() {
    for (int i = 0; i < 5; i++) {
        if (checkRow(i)) {
            return true; // Bingo found in row
        }
    }
    return false; // No Bingo in rows
}

private boolean checkRow(int row) {
    for (int j = 0; j < 5; j++) {
        if (checkboard[row][j] != 'c') {
            return false; // Not all marked in this row
        }
    }
    return true; // All marked in this row
}

private boolean checkColumns() {
    for (int j = 0; j < 5; j++) {
        if (checkColumn(j)) {
            return true; // Bingo found in column
        }
    }
    return false; // No Bingo in columns
}

private boolean checkColumn(int column) {
    for (int i = 0; i < 5; i++) {
        if (checkboard[i][column] != 'c') {
            return false; // Not all marked in this column
        }
    }
    return true; // All marked in this column
}

private boolean checkDiagonals() {
    return checkLeftToRightDiagonal() || checkRightToLeftDiagonal();
}

private boolean checkLeftToRightDiagonal() {
    for (int i = 0; i < 5; i++) {
        if (checkboard[i][i] != 'c') {
            return false; // Not all marked in left-to-right diagonal
        }
    }
    return true; // All marked in left-to-right diagonal
}

private boolean checkRightToLeftDiagonal() {
    for (int i = 0; i < 5; i++) {
        if (checkboard[i][4 - i] != 'c') {
            return false; // Not all marked in right-to-left diagonal
        }
    }
    return true; // All marked in right-to-left diagonal
}
}