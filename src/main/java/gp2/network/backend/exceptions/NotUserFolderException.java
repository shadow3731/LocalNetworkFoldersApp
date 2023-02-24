package gp2.network.backend.exceptions;

import gp2.network.mainWindow.SubDialog;

/**
 * Пользовательский класс-исключение, генерируемый в случае
 * неудачной попытки подключения к директории, указанной в
 * кнопке {@link gp2.network.mainWindow.MainButton}.
 * @author Владислав Клименок
 */
public class NotUserFolderException extends Exception {
    public NotUserFolderException() {}

    public NotUserFolderException(String message, Throwable cause) {}

    /**
     * Конструктор вызывает метод.
     * @param message Сообщение об ошибке
     */
    public NotUserFolderException(String message) {
        showError(message);
    }

    public NotUserFolderException(Throwable cause) {}

    /**
     * Метод вызывает конструктор {@link SubDialog#SubDialog(String, String)}
     * и метод этого класса {@link SubDialog#createDialog()}.
     * @param message Сообщение об ошибке
     */
    private void showError(String message) {
        new SubDialog("error", message).createDialog();
    }
}
