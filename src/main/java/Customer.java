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
    double totalAmount = 0;
    int frequentRenterPoints = 0;
    Iterator<Rental> rentals = _rentals.stream().iterator();
    StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");

    while (rentals.hasNext()) {

      Rental rental = rentals.next();

      double rentalChargeForThisRental = chargeForRental(rental);

      frequentRenterPoints += getFrequentRenterPoints(rental);

      // show figures for this rental
      result.append("\t").append(rental.getMovie().getTitle()).append("\t").append(rentalChargeForThisRental).append("\n");
      totalAmount += rentalChargeForThisRental;
    }

    // add footer lines
    result.append("You owed ").append(totalAmount).append("\n");
    result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");

    return result.toString();
  }

  private int getFrequentRenterPoints(Rental rental) {
    // add frequent renter points
    int frequentRenterPoints = 1;

    // add bonus for a two day new release rental
    if (rental.getMovie().getPriceCode() == Movie.NEW_RELEASE && rental.getDaysRented() > 1)
      frequentRenterPoints++;
    return frequentRenterPoints;
  }

  private double chargeForRental(Rental each) {
    double rentalCharge = 0;
    // determines the amount for each line
    switch (each.getMovie().getPriceCode()) {
      case Movie.REGULAR -> {
        rentalCharge += 2;
        if (each.getDaysRented() > 2)
          rentalCharge += (each.getDaysRented() - 2) * 1.5;
      }
      case Movie.NEW_RELEASE -> rentalCharge += each.getDaysRented() * 3;
      case Movie.CHILDRENS -> {
        rentalCharge += 1.5;
        if (each.getDaysRented() > 3)
          rentalCharge += (each.getDaysRented() - 3) * 1.5;
      }
    }
    return rentalCharge;
  }
}
