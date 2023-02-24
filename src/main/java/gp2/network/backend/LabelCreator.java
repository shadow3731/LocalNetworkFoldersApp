package gp2.network.backend;

import gp2.network.mainWindow.MainButton;
import gp2.network.mainWindow.MainLabel;
import java.awt.*;

/**
 * Класс для создания и конфигурирования разделов.
 * @author Владислав Клименок
 */
public class LabelCreator {
    public LabelCreator() {}

    /**
     * Метод создает раздел с сопутствующими параметрами.
     * @param buttons Массив кнопок {@link MainButton}
     * @return Возвращает готовый раздел типа {@link MainLabel}.
     */
    public static MainLabel[] createLabels(MainButton[][] buttons) {
        String[][] labelsRaw = ValuesExtractor.classifyValues('l');
        MainLabel[] labels = new MainLabel[labelsRaw.length];

        for (int i = 0; i < labels.length; i++) {
            Rectangle position = new Rectangle(
                    buttons[i][0].getX() - MainButton.getGAP(),
                    buttons[i][0].getYCoordinateInFrame() - 2 * MainButton.getGAP(),
                    MainLabel.getLabelWidth(),
                    buttons[i][buttons[i].length - 1].getYCoordinateInFrame()
                            + 2 * MainButton.getHEIGHT() + 6 * MainButton.getGAP()
                            - buttons[i][0].getYCoordinateInFrame()
                            - 2 * MainButton.getGAP());

            labels[i] = new MainLabel(labelsRaw[i][0], position, buttons[i]);
        }

        return labels;
    }
}
