package Lotto2.dto;

public class LottoStatisticsDto {
    private final int[] ranks; // 1등~5등 당첨 개수
    private final double earningsRate;

    public LottoStatisticsDto(int[] ranks, double earningsRate) {
        // 배열은 가변적이므로 방어적 복사
        this.ranks = ranks.clone();
        this.earningsRate = earningsRate;
    }

    public int[] getRanks() {
        return ranks.clone(); // 외부에서 수정 불가능하도록 복사본 반환
    }

    public double getEarningsRate() {
        return earningsRate;
    }
}
