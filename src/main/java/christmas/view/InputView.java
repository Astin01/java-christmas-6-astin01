package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.Menu;
import christmas.model.MenuGroup;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class InputView {
    private static final int MENU_MIN_COUNT = 1;
    private static final int TOTAL_MENU_MAX_COUNT = 20;

    public String readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = Console.readLine();
        try {
            int convertInput = Integer.parseInt(input);
            isInRange(convertInput);
        } catch (IllegalArgumentException error) {
            System.out.println("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            readDate();
        }
        return input;
    }

    private void isInRange(int input) {
        if (input < 1 || input > 31) {
            throw new IllegalArgumentException();
        }
    }

    public String[] readMenu() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String[] input = null;
        try {
            input = Console.readLine().split("[,\\-]");
            menuCheck(input);
            countCheck(input);
            menuDupCheck(input);
            checkOnlyDrink(input);
        } catch (IllegalArgumentException error) {
            System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            readMenu();
        }
        return input;
    }

    private void menuCheck(String[] input) {
        for (int i = 0; i < input.length; i += 2) {
            int finalI = i;
            boolean isName = Arrays.stream(Menu.values()).anyMatch(v -> v.getName().equals(input[finalI]));
            if (!isName) {
                throw new IllegalArgumentException();
            }
        }
    }

    private void countCheck(String[] menu) {
        int totalCount = 0;
        for (int i = 1; i < menu.length; i += 2) {
            int menuCount = Integer.parseInt(menu[i]);
            totalCount += menuCount;
            if (menuCount < MENU_MIN_COUNT) {
                throw new IllegalArgumentException();
            }
            if (totalCount > TOTAL_MENU_MAX_COUNT) {
                throw new IllegalArgumentException();
            }
        }
    }

    private void menuDupCheck(String[] input) {
        HashSet<String> setInput = new HashSet<>(Arrays.asList(input));

        if (input.length != setInput.size()) {
            throw new IllegalArgumentException();
        }
    }

    private void checkOnlyDrink(String[] input) {
        List<Menu> menuList = MenuGroup.DRINK.getMenuList();
        int count = 0;
        for (int i = 0; i < input.length; i += 2) {
            int finalI = i;
            boolean match = menuList.stream()
                    .anyMatch(menu -> Objects.equals(menu.getName(), input[finalI]));
            if (match) {
                count += 1;
            }
        }
        if (count == input.length / 2) {
            throw new IllegalArgumentException();
        }
    }
}
