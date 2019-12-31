package person.mochi.tool.graphic;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import person.mochi.tool.graphic.screengrabber.foundation.Anchor;

public class ScreenGrabber {

    private Robot robot;

    public ScreenGrabber() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    /**
     * the function to grabbe pic.
     *
     * @param anchor the start point of the pic you wanna grabbe.
     * @param width  the width of the pic.
     * @param height the height of the pic.
     */
    public BufferedImage grabbe(Anchor anchor, int width, int height) {
        Dimension temp = Toolkit.getDefaultToolkit().getScreenSize();
        int fullWidth = (int) temp.getWidth();
        int fullHeight = (int) temp.getHeight();
        anchor.initialize(fullWidth, fullHeight, width, height);
        Rectangle screenRectangle = new Rectangle();
        screenRectangle.setBounds(anchor.getX(), anchor.getY(), width, height);
        return robot.createScreenCapture(screenRectangle);
    }

    public static void main(String[] args) throws IOException {
        // 保存路径
        File screenFile = new File("C:\\");
        if (!screenFile.exists()) {
            screenFile.mkdir();
        }
        while (true) {
            ScreenGrabber sg = new ScreenGrabber();
            BufferedImage image = sg.grabbe(Anchor.CENTER, 100, 100);
            File f = new File(screenFile, System.currentTimeMillis() + ".png");
            ImageIO.write(image, "png", f);
        }
//		File f = new File(screenFile, "111.png");
//		ImageIO.write(image, "png", f);
//		System.out.println(image.getData().getDataBuffer().getDataType());
//		switch (image.getData().getDataBuffer().getDataType()) {
//		case DataBuffer.TYPE_BYTE:
//			System.out.println("byte");
//			break;
//		case DataBuffer.TYPE_DOUBLE:
//			System.out.println("double");
//			break;
//		case DataBuffer.TYPE_FLOAT:
//			System.out.println("float");
//			break;
//		case DataBuffer.TYPE_INT:
//			System.out.println("int");
//			break;
//		case DataBuffer.TYPE_SHORT:
//			System.out.println("short");
//			break;
//		case DataBuffer.TYPE_UNDEFINED:
//			System.out.println("undefined");
//			break;
//		case DataBuffer.TYPE_USHORT:
//			System.out.println("ushort");
//			break;
//		}
//		// 自动打开
//		if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN))
//			Desktop.getDesktop().open(f);
    }

}
