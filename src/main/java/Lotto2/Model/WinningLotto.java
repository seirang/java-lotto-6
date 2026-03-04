package Lotto2.Model;

import java.util.List;

// 당첨 로또
public class WinningLotto {
    private final List<Integer> winningNumbers;
    private final int bonus;

    public WinningLotto(List<Integer> winningNumbers, int bonus) {
        this.winningNumbers = winningNumbers;
        this.bonus = bonus;
    }

    public int countMatch(Lotto2 lotto){// 구매 로또와 번호 몇개가 맞는지 계산. 맞춘 개수로 등수(Rank)를 정하는것과는 별개지.
        int cnt = 0;
        for(int num : winningNumbers){
            if(lotto.contains(num)){
                cnt++;
            }
        }
        return cnt;
    }

    public boolean isBonusMatch(Lotto2 lotto){
        return lotto.contains(bonus);
    }
}
