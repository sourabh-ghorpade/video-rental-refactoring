class Rental {
  public static final int MINIMUM_RENT_PERIOD_FOR_BONUS_POINTS = 1;
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
    return _movie.isNewRelease() && getDaysRented() > MINIMUM_RENT_PERIOD_FOR_BONUS_POINTS;
  }

  public double charge() {
    return _movie.charge(getDaysRented());
  }
}
