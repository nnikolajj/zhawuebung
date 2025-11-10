package uebung08.GUI;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImageFlipTable {

	private class LabelWithImage {
		private JLabel label;
		private BufferedImage image;
		private boolean active;

		public LabelWithImage(JLabel label, BufferedImage image) {
			this.label = label;
			this.image = image;
			this.active = true;
		}

		public JLabel getLabel() {
			return label;
		}

		public BufferedImage getImage() {
			return image;
		}

		public boolean isActive() {
			return active;
		}
		
		public void setActive(boolean active) {
			this.active = active;
		}
	}

	private class NamedImagesWithSize {
		private int width;
		private int height;
		private HashMap<String, BufferedImage> namedBufferedImages;

		public NamedImagesWithSize(int width, int height, HashMap<String, BufferedImage> namedBufferedImages) {
			this.width = width;
			this.height = height;
			this.namedBufferedImages = namedBufferedImages;
		}

		public int getWidth() {
			return width;
		}

		public int getHeight() {
			return height;
		}

		public HashMap<String, BufferedImage> getNamedBufferedImages() {
			return namedBufferedImages;
		}
	}
	
	private class WidthHeightTuple
	{
		private int width;
		private int height;
		
		public WidthHeightTuple(int width, int height) {
			this.width = width;
			this.height = height;
		}
		
		public int getWidth() {
			return width;
		}
		
		public int getHeight() {
			return height;
		}
		
	}

	/// Holds references to labels by name so that they can be accessed later
	private HashMap<String, LabelWithImage> imageEntries = new HashMap<>();

	private int numItems;

	private JDialog dialog;

	/// The filename extension to construct an image file from a given name
	private static final String extension = ".png";

	/// the folder where the images are located
	private static final String dataFolder = "data";
	
	/// if a folder with the name customdata is present, the library will look for the image files in this place instead of the data folder.
	/// This allows using the jar file with other than the built in images 
	private static final String customDataFolder = "customdata";

	/// The delay between flipping names
	private static final int flipDelayMilliSeconds = 600;

	/**
	 * 
	 * @param names each entry must either be "" or there must be a corresponding
	 *              file with extension. e.g. "Alex" ->
	 *              "{dataFolder}/Alex{extension}" should exist
	 *              If {customDataFolder} exists, then {customDataFolder}/Alex{extension}" should exist
	 */
	public ImageFlipTable(List<String> names) {
		storeNumItems(names);
		checkFilesExist(names);
		NamedImagesWithSize iconsArrayWithSize = createImagesCalculateSize(names);
		createDialog(iconsArrayWithSize);
	}

	public ImageFlipTable() {
		this(getDefaultNames());
	}

	public void flip(String name) {
		flipImageInLabel(imageEntries.get(name));
	}
	
	public void flipNamesInList(String [] names) {
		flip(Arrays.asList(names), false);
	}

	public void flipNamesInList(List<String> names) {
		flip(names, false);
	}
	
	public void flipNamesNotInList(String [] names) {
		flip(Arrays.asList(names), true);
	}

	public void flipNamesNotInList(List<String> names) {
		flip(names, true);
	}

	public void closeDialog() {
		dialog.dispose();
	}

	public static List<String> getDefaultNames() {
		ArrayList<String> imageFilenames = new ArrayList<>();
		imageFilenames.add("Alex");
		imageFilenames.add("Alfred");
		imageFilenames.add("Anita");
		imageFilenames.add("Bernard");
		imageFilenames.add("Bill");
		imageFilenames.add("Charles");
		imageFilenames.add("David");
		imageFilenames.add("Frans");
		imageFilenames.add("Maria");
		imageFilenames.add("Robert");
		imageFilenames.add("Tom");
		return imageFilenames;
	}

	private NamedImagesWithSize createImagesCalculateSize(List<String> names) {
		int width = 0;
		int height = 0;
		HashMap<String, BufferedImage> namedBufferedImages = new HashMap<>();

		for (String name : names) {
			BufferedImage img = null;
			if (name != null) {
				img = readImg(name);
			}
			if (width == 0 && img != null) {
				width = img.getWidth();
				height = img.getHeight();
			}
			namedBufferedImages.put(name, img);
		}
		if (width == 0) {
			throw new IllegalArgumentException("Can't show image grid without a single image");
		}
		return new NamedImagesWithSize(width, height, namedBufferedImages);
	}
	
	private WidthHeightTuple calculateDialogWidthHeight(int imageWidth, int imageHeight) {
		int nCols =numItems;
		int nRows = 1;
		if (numItems > 6) {
			nCols = (int) Math.ceil(Math.sqrt(numItems));
			nRows = numItems / nCols + 1;
		}
		//System.out.println(numItems + " items " + nCols + " cols " + nRows + " rows ");
		int frame = Math.max(imageWidth, imageHeight) / 20;
		int dialogWidth = nCols * (imageWidth + frame) + frame;
		int dialogHeight = nRows * (imageHeight + frame) + frame + 32;
		return new WidthHeightTuple(dialogWidth, dialogHeight);
	}

	private void createDialog(NamedImagesWithSize iconsArrayWithSize) {
		WidthHeightTuple dialogWidthHeight = calculateDialogWidthHeight(iconsArrayWithSize.getWidth(), iconsArrayWithSize.getHeight());
		
		dialog = new JDialog();
		dialog.setTitle("ImageFlipTable");
		dialog.setSize(dialogWidthHeight.getWidth(), dialogWidthHeight.getHeight());
		JPanel panel = new JPanel(new FlowLayout());
		HashMap<String, BufferedImage> namedIcons = iconsArrayWithSize.getNamedBufferedImages();
		for (Map.Entry<String, BufferedImage> entry : namedIcons.entrySet()) {
			String name = entry.getKey();
			BufferedImage image = entry.getValue();
			ImageIcon imageIcon = new ImageIcon();
			if (image != null) {
				imageIcon = new ImageIcon(image);
			}
			JLabel label = new JLabel(imageIcon);
			panel.add(label);
			LabelWithImage iconEntry = new LabelWithImage(label, image);
			imageEntries.put(name, iconEntry);
		}

		dialog.getContentPane().add(panel);
		dialog.setVisible(true);
	}

	private boolean isResourceAvailable(String name) {
		// if file is available return that, otherwise, in the context of a jar,
		// where the image is not available as file, try to load the resource
		String filename = getFilename(name);
		File file = getFile(filename);
		if (file != null && !file.exists()) {
			URL url = getUrlForResourceName(name);
			boolean isUrlNotNull = (url != null);
			return isUrlNotNull;
		} else {
			return true;
		}
	}

	private File getFile(String name) {
		if (name == null) {
			return null;
		}
		try {
			return new File(name);
		} catch (Exception e) {
			System.out.println("Working Directory = " + System.getProperty("user.dir"));
			System.out.println("Trying to read file " + name);
			System.out.println("Error: " + e);
			return null;
		}
	}

	private void storeNumItems(List<String> names) {
		numItems = names.size();
		if (numItems == 0) {
			throw new IllegalArgumentException("Can't show " + this.getClass().getSimpleName() + " with 0 names.");
		}
	}

	private void checkFilesExist(List<String> names) {
		ArrayList<String> missingFiles = new ArrayList<>();
		for (String name : names) {
			if (!isResourceAvailable(name)) {
				String filename = getFilename(name);
				missingFiles.add(filename);
			}
		}

		if (!missingFiles.isEmpty()) {
			String namesText = String.join(", ", missingFiles);
			throw new IllegalArgumentException(missingFiles.size() + " files don't exist: " + namesText);
		}
	}

	private String getFilename(String name) {
		String actualDataFolder = dataFolder;
		Path path = Paths.get(customDataFolder);
		if (Files.isDirectory(path)) {
			actualDataFolder = customDataFolder;
		}
		String filename = null;
		if (name != null) {
			filename = actualDataFolder + '/' + name + extension;
		}
		return filename;
	}

	private URL getUrlForResourceName(String name) {
		// when loading a resource from the class file, / must be prepended
		name = '/' + dataFolder + '/' + name + extension;
		URL url = getClass().getResource(name);
		return url;
	}

	private BufferedImage readImg(String name) {
		String filename = getFilename(name);
		try {
			File file = getFile(filename);
			if (file != null && file.exists()) {
				return ImageIO.read(new File(filename));
			} else {
				URL url = getUrlForResourceName(name);
				return ImageIO.read(url);
			}
		} catch (Exception e) {
			System.out.println("Working Directory = " + System.getProperty("user.dir"));
			System.out.println("Trying to read file " + filename);
			System.out.println("Error: " + e);
			return null;
		}
	}

	private int[][] readImageData(BufferedImage img) {
		if (img == null) {
			return new int[0][0];
		}
		int width = img.getWidth();
		int height = img.getHeight();
		int[][] data = new int[height][width];

		for (int h = 0; h < height; h++) {
			for (int w = 0; w < width; w++) {
				data[h][w] = (img.getRGB(w, h) & 0xFFFFFF); // remove rest
			}
		}
		return data;
	}

	private int[][] calculateGrayPixelImage(int[][] data) {
		int[][] gray = new int[data.length][data[0].length];

		for (int h = 0; h < data.length; h++) {
			for (int w = 0; w < data[0].length; w++) {
				int r = (data[h][w] & 0xFF0000) >> 16;
				int g = (data[h][w] & 0xFF00) >> 8;
				int b = (data[h][w] & 0xFF);
				int gv = (r + g + b) / 3; // gray value
				gv *= 0.6;
				gray[h][w] = (gv << 16) | (gv << 8) | gv;
			}
		}
		return gray;
	}

	private BufferedImage getGrayVersion(BufferedImage image) {
		int[][] data = readImageData(image);
		data = calculateGrayPixelImage(data);
		BufferedImage grayImg = new BufferedImage(data[0].length, data.length,
				java.awt.image.BufferedImage.TYPE_INT_RGB);
		for (int h = 0; h < data.length; h++) {
			for (int w = 0; w < data[0].length; w++) {
				grayImg.setRGB(w, h, data[h][w]);
			}
		}
		return grayImg;
	}

	private boolean flipImageInLabel(LabelWithImage entry) {
		if (entry != null && entry.isActive()) {
			JLabel label = entry.getLabel();
			BufferedImage image = entry.getImage();
			if (image != null) {
				BufferedImage grayImg = getGrayVersion(image);
				ImageIcon grayIcon = new ImageIcon(grayImg);
				label.setIcon(grayIcon);
				entry.setActive(false);
				return true;
			}
		}
		return false;
	}

	private void flip(List<String> names, boolean flipCounterset) {
		Set<String> nameSet = new HashSet<>(names);

		for (String name : imageEntries.keySet()) {
			// XOR (^) to decide if name should be flipped
			if (nameSet.contains(name) ^ flipCounterset) {
				boolean didFlip = flipImageInLabel(imageEntries.get(name));
				try {
					if (didFlip) {
						Thread.sleep(flipDelayMilliSeconds);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
