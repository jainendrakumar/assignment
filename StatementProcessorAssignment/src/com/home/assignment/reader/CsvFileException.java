package com.home.assignment.reader;

/**
 * CSV File Exception class.
 * 
 * @author      Jainendra Kumar
 * @version     1.0.1
 * @since       1.0
 */
public class CsvFileException extends Exception {

	/**
	 * Exception handling for CSV file.
	 */
	private static final long serialVersionUID = 3881004401222945223L;

	/**
	 * Round of a double value to 2 place of precision.
	 *
	 * @param  value  a double value which which need to be rounded off to 2 digit of decimal value.
	 * @return      double value with 2 digit of precision.
	 */
	public CsvFileException(String msg) {
		super(msg);
	}

}
