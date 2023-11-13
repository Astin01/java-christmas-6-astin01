package christmas.view;

import christmas.model.Event;
import java.util.HashMap;
import java.util.Map;

public class OutputView {
    public void showStartBanner() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void showEventBanner(String date) {
        System.out.println("12월 " + date + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public void showMenuList(HashMap<String, String> menuInput) {
        System.out.println("\n<주문 메뉴>");
        for (String i : menuInput.keySet()) {
            System.out.println(i + " " + menuInput.get(i) + "개");
        }
    }

    public void showTotalBeforeSale(int sum) {
        System.out.println("\n<할인 전 총주문 금액>");
        System.out.println(sum + "원");
    }

    public void showGiveBanner(int give) {
        System.out.println("\n<증정 메뉴>");
        if (give > 0) {
            System.out.println("샴페인 " + give + "개");
            return;
        }
        System.out.println("없음");
    }

    public void showBenefitBanner(Map<Event, Integer> sale) {
        System.out.println("\n<혜택 내역>");
        if (sale.containsKey(Event.D_DAY)) {
            System.out.println("크리스마스 디데이 할인: -" + sale.get(Event.D_DAY) + "원");
        }
        if (sale.containsKey(Event.WEEKDAY)) {
            System.out.println("평일 할인: -" + sale.get(Event.WEEKDAY) + "원");
        }
        if (sale.containsKey(Event.WEEKEND)) {
            System.out.println("주말 할인: -" + sale.get(Event.WEEKEND) + "원");
        }
        if (sale.containsKey(Event.SPECIAL)) {
            System.out.println("특별 할인: -" + sale.get(Event.SPECIAL) + "원");
        }
        if (sale.containsKey(Event.GIVE)) {
            System.out.println("증정 이벤트: -" + sale.get(Event.GIVE) + "원");
        }
        if (sale.isEmpty()) {
            System.out.println("없음");
        }
    }

    public void showTotalBenefit(int saleSum) {
        System.out.println("\n<총혜택 금액>");
        if (saleSum == 0) {
            System.out.println(saleSum + "원");
            return;
        }
        System.out.println("-" + saleSum + "원");
    }

    public void showTotalPrice(int totalSum) {
        System.out.println("\n<할인 후 예상 결제 금액>");
        System.out.println(totalSum + "원");
    }

    public void showEventBadge(String badge) {
        System.out.println("\n<12월 이벤트 배지>");
        System.out.println(badge);
    }

}
