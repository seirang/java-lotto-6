package Lotto2.Model;

public class ProfitCalculator {
    // 수익률 계산 로직
    public static double calculateProfit(int purchaseAmount, int[] ranks) {
        long totalPrize = 0;
        // ranks[0]: 1등, ranks[1]: 2등 ... ranks[4]: 5등 순서라고 가정
        totalPrize += (long) Rank.FIRST.getPrizeMoney() * ranks[0];
        totalPrize += (long) Rank.SECOND.getPrizeMoney() * ranks[1];
        totalPrize += (long) Rank.THIRD.getPrizeMoney() * ranks[2];
        totalPrize += (long) Rank.FOURTH.getPrizeMoney() * ranks[3];
        totalPrize += (long) Rank.FIFTH.getPrizeMoney() * ranks[4];

        double rate = ((double) (totalPrize - purchaseAmount) / purchaseAmount) * 100;
        return Math.round(rate * 10) / 10.0; // 소수점 둘째 자리 반올림
    }
}
