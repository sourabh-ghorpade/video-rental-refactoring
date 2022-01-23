import java.util.ArrayList;
import java.util.List;

public class Customer {
  private final String _name;
  private final List<Rental> _rentals = new ArrayList<>();

  public Customer(String name) {
    this._name = name;
  }

  public void addRental(Rental rental) {
    _rentals.add(rental);
  }

  public String statement() {
    return statement("text");
  }

  public String statement(String invoiceType) {
    return new InvoiceViewGenerator(InvoiceView.getInvoiceViewFor(invoiceType))
        .generate(getName(), generateItemisedCharges(), totalInvoiceAmount(), frequentRenterPoints());
  }

  private List<ItemCharges> generateItemisedCharges() {
    ArrayList<ItemCharges> itemCharges = new ArrayList<>();
    _rentals.stream()
        .map(rental -> new ItemCharges(rental.getMovie(), rental.charge()))
        .forEach(itemCharges::add);
    return itemCharges;
  }

  private int frequentRenterPoints() {
    return _rentals.stream().mapToInt(Rental::getFrequentRenterPoints).sum();
  }

  private double totalInvoiceAmount() {
    return _rentals.stream().mapToDouble(Rental::charge).sum();
  }

  public String getName() {
    return _name;
  }
}
