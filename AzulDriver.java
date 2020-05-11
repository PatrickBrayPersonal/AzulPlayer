// generates tiles input to coasters
// Takes user inputs to their moves
// Calculates score until game is over
public class AzulDriver{
  public void runDriver(){

    // INTRODUCTION see: AzulIntro
    AzulIntro intro = new AzulIntro();
    intro.runIntro();
    AzulPlayer[] playerArr = new AzulPlayer[intro.playerCount];
    playerArr = intro.runIntro2();
    // update variables after intro
    int playerCount = intro.playerCount;
    int coasterCount = intro.coasterCount;

    // GAMEPLAY see: AzulGame
    AzulGame game = new AzulGame(playerCount, coasterCount, playerArr);
    game.runGame();

    //AzulScore score = new AzulScore();
  }



  public static void main(String[] args) {
    AzulDriver a = new AzulDriver();
    a.runDriver();
  }
}
