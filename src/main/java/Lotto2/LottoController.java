package Lotto2;

import Lotto2.Model.Lotto2;
import Lotto2.Model.WinningLotto;
import Lotto2.Model.Rank;
import Lotto2.Model.ProfitCalculator;
import Lotto2.dto.LottoTicketDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// LottoController - MVC 패턴의 Controller 역할
// Model(데이터)과 View(UI) 사이의 중개자 역할을 담당합니다.
public class LottoController {
    private List<Lotto2> purchasedLottos;   // 구매한 로또 번호들
    private WinningLotto winningLotto;      // 당첨 번호 + 보너스 번호

    public LottoController() {
        purchasedLottos = new ArrayList<>();
    }

    // 입력받은 구매 금액이 유효한지 검증합니다.
    public void validatePurchaseAmount(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("구매 금액은 0보다 커야 합니다.");
        }
        if (amount % 1000 != 0) {
            throw new IllegalArgumentException("로또는 천원 단위로 구매가 가능합니다.");
        }
    }

    // 구매 금액으로부터 로또 개수를 계산합니다.
    public int calculateLottoCount(int amount) {
        return amount / 1000;
    }

    // 지정된 개수만큼 로또를 구매합니다.
    public void purchaseLotto(int count) {
        for (int i = 0; i < count; i++) {
            purchasedLottos.add(Lotto2.create());
        }
    }

    // 구매한 모든 로또를 반환합니다.
    public List<LottoTicketDto> getPurchasedLottos() {
        return purchasedLottos.stream()
                .map(lotto -> new LottoTicketDto(lotto.getNumbers()))
                .collect(Collectors.toList());
    }

    // 당첨 번호와 보너스 번호를 등록합니다.
    public void registerWinningLotto(String input, int bonus) {
        List<Integer> numbers = parseWinningNumbers(input);
        this.winningLotto = new WinningLotto(numbers, bonus);
    }

    // 쉼표로 구분된 문자열을 정수 리스트로 파싱합니다.
    // 예: "1,2,3,4,5,6" -> [1, 2, 3, 4, 5, 6]
    private List<Integer> parseWinningNumbers(String input) {
        String[] tokens = input.split(",");
        List<Integer> numbers = new ArrayList<>();
        for (String token : tokens) {
            if (token.trim().isEmpty()) {
                throw new IllegalArgumentException("빈 번호가 있습니다.");
            }
            try {
                numbers.add(Integer.parseInt(token.trim()));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("유효하지 않은 번호입니다: " + token);
            }
        }
        return numbers;
    }


     // 주어진 로또의 등수를 반환합니다.
    public Rank getRankType(Lotto2 lotto) {
        int matchCount = winningLotto.countMatch(lotto);
        boolean bonusHit = winningLotto.isBonusMatch(lotto);
        return Rank.valueOf(matchCount, bonusHit);
    }

    // 당첨 통계를 계산합니다.
    //ranks[0] = 1등, ranks[1] = 2등, ..., ranks[4] = 5등
    public int[] getStatistics() {
        int[] ranks = new int[5]; // 1등~5등만 기록
        for (Lotto2 lotto : purchasedLottos) {
            Rank type = getRankType(lotto);
            if (type == Rank.FIRST) ranks[0]++;
            else if (type == Rank.SECOND) ranks[1]++;
            else if (type == Rank.THIRD) ranks[2]++;
            else if (type == Rank.FOURTH) ranks[3]++;
            else if (type == Rank.FIFTH) ranks[4]++;
        }
        return ranks;
    }

    // 수익률을 계산합니다.
    public double calculateEarningsRate(int amount, int[] ranks) {
        return ProfitCalculator.calculateProfit(amount, ranks);
    }

}
