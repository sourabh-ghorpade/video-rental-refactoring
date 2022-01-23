import java.util.List;

public class TextInvoice implements InvoiceView {

  public String generate(String customerName, List<ItemCharges> itemCharges, double totalInvoiceAmount, int frequentRenterPoints) {
    StringBuilder invoiceStatement = new StringBuilder(header(customerName));
    itemCharges.stream()
        .map(itemCharge -> generateInvoiceLineForRental(itemCharge.getMovie(), itemCharge.getRentalChargeForThisRental()))
        .forEach(invoiceStatement::append);
    addFooterLines(totalInvoiceAmount, frequentRenterPoints, invoiceStatement);
    return invoiceStatement.toString();
  }

  public String header(String name) {
    return "Rental Record for " + name + "\n";
  }

  public void addFooterLines(double totalAmount, int frequentRenterPoints, StringBuilder result) {
    result.append("You owed ").append(totalAmount).append("\n");
    result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
  }

  public String generateInvoiceLineForRental(Movie movie, double rentalChargeForThisRental) {
    return "\t" + movie.getTitle() + "\t" + rentalChargeForThisRental + "\n";
  }
}
