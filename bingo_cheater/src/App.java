import java.util.Scanner;
import java.util.Random;

public class App {
    public static void main(String[] args) {
        boolean bingo = false;
        String nextround = "y";
        int chip = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Number of Boards");
        
        int size =  Integer.parseInt(scanner.nextLine());
        BingoGame[] board = new BingoGame[size];
        for (int i = 0; i < size; i++) {
            board[i] = new BingoGame();
        }

        while (nextround.equalsIgnoreCase("y")) {
            System.out.print("Input game type: Regular-R, Postage-P, X, T, Corners-C, Blackout-B: ");
            String gametype = scanner.nextLine();
            char firstChar = gametype.charAt(0);
            while (!bingo) {
                System.out.print("Enter chip number: ");
                chip = scanner.nextInt();
                for (int i = 0; i < size; i++) {
                    if(board[i].makeMove(chip, firstChar)){
                        bingo = true;
                    }
                }
            }

            System.out.print("New Game? Y/N: ");
            bingo = false;
            nextround = scanner.next();
            scanner.nextLine();  
        }

        scanner.close();
    }
}
