package com.AtomicGE.mathUtil;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

public class MatrixMath {
	
	
	
	/**
	 * Calculates matrix a multiplied by matrix b.
	 * Returns null if the matrices cannot be multiplied to do dimensional differences.
	 * @param a the first matrix
	 * @param b the second matrix
	 * @return a new Matrix object which is the result of the multiplication.
	 */
	public static Matrix multiply(Matrix a, Matrix b){
		if(a.getColumns() != b.getRows()) return null; //sanity check
		double[][] result = new double[a.getRows()][b.getColumns()];//rows columns
		for(int col = 0; col < result.length; col++){
			for(int row = 0; row < result[0].length; row++){
				result[row][col] = multiplyArray(a.getRow(row),b.getColumn(col));
			}
		}
		return new Matrix(result);
	}
	
	
	/**
	 * Multiplies two arrays together as a dot product.
	 * @param a first array
	 * @param b second array
	 * @return their dot product
	 */
	private static double multiplyArray(double[] a, double[] b){
		double result = 0;
		for(int i = 0; i < a.length; i++){
			result += a[i] * b[i];
		}
		return result;
	}
	
	
	/**
	 * Creates an openGL compatible FloatBuffer to represent a given Matrix.
	 * Matrix should have same number of rows and columns.
	 * @param matrix the matrix to create a FloatBuffer of.
	 * @return a FloatBuffer comprised of the elements in the matrix ordered row by row from top to bottom
	 */
	public static FloatBuffer getMatrixAsBuffer(Matrix matrix){
		if(matrix.getRows() != matrix.getColumns()) System.out.println("WARNING: MatrixMath.java -Creating improper Buffer");
		FloatBuffer buffer = BufferUtils.createFloatBuffer(matrix.getRows() * matrix.getColumns());
		for(int col = 0; col < matrix.getColumns(); col++){
			for(int row = 0; row < matrix.getRows(); row++){
				buffer.put((float)matrix.getIndex(row, col));
			}
		}
		buffer.flip();
		return buffer;
	}
	
	
	
}
