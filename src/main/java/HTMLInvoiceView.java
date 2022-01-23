import java.util.List;

public class HTMLInvoiceView implements InvoiceView {
  @Override
  public String generate(String customerName, List<ItemCharges> itemCharges, double totalInvoiceAmount, int frequentRenterPoints) {
    StringBuilder invoiceStatement = new StringBuilder(invoiceHeader(customerName));
    addFooterLines(totalInvoiceAmount, frequentRenterPoints, invoiceStatement);
    return invoiceStatement.toString();
  }

  public void addFooterLines(double totalInvoiceAmount, int frequentRenterPoints, StringBuilder invoiceStatement) {
    invoiceStatement.append("<p>Amount owed is <em>" + totalInvoiceAmount + "</em></p>");
    invoiceStatement.append("<p>You earned <em>" + frequentRenterPoints + "</em> frequent renter points</p>");
  }

  public String invoiceHeader(String customerName) {
    return "<h1>Rental Record for <em>" + customerName + "</em></h1>";
  }
}
