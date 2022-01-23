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
      double thisAmount = 0;
      Rental each = rentals.next();

      // determines the amount for each line
      switch (each.getMovie().getPriceCode()) {
        case Movie.REGULAR -> {
          thisAmount += 2;
          if (each.getDaysRented() > 2)
            thisAmount += (each.getDaysRented() - 2) * 1.5;
        }
        case Movie.NEW_RELEASE -> thisAmount += each.getDaysRented() * 3;
        case Movie.CHILDRENS -> {
          thisAmount += 1.5;
          if (each.getDaysRented() > 3)
            thisAmount += (each.getDaysRented() - 3) * 1.5;
        }
      }

      // add frequent renter points
      frequentRenterPoints++;

      // add bonus for a two day new release rental
      if (each.getMovie().getPriceCode() == Movie.NEW_RELEASE && each.getDaysRented() > 1)
        frequentRenterPoints++;

      // show figures for this rental
      result.append("\t").append(each.getMovie().getTitle()).append("\t").append(String.valueOf(thisAmount)).append("\n");
      totalAmount += thisAmount;
    }

    // add footer lines
    result.append("You owed ").append(String.valueOf(totalAmount)).append("\n");
    result.append("You earned ").append(String.valueOf(frequentRenterPoints)).append(" frequent renter points");

    return result.toString();
  }
}
