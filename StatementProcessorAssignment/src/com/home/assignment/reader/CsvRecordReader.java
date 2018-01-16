package com.home.assignment.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.home.assignment.data.Customer;

/**
 * Utility class to read a CSV file and populate customer information.
 * 
 * @author      Jainendra Kumar
 * @version     1.0.1
 * @since       1.0
 */
public class CsvRecordReader {

	/**
	 * Reads the CSV file and returns customer record information.
	 *
	 * @param  CSV file name.
	 * @return      List of Customers.
	 */
	public static List<Customer> readCustomerRecordsFromCSVFile(String csvFile) throws CsvFileException {
		List<Customer> customers = new ArrayList<Customer>();

		try {
			File file = new File(csvFile);
			File file1 = new File(csvFile);
			FileReader fileReader1 = new FileReader(file);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferReader1 = new BufferedReader(fileReader1);
			BufferedReader bufferReader = new BufferedReader(fileReader);

			String line = "";

//			while((line = bufferReader.readLine()) != null )
			while ((line = bufferReader.readLine()) != null) {

				if(line.startsWith("Reference")) {
					continue;
				}

				String[] customerAtrributes = line.split(",");
				Customer book = newCustomer(customerAtrributes);

				customers.add(book);				
			}
			bufferReader.close();

		} catch (IOException ex) {
			throw new CsvFileException("File does not contains data in correcct format.");
		}

		if (customers.size() == 0) {
			throw new CsvFileException("File does not contains correct Customer data.");
		}

		return customers;   	
	}

	/**
	 * Populate customer information.
	 *
	 * @param  list of attributes.
	 * @return      Customer inforamtion.
	 */
	private static Customer newCustomer(String[] metadata) {

		long transactionReference = Long.parseLong(metadata[0]);
		String accountNumber = metadata[1];
		String description = metadata[2];
		double startBalance = Double.parseDouble(metadata[3]);
		double mutation = Double.parseDouble(metadata[4]);;
		double endBalance = Double.parseDouble(metadata[5]);;

		return new Customer(transactionReference, accountNumber, description, startBalance, mutation, endBalance);
	}

}


