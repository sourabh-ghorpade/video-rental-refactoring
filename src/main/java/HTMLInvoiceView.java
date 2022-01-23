public class HTMLInvoiceView implements InvoiceView {
  public String footer(double totalInvoiceAmount, int frequentRenterPoints) {
    return "<p>Amount owed is <em>" + totalInvoiceAmount + "</em></p>" +
        "<p>You earned <em>" + frequentRenterPoints + "</em> frequent renter points</p>";
  }

  // Requirement is that HTML Invoice doesnt require the full itemised bill.
  @Override
  public String lineItems(Movie movie, double rentalChargeForThisRental) {
    return "";
  }

  public String header(String customerName) {
    return "<h1>Rental Record for <em>" + customerName + "</em></h1>";
  }
}
