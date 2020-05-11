public class AzulPlayer{
  public String name;
  public int order;
  public int score;
  public int[][] wall = new int[5][5];
  public int floor;
  public int[][] patterns = new int[5][2];
  public int firstTile = 0;
  public AzulPlayer(){

  }
  public AzulPlayer(String name, int order){
    this.name = name;
    this.order = order;
  }
}
