package com.AtomicGE.mathUtil;

/**
 * 
 * @author Justin Bonner
 * 
 * This class represents a Matrix such that 0,0 refers to
 * the element in the top left corner of the matrix.
 *
 */
public class Matrix {
	
	private double[][] matrix; //matrix[row][column]
	
	
	/**
	 * Creates a Matrix from the given array of arrays of doubles.
	 * @param matrix an array of arrays of doubles representing the Matrix
	 */
	public Matrix(double[][] matrix){
		this.matrix = matrix;
	}
	
	
	/**
	 * Creates an empty square Matrix of side lengths dimension
	 * @param dimension the dimensions of the matrix
	 */
	public Matrix(int dimension){
		this.matrix = new double[dimension][dimension];
		
	}
	
	
	/**
	 * Gets the row at the given index
	 * @param row the index of the row to get
	 * @return the row of the matrix represented as an array
	 */
	public double[] getRow(int row){
		double[] rowArray = new double[getColumns()];
		for(int i = 0; i < rowArray.length; i++){
			rowArray[i] = this.matrix[row][i];
		}
		return rowArray;
	}
	
	
	/**
	 * Gets the column at the given index.
	 * @param col the index of the column to get
	 * @return the column of the matrix represented as an array
	 */
	public double[] getColumn(int col){
		return this.matrix[col];
	}
	
	/**
	 * Gets the value of this matrix at the specified location.
	 * @param row the row of the location
	 * @param column the column of the location
	 * @return the value at the location
	 */
	public double getIndex(int row, int column){
		return matrix[row][column];
	}
	
	
	/**
	 * Sets the value of this matrix at the specified location to the given value.
	 * @param row the row of the location
	 * @param column the column of the location
	 * @param value the value to set the location equal to
	 */
	public void setIndex(int row, int column, int value){
		matrix[row][column] = value;
	}
	
	
	/**
	 * 
	 * @return the number of rows in this Matrix
	 */
	public int getRows(){
		return matrix.length;
	}
	
	
	/**
	 * 
	 * @return the number of columns in this Matrix
	 */
	public int getColumns(){
		return matrix[0].length;
	}
	
	
	/**
	 * 
	 * @return this Matrix represented as an array of arrays of doubles
	 */
	public double[][] getAsArrays(){
		return this.matrix;
	}
	
}
