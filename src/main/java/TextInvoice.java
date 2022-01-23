import java.util.List;

public class TextInvoice {
  private final String customer;
  private final List<ItemCharges> itemCharges;
  private final double totalInvoiceAmount;
  private final int frequentRenterPoints;

  public TextInvoice(String customerName, List<ItemCharges> itemCharges, double totalInvoiceAmount, int frequentRenterPoints) {
    this.customer = customerName;
    this.itemCharges = itemCharges;
    this.totalInvoiceAmount = totalInvoiceAmount;
    this.frequentRenterPoints = frequentRenterPoints;
  }

  public String generate() {
    StringBuilder invoiceStatement = new StringBuilder(invoiceHeader(customer));
    itemCharges.stream()
        .map(itemCharges -> generateInvoiceLineForRental(itemCharges.getMovie(), itemCharges.getRentalChargeForThisRental()))
        .forEach(invoiceStatement::append);
    addFooterLines(totalInvoiceAmount, frequentRenterPoints, invoiceStatement);
    return invoiceStatement.toString();
  }

  private String invoiceHeader(String name) {
    return "Rental Record for " + name + "\n";
  }

  private void addFooterLines(double totalAmount, int frequentRenterPoints, StringBuilder result) {
    result.append("You owed ").append(totalAmount).append("\n");
    result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
  }

  private String generateInvoiceLineForRental(Movie movie, double rentalChargeForThisRental) {
    return "\t" + movie.getTitle() + "\t" + rentalChargeForThisRental + "\n";
  }
}
