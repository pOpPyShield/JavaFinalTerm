package JFileChooserCustom;

import javax.swing.*;
import javax.swing.filechooser.FileView;
import java.io.File;

public class ImageFileView extends FileView {
    ImageIcon jpgIcon = Utils.createImageIcon("/ImageIConFileChooser/jpg_icon.png");
    ImageIcon jpegIcon = Utils.createImageIcon("/ImageIConFileChooser/jpeg_icon.png");
    ImageIcon pngIcon = Utils.createImageIcon("/ImageIConFileChooser/png_icon.png");

    public String getName(File f) {
        return null; //let the L&F FileView figure this out
    }

    public String getDescription(File f) {
        return null; //let the L&F FileView figure this out
    }

    public Boolean isTraversable(File f) {
        return null; //let the L&F FileView figure this out
    }
    public String getTypeDescription(File f) {
        String extension = Utils.getExtension(f);
        String type = null;

        if (extension != null) {
            if (extension.equals(Utils.jpeg)) {
                type = "JPEG Image";
            } else if (extension.equals(Utils.png)){
                type = "PNG Image";
            } else if(extension.equals(Utils.jpg)) {
                type = "JPG Image";
            }
        }
        return type;
    }

    public Icon getIcon(File f) {
        String extension = Utils.getExtension(f);
        Icon icon = null;
        if(extension != null) {
            if(extension.equals(Utils.jpeg)) {
                icon = jpegIcon;
            } else if (extension.equals(Utils.jpg)) {
                icon = jpgIcon;
            } else if(extension.equals(Utils.png)) {
                icon = pngIcon;
            }
        }
        return icon;
    }
}
