public class AzulPrinter{
  public AzulPrinter(){

  }
  public void printCoasters(int[][] coasters, int coasterCount, int firstTile){
    for (int coasterID= 0; coasterID< coasterCount; coasterID++){
      if (coasterCheck(coasterID, coasters) > 0){
        System.out.println("_________________");
        System.out.println("COASTER " + (coasterID+1));
        for (int tileID = 0; tileID<5; tileID++){
          if (coasters[coasterID][tileID]>0){
            System.out.println(" " + coasters[coasterID][tileID] + " " +   intToColor(tileID));

          }
        }
      }
    }
    System.out.println("_________________");
    System.out.println("MIDDLE");
    if (firstTile ==1){
      System.out.println(" First Tile");
    }
    for (int tileID = 0; tileID<5; tileID++){
      if (coasters[coasterCount][tileID]>0){
        System.out.println(" " + intToColor(tileID) + " " + coasters[coasterCount][tileID]);

      }
    }
    System.out.println("_________________");
  }
              public int coasterCheck(int coasterSelection, int[][] coasters){ // used in printCoasters
                int sum = 0;
                for (int ii = 0; ii<5; ii++){
                  sum+= coasters[coasterSelection][ii];
                }
                return sum;
              }

  public void printBoard(AzulPlayer[] playerArr, int currentPlayer){
    AzulPlayer player = playerArr[currentPlayer];
    System.out.println("\n BOARD FOR " + player.name);
    System.out.println("Pattern Lines");
    for (int patternLineID = 0; patternLineID < 5; patternLineID++){
      int[][] currentPatterns = playerArr[currentPlayer].patterns;
      int tempID = currentPatterns[patternLineID][1]-1;
      int tempCount = currentPatterns[patternLineID][0];
      for (int ii = 0; ii<= patternLineID; ii++){
          System.out.print("[");
          if (tempCount > 0){
            System.out.print(intToChar(tempID));
            tempCount--;
          }
          System.out.print("]");
      }
              System.out.println();
    }
  }

  public String intToColor(int input){
    if (input == 0){
      return ("Dark Blue");
    }
    if (input == 1){
      return ("Yellow");
    }
    if (input == 2){
      return ("Red");
    }
    if (input == 3){
      return ("Black");
    }
    if (input == 4){
      return ("Light Blue");
    }
    return ("***   ERROR  intToColor *** ");

  }

  public String intToChar(int input){
    if (input == 0){
      return ("DB");
    }
    if (input == 1){
      return ("Y");
    }
    if (input == 2){
      return ("R");
    }
    if (input == 3){
      return ("B");
    }
    if (input == 4){
      return ("LB");
    }
    return ("***   ERROR  intToColor *** ");

  }
}
