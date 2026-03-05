package Lotto2;

import Lotto2.dto.LottoPurchaseResultDto;
import Lotto2.dto.LottoStatisticsDto;
import Lotto2.dto.LottoTicketDto;

import java.util.List;
import java.util.Scanner; // Added this import

// Application - MVC 패턴의 entry point
// Controller를 통해 Model과 View를 조정하여 전체 프로그램 흐름을 제어합니다.
public class Application {
    public static void main(String[] args) {
        // MVC 패턴 초기화
        View view = new View(new Scanner(System.in));
        LottoController controller = new LottoController();

        try {
            // 1단계: 로또 구매
            int purchaseAmount = view.inputPurchaseAmount();
            controller.validatePurchaseAmount(purchaseAmount);

            int lottoCount = controller.calculateLottoCount(purchaseAmount);
            controller.purchaseLotto(lottoCount);

            // 구매한 로또 정보 표시
            List<LottoTicketDto> purchasedLottos = controller.getPurchasedLottos();
            LottoPurchaseResultDto purchaseResultDto = new LottoPurchaseResultDto(lottoCount, purchasedLottos);
            view.displayPurchasedLottos(purchaseResultDto);

            // 2단계: 당첨 번호 입력
            String winningNumbers = view.inputWinningNumbers();
            int bonusNumber = view.inputBonusNumber();
            controller.registerWinningLotto(winningNumbers, bonusNumber);

            // 3단계 & 4단계: 당첨 통계 및 수익률 계산 후 DTO로 묶어 출력
            int[] statistics = controller.getStatistics();
            double earningsRate = controller.calculateEarningsRate(purchaseAmount, statistics);
            LottoStatisticsDto statisticsDto = new LottoStatisticsDto(statistics, earningsRate);
            view.displayLottoResult(statisticsDto);

        } catch (IllegalArgumentException e) {
            view.displayError(e.getMessage());
        } finally {
            view.close();
        }
    }
}




