import java.util.ArrayList;
import java.util.List;

//Abstract class providing common functionalities for CustomerService class.

abstract class CustomerServiceImplements implements CustomerService {
    protected List<Customer> customers = new ArrayList<>();
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    protected abstract void sortCustomersByName(); 
}
