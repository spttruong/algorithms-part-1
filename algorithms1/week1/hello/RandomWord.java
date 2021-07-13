import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/******************************************************************************
 *  Compilation:  javac-algs4 RandomWord.java
 *  Execution:    java-algs4 RandomWord
 *
 *  Takes a number of standard inputs (NOT command line arguments).
 *  Randomly picks one string from the set of input strings
 *  and prints the randomly selected string.
 *  Randomness is determined using Knuth's method.
 *
 *  % java-algs4 RandomWord
 *  standard input: first second third fourth
 *  [Press Ctrl+D] - signifies EOF
 *  first
 *****************************************************************************/

public class RandomWord {
    public static void main(String[] args) {
        int i = 0;
        String currentChampion = "";

        while (!StdIn.isEmpty()) {
            i++;
            String word = StdIn.readString();

            // Select a champion randomly
            if (StdRandom.bernoulli((double) 1 / i)) {
                currentChampion = word;
            }
        }
        StdOut.println(currentChampion);
    }
}
