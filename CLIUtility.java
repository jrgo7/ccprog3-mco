import java.util.ArrayList;
import java.util.Scanner;

public class CLIUtility {
  public static final int WINDOW_WIDTH = 80;
  public static final int WINDOW_COLUMNS = 3;

  /**
   * Prints a border that covers the whole screen.
   */
  public static void printBorder() {
    /* TODO: ANSI screen clearing stuff with "\033[H\033[2J" */
    int i;
    for (i = 0; i < WINDOW_WIDTH; i++)
      System.out.print("-");
    System.out.print("\n");
  }

  /**
   * Prints a calendar consisting of 31 days. Date numbers will only be shown if
   * included in the list of shown days.
   * 
   * @param message   The message to display before printing the calendar
   * @param shownDays The list of dates to show. Dates not in this list will be
   *                  replaced with a {@code [X]}.
   */
  public static void printCalendar(String message,
      ArrayList<Integer> shownDays) {
    int i;

    System.out.print(message);
    for (i = 1; i <= 31; i++) {
      if (i % 7 == 1)
        System.out.print("\n");
      if (!shownDays.contains(i))
        System.out.print("[X]  ");
      else
        System.out.printf("%-5d", i);
    }

    System.out.print("\n");
  }

  /**
   * Prints a prompt of choices from which the user must select a number.
   * 
   * @param sc      The open {@link Scanner} object to read input from
   * @param message The message displayed to prompt the user
   * @param choices The strings to print for each choice
   * @return the index of the option selected by the user
   */
  public static int promptChoiceInput(Scanner sc, String message,
      String... choices) {
    int i = 1;
    /* Format string used in printing choices to split them into columns */
    String formatter = "%-" + (WINDOW_WIDTH / WINDOW_COLUMNS - 1) + "s ";

    System.out.print(message);

    for (String choice : choices) {
      /* Print a newline once a row has been filled */
      if (i % WINDOW_COLUMNS == 1)
        System.out.print("\n");
      System.out.print(String.format(formatter, "[" + i++ + "] " + choice));
    }

    System.out.print("\n");

    /* Prompt the user for an integer and subtract by 1 to get the index */
    return promptIntegerInput(sc, "Input (1-" + (i - 1) + "):", 1, i - 1) - 1;
  }

  /**
   * Prints a prompt for the user to input a string
   * 
   * @param sc      The open {@link Scanner} object to read input from
   * @param message The message displayed to prompt the user
   * @return the string inputted by the user obtained through
   *         {@link Scanner#nextLine}()
   */
  public static String promptStringInput(Scanner sc, String message) {
    System.out.println(message);
    System.out.print(" >> ");

    return sc.nextLine();
  }

  /***
   * Prints a prompt for the user to input an integer within a specified range.
   * 
   * @param sc      The open {@link Scanner} object to read input from
   * @param message The message displayed to prompt the user
   * @param min     The minimum value that can be inputted by the user
   * @param max     The maximum value that can be inputted by the user
   * @return the value inputted by the user, validated to be in the range
   */
  public static int promptIntegerInput(Scanner sc, String message, int min,
      int max) {
    int retval;
    boolean valid = false;

    do {
      System.out.println(message);
      System.out.print(" >> ");

      /* Catch the NumberFormatException thrown by parseInt() */
      try {
        retval = Integer.parseInt(sc.nextLine());
      } catch (NumberFormatException e) {
        /* Force retval to be invalid */
        retval = min - 1;
      }

      /* Validate that the input is between min and max, inclusive */
      valid = retval >= min & retval <= max;

      if (!valid)
        System.out.println("Invalid input. Please try again:");
    } while (!valid);

    return retval;
  }

  private CLIUtility() {
    /*
     * A private no-argument constructor is added here to ensure CLIUtility
     * cannot be instantiated. A similar effect can be achieved by using an
     * interface instead, as Java 8 onward allows default implementations for
     * static methods.
     */
  }
}
