package uebung06;

public class Filter2 {

	public void main() {
		int [][] bild = ZhawImgLib.readImage("src/uebung06/eiffel.jpg");
		ZhawImgLib.showImage("eiffel.jpg");

        int [][] resized = new int[bild.length/4][bild[0].length/4];

        int a = 0;
        int b = 0;

		for (int i=0; i < bild.length; i+=4) {

            for (int j=0; j < bild[i].length; j+=4) {
                resized[a][b] = bild[i][j];

                b++;
            }

            b = 0;
            a++;
        }

		// show result
		ZhawImgLib.writeImage("resized.jpg", resized);
		ZhawImgLib.showImage("resized.jpg");
	}
}
