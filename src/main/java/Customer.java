import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Customer {
  public static final int REGULAR_MOVIE_BASE_RENTAL = 2;
  public static final double CHILDRENS_MOVIE_BASE_CHARGE = 1.5;
  public static final int NEW_RELEASE_FLAT_CHARGE = 3;
  public static final int REGULAR_MOVIE_BASE_RENTAL_PERIOD_IN_DAYS = 2;
  public static final int CHILDRENS_MOVIE_BASE_RENTAL_PERIOD_IN_DAYS = 3;
  public static final double ADDITIONAL_CHARGE_PER_DAY = 1.5;
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
        rentalCharge += REGULAR_MOVIE_BASE_RENTAL;
        rentalCharge = getRentalChargeForBeyondBasePeriod(rental.getDaysRented(), rentalCharge, REGULAR_MOVIE_BASE_RENTAL_PERIOD_IN_DAYS);
      }
      case Movie.NEW_RELEASE -> rentalCharge += rental.getDaysRented() * NEW_RELEASE_FLAT_CHARGE;
      case Movie.CHILDRENS -> {
        rentalCharge += CHILDRENS_MOVIE_BASE_CHARGE;
        rentalCharge = getRentalChargeForBeyondBasePeriod(rental.getDaysRented(), rentalCharge, CHILDRENS_MOVIE_BASE_RENTAL_PERIOD_IN_DAYS);
      }
    }
    return rentalCharge;
  }

  private double getRentalChargeForBeyondBasePeriod(int daysRented, double rentalCharge, int baseRentalPeriodInDays) {
    if (daysRented > baseRentalPeriodInDays)
      rentalCharge += (daysRented - baseRentalPeriodInDays) * ADDITIONAL_CHARGE_PER_DAY;
    return rentalCharge;
  }
}
