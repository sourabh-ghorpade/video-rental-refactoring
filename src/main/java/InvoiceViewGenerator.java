import java.util.List;

public record InvoiceViewGenerator(InvoiceView view) {

  public String generate(String customerName,
                         List<ItemCharges> itemCharges,
                         double totalInvoiceAmount,
                         int frequentRenterPoints) {
    StringBuilder invoiceStatement = new StringBuilder(view.header(customerName));
    itemCharges.stream()
        .map(itemCharge -> view.generateInvoiceLineForRental(itemCharge.getMovie(), itemCharge.getRentalChargeForThisRental()))
        .forEach(invoiceStatement::append);

    view.addFooterLines(totalInvoiceAmount, frequentRenterPoints, invoiceStatement);
    return invoiceStatement.toString();
  }
}
