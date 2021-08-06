// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities;

import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.InputStream;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.awt.Image;

public class ImageUtils
{
    public static final int NO_IMAGE_FOUND_WIDTH = 20;
    public static final int NO_IMAGE_FOUND_HEIGHT = 20;
    private static Image noImageFoundImage;
    private static boolean creatingImage;
    private static GreyOutImageFilter imgf;
    
    public static Image getImageFromJar(final String name, final Object o) {
        final InputStream resourceAsStream = o.getClass().getResourceAsStream(name);
        if (resourceAsStream == null) {
            return getNoImageFoundImage();
        }
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            int read;
            while ((read = resourceAsStream.read()) >= 0) {
                byteArrayOutputStream.write(read);
            }
        }
        catch (IOException ex) {
            return getNoImageFoundImage();
        }
        return Toolkit.getDefaultToolkit().createImage(byteArrayOutputStream.toByteArray());
    }
    
    public static Image getNoImageFoundImage() {
        if (ImageUtils.noImageFoundImage == null) {
            ImageUtils.noImageFoundImage = Toolkit.getDefaultToolkit().createImage("GIF87a\n\u0000\n\u0000\ufffd\u0000\u0000\u0000\u0000\u0000\ufffd\ufffd\ufffd,\u0000\u0000\u0000\u0000\n\u0000\n\u0000\u0000\u0002\u0016\ufffd\ufffd\u0016\ufffd\u0017\ufffd\ufffd\ufffd\ufffd\ufffdug\ufffd\ufffdb\ufffdIN4%I\u0001\u0000;".getBytes());
        }
        return ImageUtils.noImageFoundImage;
    }
    
    private static GreyOutImageFilter getImageFilter() {
        if (ImageUtils.imgf == null) {
            ImageUtils.imgf = new GreyOutImageFilter();
        }
        return ImageUtils.imgf;
    }
    
    public static Image greyOutImage(final Image image) {
        return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), getImageFilter()));
    }
}
