package gp2.network.backend.exceptions;

import gp2.network.mainWindow.SubDialog;

/**
 * Пользовательский класс-исключение, генерируемый в случае
 * неудачной попытки подключения к файлам приложения.
 * @author Владислав Клименок
 */
public class NotRootFileException extends Exception {
    public NotRootFileException() {}

    public NotRootFileException(String message, Throwable cause) {}

    /**
     * Конструктор вызывает метод.
     * @param message Сообщение об ошибке
     */
    public NotRootFileException(String message) {
        showError(message);
    }

    public NotRootFileException(Throwable cause) {}

    /**
     * Метод вызывает конструктор {@link SubDialog#SubDialog(String, String)}
     * и метод этого класса {@link SubDialog#createDialog()}.
     * @param message Сообщение об ошибке
     */
    private void showError(String message) {
        new SubDialog("error", message).createDialog();
    }
}
