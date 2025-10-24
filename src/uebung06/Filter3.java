package uebung06;

public class Filter3 {

	public void main() {
		int [][] bild = ZhawImgLib.readImage("src/uebung06/eiffel.jpg");
		ZhawImgLib.showImage("eiffel.jpg");
		
		// create array for new image
		int [][] mirrored = new int[bild.length][bild[0].length];
		

        for (int h = 0; h < bild.length; h++) {
            int [] array = new int [bild[0].length];
            int i = 0;

            for (int w = bild[0].length-1; w >= 0; w--) {

                array[i] = bild[h][w];
                i++;
            }

            mirrored[h] = array;

        }

		
		// show result
		ZhawImgLib.writeImage("mirrored.jpg", mirrored);
		ZhawImgLib.showImage("mirrored.jpg");
	}
}
