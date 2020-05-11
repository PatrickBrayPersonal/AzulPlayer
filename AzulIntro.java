// Starts game and creates players and player count
import java.util.*;
public class AzulIntro{
  Scanner scanner = new Scanner(System.in);

  public int playerCount;
  public int coasterCount;



  public AzulIntro(){

  }

  public void runIntro(){

      System.out.println(" Welcome to Azul! \n Please enter the number of players in your game \n Then press ENTER");
      playerCount = scanner.nextInt();
      scanner.nextLine();
      while(playerCount<2 || playerCount>4){
        System.out.println(" Sorry! Azul is limited to 2-4 players \n Please enter an appropriate number of players \n Then press ENTER");
        playerCount = scanner.nextInt();
        scanner.nextLine();
      }

      // Set Coaster Count based on number of players
      if (playerCount == 2)
        coasterCount = 5;
      if (playerCount == 3)
        coasterCount = 7;
      if (playerCount == 4)
        coasterCount = 9;

  }
  public AzulPlayer[] runIntro2(){

        // Create player array
      AzulPlayer[] playerArr = new AzulPlayer[playerCount];

      for (int ii = 0; ii < playerCount; ii++){
        if (ii == 0){
          System.out.println(" Enter the name of the player going first");
        }
        else{
          System.out.println(" Enter the name of the player going next");
        }
        String playerName = scanner.nextLine();
        playerArr[ii] = new AzulPlayer(playerName, ii);

      }
      return playerArr;

  }
}
