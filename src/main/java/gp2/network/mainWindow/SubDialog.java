package gp2.network.mainWindow;

import gp2.network.backend.exceptions.NotUserFolderException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Пользовательский класс диалоговых окон. Имеет свойства
 * {@link SubDialog#TARGET}, {@link SubDialog#PATH}, {@link SubDialog#ERROR_MESSAGE},
 * {@link SubDialog#label}, {@link SubDialog#passwordField}, {@link SubDialog#button}.
 * @author Владислав Клименок
 */
public class SubDialog extends JDialog implements ActionListener, KeyListener {
    /** Тип диалогового окна */
    private final String TARGET;
    
    /** Директория кнопки */
    private final Path PATH;
    
    /** Сообщение об ошибке */
    private final String ERROR_MESSAGE;
    
    /** Раздел типа {@link JLabel} */
    private JLabel label;
    
    /** Текстовое поле с паролем типа {@link JPasswordField} */
    private JPasswordField passwordField;
    
    /** Кнопка типа {@link JButton} */
    private JButton button;

    /**
     * Конструктор для создания диалогового окна, направленный на
     * требование ввода пароля.
     * @param target {@link SubDialog#TARGET}
     * @param path {@link SubDialog#PATH}
     */
    public SubDialog(String target, Path path) {
        this.TARGET = target;
        this.PATH = path;
        this.ERROR_MESSAGE = null;
    }

    /**
     * Конструктор для создания диалогового окна, направленный на
     * вывод сообщений об ошибках.
     * @param target {@link SubDialog#TARGET}
     * @param message {@link SubDialog#ERROR_MESSAGE}
     */
    public SubDialog(String target, String message) {
        this.TARGET = target;
        this.PATH = null;
        this.ERROR_MESSAGE = message;
    }


    /**
     * Метод для создания и конфигурирования диалогового окна.
     */
    public void createDialog() {
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.getContentPane().setBackground(Color.WHITE);

        if (TARGET.equals("password")) {
            final short WIDTH = 300;
            final short HEIGHT = 100;

            this.setSize(WIDTH, HEIGHT);
            this.setModal(true);
            this.setTitle("Не так быстро!");

            label = new JLabel();
            label.setText("Для открытия папки требуется ввести пароль");
            label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalTextPosition(JLabel.CENTER);
            label.setBounds(0, 0, WIDTH - 15, HEIGHT / 4);

            passwordField = new JPasswordField();
            passwordField.setBounds(5, HEIGHT - 70, 215, 26);
            passwordField.addKeyListener(this);

            button = new JButton();
            button.setText("OK");
            button.setBounds(WIDTH - 76, HEIGHT - 70, 55, 25);
            button.addActionListener(this);

            this.add(label);
            this.add(passwordField);
            this.add(button);
        } else if (TARGET.equals("error")) {
            final short WIDTH = 450;
            final short HEIGHT = 150;

            this.setSize(WIDTH, HEIGHT);
            this.setModal(false);
            this.setTitle("Ошибка");

            label = new JLabel();
            label.setText(ERROR_MESSAGE);
            label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setBounds(5, 5, WIDTH - 25, HEIGHT - 75);

            button = new JButton();
            button.setText("OK");
            button.setBounds(WIDTH - 76, HEIGHT - 70, 55, 25);
            button.addActionListener(this);

            this.add(label);
            this.add(button);
        }

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dimension.width/2-this.getSize().width/2, dimension.height/2-this.getSize().height/2);

        this.setLayout(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            if (TARGET.equals("password")) showPasswordDialog();
            else if (TARGET.equals("error")) this.dispose();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 10) showPasswordDialog();
    }

    /**
     * Метод проверяет првильность введенного пароля в диалоговом
     * окне. Если пароль правильный, то закрывает диалоговое окно 
     * и открывает директорию, если неправильный, то требует ввести пароль еще раз.
     */
    private void showPasswordDialog() {
        final String PASSWORD = "4444";
        String password = new String(passwordField.getPassword());

        if (password.equals(PASSWORD)) {
            this.dispose();
            try {
                Desktop.getDesktop().open(PATH.toFile());
            } catch (IOException | IllegalArgumentException e) {
                try {
                    throw new NotUserFolderException("<html>Не удалось "
                            + "подключиться к серверу либо директория не найдена. "
                            + "Обратитесь за помощью к системному "
                            + "администратору.<br/>Причина: " + e.getMessage()
                            + "</html");
                } catch (NotUserFolderException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            this.label.setText("Неправильный пароль");
            this.passwordField.setText("");
        }
    }
}
