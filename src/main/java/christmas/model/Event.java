package christmas.model;

public enum Event {
    D_DAY(1000,"크리스마스 디데이 할인"),
    WEEKDAY(2023,"평일 할인"),
    WEEKEND(2023,"주말 할인"),
    SPECIAL(1000,"특별 할인"),
    GIVE(25_000,"증정 이벤트"),
    NONE(0,"없음");

    final private int benefit;
    final private String name;

    Event(int benefit,String name) {
        this.benefit = benefit;
        this.name = name;
    }

    public int getBenefit() {
        return benefit;
    }
    public String getName() {
        return name;
    }


}
