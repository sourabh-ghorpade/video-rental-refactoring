public class ItemCharges {
  private Movie movie;
  private final double rentalChargeForThisRental;

  public ItemCharges(Movie movie, double rentalChargeForThisRental) {
    this.movie = movie;

    this.rentalChargeForThisRental = rentalChargeForThisRental;
  }

  public double getRentalChargeForThisRental() {
    return rentalChargeForThisRental;
  }

  public Movie getMovie() {
    return movie;
  }
}
