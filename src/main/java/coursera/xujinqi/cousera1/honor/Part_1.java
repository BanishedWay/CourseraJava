package coursera.xujinqi.cousera1.honor;

import java.io.File;

import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;

public class Part_1 {
    public ImageResource makeGrey(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());

        for (Pixel pixel : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());

            int average = (inPixel.getRed() + inPixel.getGreen() + inPixel.getBlue()) / 3;

            pixel.setRed(average);
            pixel.setGreen(average);
            pixel.setBlue(average);
        }
        return outImage;
    }

    public void doSave() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            String path = f.getAbsolutePath().substring(0,
                    f.getAbsolutePath().lastIndexOf("\\") + 1);
            ImageResource image = new ImageResource(f);
            ImageResource gray = makeGrey(image);
            String name = image.getFileName();
            String grayName = "gray-" + name;
            gray.setFileName(path + grayName);
            image.draw();

            gray.save();
        }
    }

    public ImageResource inverseImage(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());

        for (Pixel pixel : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());

            pixel.setRed(255 - inPixel.getRed());
            pixel.setBlue(255 - inPixel.getBlue());
            pixel.setGreen(255 - inPixel.getGreen());
        }
        return outImage;
    }

    public void selectAndInvert() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            String path = f.getAbsolutePath().substring(0,
                    f.getAbsolutePath().lastIndexOf("\\") + 1);
            ImageResource image = new ImageResource(f);
            ImageResource invertedImage = inverseImage(image);
            String name = image.getFileName();
            String inverseName = "inverted-" + name;
            invertedImage.setFileName(path + inverseName);
            image.draw();
            invertedImage.draw();

            invertedImage.save();
        }

    }

    public static void main(String[] args) {
        new Part_1().selectAndInvert();
    }
}
