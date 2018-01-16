package com.home.assignment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import com.home.assignment.data.Customer;
import com.home.assignment.reader.CsvFileException;
import com.home.assignment.reader.CsvRecordReader;

/**
 * Bank receives monthly deliveries of customer statement records in CSV format
 * and the records need to be validated.
 * 
 * @author      Jainendra Kumar
 * @version     1.0.1
 * @since       1.0
 */
public class StatementProcessor {

	/**
	 * Bank Statement verification is done in following main method.
	 * 1. Input comes from data/app.properties file. CSVFile parameter defines the input location.
	 * 	  and ReportOutput defines the report output file location.
	 * 2. Following validation is done.
	 * 	  a. all transaction references should be unique. if transaction are not unique these captured in duplicate record.
	 *    b. the end balance needs to be validated. If balance not validated, this is captured as failed record
	 * 3. Output is flat file with information on duplicate and failed records.
	 */
	public static void main(final String[] args) throws CsvFileException {
		
		String inputCsvFile = null;
		Properties properties = null;
		
		try {
			File file = new File("data/app.properties");
			FileInputStream fileInput = new FileInputStream(file);
			properties = new Properties();	
			properties.load(fileInput);
			fileInput.close();
			inputCsvFile = properties.getProperty("CSVFile");
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		
		final List<Customer> csvCustomersRecords = CsvRecordReader.readCustomerRecordsFromCSVFile(inputCsvFile);

		
		//Duplicate Transactions entry in the report.
		String strOutputFile = properties.getProperty("ReportOutput");
		csvCustomersRecords.stream()
				.collect(Collectors.groupingBy(Customer::getTransactionReference))
				.values().stream()
		        .filter(duplicateTransacationReference -> duplicateTransacationReference.size() > 1)
		        .forEach(duplicateTransacationReference -> {
					try {
						Files.write(Paths.get(strOutputFile ), (new String("Duplicate Transaction Reference : \n"+ duplicateTransacationReference+"\n")).getBytes());
					} catch (IOException exception) {
						exception.printStackTrace();
					}
				});

		
		//Wrong transactions.
		csvCustomersRecords.parallelStream()
		        .filter(customer -> !(round(customer.getEndBalance()) == round(customer.getMutation()+customer.getStartBalance())))
		        .forEach(printWrongRecords -> {
					try {
						Files.write(Paths.get(strOutputFile), (new String("\nWrong Transactions : \n"+ printWrongRecords+"\n")).getBytes(), StandardOpenOption.APPEND);
					} catch (IOException exception) {
						exception.printStackTrace();
					}
				});
		
	}

	/**
	 * Round of a double value to 2 place of precision.
	 *
	 * @param  value  a double value which which need to be rounded off to 2 digit of decimal value.
	 * @return      double value with 2 digit of precision.
	 */
	public static double round(final double value) {
		final int places = 2;

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

}