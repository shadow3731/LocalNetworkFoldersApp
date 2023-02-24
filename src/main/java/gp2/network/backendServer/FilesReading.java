package gp2.network.backendServer;

import gp2.network.mainWindow.MainButton;

import java.io.*;
import java.util.ArrayList;

/**
 * Класс для чтения данных из файлов, расположенных на сервере.
 * @author Владислав Клименок
 */
public class FilesReading {
    public FilesReading() {}

    /**
     * Метод читает данные из файла, в которую занесена инфорация
     * о наименовании организации, которая использует данное приложение.
     * @return Возвращает название организации, если приложению
     * удалось прочесть файл, либо <b>null</b> в противном случае.
     * @see gp2.network.backend.FilesReading#readOrganizationLocal() 
     */
    public static String readOrganization() {
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader file = new BufferedReader(
                    new FileReader("\\\\server" + File.separator
                            + "Локальная папка" + File.separator
                            + "Local Network Folders App" + File.separator
                            + "Organization.dat"));

            String temp;
            while ((temp = file.readLine()) != null) {
                text.append(temp);
            }
            file.close();
        } catch (IOException e) {
            return null;
        }
        return text.toString();
    }

    /**
     * Метод читает данные из файлов, в которые занесена инфорация
     * о разделах и кнопках, которые используются в данном приложении.
     * @param flag указатель на файл, который нужно прочесть.
     * @return Возвращает информацию о разделах и кнопках, если приложению
     * удалось прочесть файл, либо <b>null</b> в противном случае.
     * @see FilesReading#readValues(char) 
     */
    public static String[] readValues(char flag) {
        String buttonsFileName = "Buttons.dat";
        String labelsFileName = "Labels.dat";
        ArrayList<String> valuesList = new ArrayList<>();

        try {
            BufferedReader file;
            if (flag == 'b') {
                file = new BufferedReader(
                        new FileReader("\\\\server" + File.separator
                                + "Локальная папка" + File.separator
                                + "Local Network Folders App" + File.separator
                                + buttonsFileName));
            } else {
                file = new BufferedReader(
                        new FileReader("\\\\server" + File.separator
                                + "Локальная папка" + File.separator
                                + "Local Network Folders App" + File.separator
                                + labelsFileName));
                }

            String temp;
            while ((temp = file.readLine()) != null) {
                valuesList.add(temp);
            }
            file.close();
        } catch (IOException e) {
            return null;
        }

        return valuesList.toArray(new String[0]);
    }


    /**
     * Метод читает данные из файла, в которые занесена инфорация
     * о кнопках, которые используются в данном приложении, и ищет
     * кнопку с определенным значением.
     * @param name название кнопки, которую нужно найти.
     * @return Возвращает информацию о кнопке, если приложению
     * удалось прочесть файл, либо <b>null</b> в противном случае.
     * @see gp2.network.backend.ValuesExtractor#setSecuredStatus(MainButton) 
     */
    public static String readValue(String name) {
        try {
            BufferedReader file = new BufferedReader(new FileReader("\\\\server"
                    + File.separator + "Локальная папка" + File.separator
                    + "Local Network Folders App" + File.separator
                    + "Buttons.dat"));

            String temp = "";
            boolean foundString = false;
            while (!foundString && (temp = file.readLine()) != null) {
                if (temp.contains(name)) foundString = true;
            }
            file.close();

            return temp;
        } catch (IOException e) {
            return null;
        }
    }
}
