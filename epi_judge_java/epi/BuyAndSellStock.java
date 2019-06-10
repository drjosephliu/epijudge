package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
import java.util.TreeSet;
public class BuyAndSellStock {
  @EpiTest(testDataFile = "buy_and_sell_stock.tsv")
  public static double computeMaxProfit(List<Double> prices) {

    // /* BRUTE FORCE ALGORITHM */
    // double profit = 0;
    //
    // for (int i = 0; i < prices.size() - 1; i++) {
    //   for (int j = i + 1; j < prices.size(); j++) {
    //     double newProfit = prices.get(j) - prices.get(i);
    //
    //     if (newProfit > profit) {
    //       profit = newProfit;
    //     }
    //   }
    // }
    //
    // return profit;
    // // Time complexity = O(n^2). Space complexity = O(1).


    /* IMPROVED VERSION */

    // TreeSet<Double> priceSet = new TreeSet<>();
    // priceSet.add(prices.get(0));

    double profit = 0;

    double lowestPrice = prices.get(0);

    for (int i = 1; i < prices.size(); i++) {
      double newPrice = prices.get(i);
      double newProfit = newPrice - lowestPrice;

      profit = Math.max(profit, newProfit);
      lowestPrice = Math.min(newPrice, lowestPrice);
    }
    return profit;

    // Time complexity = O(n). Space complexity = O(1).
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BuyAndSellStock.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
