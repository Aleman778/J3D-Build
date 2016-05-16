package j3dbuild.utils;

import java.awt.Color;

public class ThemeUtils {

    public static final int CLASSIC_THEME = 0;
    public static final int DARK_THEME = 1;
    public static final int PURPLE_DARK_THEME = 2;

    // Theme Colors
    public static Color COLOR_PANEL = new Color(244, 244, 244);
    public static Color COLOR_ITEM = new Color(150, 150, 150);
    public static Color COLOR_ITEM_BORDER = new Color(150, 150, 150);
    public static Color COLOR_ITEM_HOVER = new Color(51, 153, 255);
    public static Color COLOR_ITEM_SELECTED = new Color(51, 153, 255);
    public static Color COLOR_ITEM_DISABLED = new Color(100, 100, 100);
    public static Color COLOR_FOREGROUND = new Color(0, 0, 0);
    public static Color COLOR_BACKGROUND = new Color(200, 200, 200);
    public static Color COLOR_SELECTION = new Color(20, 170, 222);
    public static Color COLOR_SELECTION_FOREGROUND = new Color(255, 255, 255);
    public static Color COLOR_ERROR = Color.RED;

    public static void setTheme(int THEME) {
        switch (THEME) {
            case CLASSIC_THEME:
                COLOR_PANEL = new Color(244, 244, 244);
                COLOR_ITEM = new Color(150, 150, 150);
                COLOR_ITEM_DISABLED = new Color(100, 100, 100);
                COLOR_FOREGROUND = new Color(0, 0, 0);
                COLOR_BACKGROUND = new Color(200, 200, 200);
                COLOR_SELECTION = new Color(51, 153, 255);
                COLOR_SELECTION_FOREGROUND = new Color(255, 255, 255);
                COLOR_ERROR = Color.RED;
                break;
            case DARK_THEME:
                COLOR_PANEL = new Color(55, 55, 55);
                COLOR_ITEM = new Color(73, 73, 75);
                COLOR_ITEM_DISABLED = new Color(150, 150, 150);
                COLOR_FOREGROUND = new Color(255, 255, 255);
                COLOR_BACKGROUND = new Color(37, 37, 38);
                COLOR_SELECTION = new Color(57, 69, 100);
                COLOR_SELECTION_FOREGROUND = new Color(255, 255, 255);
                COLOR_ERROR = Color.RED;
                break;
            case PURPLE_DARK_THEME:
                COLOR_PANEL = new Color(55, 55, 55);
                COLOR_ITEM = new Color(171, 20, 222);
                COLOR_ITEM_HOVER = new Color(191, 98, 222);
                COLOR_ITEM_DISABLED = new Color(191, 98, 222);
                COLOR_ITEM_SELECTED = new Color(171, 20, 222);
                COLOR_FOREGROUND = new Color(255, 255, 255);
                COLOR_BACKGROUND = new Color(37, 37, 38);
                COLOR_SELECTION = new Color(171, 20, 222);
                COLOR_SELECTION_FOREGROUND = new Color(255, 255, 255);
                break;
        }
    }
}
