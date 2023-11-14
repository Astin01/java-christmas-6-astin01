package christmas.controller;

import christmas.model.Badge;
import christmas.model.Event;
import christmas.model.Order;
import christmas.model.Sales;
import christmas.util.Sum;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.HashMap;
import java.util.Map;

public class EventPlanner {
    InputView inputView = new InputView();
    OutputView outputView = new OutputView();
    Order order;
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

        order = new Order(menu);
        sales = new Sales(date, order);

        outputView.showEventBanner(date);
        outputView.showMenuList(menu);
    }

    public void startPlanner() {
        int sum = Sum.calculateOrderSum(order.getOrder());
        Map<Event, Integer> sale = sales.getSale();

        outputView.showTotalBeforeSale(sum);
        outputView.showGiveBanner(sales);
        outputView.showBenefitBanner(sale);
    }

    public void endPlanner() {
        int orderSum = Sum.calculateOrderSum(order.getOrder());
        int saleSum = Sum.calculateSaleSum(sales.getSale());
        int totalSum = Sum.calculateTotalOrderSum(orderSum,saleSum,sales);

        Badge badge= new Badge(saleSum);
        String badgeGrade = badge.getBadgeGrade();

        outputView.showTotalBenefit(saleSum);
        outputView.showTotalPrice(totalSum);
        outputView.showEventBadge(badgeGrade);
    }

}
