package gp2.network.backendServer;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

/**
 * Класс для перезаписи данных, взятых из файлов сервера, на
 * персональный компьютер.
 * @author Владислав Клименок
 */
public class FilesWriting {
    public FilesWriting() {}

    /**
     * Метод берет данные из {@link FilesReading#readOrganization()} 
     * и {@link FilesReading#readValues(char)}, и в зависимости
     * от полученных данных перезаписывает содержимое определенного 
     * файла на персональном компьютере. Если Метод получает <b>null</b>, 
     * то перезапись не происходит.
     * @see FilesReading#readValues(char) 
     */
    public static void updateAppInfo() {
        Optional<String> organization = Optional.ofNullable(FilesReading.readOrganization());
        Optional<String[]> buttons = Optional.ofNullable(FilesReading.readValues('b'));
        Optional<String[]> labels = Optional.ofNullable(FilesReading.readValues('l'));

        checkFolder();

        organization.ifPresent(o -> {
            try {
                PrintWriter file = new PrintWriter(
                        new FileWriter("C:" + File.separator
                                + "Program Files" + File.separator
                                + "Local Network Folders App" + File.separator
                                + "Organization.dat", false));
                file.write(o);
                file.flush();
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        buttons.ifPresent(b -> {
            try {
                PrintWriter file = new PrintWriter(
                        new FileWriter("C:" + File.separator
                                + "Local Network Folders App" + File.separator
                                + "Buttons.dat", false));
                for (String button : b) {
                    file.write(button + "\n");
                }
                file.flush();
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        labels.ifPresent(l -> {
            try {
                PrintWriter file = new PrintWriter(
                        new FileWriter("C:" + File.separator
                                + "Local Network Folders App" + File.separator
                                + "Labels.dat", false));
                for (String label : l) {
                    file.write(label + "\n");
                }
                file.flush();
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Метод для проверки наличия необходимой папки,
     * в которой содержатся файлы приложения. Если папка не
     * найдена, то создает ее.
     */
    private static void checkFolder() {
        Path rootFolder = FileSystems.getDefault().getPath("C:"
                + File.separator + "Local Network Folders App");

        if (Files.notExists(rootFolder) && !Files.isDirectory(rootFolder)) {
            try {
                Files.createDirectory(rootFolder);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
