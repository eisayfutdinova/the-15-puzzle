package FileDialogPackage;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * FileDialig using for open and for tailor picture in needed size
 */

public class FileDialog extends JFileChooser {


    public FileDialog() {
        configureDialog();
    }

    private void configureDialog() {
        FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
        addChoosableFileFilter(fileFilter);
        setAcceptAllFileFilterUsed(false);
    }

    public File openDialog() {
        String frameTitle = "Select file";
        int dialogResult = showDialog(null, frameTitle);
        switch (dialogResult) {
            case JFileChooser.APPROVE_OPTION:
                return getSelectedFile();
            case JFileChooser.CANCEL_OPTION:
                return null;
            case JFileChooser.ERROR_OPTION:
                throw new ExceptionInInitializerError("File dialog error");
            default:
                return null;

        }

    }

    public BufferedImage[] setDialogResult(File dialogResult) {
        return FileProcess.chunkImage(Objects.requireNonNull(FileProcess.cutImage(dialogResult)));
    }


}
