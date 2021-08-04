package JFileChooserCustom;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class ImageFilter extends FileFilter {
    @Override
    public boolean accept(File file) {
        if(file.isDirectory()) {
            return true;
        }
        String extension = Utils.getExtension(file);
        if(extension != null) {
            if(extension.equals(Utils.jpeg) || extension.equals(Utils.jpg) || extension.equals(Utils.png)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "Images";
    }
}
