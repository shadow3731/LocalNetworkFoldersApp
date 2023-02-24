package gp2.network.mainWindow;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Пользовательский класс разделов.
 * @author Владислав Клименок
 */
public class MainLabel extends JLabel {
    /** Координата раздела по оси X */
    private static final short X_COORDINATE = 15;

    /** Координата раздела по оси Y */
    private static final short Y_COORDINATE = 5;

    /** Ширина раздела */
    private static final short WIDTH = (short) (MainFrame.getFrameWidth() - 45);

    /** Отступ от раздела */
    private static final short GAP = 15;

    /**
     * Конструктор для создания и конфигурирования раздела.
     * @param labelName Название раздела
     * @param position Местоположение раздела
     * @param buttons Кнопки {@link MainButton}, входящие в данный раздел
     */
    public MainLabel(String labelName, Rectangle position, MainButton[] buttons) {
        Border border = BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(0),
                labelName, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION);
        this.setBorder(border);

        for (MainButton button : buttons) {
            this.add(button);
        }

        this.setBounds(position);
    }

    public static short getXCoordinate() {
        return X_COORDINATE;
    }

    public static short getYCoordinate() {
        return Y_COORDINATE;
    }

    public static short getLabelWidth() {
        return WIDTH;
    }

    public static short getGAP() {
        return GAP;
    }
}
