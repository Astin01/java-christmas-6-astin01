package christmas.model;

public enum Menu {
    MUSHROOM_SOUP(6_000, "6,000", "양송이수프"),
    TAPAS(5_500, "5,500", "타파스"),
    CAESAR_SALAD(8_000, "8,000", "시저샐러드"),
    CHOCOLATE_CAKE(15_000, "15,000", "초콜릿케이크"),
    ICE_CREAM(5_000, "5,0", "아이스크림"),
    ZERO_COLA(3_000, "3,000", "제로콜라"),
    RED_WINE(60_000, "60,000", "레드와인"),
    CHAMPAGNE(25_000, "25,000", "샴페인"),
    T_BONE_STEAK(55_000, "55,000", "티본스테이크"),
    BARBECUE_LIB(54_000, "54,000", "바베큐립"),
    SEAFOOD_PASTA(35_000, "35,000", "시푸드파스타"),
    CHRISTMAS_PASTA(25_000, "25,000", "크리스마스파스타");

    final private int price;
    final private String convertPrice;
    final private String name;

    Menu(int price, String convertPrice, String name) {
        this.price = price;
        this.convertPrice = convertPrice;
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public String getConvertPrice() {
        return convertPrice;
    }

    public String getName() {
        return name;
    }

}
