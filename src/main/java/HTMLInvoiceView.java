
public class HTMLInvoiceView implements InvoiceView {
  public void addFooterLines(double totalInvoiceAmount, int frequentRenterPoints, StringBuilder invoiceStatement) {
    invoiceStatement.append("<p>Amount owed is <em>").append(totalInvoiceAmount).append("</em></p>");
    invoiceStatement.append("<p>You earned <em>").append(frequentRenterPoints).append("</em> frequent renter points</p>");
  }

  @Override
  public String generateInvoiceLineForRental(Movie movie, double rentalChargeForThisRental) {
    return "";
  }

  public String header(String customerName) {
    return "<h1>Rental Record for <em>" + customerName + "</em></h1>";
  }
}
