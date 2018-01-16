package com.home.assignment.data;

/**
 * Customer data object.
 * 
 * @author      Jainendra Kumar
 * @version     1.0.1
 * @since       1.0
 */
public class Customer {

	private long transactionReference;
	private String accountNumber;
	private String description;
	private double startBalance;
	private double mutation;
	private double endBalance;

	public Customer() {
		super();
	}

	public Customer(long transactionReference, String accountNumber, String description, double startBalance,
			double mutation, double endBalance) {
		super();
		this.transactionReference = transactionReference;
		this.accountNumber = accountNumber;
		this.description = description;
		this.startBalance = startBalance;
		this.mutation = mutation;
		this.endBalance = endBalance;
	}

	/**
	 * @return the transactionReference
	 */
	public long getTransactionReference() {
		return transactionReference;
	}

	/**
	 * @param transactionReference the transactionReference to set
	 */
	public void setTransactionReference(long transactionReference) {
		this.transactionReference = transactionReference;
	}

	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the startBalance
	 */
	public double getStartBalance() {
		return startBalance;
	}

	/**
	 * @param startBalance the startBalance to set
	 */
	public void setStartBalance(double startBalance) {
		this.startBalance = startBalance;
	}

	/**
	 * @return the mutation
	 */
	public double getMutation() {
		return mutation;
	}

	/**
	 * @param mutation the mutation to set
	 */
	public void setMutation(double mutation) {
		this.mutation = mutation;
	}

	/**
	 * @return the endBalance
	 */
	public double getEndBalance() {
		return endBalance;
	}

	/**
	 * @param endBalance the endBalance to set
	 */
	public void setEndBalance(double endBalance) {
		this.endBalance = endBalance;
	}

	@Override
	public String toString() {
		return "Transaction Reference=[" + transactionReference + "] & Description=[" + description + "]";
	}

	@Override
	public boolean equals(Object object) {
		if(object instanceof Customer)
		{
			Customer c = (Customer)object;
			return (getTransactionReference() == c.getTransactionReference()); //& (getEndBalance() == c.getEndBalance());
		}
		return false;
	}

}
