package gp2.network.backend;

import gp2.network.mainWindow.MainButton;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс для преобразования данных, полученных из файлов приложения,
 * в данные, которые будут обрабатываться внутри кода приложения.
 * @author Владислав Клименок
 */
public class ValuesExtractor {
    public ValuesExtractor() {}

    /**
     * Метод получает данные из {@link FilesReading#readValuesLocal(char)}
     * и преобразует их в массивы данных, которые требуются для создания кнопок
     * типа {@link MainButton} и разделов типа {@link gp2.network.mainWindow.MainLabel}.
     * @param flag указатель на файл, который нужно прочесть.
     * @return Возвращает массив с преобразованными данными.
     */
    public static String[][] classifyValues(char flag) {
        Optional<String[]> valuesRaw = Optional.ofNullable(FilesReading.readValuesLocal(flag));
        if (valuesRaw.isPresent()) {
            String[][] valuesStr = new String[valuesRaw.get().length][6];
            Pattern pattern1 = Pattern.compile("[^A-Za-z,=\\n]+['A-Za-zА-Я-а-я0-9-\\\\\\p{Blank}.()]+");
            Pattern pattern2 = Pattern.compile("[^']+");

            for (int i = 0; i < valuesRaw.get().length; i++) {
                Matcher matcher1 = pattern1.matcher(valuesRaw.get()[i]);
                int j = 0;
                while (matcher1.find()) {
                    String temp = matcher1.group();
                    Matcher matcher2 = pattern2.matcher(temp);
                    while (matcher2.find()) {
                        valuesStr[i][j] = matcher2.group();
                    }
                    j++;
                }
            }

            return valuesStr;
        }
        return null;
    }

    /**
     * Метод проверяет установлен ли пароль на данную кнопку путем считывания
     * информации из {@link gp2.network.backendServer.FilesReading#readValue(String)}
     * @param button Кнопка, которую нужно проверить
     */
    public static void setSecuredStatus(MainButton button) {
        Optional<String> buttonRaw = Optional.ofNullable(gp2.network.backendServer.FilesReading.readValue(button.getText()));
        if (buttonRaw.isPresent()) {
            Pattern pattern1 = Pattern.compile("isSecured='[a-z]+'");
            String temp = "";

            Matcher matcher1 = pattern1.matcher(buttonRaw.get());
            while (matcher1.find()) temp = matcher1.group();
            button.setSecured(temp.contains("true"));
        }

    }
}
