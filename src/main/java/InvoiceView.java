public interface InvoiceView {
  String header(String customerName);

  String footer(double totalAmount, int frequentRenterPoints);

  String lineItems(Movie movie, double rentalChargeForThisRental);

  static InvoiceView getInvoiceViewFor(String invoiceType) {
    return switch (invoiceType) {
      case "text" -> new TextInvoice();
      case "html" -> new HTMLInvoiceView();
      default -> throw new IllegalStateException("Unexpected value: " + invoiceType);
    };
  }
}
