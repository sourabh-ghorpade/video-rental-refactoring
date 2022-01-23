import java.util.*;

public class Customer {
  private final String _name;
  private final List<Rental> _rentals = new ArrayList<>();

  public Customer(String name) {
    this._name = name;
  }

  public void addRental(Rental rental) {
    _rentals.add(rental);
  }

  public String getName() {
    return _name;
  }

  public String statement() {
    double totalInvoiceAmount = 0;
    int frequentRenterPoints = 0;
    Iterator<Rental> rentals = _rentals.stream().iterator();
    StringBuilder invoiceStatement = new StringBuilder(invoiceHeader());

    while (rentals.hasNext()) {
      Rental rental = rentals.next();
      double rentalChargeForThisRental = chargeForRental(rental);
      frequentRenterPoints += getFrequentRenterPoints(rental);
      invoiceStatement.append(generateInvoiceLineForRental(rental, rentalChargeForThisRental));
      totalInvoiceAmount += rentalChargeForThisRental;
    }

    addFooterLines(totalInvoiceAmount, frequentRenterPoints, invoiceStatement);
    return invoiceStatement.toString();
  }

  private String invoiceHeader() {
    return "Rental Record for " + getName() + "\n";
  }

  private void addFooterLines(double totalAmount, int frequentRenterPoints, StringBuilder result) {
    result.append("You owed ").append(totalAmount).append("\n");
    result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
  }

  private String generateInvoiceLineForRental(Rental rental, double rentalChargeForThisRental) {
    return "\t" + rental.getMovie().getTitle() + "\t" + rentalChargeForThisRental + "\n";
  }

  private int getFrequentRenterPoints(Rental rental) {
    int frequentRenterPoints = 1;

    // add bonus for a two day new release rental
    if (rental.getMovie().getPriceCode() == Movie.NEW_RELEASE && rental.getDaysRented() > 1)
      frequentRenterPoints++;
    return frequentRenterPoints;
  }

  private double chargeForRental(Rental rental) {
    double rentalCharge = 0;
    // determines the amount for each line
    switch (rental.getMovie().getPriceCode()) {
      case Movie.REGULAR -> {
        rentalCharge += 2;
        rentalCharge = getRentalCharge(rental.getDaysRented(), rentalCharge, 2);
      }
      case Movie.NEW_RELEASE -> rentalCharge += rental.getDaysRented() * 3;
      case Movie.CHILDRENS -> {
        rentalCharge += 1.5;
        rentalCharge = getRentalCharge(rental.getDaysRented(), rentalCharge, 3);
      }
    }
    return rentalCharge;
  }

  private double getRentalCharge(int daysRented, double rentalCharge, int baseRentalPeriodInDays) {
    if (daysRented > baseRentalPeriodInDays)
      rentalCharge += (daysRented - baseRentalPeriodInDays) * 1.5;
    return rentalCharge;
  }
}
