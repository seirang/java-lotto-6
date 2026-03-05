package Lotto2.dto;

import java.util.Collections;
import java.util.List;

public class LottoPurchaseResultDto {
    private final int count;
    private final List<LottoTicketDto> purchasedLottos;

    public LottoPurchaseResultDto(int count, List<LottoTicketDto> purchasedLottos) {
        this.count = count;
        this.purchasedLottos = Collections.unmodifiableList(purchasedLottos); // 불변 리스트로 저장
    }

    public int getCount() {
        return count;
    }

    public List<LottoTicketDto> getPurchasedLottos() {
        return purchasedLottos;
    }
}
