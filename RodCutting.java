/**
 * Rod cutting problem
 * Author: Nehal Patel
 */

public class RodCutting {
  static final int M_INF = (int) Double.NEGATIVE_INFINITY;

  // Do not change the parameters!
  public int rodCuttingRecur(int rodLength, int[] lengthPrices) {
    int[] memo = new int[rodLength];

    for(int i = 0; i < memo.length; i++){
      memo[i] = M_INF;
    }
    return rodCuttingRecur(rodLength, lengthPrices, memo);
  }
  
  private int rodCuttingRecur(int rodLength, int[] lengthPrices, int[] memo){
    if(rodLength <= 0){
      return M_INF;
    }
    else if(memo[rodLength - 1] > M_INF){
      return memo[rodLength - 1];
    }

    int maxProfit = M_INF;

    for(int i = 0; i < rodLength; i++){
      maxProfit = Math.max( maxProfit, 
                            rodCuttingRecur(rodLength - i - 1, lengthPrices, memo) + lengthPrices[i]
                          );
    }
    maxProfit = Math.max(maxProfit, lengthPrices[rodLength - 1]);

    memo[rodLength - 1] = maxProfit;
    return memo[rodLength - 1];
  }

  // Do not change the parameters!
  public int rodCuttingBottomUp(int rodLength, int[] lengthPrices) {
    int[] maxProfit = new int[rodLength];

    for(int i = 0; i < rodLength; i++){
      int maxP = M_INF;

      for(int j = 0; j < i; j++){
        maxP = Math.max( maxP, 
                         maxProfit[j] + maxProfit[i - j - 1]
                       );
      }
      maxP = Math.max(maxP, lengthPrices[i]);
      maxProfit[i] = maxP;
    }

    return maxProfit[rodLength - 1];
  }


  public static void main(String args[]){
      RodCutting rc = new RodCutting();

      // In your turned in copy, do not touch the below lines of code.
      // Make sure below is your only output.
      int length1 = 7;
      int[] prices1 = {1, 4, 7, 3, 19, 5, 12};
      int length2 = 14;
      int[] prices2 = {2, 5, 1, 6, 11, 15, 17, 12, 13, 9, 10, 22, 18, 26};
      int maxSell1Recur = rc.rodCuttingRecur(length1, prices1);
      int maxSell1Bottom = rc.rodCuttingBottomUp(length1, prices1);
      int maxSell2Recur = rc.rodCuttingRecur(length2, prices2);
      int maxSell2Bottom = rc.rodCuttingBottomUp(length2, prices2);
      System.out.println(maxSell1Recur + " " + maxSell1Bottom);
      System.out.println(maxSell2Recur + " " + maxSell2Bottom);
  }
}
