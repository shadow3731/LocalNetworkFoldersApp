package gp2.network.mainWindow;

import gp2.network.DirectoryOpener;
import gp2.network.backend.ButtonCreator;
import gp2.network.mainWindow.configuration.ChangerColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Пользовательский класс кнопки. Имеет свойства {@link MainButton#placement},
 * {@link MainButton#isSecured}, {@link MainButton#isWide}, {@link MainButton#path},
 * {@link MainButton#yCoordinateInFrame}.
 * @author Владислав Клименок
 */
public class MainButton extends JButton implements ActionListener, KeyListener {
    /** Ширина кнопки */
    private static final short WIDTH = 75;

    /** Высота кнопки */
    private static final short HEIGHT = 25;

    /** Отступ от кнопки */
    private static final short GAP = 5;

    /** Номер раздела */
    private final byte placement;

    /** Флаг установки пароля на кнопку */
    private boolean isSecured;

    /** Флаг ширины кнопки */
    private final boolean isWide;

    /** Путь, на который ссылается кнопка */
    private final String path;

    /** Координата кнопки по оси X, относительно раздела */
    private static final short X_COORDINATE = (short) (MainLabel.getXCoordinate() + GAP);

    /** Координата кнопки по оси Y, относительно раздела */
    private static final short Y_COORDINATE = (short) (MainLabel.getYCoordinate() + 4 * GAP);

    /** Координата кнопки по оси Y, относительно окна */
    private short yCoordinateInFrame;

    /**
     * Конструктор создания и конфигурирования кнопки.
     * @param name Имя кнопки
     * @param placement {@link MainButton#placement}
     * @param isSecured {@link MainButton#isSecured}
     * @param isWide {@link MainButton#isWide}
     * @param colorID Номер цвета кнопки
     * @param path {@link MainButton#path}
     */
    public MainButton(String name, byte placement, boolean isSecured, boolean isWide, byte colorID, String path) {
        this.placement = placement;
        this.isSecured = isSecured;
        this.isWide = isWide;
        this.path = path;

        this.setBounds(ButtonCreator.definePosition(this));
        this.setMargin(new Insets(2, 2, 2, 2));
        this.setText(name);
        this.setBackground(ChangerColors.changeButtonBackground(colorID));
        this.setForeground(ChangerColors.changeButtonForeground(colorID));
        this.addActionListener(this);
        this.addKeyListener(this);
    }

    public static short getXCoordinate() {
        return X_COORDINATE;
    }

    public static short getYCoordinate() {
        return Y_COORDINATE;
    }

    public static short getWIDTH() {
        return WIDTH;
    }

    public static short getHEIGHT() {
        return HEIGHT;
    }

    public static short getGAP() {
        return GAP;
    }

    public byte getPlacement() {
        return placement;
    }

    public boolean isSecured() {
        return isSecured;
    }

    public void setSecured(boolean isSecured) {
        this.isSecured = isSecured;
    }

    public boolean isWide() {
        return isWide;
    }

    public String getPath() {
        return path;
    }

    public short getYCoordinateInFrame() {
        return yCoordinateInFrame;
    }

    public void setYCoordinateInFrame(short yCoordinateInFrame) {
        this.yCoordinateInFrame = yCoordinateInFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this) DirectoryOpener.openDirectory(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 10) DirectoryOpener.openDirectory(this);
    }
}
