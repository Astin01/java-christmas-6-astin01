package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public String readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = Console.readLine();
//        try {
//            isCorrectMoney(money);
//        } catch (IllegalArgumentException error) {
//            System.out.println("[ERROR] 구입금액은 1,000원 단위로 입력해주세요.");
//            moneyInput();
//        }
        return input;
    }
    public String[] readMenu(){
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String[] input = Console.readLine().split("[,\\-]");
//        try {
//            isCorrectMoney(money);
//        } catch (IllegalArgumentException error) {
//            System.out.println("[ERROR] 구입금액은 1,000원 단위로 입력해주세요.");
//            moneyInput();
//        }
        return input;
    }
}
