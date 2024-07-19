package src.view.cli;

import java.util.Scanner;

/** Contains methods used to abstract the process of getting user inputs. */
public final class CLIUtility {
  /** The width of the window in characters. Set to 80. */
  public static final int WINDOW_WIDTH = 80;

  /** The number of columns to print menu options in. Set to 3. */
  public static final int WINDOW_COLUMNS = 3;

  /**
   * Prints a border that covers the whole screen.
   */
  public static void printBorder() {
    int i;
    for (i = 0; i < WINDOW_WIDTH; i++)
      System.out.print("-");
    System.out.print("\n");
  }

  /**
   * Prints a prompt of choices from which the user must select a number. The
   * choices are printed in columns; if an choice is too long, it will be
   * truncated with an ellipsis.
   * 
   * @param sc      The open {@link Scanner} object to read input from
   * @param message The message displayed to prompt the user
   * @param choices The strings to print for each choice
   * @return the index of the option selected by the user
   */
  public static int promptChoice(Scanner sc, String message,
      String... choices) {
    /* Subtract 1 to account for rounding */
    int i = 1, width = WINDOW_WIDTH / WINDOW_COLUMNS - 1;
    /* Format string used in printing choices to split them into columns */
    String formatter = "%-" + width + "s ";

    System.out.print(message);

    String option;
    for (String choice : choices) {
      /* Print a newline once a row has been filled */
      if (i % WINDOW_COLUMNS == 1)
        System.out.print("\n");

      /* Prepend a choice number to the option */
      option = "[" + (i++) + "] " + choice;

      /* Trim if the option string exceeds the maximum width */
      if (option.length() > width)
        option = option.substring(0, width - 3) + "...";

      System.out.print(String.format(formatter, option));
    }

    System.out.print("\n");

    /* Prompt the user for an integer and subtract by 1 to get the index */
    return promptInt(sc, "Input (1-" + (i - 1) + "):", 1, i - 1) - 1;
  }

  /**
   * Prints a prompt for the user to input a string
   * 
   * @param sc      The open {@link Scanner} object to read input from
   * @param message The message displayed to prompt the user
   * @return the string inputted by the user obtained through
   *         {@link Scanner#nextLine}()
   */
  public static String promptString(Scanner sc, String message) {
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
  public static int promptInt(Scanner sc, String message, int min,
      int max) {
    int retval;
    boolean valid = false;

    System.out.println(message);
    do {
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

  /**
   * Abstracts a call to {@link #promptChoice(Scanner, String, String...)} to
   * simplify prompting the user to confirm an action with {@code "Yes"} or to
   * abort with {@code "No"}.
   * 
   * @param sc      The open {@link Scanner} object to read input from
   * @param message The message displayed to prompt the user
   * @return {@code true} if the user confirms, {@code false} if the user aborts
   */
  public static boolean confirm(Scanner sc, String message) {
    return promptChoice(sc, message, "Yes", "No") == 0;
  }

  /** Ensures ClIUtility cannot be instantiated. */
  private CLIUtility() {
    /*
     * A private no-argument constructor is added here to ensure CLIUtility
     * cannot be instantiated. A similar effect can be achieved by using an
     * interface instead, as Java 8 onward allows default implementations for
     * static methods.
     */
  }
}