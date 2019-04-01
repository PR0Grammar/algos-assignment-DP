/**
 * Glass Falling
 * Author: Nehal Patel
 */

public class GlassFalling {
  static final int INF = (int) Double.POSITIVE_INFINITY;

  public int glassFallingRecur(int floors, int sheets) {
    int[][] memo = new int[sheets + 1][floors  + 1];

    //If only one floor, all floors will have one trial
    //If zero floors, no trials to perform
    for(int i = 0; i <= sheets; i++){
      memo[i][0] = 0;
      memo[i][1] = 1;
    }
    //If only one glass sheet, each floor would need to be checked one by one
    //If no glass sheet, we can't run trials
    for(int i = 0; i <= floors; i++){
      memo[1][i] = i;
      memo[0][i] = 0;
    }
    //Preset all other values to INF as 'dummy' values
    for(int i = 2; i <= sheets; i++){
      for(int j = 2; j <= floors; j++){
        memo[i][j] = INF;
      }
    }

    return glassFallingMemoized(floors, sheets, memo);
  }
  
  private int glassFallingMemoized(int f, int s, int[][] memo) {
    if(memo[s][f] < INF){
      return memo[s][f];
    }
    int minNumOfTrialsReq = INF;

    for(int i = 1; i <= f; i++){
      //Grab the 'worst case' scenario if we chose the ith floor first to test
      int trials = 1 + Math.max( glassFallingMemoized(i - 1, s - 1, memo),
                                 glassFallingMemoized(f - i, s, memo)
                               );
      //Grab the floor to start that produces lowest 'worst case' possible.
      minNumOfTrialsReq = Math.min(minNumOfTrialsReq, trials);
    }
    memo[s][f] = minNumOfTrialsReq;
    return memo[s][f];
  }
  
  public int glassFallingBottomUp(int floors, int sheets) {
    int[][] minTrials = new int[sheets + 1][floors + 1];

    for(int i = 0; i <= floors; i++){
      minTrials[0][i] = 0;
      minTrials[1][i] = i;
    }

    for(int i = 0; i <= sheets; i++){
      minTrials[i][0] = 0;
      minTrials[i][1] = 1;
    }

    //Fill the rest of the matrix based on base values
    for(int i = 2; i <= sheets; i++){   
      for(int j = 2; j <= floors; j++){
        int minT = INF;
        
        for(int k = 1; k <= j; k++){
          int trials = 1 + Math.max( minTrials[i - 1][k - 1],
                                     minTrials[i][j - k]
                                   );
          minT = Math.min(minT, trials);
        }
        minTrials[i][j] = minT;
      }
    }
    
    return minTrials[sheets][floors];
  }


  public static void main(String args[]){
      GlassFalling gf = new GlassFalling();

      // Do not touch the below lines of code, and make sure
      // in your final turned-in copy, these are the only things printed
      int minTrials1Recur = gf.glassFallingRecur(27, 2);
      int minTrials1Bottom = gf.glassFallingBottomUp(27, 2);
      int minTrials2Recur = gf.glassFallingRecur(100, 3);
      int minTrials2Bottom = gf.glassFallingBottomUp(100, 3);
      System.out.println(minTrials1Recur + " " + minTrials1Bottom);
      System.out.println(minTrials2Recur + " " + minTrials2Bottom);
  }
}
