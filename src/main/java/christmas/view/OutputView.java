package christmas.view;

import christmas.model.Event;
import christmas.model.Menu;
import christmas.model.MenuGroup;
import christmas.model.Sales;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class OutputView {
    DecimalFormat df = new DecimalFormat("###,###");

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
        String totalBeforeBenefit = df.format(sum);
        System.out.println("\n<할인 전 총주문 금액>");
        System.out.println(totalBeforeBenefit + "원");
    }

    public void showGiveBanner(Sales sales) {
        System.out.println("\n<증정 메뉴>");
        int give = judgeGive(sales);
        if (give > 0) {
            System.out.println("샴페인 " + give + "개");
            return;
        }
        System.out.println("없음");
    }

    private int judgeGive(Sales sales) {
        Map<Event, Integer> sale = sales.getSale();
        int giftPrice = Event.GIVE.getBenefit();
        if (sale.get(Event.GIVE) != null) {
            return sale.get(Event.GIVE) / giftPrice;
        }
        return 0;
    }

    public void showBenefitBanner(Map<Event, Integer> sale) {
        System.out.println("\n<혜택 내역>");
        Arrays.stream(Event.values())
                .forEach(event -> judgeBenefitBanner(sale, event));
        if (sale.isEmpty()) {
            System.out.println("없음");
        }
    }

    private void judgeBenefitBanner(Map<Event, Integer> sale, Event event) {
        if (sale.containsKey(event)) {
            String eventName = event.getName();
            int salePrice = sale.get(event);
            String convertSalePrice = df.format(salePrice);
            System.out.println(eventName + ": -" + convertSalePrice + "원");
        }
    }

    public void showTotalBenefit(int saleSum) {
        String totalBenefit = df.format(saleSum);
        System.out.println("\n<총혜택 금액>");
        if (saleSum == 0) {
            System.out.println(totalBenefit + "원");
            return;
        }
        System.out.println("-" + totalBenefit + "원");
    }

    public void showTotalPrice(int totalSum) {
        String afterBenefit = df.format(totalSum);
        System.out.println("\n<할인 후 예상 결제 금액>");
        System.out.println(afterBenefit + "원");
    }

    public void showEventBadge(String badge) {
        System.out.println("\n<12월 이벤트 배지>");
        System.out.println(badge);
    }
}
