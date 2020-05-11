import java.util.*;
public class AzulGame{
  public int playerCount;
  public int coasterCount;
  public AzulPlayer[] playerArr;
  public int[] bowl;
  public int[] bag;
  public int firstTile;
  Random random = new Random();
  Scanner scanner = new Scanner(System.in);
  AzulPrinter printer = new AzulPrinter();

  public AzulGame(){

  }
  public AzulGame(int playerCount, int coasterCount, AzulPlayer[] playerArr){
    this.playerCount = playerCount;
    this.coasterCount = coasterCount;
    this.playerArr = playerArr;
  }







  //MAIN FUNCTION
  public void runGame(){

    bag = newBag();
    bowl = new int[5];
    int[][] coasters = new int[coasterCount + 1][5];
    int currentPlayer = 0; // used inside rounds
    int gameEnd = checkGameEnd(); // make sure single row isn't full
    for (int round = 0 ; gameEnd == 0;round++){ // ends when a row is full
      coasters = fillCoasters();
      firstTile = 1;
      for (int turn = 0; coasterSum(coasters) > 0; turn++){ // ends when all coasters and middle are empty
        printer.printCoasters(coasters, coasterCount, firstTile);
        currentPlayer = whoseTurnIsItAnyway(currentPlayer, round, turn);
        printer.printBoard(playerArr, currentPlayer); // show the player's board
        int coasterSelection = coasterSelector(currentPlayer, coasters); // Pick Coaster
        int tileSelection = tileSelector(currentPlayer, coasters, coasterSelection); // pick tile
        int patternSelection = patternSelector(currentPlayer, coasters, coasterSelection, tileSelection); // pick pattern
        coasters = fillPatternFloorMiddle(currentPlayer, coasters, coasterSelection, tileSelection, patternSelection); // fill the pattern
        printer.printBoard(playerArr, currentPlayer); // show the player's board

      }

    }
  }








  // TOOLS
  public int[] newBag(){ // only for start of game VERIFIED
    int[] bag = {20,20,20,20,20};
    return bag;

  }

  public int coasterSum(int[][] coasters){ // number of tiles on all coasters including middle (used to check end of round) NOT VERIFIED
    int sum = 0;
    for (int ii = 0; ii < coasterCount+1; ii++){
      for (int jj = 0; jj < 5; jj++){
        sum += coasters[ii][jj];
      }
    }
    return sum;
  }

  public int bagSum(int[] bag){ // number of tiles in the bag VERIFIED
    int sum = 0;
    for (int ii = 0; ii< 5; ii++){
      sum += bag[ii];
    }
    return sum;
  }

  public int[][] fillCoasters(){ // randomly picks tiles out of the bag and places them on coasters VERIFIED
    int[][] coasters = new int[coasterCount+1][5];
    int bagTotal = bagSum(bag);
    for (int coasterID = 0; coasterID < coasterCount; coasterID++){
      for (int tileID = 0; tileID < 4; tileID++){
        if (bagTotal == 0){// if bag empties
          bag = bowl; // fill it with the bowl
          int[] newBowl = new int[5];
          bowl = newBowl;
        }
        int randomSelection = random.nextInt(bagTotal);
        for (int bagTileID = 0; bagTileID < 5; bagTileID++){
          if (randomSelection >= bag[bagTileID]){ // move past tiles not selected
            randomSelection -= bag[bagTileID];
          }
          else{ // move tile to coaster
            coasters[coasterID][bagTileID]++;
            bag[bagTileID]--;
            bagTotal--;
            bagTileID = 5;
          }
        }
      }
    }
    return coasters;
  }

  public int checkGameEnd(){ // checks for completed row   NOT VERIFIED
    int output = 0;
    for (int playerID = 0; playerID<playerArr.length; playerID++){
      int[][] currentWall = playerArr[playerID].wall;
      for (int ii = 0; ii<5; ii++){
        int rowSum = 0;
        for (int jj = 0; jj<5; jj++){
          rowSum+=currentWall[ii][jj];
        }
        if (rowSum == 5){
          output = 1;
        }
      }
    }
    return output;
  }




  public int whoseTurnIsItAnyway(int currentPlayer, int round, int turn){ // finds who should be going next NOT VERIFIED
    int output = 0;
    if (turn == 0){
      if (round == 0){
        output = 0;
      }
      else{
        for (int playerID = 0; playerID<playerCount; playerID++){
          if (playerArr[playerID].firstTile == 1){
            output = playerID;
            playerArr[playerID].firstTile = 0; // reset first tile
          }
        }
      }
    }
    else{
      output = currentPlayer + 1;
      if (output == playerCount){
        output = 0;
      }
    }
    return output;
  }

  public int coasterSelector(int currentPlayer, int[][] coasters){
    int isCorrect = 0;
    int coasterSelection = 100;
    while (isCorrect == 0){
      System.out.println(playerArr[currentPlayer].name + ", which COASTER will you select a tile from? \n input the COASTER number or " + (coasterCount + 1) + " to take from the MIDDLE");
      coasterSelection = scanner.nextInt()-1;
      scanner.nextLine();
      if(coasterSelection <= coasterCount && coasterCheck(coasterSelection, coasters) > 0){
        isCorrect++;
      }
      else{
        System.out.println("INPUT ERROR try again! \n Make sure your coaster exists and is full");
      }
    }
    return coasterSelection;
  }
            public int coasterCheck(int coasterSelection, int[][] coasters){ // used in coaster selector
              int sum = 0;
              for (int ii = 0; ii<5; ii++){
                sum+= coasters[coasterSelection][ii];
              }
              return sum;
            }



