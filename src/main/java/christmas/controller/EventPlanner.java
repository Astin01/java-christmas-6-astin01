package christmas.controller;

import christmas.model.Event;
import christmas.model.Order;
import christmas.model.Sales;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.HashMap;
import java.util.Map;

public class EventPlanner {
    private static final int BADGE_CRITERIA_STAR = 5_000;
    private static final int BADGE_CRITERIA_TREE = 10_000;
    private static final int BADGE_CRITERIA_SANTA = 20_000;

    InputView inputView = new InputView();
    OutputView outputView = new OutputView();
    Order order;
    HashMap<String, String> menuInput = new HashMap<>();
    Sales sales;

    public void run() {
        initPlanner();
        startPlanner();
        endPlanner();
    }

    public void initPlanner() {
        outputView.showStartBanner();
        String date = inputView.readDate();
        HashMap<String, String> menu = inputView.readMenu();

        order = new Order(menuInput);
        order.calculateSum();
        sales = new Sales(date, order);

        outputView.showEventBanner(date);
    }

    public void startPlanner() {
        outputView.showMenuList(menuInput);
        int sum = order.getSum();
        outputView.showTotalBeforeSale(sum);
        int give = judgeGive();
        outputView.showGiveBanner(give);
        Map<Event, Integer> sale = sales.getSale();
        outputView.showBenefitBanner(sale);
        sales.calculateSum();
    }

    public void endPlanner() {
        int saleSum = sales.getSaleSum();
        int sum = order.getSum();
        int totalSum = calculateTotalSum(saleSum, sum);
        outputView.showTotalBenefit(saleSum);
        outputView.showTotalPrice(totalSum);
        String badge = judgeBadge(saleSum);
        outputView.showEventBadge(badge);
    }

    private int judgeGive() {
        Map<Event, Integer> sale = sales.getSale();
        int giftPrice = Event.GIVE.getBenefit();
        if (sale.get(Event.GIVE) != null) {
            return sale.get(Event.GIVE) / giftPrice;
        }
        return 0;
    }

    private int calculateTotalSum(int saleSum, int sum) {
        Map<Event, Integer> sale = sales.getSale();
        if (sale.get(Event.GIVE) != null) {
            return sum - saleSum + sale.get(Event.GIVE);
        }
        return sum - saleSum;
    }

    private String judgeBadge(int saleSum) {
        if (saleSum >= BADGE_CRITERIA_SANTA) {
            return "산타";
        }
        if (saleSum >= BADGE_CRITERIA_TREE) {
            return "트리";
        }
        if (saleSum >= BADGE_CRITERIA_STAR) {
            return "별";
        }
        return "없음";
    }

}
