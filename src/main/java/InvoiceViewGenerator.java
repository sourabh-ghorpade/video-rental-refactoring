import java.util.List;

public class InvoiceViewGenerator {
  private final InvoiceView invoiceView;

  public InvoiceViewGenerator(InvoiceView invoiceView) {
    this.invoiceView = invoiceView;
  }

  public String generate(String customerName,
                         List<ItemCharges> itemCharges,
                         double totalInvoiceAmount,
                         int frequentRenterPoints) {

      return invoiceView.generate(customerName, itemCharges, totalInvoiceAmount, frequentRenterPoints);
    }
}
