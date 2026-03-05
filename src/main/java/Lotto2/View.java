package Lotto2;


import Lotto2.dto.LottoPurchaseResultDto;
import Lotto2.dto.LottoStatisticsDto;
import Lotto2.dto.LottoTicketDto;

import java.util.List;
import java.util.Scanner;

// View: 사용자에게 직접적으로 보이는 부분. (출력문, 입력문만 담당)
public class View {
    private final Scanner sc;

    public View(Scanner scanner) {
        this.sc = scanner;
    }


     // 로또 구매 금액을 입력받고 반환합니다.
    public int inputPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        if (!sc.hasNextInt()) {
            throw new IllegalArgumentException("입력값이 잘못됐습니다.");
        }
        int amount = sc.nextInt();
        
        
        sc.nextLine();  // 버퍼 비워주기
        return amount;
    }


     // 구매한 로또 개수와 로또 번호 목록을 출력합니다.
    public void displayPurchasedLottos(LottoPurchaseResultDto dto) {
        System.out.println(dto.getCount() + "개를 구매했습니다.");
        for (LottoTicketDto lotto : dto.getPurchasedLottos()) {
            System.out.println(lotto);
        }
    }


    // 당첨 번호를 입력받고 반환합니다.
    public String inputWinningNumbers() {
        System.out.println("당첨 번호를 입력해 주세요.");
        
        String numbers = sc.nextLine();
        
        return numbers;
    }


     // 보너스 번호를 입력받고 반환합니다.
    public int inputBonusNumber() {
        System.out.println("보너스 번호를 입력해 주세요.");
        
        int bonus = sc.nextInt();
        
        
        sc.nextLine();
        return bonus;
    }

     // 로또 당첨 통계와 수익률을 출력합니다.
    public void displayLottoResult(LottoStatisticsDto dto) {
        int[] ranks = dto.getRanks();
        double earningsRate = dto.getEarningsRate();

        System.out.println("당첨 통계");
        System.out.println("---");
        System.out.println("3개 일치 (5,000원) - " + ranks[4] + "개");
        System.out.println("4개 일치 (50,000원) - " + ranks[3] + "개");
        System.out.println("5개 일치 (1,500,000원) - " + ranks[2] + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + ranks[1] + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + ranks[0] + "개");
        System.out.println("총 수익률은 " + String.format("%.2f", earningsRate) + "%입니다.");
    }


     // 에러 메시지를 출력합니다.
    public void displayError(String message) {
        System.out.println("[ERROR] " + message);
    }


     // Scanner를 종료합니다.
    public void close() {
        sc.close();
    }
}
