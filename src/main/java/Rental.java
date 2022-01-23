class Rental {
  public static final int MINIMUM_RENT_PERIOD_FOR_BONUS_POINTS = 1;
  public static final int REGULAR_MOVIE_BASE_RENTAL = 2;
  public static final double CHILDRENS_MOVIE_BASE_CHARGE = 1.5;
  public static final int NEW_RELEASE_FLAT_CHARGE = 3;
  public static final int REGULAR_MOVIE_BASE_RENTAL_PERIOD_IN_DAYS = 2;
  public static final int CHILDRENS_MOVIE_BASE_RENTAL_PERIOD_IN_DAYS = 3;
  public static final double ADDITIONAL_CHARGE_PER_DAY = 1.5;

  private final Movie _movie;
  private final int _daysRented;

  public Rental(Movie movie, int daysRented) {
    _movie = movie;
    _daysRented = daysRented;
  }

  public int getDaysRented() {
    return _daysRented;
  }

  public Movie getMovie() {
    return _movie;
  }

  public int getFrequentRenterPoints() {
    int frequentRenterPoints = 1;
    if (isEligibleForBonus()) {
      frequentRenterPoints++;
    }
    return frequentRenterPoints;
  }

  private boolean isEligibleForBonus() {
    return getMovie().getPriceCode() == Movie.NEW_RELEASE && getDaysRented() > MINIMUM_RENT_PERIOD_FOR_BONUS_POINTS;
  }

  double charge() {
    double rentalCharge = 0;
    switch (getMovie().getPriceCode()) {
      case Movie.REGULAR -> {
        rentalCharge += REGULAR_MOVIE_BASE_RENTAL;
        rentalCharge = getRentalChargeForBeyondBasePeriod(getDaysRented(), rentalCharge, REGULAR_MOVIE_BASE_RENTAL_PERIOD_IN_DAYS);
      }
      case Movie.NEW_RELEASE -> rentalCharge += getDaysRented() * NEW_RELEASE_FLAT_CHARGE;
      case Movie.CHILDRENS -> {
        rentalCharge += CHILDRENS_MOVIE_BASE_CHARGE;
        rentalCharge = getRentalChargeForBeyondBasePeriod(getDaysRented(), rentalCharge, CHILDRENS_MOVIE_BASE_RENTAL_PERIOD_IN_DAYS);
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
