package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.Menu;
import christmas.model.MenuGroup;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class InputView {
    private static final int MENU_MIN_COUNT = 1;
    private static final int TOTAL_MENU_MAX_COUNT = 20;

    public String readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String date = Console.readLine();
        try {
            int convertDate = Integer.parseInt(date);
            isInRange(convertDate);
        } catch (IllegalArgumentException error) {
            System.out.println("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            readDate();
        }
        return date;
    }

    private void isInRange(int date) {
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException();
        }
    }

    public HashMap<String, String> readMenu() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String[] menu;
        HashMap<String, String> menuInput = new HashMap<>();
        try {
            menu = Console.readLine().split("[,\\-]");
            validateMenu(menu);
            putMenuInput(menu, menuInput);
        } catch (IllegalArgumentException error) {
            System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            readMenu();
        }
        return menuInput;
    }

    private void putMenuInput(String[] menu, HashMap<String, String> menuInput) {
        for (int i = 0; i < menu.length; i += 2) {
            menuInput.put(menu[i], menu[i + 1]);
        }
    }

    private void validateMenu(String[] menu) {
        menuCheck(menu);
        countCheck(menu);
        menuDupCheck(menu);
        checkOnlyDrink(menu);
    }

    private void menuCheck(String[] menu) {
        for (int i = 0; i < menu.length; i += 2) {
            int finalI = i;
            boolean isName = Arrays.stream(Menu.values()).anyMatch(v -> v.getName().equals(menu[finalI]));
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

            minCountCheck(menuCount);
        }
        maxCountCheck(totalCount);
    }

    private void minCountCheck(int menuCount) {
        if (menuCount < MENU_MIN_COUNT) {
            throw new IllegalArgumentException();
        }
    }

    private void maxCountCheck(int totalCount) {
        if (totalCount > TOTAL_MENU_MAX_COUNT) {
            throw new IllegalArgumentException();
        }
    }

    private void menuDupCheck(String[] input) {
        HashSet<String> setInput = new HashSet<>();
        int menuCount = input.length / 2;

        for (int i = 0; i < input.length; i += 2) {
            setInput.add(input[i]);
        }
        if (menuCount != setInput.size()) {
            throw new IllegalArgumentException();
        }
    }

    private void checkOnlyDrink(String[] input) {
        List<Menu> menuList = MenuGroup.DRINK.getMenuList();
        int drinkCount = 0;
        for (int i = 0; i < input.length; i += 2) {
            int finalI = i;
            boolean match = menuList.stream()
                    .anyMatch(menu -> Objects.equals(menu.getName(), input[finalI]));
            if (match) {
                drinkCount += 1;
            }
        }
        checkDrinkCount(drinkCount, input.length / 2);
    }

    private void checkDrinkCount(int drinkCount, int menuCount) {
        if (drinkCount == menuCount) {
            throw new IllegalArgumentException();
        }
    }
}
