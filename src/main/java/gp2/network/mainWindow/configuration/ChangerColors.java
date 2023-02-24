package gp2.network.mainWindow.configuration;

import gp2.network.mainWindow.MainFrame;

import java.awt.*;

/**
 * Класс конфигурации кнопок.
 * @author Владислав Клименок
 */
public class ChangerColors {
    public ChangerColors() {}

    /**
     * Метод присваивает цвет поля кнопке.
     * @param colorID Номер цвета кнопки
     * @return Возвращает объект типа {@link Color}, в котором
     * содержится цвет.
     */
    public static Color changeButtonBackground(byte colorID) {
        switch (colorID) {
            case 1 -> {
                return Color.RED;
            }
            case 2 -> {
                return Color.YELLOW;
            }
            case 3 -> {
                return Color.GREEN;
            }
            case 4 -> {
                return Color.BLUE;
            }
            case 5 -> {
                return Color.GRAY;
            }
            default -> {
                return MainFrame.getDefaultColor();
            }
        }
    }

    /**
     * Метод присваивает цвет текста кнопке.
     * @param colorID Номер цвета кнопки
     * @return Возвращает объект типа {@link Color}, в котором
     * содержится цвет.
     */
    public static Color changeButtonForeground(byte colorID) {
        switch (colorID) {
            case 1, 4, 5 -> {
                return Color.WHITE;
            }
            default -> {
                return Color.BLACK;
            }
        }
    }
}
