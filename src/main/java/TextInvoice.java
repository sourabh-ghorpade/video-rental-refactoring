import java.util.List;

public class TextInvoice implements InvoiceView {

  public String generate(String customerName, List<ItemCharges> itemCharges, double totalInvoiceAmount, int frequentRenterPoints) {
    StringBuilder invoiceStatement = new StringBuilder(header(customerName));
    itemCharges.stream()
        .map(itemCharge -> lineItems(itemCharge.getMovie(), itemCharge.getRentalChargeForThisRental()))
        .forEach(invoiceStatement::append);
    footer(totalInvoiceAmount, frequentRenterPoints);
    return invoiceStatement.toString();
  }

  public String header(String name) {
    return "Rental Record for " + name + "\n";
  }

  public String footer(double totalAmount, int frequentRenterPoints) {
    return "You owed " + totalAmount + "\n" +
        "You earned " + frequentRenterPoints + " frequent renter points";
  }

  public String lineItems(Movie movie, double rentalChargeForThisRental) {
    return "\t" + movie.getTitle() + "\t" + rentalChargeForThisRental + "\n";
  }
}
