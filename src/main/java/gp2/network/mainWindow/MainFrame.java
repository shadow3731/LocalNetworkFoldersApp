package gp2.network.mainWindow;

import gp2.network.backend.FilesReading;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Пользовательский класс окна.
 * @author Владислав Клименок
 */
public class MainFrame extends JFrame {
    /** Ширина окна */
    private final static short FRAME_WIDTH = 840;
    
    /** Стандартный цвет окна */
    private final static Color DEFAULT_COLOR = new Color(142, 245, 255);

    /**
     * Конструктор создания и конфигурирования окон.
     * @param labels массив разделов {@link MainLabel}
     */
    public MainFrame(MainLabel[] labels) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setTitle("Сетевое окружение " + FilesReading.readOrganizationLocal());
        this.setSize(FRAME_WIDTH, (short) labels[labels.length - 1].getY() + labels[labels.length - 1].getHeight() + 55);
        this.setResizable(false);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dimension.width/2-this.getSize().width/2, dimension.height/2-this.getSize().height/2);

        ImageIcon icon = new ImageIcon("src" + File.separator + "loop.png");
        this.setIconImage(icon.getImage());

        this.setLayout(null);
        for (MainLabel mainLabel : labels) {
            this.add(mainLabel);
        }

        this.getContentPane().setBackground(DEFAULT_COLOR);
    }

    /**
     * Метод делает окно видимым.
     */
    public void makeVisible() {
        this.setVisible(true);
    }

    public static short getFrameWidth() {
        return FRAME_WIDTH;
    }
    
    public static Color getDefaultColor() {
        return DEFAULT_COLOR;
    }
}
