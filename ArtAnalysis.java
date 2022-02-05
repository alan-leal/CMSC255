/*************************************************************************************************
 * Project07 - Art Analysis
 *************************************************************************************************
 * This project reads an input text file and writes out to an output text file the average value and highest value between 8 separate pieces of art.
 * This project also calculates which art is above the average value and outputs information about the art pieces that are above the value.
 * Lastly, this project checks to see if a given art piece is among the art pieces located in the input text file.
 *________________________________________________________________________________________________
 * Alan Leal
 * May 7th 2021
 * CMSC255-003
 ****************************************************************************/
package Project7;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ArtAnalysis {
    /**
     * This method allows for the file to be read through the action of looping line by line
     *
     * @param inputFile
     * @return
     * @throws FileNotFoundException
     */
    public static ArrayList<String> readFile(File inputFile) throws FileNotFoundException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
        ArrayList<String> lines = new ArrayList<String>();
        try {
            /* TRY reading file input */
            String line = bufferedReader.readLine();
            while (line != null) {
                lines.add(line);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();

        } catch (IOException e) {
            throw new FileNotFoundException();
        }
        return lines;
    }
    /**
     * This method converts the String input into a double value for Art objects priced value
     * @param value2
     * @return
     */
    public static double convertStringToDouble (String value2) {
        try {
            double value = Double.parseDouble(value2.trim());
            if (value < 0) {
                return 0.0;
            }
            return value;
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
    /**
     * This method parses each element's data in the array list into a new array list containing Art objects
     *
     * @param lines
     * @return
     */
    public static ArrayList<Art> parseData(ArrayList<String> lines) {
        ArrayList<Art> artPieces = new ArrayList<Art>();
        int i = 0;
        while (i < lines.size()) {
            /* Creating temp array to store split string */
            String [] temp = lines.get(i).split(" ");

            /* New art object */
            Art art = new Art();
            if (temp.length == 4) {
                art.setValue(convertStringToDouble(temp[2]));
            }
            artPieces.add(art);
            i++;
        }
        return artPieces;
    }

    /**
     * This method calculates and returns the average value between all artworks
     * @param artworks
     * @return
     */
    public static double calcValueAvg(ArrayList<Art> artworks) {
        double valTotal = 0.0;
        int i = 0;
        while (i < artworks.size()) {
            valTotal += artworks.get(i).getValue();
            i++;
        }
        return valTotal / artworks.size();
    }

    /**
     * This method returns the artwork with the highest priced value out of the pool of Art objects
     * @param artworks
     * @return
     */
    public static double findHighValue(ArrayList<Art> artworks) {
        double highestVal = 0;
        int i = 1;
        while (i < artworks.size()) {
            if (artworks.get(i).getValue() > highestVal) {
                highestVal = artworks.get(i).getValue();
            }
            i++;
        }
        return highestVal;
    }

    /**
     * This method returns the artwork(s) with values above the average value
     * @param artworks
     * @param avg
     * @return
     */
    public static ArrayList<Art> findHighestArtByValue(ArrayList<Art> artworks, double avg) {
        ArrayList<Art> aboveAvgArt = new ArrayList<Art>();
        int i = 0;
        while (i < artworks.size()) {
            if (artworks.get(i).getValue() > avg) {
                aboveAvgArt.add(artworks.get(i));
            }
            i++;
        }
        return aboveAvgArt;
    }

    /**
     * This method enables the user to check if an Art object is located in the array list
     * @param artworks
     * @param art
     * @return
     */
    public static boolean findArt(ArrayList<Art> artworks, Art art) {
        int i = 0;
        while (i < artworks.size()) {
            if (artworks.get(i).equals(art)) {
                return true;
            }
            i++;
        }
        return false;
    }

    /**
     * These three methods writes out the values from each output in the program to the outputFile parameter
     * @param outputMessage
     * @param artworks
     * @param out
     * @throws FileNotFoundException
     */
    /* Output message for the Art array list */
    public static void writeOutData (String outputMessage, ArrayList<Art> artworks, PrintWriter out) throws FileNotFoundException {
        System.out.println(outputMessage);
        int i = 0;
        while (i < artworks.size()) {
            System.out.println(artworks.get(i));
            i++;
        }
    }
    /* Output for value of artworks */
    public static void writeOutData (String outputMessage, double value, PrintWriter out) throws FileNotFoundException {
        System.out.print(outputMessage);
        System.out.print(": %.2f" + value);
        System.out.println("\n");
    }
    /* Output for boolean value */
    public static void writeOutData (String outputMessage, boolean value, PrintWriter out) throws FileNotFoundException {

    }

    /**
     * Main method
     * @param args
     */
    public static void main(String [] args) {
        Scanner scnr = new Scanner(System.in);
        String inputPath = null;
        String outputPath = null;

        /* case: no files in the command line */
        if (args.length == 0) {
            System.out.print("Enter input file name: ");
            inputPath = scnr.nextLine();
            System.out.print("Enter output file name: ");
            outputPath = scnr.nextLine();

            File newInputFile = new File(inputPath);
            File newOutputFile = new File(outputPath);
        }

        /* case: two file paths in the command line */
        else if (args.length == 2) {
            inputPath = args[0];
            outputPath = args[1];
        }
       /* try & catch block for possible exception handling */
        ArrayList<Art> artPieces = null;
        try {
            artPieces = parseData(readFile(new File(inputPath)));
        } catch (FileNotFoundException e) {
            System.out.println("Incorrect input filename");
        }

        /* channeling average value between all artworks */
        double averageValue = calcValueAvg(artPieces);
        ArrayList<Art> artAverage = findHighestArtByValue(artPieces, averageValue);

        /* output average */
        System.out.println("The average value is: %.2f\n" + artAverage);
        System.out.println();

        /* output highest value for all Art objects */
        System.out.printf("The highest value is: %.2f\n" + findHighValue(artPieces));

        /* output works of art above the average value */
        System.out.println("The art above the average value are: ");
        for (int i = 0; i < artAverage.size(); i++) {
            System.out.println(artAverage.get(i));
        }



    }
}