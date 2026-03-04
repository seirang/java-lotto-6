package Lotto2.Model;

// 로또 등수
public enum Rank {  // 여기에 상금까지 등록해서
    FIRST(6,2_000_000_000,false),
    SECOND(5,30_000_000,true),
    THIRD(5, 1_500_000,false),
    FOURTH(4,50_000,false),
    FIFTH(3,5_000,false),
    MISS(0, 0, false);

    private final int matchCount;
    private final boolean bonusRequired; // 보너스 번호가 필요한지 아닌지.
    private final int prizeMoney;


    Rank(int matchCount, int prizeMoney, boolean bonusRequired) {
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
        this.bonusRequired = bonusRequired;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public boolean isBonusRequired () {
        return bonusRequired;
    }

    public static Rank valueOf(int matchCount, boolean bonusHit) {
        for (Rank rank : values()) {
            if (rank.matchCount == matchCount) {
                if (rank.bonusRequired) {
                    if (bonusHit) return rank;    // 보너스 맞으면 2등
                } else {
                    return rank;        // 아닐 경우 3등
                }
            }
        }
        return MISS;
    }
}
