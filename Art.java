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

public class Art {
    /**
     * Instance variables
     */
    private String name;
    private String creator;
    private double value;
    private String location;

    /**
     * Default constructor
     * Set string values to null
     * Set double value to 0.0;
     */
    public Art() {
        name = null;
        creator = null;
        value = 0.0;
        location = null;
    }

    /**
     * Parameterized constructor
     * 4-arg constructor passing in each instance variable
     */
    public Art(String name, String creator, double value, String location) {
        this.name = name;
        this.creator = creator;
        this.value = value;
        this.location = location;
    }

    /**
     * This method returns the name of the art piece
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * This method returns the name of the artist for a given art piece
     * @return creator
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method returns the priced value of the art piece
     * @return value
     */
    public double getValue() {
        return value;
    }

    /**
     * This method returns the location at which the art piece is currently located
     * @return location
     */
    public String getLocation() {
        return location;
    }


    /**
     * This method sets the art piece's name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method sets the name of the artist
     * @param creator
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * This method sets the priced value of the art piece
     * @param value
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * This method sets where the art piece is located
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * This method returns the name of the art, the name of the artist, the priced value of the art, and the art's location, in that order.
     */
    public String toString(String name, String creator, double value, String location) {
        String artValue = String.format("%.2f", value);
        String artDescription = name + " " + creator + " " + artValue + " " + location;
        return artDescription;
    }

    /**
     * This method checks to see a piece of art is equal in name and artist name by testing through the instanceOf method
     * @param obj
     * @return
     */
    public boolean equals(Object obj) {
        if (obj instanceof Art) {
            Art a = (Art) obj;

            if (this.name.equals(a.getName()) && this.creator.equals(a.getCreator())) {

                return true;
            }

        }
        return false;
    }
}


