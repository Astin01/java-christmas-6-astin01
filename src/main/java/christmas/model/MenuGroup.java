package christmas.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public enum MenuGroup {
    MAIN_DISH("메인메뉴", Arrays.asList(Menu.T_BONE_STEAK, Menu.BARBECUE_LIB, Menu.SEAFOOD_PASTA, Menu.CHRISTMAS_PASTA)),
    APPETIZER("에피타이저", Arrays.asList(Menu.MUSHROOM_SOUP, Menu.TAPAS, Menu.CAESAR_SALAD)),
    DESSERT("디저트", Arrays.asList(Menu.CHOCOLATE_CAKE, Menu.ICE_CREAM)),
    DRINK("음료", Arrays.asList(Menu.ZERO_COLA, Menu.RED_WINE, Menu.CHAMPAGNE)),
    EMPTY("없음", Collections.EMPTY_LIST);
    private String menuTitle;
    private List<Menu> menuList;

    MenuGroup(String menuTitle, List<Menu> menuList) {
        this.menuTitle = menuTitle;
        this.menuList = menuList;
    }

    public static MenuGroup findByMenu(Menu menuType) {
        return Arrays.stream(MenuGroup.values())
                .filter(menugroup -> menugroup.hasMenu(menuType))
                .findAny()
                .orElse(EMPTY);
    }

    public boolean hasMenu(Menu menuType) {
        return menuList.stream()
                .anyMatch(menu -> menu == menuType);
    }

    public String getMenuTitle() {
        return this.menuTitle;
    }

}
