package christmas.model;

public class Badge {
    private static final int BADGE_CRITERIA_STAR = 5_000;
    private static final int BADGE_CRITERIA_TREE = 10_000;
    private static final int BADGE_CRITERIA_SANTA = 20_000;

    String badgeGrade;

    public Badge(int saleSum){
        this.badgeGrade = judgeBadge(saleSum);
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

    public String getBadgeGrade(){
        return badgeGrade;
    }
}
