import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

  @Test
  public void shouldReturnTextInvoiceWhenRentalsAreCreatedWithNoSpecificationForViewType() {
    Customer customer = new Customer("Bob");

    customer.addRental(new Rental(new Movie("Jaws", Movie.REGULAR), 2));
    customer.addRental(new Rental(new Movie("GoldenEye", Movie.REGULAR), 3));
    customer.addRental(new Rental(new Movie("ShortNew", Movie.NEW_RELEASE), 1));
    customer.addRental(new Rental(new Movie("LongNew", Movie.NEW_RELEASE), 2));
    customer.addRental(new Rental(new Movie("Bambi", Movie.CHILDRENS), 3));
    customer.addRental(new Rental(new Movie("Toy Story", Movie.CHILDRENS), 4));

    assertEquals("Rental Record for Bob\n" +
        "\tJaws\t2.0\n" +
        "\tGoldenEye\t3.5\n" +
        "\tShortNew\t3.0\n" +
        "\tLongNew\t6.0\n" +
        "\tBambi\t1.5\n" +
        "\tToy Story\t3.0\n" +
        "You owed 19.0\n" +
        "You earned 7 frequent renter points", customer.statement());
  }

  @Test
  public void shouldReturnTextInvoiceWhenRentalsAreCreatedAndViewTypeIsText() {
    Customer customer = new Customer("Bob");

    customer.addRental(new Rental(new Movie("Jaws", Movie.REGULAR), 2));
    customer.addRental(new Rental(new Movie("GoldenEye", Movie.REGULAR), 3));
    customer.addRental(new Rental(new Movie("ShortNew", Movie.NEW_RELEASE), 1));
    customer.addRental(new Rental(new Movie("LongNew", Movie.NEW_RELEASE), 2));
    customer.addRental(new Rental(new Movie("Bambi", Movie.CHILDRENS), 3));
    customer.addRental(new Rental(new Movie("Toy Story", Movie.CHILDRENS), 4));

    assertEquals("Rental Record for Bob\n" +
        "\tJaws\t2.0\n" +
        "\tGoldenEye\t3.5\n" +
        "\tShortNew\t3.0\n" +
        "\tLongNew\t6.0\n" +
        "\tBambi\t1.5\n" +
        "\tToy Story\t3.0\n" +
        "You owed 19.0\n" +
        "You earned 7 frequent renter points", customer.statement("text"));
  }

  @Test
  public void shouldReturnHTMLInvoiceWhenRentalsAreCreatedAndViewTypeIsHTML() {
    Customer customer = new Customer("Bob");

    customer.addRental(new Rental(new Movie("Jaws", Movie.REGULAR), 2));
    customer.addRental(new Rental(new Movie("GoldenEye", Movie.REGULAR), 3));
    customer.addRental(new Rental(new Movie("ShortNew", Movie.NEW_RELEASE), 1));
    customer.addRental(new Rental(new Movie("LongNew", Movie.NEW_RELEASE), 2));
    customer.addRental(new Rental(new Movie("Bambi", Movie.CHILDRENS), 3));
    customer.addRental(new Rental(new Movie("Toy Story", Movie.CHILDRENS), 4));

    assertEquals("<h1>Rental Record for <em>Bob</em></h1>" +
        "<p>Amount owed is <em>19.0</em></p>" +
        "<p>You earned <em>7</em> frequent renter points</p>", customer.statement("html"));
  }

  @Test
  public void testNothingRented() {
    Customer customer = new Customer("Bob");

    assertEquals("""
        Rental Record for Bob
        You owed 0.0
        You earned 0 frequent renter points""", customer.statement());
  }
}
