/******************************************************************************
 *  Compilation:  javac HelloGoodbye.java
 *  Execution:    java HelloGoodbye
 *
 *  Takes two command line arguments (names)
 *  Prints a greeting and goodbye containing the names.
 *  In the goodbye statement, the order of the names are reversed.
 *
 *  % java HelloGoodbye Kevin Bob
 *  Hello Kevin and Bob.
 *  Goodbye Bob and Kevin.
 *****************************************************************************/

public class HelloGoodbye {
    public static void main(String[] args) {

        String name1, name2;

        // Checks if user entered any command line arguments
        if (args.length > 0) {
            // Parse string values to integer
            name1 = args[0];
            name2 = args[1];

            // Prints the 'Hello' and 'Goodbye' statements to the terminal window
            System.out.printf("Hello %s and %s.\n", name1, name2);
            System.out.printf("Goodbye %s and %s.\n", name2, name1);
        } else {
            System.err.println("Argument must not be blank.");
        }
    }
}
