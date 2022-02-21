package coursera.xujinqi.cousera1.honor;

import java.io.File;

import edu.duke.DirectoryResource;
import edu.duke.ImageResource;

public class ImageSaver {
    public void doSave() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource image = new ImageResource(f);
            String fName = image.getFileName();
            String newName = "copy-" + fName;
            image.setFileName(newName);

            image.draw();
            image.save();
        }
    }

    public static void main(String[] args) {
        new ImageSaver().doSave();
    }
}
