import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;


/**
 * This project implements a simple application. Properties from a fixed
 * file can be displayed. 
 * Bernardo Guterres
 * k23092429
 */
public class PropertyViewer
{    
    private PropertyViewerGUI gui;// the Graphical User Interface
    private Portfolio portfolio;// the Portoflio
    private Property property;// the Property
    private int indexOfProperty = 0;
    private int numOfProperties;
    private int numOfPropertiesViewed = 1;
    private int totalPropertyPrice;
    private int averagePropertyPrice;
    /**
     * Create a PropertyViewer and display its GUI on screen as well as the data of the first property.
     */
    public PropertyViewer()
    {
        gui = new PropertyViewerGUI(this);
        portfolio = new Portfolio("airbnb-london.csv");
        //initializes the property object by giving it the values of the first property in the array stored in the portfolio class.
        property = portfolio.getProperty(indexOfProperty);
        //Calls the show methods in the PropertyViewerGUI class to display data of first property when the application opens.
        gui.showProperty(property);
        gui.showID(property);
        //Calls and updates the average property price method.
        averagePropertyPrice();
    }

    /**
     * Calls for the property in the folowing index in the portfolio, updating the GUI.
     * If clicked at last property, returns to first property.
     */
    public void nextProperty()
    {
        //Initializes and equates the numOfProperties variable to the number of properties in the array
        numOfProperties = portfolio.numberOfProperties(); 
        //Updates index of property, gets the data of such property, and displays it
        indexOfProperty= (indexOfProperty + 1) % numOfProperties;
        property = portfolio.getProperty(indexOfProperty);
        gui.showProperty(property);
        gui.showID(property);
        gui.showFavourite(property);
        //Updates the number of properties viewed and average property price values
        numOfPropertiesViewed++;
        averagePropertyPrice();
    }

    /**
     * Calls for the property in the previous index in the protofolio, updating the GUI.
     * If clicked at first property, returns to last property.
     */
    public void previousProperty()
    {
        //Updates index of property, gets the data of such property, and displays it
        indexOfProperty = (numOfProperties + indexOfProperty - 1) % numOfProperties;
        property = portfolio.getProperty(indexOfProperty);
        gui.showProperty(property);
        gui.showID(property);
        gui.showFavourite(property);
        //Updates the number of properties viewed and average property price values
        numOfPropertiesViewed++;
        averagePropertyPrice();
    }

    /**
     * Changes favourite status of the property
     * Updates GUI, either will show favourite or not. 
     */
    public void toggleFavourite()
    {
       property.toggleFavourite();
       gui.showFavourite(property);
    }
    

    //----- methods for challenge tasks -----
    
    /**
     * This method opens the system's default internet browser
     * The Google maps page shows the current properties location on the map.
     */
    public void viewMap() throws Exception
    {
       double latitude = property.getLatitude();
       double longitude = property.getLongitude();
       
       URI uri = new URI("https://www.google.com/maps/place/" + latitude + "," + longitude);
       java.awt.Desktop.getDesktop().browse(uri); 
    }
    
    /**
     * Returns the number of propertiews viewed by the user.
     */
    public int getNumberOfPropertiesViewed()
    {
        return numOfPropertiesViewed; 
    }
    
    /**
     * Calculates the average property price of properties viewed by the user.
     */
    public void averagePropertyPrice()
    {   
        totalPropertyPrice = totalPropertyPrice + property.getPrice();
        averagePropertyPrice = (totalPropertyPrice/numOfPropertiesViewed);
    }
    
    /**
     * Returns the average property price.
     */
    public int getAveragePropertyPrice(){
        return averagePropertyPrice;
    }

    /**
     * Calls the method in the PropertyViewerGUI class to make the Statistics Frame.
     * The Frame will update automatically as the next and previous buttons are clicked.
     */
    public void statisticsButton(){
        gui.makeStatisticsFrame();
    }

}
