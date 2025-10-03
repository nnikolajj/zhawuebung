package uebung03;

import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ZhawCsvLib {

	//private constructor to hide the public one.
	private ZhawCsvLib() {}

	/**
	 * Read a csv file from the path @code filename and returns a double array.
	 * 
	 * The file must be ',' separated.
	 * 
	 * Example of file content:
	 * 
	 * <pre>
	 * 12.1,24.5,35.3
	 * </pre>
	 * 
	 * Would return and double array with three elements: 12.1, 24.5 and 35.3
	 * 
	 * @param filename path to the file
	 * @return an double array containing the numbers.
	 */
	static double[] readDoubleArray(String filename) {
		String content = readFile(filename);
		return Arrays.stream(content.split(",")).mapToDouble(Double::parseDouble).toArray();
	}

	/**
	 * Read a csv file from the path @code filename and returns a double two
	 * dimensional array.
	 * 
	 * The file must be ',' separated.
	 * 
	 * Example of file content:
	 * 
	 * <pre>
	 * 12.1,24.5,35.3
	 * 10,21.1,5.5
	 * 0,-3.41,-12
	 * </pre>
	 * 
	 * Would return and double two dimensional array with the following elements:
	 * 
	 * <pre>
	 * 12.1	24.5	35.3
	 * 10	21.1	5.5
	 * 0	-3.41	-12
	 * </pre>
	 * 
	 * @param filename path to the file
	 * @return an double two dimensional array containing the numbers.
	 */
	static double[][] readDoubleArray2D(String filename) {
		String content = readFile(filename);
		return Arrays.stream(content.split("\n"))
				.map(s -> Arrays.stream(s.split(",")).mapToDouble(Double::parseDouble).toArray())
				.toArray(double[][]::new);
	}

	/**
	 * Writes a double array to a @code filename
	 * 
	 * @code {@link FileNotFoundException} are ignored. Check the console for errors
	 * 
	 *       @param filename path to the file
	 * @param array with content to be written
	 * @return @code true if the file was written, otherwise @code false
	 */
	static boolean writeDoubleArray(String filename, double[] array) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < array.length - 1; i++) {
			sb.append(array[i]);
			sb.append(",");
		}
		sb.append(array[array.length - 1]);
		return writeFile(filename, sb.toString());
	}

	/**
	 * Writes a double two dimensional array to a @code filename
	 * 
	 * @code {@link FileNotFoundException} are ignored. Check the console for errors
	 * 
	 *       @param filename path to the file
	 * @param array with content to be written
	 * @return @code true if the file was written, otherwise @code false
	 */
	static boolean writeDoubleArray(String filename, double[][] array) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length - 1; j++) {
				sb.append(array[i][j]);
				sb.append(",");
			}
			sb.append(array[i][array[i].length - 1]);
			sb.append("\n");
		}
		return writeFile(filename, sb.toString());
	}

	/**
	 * Writes a string to a @code filename
	 * 
	 * @code {@link FileNotFoundException} are ignored. Check the console for errors
	 * 
	 *       @param filename path to the file
	 * @param content to be written
	 * @return @code true if the file was written, otherwise @code false
	 */
	static boolean writeFile(String filename, String content) {
		try (FileWriter writer = new FileWriter(filename)) {
			writer.write(content);
		} catch (Exception e) {
			System.out.println("Working Directory = " + System.getProperty("user.dir"));
			System.out.println("Trying to read file " + filename);
			System.out.println("Error: " + e);
			return false;
		}
		return true;
	}

	/**
	 * Read a file and returns its content.
	 * 
	 * @code {@link FileNotFoundException} are ignored. Check the console for errors
	 * 
	 *       @param filename path to the file
	 * @return @code true if the file was written, otherwise @code false
	 */
	static String readFile(String filename) {
		StringBuilder sb = new StringBuilder();
		try (
				FileReader reader = new FileReader("src/uebung03/" +filename, StandardCharsets.UTF_8);
				BufferedReader bReader = new BufferedReader(reader)
		) {

			String line;
			while ((line = bReader.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}
		} catch (Exception e) {
			System.out.println("Working Directory = " + System.getProperty("user.dir"));
			System.out.println("Trying to read file " + filename);
			System.out.println("Error: " + e);
			return "";
		}
		return sb.toString();
	}
}
