public class Movie {
  public static final int CHILDRENS = 2;
  public static final int REGULAR = 0;
  public static final int NEW_RELEASE = 1;
  public static final int REGULAR_MOVIE_BASE_RENTAL = 2;
  public static final double CHILDRENS_MOVIE_BASE_CHARGE = 1.5;
  public static final int NEW_RELEASE_FLAT_CHARGE = 3;
  public static final int REGULAR_MOVIE_BASE_RENTAL_PERIOD_IN_DAYS = 2;
  public static final int CHILDRENS_MOVIE_BASE_RENTAL_PERIOD_IN_DAYS = 3;
  public static final double ADDITIONAL_CHARGE_PER_DAY = 1.5;

  private final String _title;
  private final int _priceCode;

  public Movie(String title, int priceCode) {
    _title = title;
    _priceCode = priceCode;
  }

  public int getPriceCode() {
    return _priceCode;
  }

  public String getTitle() {
    return _title;
  }

  public boolean isNewRelease() {
    return getPriceCode() == NEW_RELEASE;
  }

  public double charge(int daysRented) {
    double rentalCharge = 0;
    switch (getPriceCode()) {
      case Movie.REGULAR -> rentalCharge = getRentalCharge(daysRented, REGULAR_MOVIE_BASE_RENTAL, REGULAR_MOVIE_BASE_RENTAL_PERIOD_IN_DAYS);
      case Movie.NEW_RELEASE -> rentalCharge = daysRented * NEW_RELEASE_FLAT_CHARGE;
      case Movie.CHILDRENS -> rentalCharge = getRentalCharge(daysRented, CHILDRENS_MOVIE_BASE_CHARGE, CHILDRENS_MOVIE_BASE_RENTAL_PERIOD_IN_DAYS);
    }
    return rentalCharge;
  }

  private double getRentalCharge(int daysRented, double baseCharge, int baseRentalPeriodInDays) {
    return baseCharge + getRentalChargeForBeyondBasePeriod(daysRented, baseRentalPeriodInDays);
  }

  private double getRentalChargeForBeyondBasePeriod(int daysRented, int baseRentalPeriodInDays) {
    if (daysRented <= baseRentalPeriodInDays) {
      return 0.0;
    }
    return (daysRented - baseRentalPeriodInDays) * ADDITIONAL_CHARGE_PER_DAY;
  }
}
