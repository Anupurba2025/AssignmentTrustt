import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Customer management service with sorting functionalities.
 */
class CustomerManagementService extends CustomerServiceImplements {

    // Implement sorting by Customer's name alphabetically
    @Override
    protected void sortCustomersByName() {
        Collections.sort(customers, Comparator.comparing(Customer::getName));
    }

    @Override
    public List<String> getAddress() {
        sortCustomersByName();
        return customers.stream()
                .map(Customer::getAddress)
                .collect(Collectors.toList());
    }
}