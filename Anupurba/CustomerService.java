import java.util.List;

interface CustomerService{

    /**
     * @param customer the customer to be added
     * @throws InvalidInputException if input is invalid
     */

    void addCustomer(Customer customer) throws InvalidInputException;

    /**@return a list of formatted addresses */
    List<String> getAddress();
}