package coursera.xujinqi.cousera1.honor;

import java.io.File;

import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;

public class GreyScaleConverter {
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

    public void testGray() {
        ImageResource ir = new ImageResource();
        ImageResource gray = makeGrey(ir);
        gray.draw();
    }

    public void selectAndConvert() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource inFile = new ImageResource(f);
            ImageResource gray = makeGrey(inFile);
            gray.draw();
        }
    }

    public static void main(String[] args) {
        new GreyScaleConverter().selectAndConvert();
    }

}
