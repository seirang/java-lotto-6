package Lotto2;

import Lotto2.Model.Lotto2;

import java.util.List;

/**
 * Application - MVC 패턴의 entry point
 * Controller를 통해 Model과 View를 조정하여 전체 프로그램 흐름을 제어합니다.
 */
public class Application {
    public static void main(String[] args) {
        // MVC 패턴 초기화
        View view = new View();
        LottoController controller = new LottoController();

        try {
            // 1단계: 로또 구매
            int purchaseAmount = view.inputPurchaseAmount();
            controller.validatePurchaseAmount(purchaseAmount);

            int lottoCount = controller.calculateLottoCount(purchaseAmount);
            controller.purchaseLotto(lottoCount);

            // 구매한 로또 정보 표시
            List<Lotto2> purchasedLottos = controller.getPurchasedLottos();
            view.displayPurchasedLottos(lottoCount, purchasedLottos);

            // 2단계: 당첨 번호 입력
            String winningNumbers = view.inputWinningNumbers();
            int bonusNumber = view.inputBonusNumber();
            controller.registerWinningLotto(winningNumbers, bonusNumber);

            // 3단계: 당첨 통계 계산 및 출력
            int[] statistics = controller.getStatistics();
            view.displayStatistics(statistics);

            // 4단계: 수익률 계산 및 출력
            double earningsRate = controller.calculateEarningsRate(purchaseAmount, statistics);
            view.displayEarningsRate(earningsRate);

        } catch (IllegalArgumentException e) {
            view.displayError(e.getMessage());
        } finally {
            view.close();
        }
    }
}




