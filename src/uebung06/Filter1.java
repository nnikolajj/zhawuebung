package uebung06;

public class Filter1 {

	public void main() {
		int [][] bild = ZhawImgLib.readImage("src/uebung06/eiffel.jpg");
		ZhawImgLib.showImage("eiffel.jpg");

		// create array for new image
		int [][] noGreen = new int[bild.length][bild[0].length];

		// remove green
		for (int h = 0; h < bild.length; h++) {
			for (int w = 0; w < bild[0].length; w++) {
				noGreen[h][w] = bild[h][w] & 0xFF00FF;
			}
		}

		// show result
		ZhawImgLib.writeImage("noGreen.jpg", noGreen);
		ZhawImgLib.showImage("noGreen.jpg");
	}
}
