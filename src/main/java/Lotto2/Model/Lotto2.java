package Lotto2.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Lotto2 {
    private final List<Integer> numbers;

    public Lotto2(List<Integer> numbers) {  // 생성자로 초기화
        validate(numbers);
        this.numbers = numbers;
    }

    @Override
    public String toString() {
        return numbers.toString();
    }

    public List<Integer> getNumbers() {     // getter
        return numbers;
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }

    public static Lotto2 create(){      // static은 왜 있는거지? -> 이것은 정적 팩토리 메서드(Static Factory Method) 패턴입니다. new Lotto2(...)라고 생성자를 직접 부르는 대신, 이름을 가진 메서드로 객체 생성을 캡슐화하는 좋은 습관입니다.
        List<Integer> oneGame = new ArrayList<>();
        Random r = new Random();
        while(oneGame.size() != 6){
            int temp = r.nextInt(45) + 1;
            if(!oneGame.contains(temp)){
                oneGame.add(temp);
            }
        }
        Collections.sort(oneGame);
        return new Lotto2(oneGame);
    }


    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }

        // 중복 번호 체크
        if (numbers.stream().distinct().count() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 중복될 수 없습니다.");
        }

        for (int num : numbers) {
            if (num < 1 || num > 45) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
            }
        }
    }
}
