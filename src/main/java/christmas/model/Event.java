package christmas.model;

public enum Event {
    D_DAY(1000),
    WEEKDAY(2023),
    WEEKEND(2023),
    SPECIAL(1000),
    GIVE(25_000);

    final private int benefit;

    Event(int benefit) {
        this.benefit = benefit;
    }

    public int getBenefit() {
        return benefit;
    }


}
