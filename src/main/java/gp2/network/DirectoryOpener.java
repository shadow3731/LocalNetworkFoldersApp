package gp2.network;

import gp2.network.backend.ValuesExtractor;
import gp2.network.backend.exceptions.NotUserFolderException;
import gp2.network.mainWindow.MainButton;
import gp2.network.mainWindow.SubDialog;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Класс открытия директории, заданный в кнопке {@link MainButton}.
 * @author Владислав Клименок
 */
public class DirectoryOpener {
    public DirectoryOpener() {}

    /**
     * Метод открывает директорию кнопки, предварительно проверяя
     * наличие пароля на кнопке. Если к директории не удалось подключиться,
     * то создает диалоговое окно {@link SubDialog} с сообщением об ошибке.
     * @param button Кнопка типа {@link MainButton}
     */
    public static void openDirectory(MainButton button) {
        final Path PATH = Paths.get(button.getPath());
        ValuesExtractor.setSecuredStatus(button);

        if (button.isSecured()) {
            SubDialog dialog = new SubDialog("password", PATH);
            dialog.createDialog();
        } else {
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
        }
    }
}
