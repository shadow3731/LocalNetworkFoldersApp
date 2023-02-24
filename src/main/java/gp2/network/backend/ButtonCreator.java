package gp2.network.backend;

import gp2.network.mainWindow.MainButton;
import gp2.network.mainWindow.MainFrame;
import gp2.network.mainWindow.MainLabel;

import java.awt.*;
import java.util.ArrayList;

/**
 * Класс для создания и конфигурации кнопок. Имеет свойства {@link ButtonCreator#currentXPosition},
 * {@link ButtonCreator#currentYPosition}, {@link ButtonCreator#currYPosInFrame}, {@link ButtonCreator#currentPlacement}.
 * @author Владислав Клименок
 */
public class ButtonCreator {
    /** Координата кнопки по оси X относительно раздела */
    private static short currentXPosition = MainButton.getXCoordinate();

    /** Координата кнопки по оси Y относительно раздела */
    private static short currentYPosition = MainButton.getYCoordinate();

    /** Координата кнопки по оси Y относительно окна */
    private static short currYPosInFrame = MainButton.getYCoordinate();

    /** Номер раздела, в котором распологается данная кнопка */
    private static byte currentPlacement = 0;

    public ButtonCreator() {}

    /**
     * Метод создает кнопку с сопутствующими параметрами.
     * @return Возвращает готовую кнопку типа {@link MainButton}.
     * @see LabelCreator#createLabels(MainButton[][])
     */
    public static MainButton[][] createButtons() {
        String[][] buttonsRaw = ValuesExtractor.classifyValues('b');
        ArrayList<MainButton[]> buttons = new ArrayList<>();
        ArrayList<MainButton> buttonsGroup = new ArrayList<>();
        byte currentPlacement = 0;

        for (String[] strings : buttonsRaw) {
            if (currentPlacement != Byte.parseByte(strings[1])) {
                buttons.add(buttonsGroup.toArray(new MainButton[0]));
                buttonsGroup.clear();
                currentPlacement++;
            }
            buttonsGroup.add(new MainButton(strings[0],
                    Byte.parseByte(strings[1]),
                    Boolean.parseBoolean(strings[2]),
                    Boolean.parseBoolean(strings[3]),
                    Byte.parseByte(strings[4]),
                    strings[5]));
        }
        buttons.add(buttonsGroup.toArray(new MainButton[0]));

        return buttons.toArray(new MainButton[0][0]);
    }

    /**
     * Метод определеят координаты кнопки внутри определенного раздела,
     * а также координату относительно окна.
     * @param button Кнопка, для которой нужно задать координаты.
     * @return Возвращает массив {@link Rectangle}, в которой задано
     * местоположение кнопки.
     */
    public static Rectangle definePosition(MainButton button) {
        checkPlacement(button.getPlacement());
        checkNewLine(button.isWide());

        Rectangle position = new Rectangle(currentXPosition, currentYPosition, 0, 0);

        if (button.isWide()) {
            position.setSize(2 * MainButton.getWIDTH() + 2 * MainButton.getGAP(), MainButton.getHEIGHT());
        } else {
            position.setSize(MainButton.getWIDTH(), MainButton.getHEIGHT());
        }

        currentXPosition += (short) (position.getWidth() + 2 * MainButton.getGAP());

        button.setYCoordinateInFrame(currYPosInFrame);
        return position;
    }

    /**
     * Метод проверяет находится ли данная кнопка в другом разделе.
     * Если так, все свойства {@link ButtonCreator} меняют свое значение.
     * @param placement Номер раздела, в котором находится данная кнопка.
     */
    private static void checkPlacement(byte placement) {
        if (currentPlacement != placement) {
            currentPlacement = placement;
            currentXPosition = MainButton.getXCoordinate();
            currYPosInFrame += MainButton.getHEIGHT() + 2 * MainLabel.getGAP() + MainButton.getYCoordinate();
            currentYPosition = MainButton.getYCoordinate();
        }
    }

    /**
     * Метод считывает информацию о местоположении кнопки и
     * определяет стоит ли ее переносить на новую строку.
     * @param isWide {@link MainButton#isWide}.
     */
    private static void checkNewLine(boolean isWide) {
        if (isWide) {
            if (currentXPosition >= MainFrame.getFrameWidth() - 45 - 4 * MainButton.getGAP() - 2 * MainButton.getWIDTH()) {
                currentXPosition = MainButton.getXCoordinate();
                currentYPosition += MainButton.getHEIGHT() + 2 * MainButton.getGAP();
                currYPosInFrame += MainButton.getHEIGHT() + 2 * MainButton.getGAP();
            }
        } else {
            if (currentXPosition >= MainFrame.getFrameWidth() - 45 - 2 * MainButton.getGAP() - MainButton.getWIDTH()) {
                currentXPosition = MainButton.getXCoordinate();
                currentYPosition += MainButton.getHEIGHT() + 2 * MainButton.getGAP();
                currYPosInFrame += MainButton.getHEIGHT() + 2 * MainButton.getGAP();
            }
        }

    }
}
