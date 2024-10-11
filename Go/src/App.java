//importing Scanner class
import java.util.Scanner;

//creating class App
public class App {
    
    //declaring variables
    static String [] [] goBoard = new String [9] [9];

    static void getBoard(String [] [] b){

    //main method
    public static void main(String[] args) throws Exception {
        while (True){
            goBoard[1][1] = "-o";
            goBoard[2][2] = "-*";

            //creating a scanner object
            Scanner myObj = new Scanner(System.in); 

            //player 1 move
            System.out.println("Player 1, Enter your move (x y): ");
            int moveX = myObj.nextInt();
            int moveY = myObj.nextInt();

            //move valid check
            if (goBoard[moveX][moveY] == null) {
                goBoard[moveX][moveY] = "-o";
            } else {
                System.out.println("Invalid move, please try again.");
            }

            //player 2 move
            System.out.println("Player 2, Enter your move (x y): ");
            int moveX2 = myObj.nextInt();
            int moveY2 = myObj.nextInt();

            //move valid check
            if (goBoard[moveX2][moveY2] == null) {
                goBoard[moveX2][moveY2] = "-*";
            } else {
                System.out.println("Invalid move, please try again.");
            }

            
            //calling the printBoard method
            System.out.println(" 0 1 2 3 4 5 6 7 8");
            for (int i = 0; i < goBoard. length; i++){
                for (int j=0; j < goBoard[i].length; j++) {
                    if(goBoard[i][j] == null){
                        if(j == 0){
                            System.out.print("|");
                        }
                        else{
                            System.out.print("-|");
                        }
                    }
                    else{
                        System.out.print(goBoard[i][j]);
                    }
                }
                System.out.println();
            }
            public static void main (String[] )
        
            //saving the board state
            myObj.close();
            }
    }