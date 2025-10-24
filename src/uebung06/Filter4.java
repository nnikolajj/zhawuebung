package uebung06;

public class Filter4 {

	public void main() {
		int [][] bild = ZhawImgLib.readImage("src/uebung06/eiffel.jpg");
		ZhawImgLib.showImage("eiffel.jpg");

        int verringerung = 0xE0;
		
		// create array for new image
		int [][] lessColors = new int[bild.length][bild[0].length];

        for (int i = 0; i < bild.length; i++) {
            for (int j = 0; j < bild[0].length; j++) {

            int pixel = bild[i][j];

            int red = pixel >> 16 & 0xFF;
            int green = pixel >> 8 & 0xFF;
            int blue = pixel & 0xFF;

            red   = red & verringerung;
            green = green & verringerung;
            blue  = blue & verringerung;

            pixel = (red << 16) | (green << 8) | blue;

            lessColors[i][j] = pixel;

            }

        }
		
		// show result
		ZhawImgLib.writeImage("lessColors.jpg", lessColors);
		ZhawImgLib.showImage("lessColors.jpg");
	}
}
