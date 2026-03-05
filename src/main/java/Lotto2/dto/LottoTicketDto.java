package Lotto2.dto;

import java.util.Collections;
import java.util.List;

public class LottoTicketDto {
    private final List<Integer> numbers;

    public LottoTicketDto(List<Integer> numbers) {
        this.numbers = Collections.unmodifiableList(numbers); // 불변 리스트로 저장
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
