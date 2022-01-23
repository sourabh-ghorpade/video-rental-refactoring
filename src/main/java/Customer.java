import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Customer {
  private final String _name;
  private final List<Rental> _rentals = new ArrayList<>();

  public Customer(String name) {
    this._name = name;
  }

  public void addRental(Rental rental) {
    _rentals.add(rental);
  }

  public String getName() {
    return _name;
  }

  public String statement() {
    double totalInvoiceAmount = 0;
    int frequentRenterPoints = 0;
    Iterator<Rental> rentals = _rentals.stream().iterator();
    StringBuilder invoiceStatement = new StringBuilder(invoiceHeader());

    while (rentals.hasNext()) {
      Rental rental = rentals.next();
      double rentalChargeForThisRental = rental.charge();
      frequentRenterPoints += rental.getFrequentRenterPoints();
      invoiceStatement.append(generateInvoiceLineForRental(rental, rentalChargeForThisRental));
      totalInvoiceAmount += rentalChargeForThisRental;
    }

    addFooterLines(totalInvoiceAmount, frequentRenterPoints, invoiceStatement);
    return invoiceStatement.toString();
  }

  private String invoiceHeader() {
    return "Rental Record for " + getName() + "\n";
  }

  private void addFooterLines(double totalAmount, int frequentRenterPoints, StringBuilder result) {
    result.append("You owed ").append(totalAmount).append("\n");
    result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
  }

  private String generateInvoiceLineForRental(Rental rental, double rentalChargeForThisRental) {
    return "\t" + rental.getMovie().getTitle() + "\t" + rentalChargeForThisRental + "\n";
  }
}