  public int tileSelector(int currentPlayer, int[][] coasters, int coasterSelection){
    int isCorrect = 0;
    int tileSelection = 100;
    while (isCorrect == 0){
      System.out.println(playerArr[currentPlayer].name + ", which COLOR tile would you like to select from COASTER " + coasterSelection+ "?");
      System.out.println(" Dark Blue = 1\n Yellow = 2\n Red = 3\n Black = 4\n Light Blue = 5");
      tileSelection = scanner.nextInt()-1;
      scanner.nextLine();
      if(tileSelection<5 && tileSelection >= 0 && isTileOnCoaster(tileSelection, coasters, coasterSelection) == 1){
        isCorrect++;
      }
      else{
        System.out.println("INPUT ERROR try again! \n make sure the tile color is valid and present on the coaster you chose");
      }

    }
    return tileSelection;


  }
            public int isTileOnCoaster(int tileSelection, int[][] coasters, int coasterSelection){ // used in tileSelector
              if(coasters[coasterSelection][tileSelection] > 0){
                return 1;
              }
              else
              return 0;
            }


  public int patternSelector(int currentPlayer, int[][] coasters, int coasterSelection, int tileSelection){ // NEEDS WORK
    int isCorrect = 0;
    int patternSelection = 100;
    while (isCorrect ==0){
      System.out.println(playerArr[currentPlayer].name + ", which pattern line would you like to select?");
      patternSelection  = scanner.nextInt()-1;
      scanner.nextLine();
      System.out.println(" WALL AND PATTERN CHECK = " + wallAndPatternCheck(currentPlayer, patternSelection, tileSelection));
      System.out.println(" SELECTED PATTERN CHECK " + (playerArr[currentPlayer].patterns[patternSelection][0] == 0 || playerArr[currentPlayer].patterns[patternSelection][1] == tileSelection+1)); // either empty or same color
      if(patternSelection >= 0 && patternSelection < 5 && wallAndPatternCheck(currentPlayer, patternSelection, tileSelection) == 1 && (playerArr[currentPlayer].patterns[patternSelection][1] == 0 || playerArr[currentPlayer].patterns[patternSelection][1] == tileSelection)){
        isCorrect++;
      }
      else{
        System.out.println("____________________\nInput ERROR try again! \n make sure the number you input is valid and the same color tiles are not present on another pattern or wall\n_____________________");
      }
    }
    return patternSelection;
  }
              public int wallAndPatternCheck(int currentPlayer, int patternSelection, int tileSelection){ // used in pattern selection NOT VERIFIED
                int output = 1;
                int[][] currentWall = playerArr[currentPlayer].wall;
                int[][] currentPatterns = playerArr[currentPlayer].patterns;
                System.out.println("CURRENT PATTERNS" + Arrays.deepToString(currentPatterns));

                if (currentWall[patternSelection][tileSelection] != 0){ //checkWall
                  output = 0;
                  System.out.println("W&P 1");
                }
                for (int ii = 0 ; ii<5; ii++){
                  if (ii != patternSelection && currentPatterns[ii][1] == tileSelection+1){ // plus 1 because all colors are added 1 in patterns[1]
                    System.out.println("W&P 2");
                    output = 0;
                  }
                }
              return output;
              }


    public int[][] fillPatternFloorMiddle(int currentPlayer, int[][] coasters, int coasterSelection, int tileSelection, int patternSelection){
      // ADD TILES TO PATTERN AND FLOOR
      int filling = coasters[coasterSelection][tileSelection]; // amount that is added
      int overflow = filling - patternSelection+1; // amount over can be fit
      playerArr[currentPlayer].patterns[patternSelection][0] += filling; // adds filling to current pattern
      playerArr[currentPlayer].patterns[patternSelection][1] = tileSelection+1; // updates tile type on pattern
      if(overflow>0){ // if you need to put some in the floor
        playerArr[currentPlayer].floor += overflow; // adds to floor
        bowl[tileSelection] += overflow; // adds to bowl in advance
        playerArr[currentPlayer].patterns[patternSelection][0] = patternSelection+1; // resets pattern to being full
      }
      // MOVE COASTER TILES TO MIDDLE
      if(coasterSelection < coasterCount){ // if selected from one of the coasters
        coasters[coasterSelection][tileSelection] = 0; // set taken to 0
        for(int ii = 0; ii<5;ii++){// adds non chosen tiles to middle
            coasters[coasterCount][ii] += coasters[coasterSelection][ii];
            coasters[coasterSelection][ii] = 0; // empties coaster
        }
      }
      else{ // If taken from the middle
        if (firstTile == 1){ // grab the first tile
          playerArr[currentPlayer].firstTile =1;
          coasters[coasterSelection][tileSelection] = 0; // set taken to 0
          firstTile = 0;
        }
      }
      return coasters;
    }




}
