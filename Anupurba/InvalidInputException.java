//Exception thrown when a required input is invalid or missing.
 
public class InvalidInputException extends Exception {
    public InvalidInputException() {
        super("Please fill in all details properly..!");
    }
}
