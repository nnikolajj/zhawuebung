package uebung06;

public class Filter5 {

	public void main() {
		int [][] bild = ZhawImgLib.readImage("src/uebung06/eiffel.jpg");
		ZhawImgLib.showImage("eiffel.jpg");
		
		// create array for new image
		int [][] gray = new int[bild.length][bild[0].length];
		
		for (int i = 0; i < bild.length; i++) {
            for (int j = 0; j < bild[0].length; j++) {

                int pixel = bild[i][j];

                int red = (pixel >> 16) & 0xFF;
                int green = (pixel >> 8) & 0xFF;
                int blue = (pixel) & 0xFF;

                int durchschnittProFarbe = (red + green + blue) / 3;

                pixel = (durchschnittProFarbe << 16) | (durchschnittProFarbe << 8) | durchschnittProFarbe;

                gray[i][j] = pixel;



            }
        }
		
		
		// show result
		ZhawImgLib.writeImage("gray.jpg", gray);
		ZhawImgLib.showImage("gray.jpg");
	}
}
