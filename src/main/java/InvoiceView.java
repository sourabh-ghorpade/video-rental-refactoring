public interface InvoiceView {
  String header(String customerName);

  void addFooterLines(double totalAmount, int frequentRenterPoints, StringBuilder result);

  String generateInvoiceLineForRental(Movie movie, double rentalChargeForThisRental);

  static InvoiceView getInvoiceViewFor(String invoiceType) {
    return switch (invoiceType) {
      case "text" -> new TextInvoice();
      case "html" -> new HTMLInvoiceView();
      default -> throw new IllegalStateException("Unexpected value: " + invoiceType);
    };
  }
}
