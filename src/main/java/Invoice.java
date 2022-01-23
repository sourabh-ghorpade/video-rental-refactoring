import java.util.List;

public record Invoice(InvoiceView view) {

  public String generate(String customerName,
                         List<ItemCharges> itemCharges,
                         double totalInvoiceAmount,
                         int frequentRenterPoints) {
    StringBuilder invoiceStatement = new StringBuilder(view.header(customerName));
    itemCharges.stream()
        .map(itemCharge -> view.lineItems(itemCharge.getMovie(), itemCharge.getRentalChargeForThisRental()))
        .forEach(invoiceStatement::append);
    invoiceStatement.append(view.footer(totalInvoiceAmount, frequentRenterPoints));

    return invoiceStatement.toString();
  }
}
