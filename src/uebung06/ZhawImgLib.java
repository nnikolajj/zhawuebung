package uebung06;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * 
 * ZhawImgLib is a simple library for image reading and writing. 
 * This library is meant for educational purpose only.
 *
 */
public class ZhawImgLib {

	//private constructor to hide the public one.
	private ZhawImgLib() {}

	/**
	 * Read a filename returns a two dimensional integer array
	 * @param filename path to the image to read.
	 * @return an two dimensional integer array. Each position corresponds to a pixel in the image. 
	 * The values of each cell are integer pixel in the default RGB color model and default sRGB colorspace.
	 */
	public static int[][] readImage(String filename) {
		BufferedImage img = readImg(filename);
		if (img == null) {
			return new int[0][0];
		}
		int width = img.getWidth();
		int height = img.getHeight();
		int[][] data = new int[height][width];

		for (int h = 0; h < height; h++) {
			for (int w = 0; w < width; w++) {
				data[h][w] = (img.getRGB(w, h) & 0xFFFFFF);  // remove rest
			}
		}
		return data;
	}

	/**
	 * Writes an two dimensional integer array as an image. The values of each cell represents a color in style of
	 * @see java.awt.image.BufferedImage.TYPE_INT_ARGB. 
	 * @param filename
	 * @param data
	 * @return
	 */
	public static boolean writeImage(String filename, int[][] data) {
		BufferedImage img = new BufferedImage(data[0].length, data.length, java.awt.image.BufferedImage.TYPE_INT_RGB);
		for (int h = 0; h < data.length; h++) {
			for (int w = 0; w < data[0].length; w++) {
				img.setRGB(w, h, data[h][w]);
			}
		}
		try {
			ImageIO.write(img, "jpg", new File(filename));
		} catch (Exception e) {
			System.out.println("Error: " + e);
			return false;
		}
		return true;
	}

	/**
	 * Display @code filename as an image
	 * @param filename path to the file to display.
	 */
	public static void showImage(String filename) {
		BufferedImage img = readImg(filename);
		if (img == null) {
			return;
		}
		ImageIcon icon = new ImageIcon(img);
		JLabel label = new JLabel(icon);
		Object[] inputFields = { label };
		JOptionPane.showOptionDialog(null, inputFields, "Image", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
				null, null, null);
	}
	
	/**
	 * Returns the RGB value representing the color in the default sRGB
     * {@link ColorModel}.
     * (Bits 24-31 are alpha, 16-23 are red, 8-15 are green, 0-7 are
     * blue).
     * 
     * To get the right color see {@link https://www.tydac.ch/color/}. 
     * Will use alpha value of 255.
     * 
     * @throws IllegalArgumentException if {@code red}, {@code green},
     *        {@code blue} are outside of the range
     *        0 to 255, inclusive
	 * @param red
	 * @param green
	 * @param blue
	 * @return the RGB value of the color in the default sRGB
     *         {@code ColorModel}.
	 */
	public static int rgbToArgb(int red, int green, int blue) {
		return new Color(red, green, blue, 255).getRGB();
	}

	private static BufferedImage readImg(String filename) {
		try {
			return ImageIO.read(new File(filename));
		} catch (Exception e) {
			System.out.println("Working Directory = " + System.getProperty("user.dir"));
			System.out.println("Trying to read file " + filename);
			System.out.println("Error: " + e);
			return null;
		}
	}
}
