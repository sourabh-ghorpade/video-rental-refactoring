import java.util.List;

public interface InvoiceView {
  String generate(String customerName,
                  List<ItemCharges> itemCharges,
                  double totalInvoiceAmount,
                  int frequentRenterPoints);

  static InvoiceView getInvoiceViewFor(String invoiceType) {
    return switch (invoiceType) {
      case "text" -> new TextInvoice();
      case "html" -> new HTMLInvoiceView();
      default -> throw new IllegalStateException("Unexpected value: " + invoiceType);
    };
  }
}
