package gp2.network;

import gp2.network.backend.ButtonCreator;
import gp2.network.backend.LabelCreator;
import gp2.network.backendServer.FilesWriting;
import gp2.network.mainWindow.MainButton;
import gp2.network.mainWindow.MainFrame;
import gp2.network.mainWindow.MainLabel;

public class Main {
    public static void main(String[] args) {
        FilesWriting.updateAppInfo();
        MainButton[][] buttons = ButtonCreator.createButtons();
        MainLabel[] labels = LabelCreator.createLabels(buttons);
        MainFrame frame = new MainFrame(labels);
        frame.makeVisible();
    }
}