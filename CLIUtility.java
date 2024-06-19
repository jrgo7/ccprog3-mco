import java.util.Scanner;

public class CLIUtility {
  public static final int WINDOW_WIDTH = 80;

  public static void printBorder() {
    /* TODO: ANSI screen clearing stuff */
    // System.out.print("\033[H\033[2J");
    System.out.println(
        "--------------------------------------------------------------------------------");
  }

  /**
   * Prints a prompt of choices from which the user must select a number.
   * 
   * @param message The message displayed to prompt the user
   * @param choices The strings to print for each choice
   * @return the index of the option selected by the user
   */
  public static int promptChoiceInput(Scanner sc, String message, String... choices) {
    int i = 1, columns = 3;
    String format = "%-" + WINDOW_WIDTH / columns + "s";

    System.out.print(message);

    for (String choice : choices) {
      if (i % columns == 1)
        System.out.print("\n");
      System.out.print(String.format(format, "[" + i++ + "] " + choice));
    }

    System.out.print("\n");

    return promptIntegerInput(sc, "Input (1-" + (i - 1) + "):", 1, i - 1) - 1;
  }

  /**
   * Prints a prompt for the user to input a string
   * 
   * @param message The message displayed to prompt the user
   * @return the string inputted by the user obtained through
   *         {@link Scanner#nextsLine}()
   */
  public static String promptStringInput(Scanner sc, String message) {
    System.out.println(message);
    System.out.print(" >> ");

    return sc.nextLine();
  }

  public static int promptIntegerInput(Scanner sc, String message, int min, int max) {
    int retval;
    boolean valid = false;

    do {
      System.out.println(message);
      System.out.print(" >> ");

      /* Catch the NumberFormatException thrown by parseInt() */
      try {
        retval = Integer.parseInt(sc.nextLine());
      } catch (NumberFormatException e) {
        retval = -1;
      }

      valid = retval >= min & retval <= max;

      if (!valid)
        System.out.println("Invalid input. Please try again:");
    } while (!valid);

    return retval;
  }

  private CLIUtility() {}
}
