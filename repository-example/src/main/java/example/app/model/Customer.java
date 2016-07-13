package example.app.model;

import org.springframework.data.gemfire.mapping.Region;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

/**
 * The Customer class is an abstract data type (ADT) that models a customer.
 *
 * @author John Blum
 * @see example.app.model.Person
 * @see org.springframework.data.gemfire.mapping.Region
 * @since 1.0.0
 */
@Region("Customers")
@SuppressWarnings("unused")
public class Customer extends Person {

	private String accountNumber;

	public static Customer newCustomer(String firstName, String lastName) {
		Assert.hasText(firstName, "firstName is required");
		Assert.hasText(lastName, "lastName is required");

		Customer customer = new Customer();

		customer.setFirstName(firstName);
		customer.setLastName(lastName);

		return customer;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj)) {
			return false;
		}

		if (!(obj instanceof Customer)) {
			return false;
		}

		Customer that = (Customer) obj;

		return ObjectUtils.nullSafeEquals(this.getAccountNumber(), that.getAccountNumber());
	}

	@Override
	public int hashCode() {
		int hashValue = super.hashCode();
		hashValue = 37 * hashValue + ObjectUtils.nullSafeHashCode(getAccountNumber());
		return hashValue;
	}

	@Override
	public String toString() {
		return String.format("{ @type = %1$s, name = %2$s, accountNumber = %3$s }",
			getClass().getName(), getName(), getAccountNumber());
	}

	public Customer with(String accountNumber) {
		setAccountNumber(accountNumber);
		return this;
	}
}