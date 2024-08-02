import java.util.Scanner;
import java.util.Random;

public class RockPaperScissors 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String[] rps = {"Rock", "Paper", "Scissors"};
        String computerMove;
        String playerMove;
        
        while (true) 
        {
            System.out.println("Enter move (Rock, Paper, Scissors). To exit the game, type \"exit\": ");
            playerMove = scanner.nextLine();
            
            if (playerMove.equalsIgnoreCase("exit")) 
            {
                System.out.println("Thanks for playing!");
                break;
            }
            
            if (!playerMove.equalsIgnoreCase("Rock") && 
                !playerMove.equalsIgnoreCase("Paper") && 
                !playerMove.equalsIgnoreCase("Scissors")) {
                System.out.println("Invalid move, please try again.");
                continue;
            }
            
            computerMove = rps[random.nextInt(3)];
            System.out.println("Computer chose: " + computerMove);
            
            if (playerMove.equalsIgnoreCase(computerMove)) 
            {
                System.out.println("It's a tie!");
            } 
            else if (playerMove.equalsIgnoreCase("Rock")) 
            {
                if (computerMove.equals("Scissors")) {
                    System.out.println("You win!");
                } else {
                    System.out.println("You lose!");
                }
            } 
            else if (playerMove.equalsIgnoreCase("Paper")) 
            {
                if (computerMove.equals("Rock")) {
                    System.out.println("You win!");
                } else {
                    System.out.println("You lose!");
                }
            } 
            else if (playerMove.equalsIgnoreCase("Scissors")) 
            {
                if (computerMove.equals("Paper")) {
                    System.out.println("You win!");
                } else {
                    System.out.println("You lose!");
                }
            }
            
            System.out.println();
        }
        
        scanner.close();
    }
}
