public class Customer {

    private String name;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;

    /**
     * Constructs a Customer instance with the given details.
     *
     * @param name         Customer's name
     * @param addressLine1 Primary address line
     * @param addressLine2 Secondary address line
     * @param city         City of residence
     * @param state        State of residence
     * @param country      Country of residence
     */

    public Customer(String name, String addressLine1, String addressLine2, String city, String state, String country) {
        this.name = FirstLetterToUpperCase(name);
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    /**
     * Capitalizes the first letter of the given string.
     * 
     * @param str the string to be capitalized
     * @return    the string with the first letter in uppercase
     */

    private String FirstLetterToUpperCase(String name){
        return name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
    }

    /** @return the customer's name */
    public String getName(){
        return name;
    }
    
    /** @return a formatted string of the customer's address */
    
    public String getAddress(){
        return String.join(",",name,addressLine1,addressLine2,city,state,country);
    }
}

