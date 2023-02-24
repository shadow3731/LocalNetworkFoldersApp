package gp2.network.backend;

import gp2.network.backend.exceptions.NotRootFileException;

import java.io.*;
import java.util.ArrayList;

/**
 * Класс для чтения данных из файлов, расположенных на пользовательском компьютере.
 * @author Владислав Клименок
 */
public class FilesReading {
    public FilesReading() {}

    /**
     * Метод читает данные из файла, в которую занесена инфорация
     * о наименовании организации, которая использует данное приложение.
     * @return Возвращает название организации, если приложению
     * удалось прочесть файл, либо <b>null</b> в противном случае.
     * @see gp2.network.backendServer.FilesReading#readOrganization()
     */
    public static String readOrganizationLocal() {
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader file = new BufferedReader(
                    new FileReader("C:" + File.separator
                            + "Local Network Folders App" + File.separator
                            + "Organization.dat"));

            String temp;
            while ((temp = file.readLine()) != null) {
                text.append(temp);
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return text.toString();
    }

    /**
     * Метод читает данные из файлов, в которые занесена инфорация
     * о разделах и кнопках, которые используются в данном приложении.
     * @param flag указатель на файл, который нужно прочесть.
     * @return Возвращает информацию о разделах и кнопках, если приложению
     * удалось прочесть файл, либо генерирует диалоговое окно {@link gp2.network.mainWindow.SubDialog}
     * с сообщением об ошибке в противном случае.
     * @see gp2.network.backend.FilesReading#readValuesLocal(char)
     */
    public static String[] readValuesLocal(char flag) {
        String buttonsFileName = "Buttons.dat";
        String LabelsFileName = "Labels.dat";
        ArrayList<String> valuesList = new ArrayList<>();

        try {
            BufferedReader file;
            if (flag == 'b') {
                file = new BufferedReader(
                        new FileReader("C:" + File.separator
                                + "Local Network Folders App" + File.separator
                                + buttonsFileName));
            } else {
                file = new BufferedReader(
                        new FileReader("C:" + File.separator
                                + "Local Network Folders App" + File.separator
                                + LabelsFileName));
            }

            String temp;
            while ((temp = file.readLine()) != null) {
                valuesList.add(temp);
            }
            file.close();
            
            return valuesList.toArray(new String[0]);
        } catch (IOException e) {
            try {
                throw new NotRootFileException("<html>Не найден корневой документ. "
                        + "Обратитесь за помощью к системному администратору.<br/>"
                        + "Причина: " + e.getMessage() + "</html>");
            } catch (NotRootFileException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return null;
        }
    }
}
