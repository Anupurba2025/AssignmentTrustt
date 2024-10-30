import java.util.List;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) throws InvalidInputException{
        CustomerService crmService = (CustomerService) new CustomerManagementService();
        Scanner sc = new Scanner(System.in);

        //User Input
        while (true) { 
            System.out.println("Please enter the Customer details : ");
            System.out.println("Customer name : ");
            String name = sc.nextLine();

            System.out.println("AddressLine1 : ");
            String addressLine1 = sc.nextLine();

            System.out.println("AddressLine2 : ");
            String addressLine2 = sc.nextLine();

            System.out.println("City : ");
            String city = sc.nextLine();

            System.out.println("State : ");
            String state = sc.nextLine();

            System.out.println("Country : ");
            String country = sc.nextLine();

            Customer customer = new Customer(name, addressLine1, addressLine2, city, state, country);
            crmService.addCustomer(customer);

            validateInputs(name, addressLine1, addressLine2, city, state, country);


            System.out.println("You Want to Add more Customer Details ( YES / NO ) : ");
            String choice = sc.nextLine();

            if(choice.equalsIgnoreCase("no")){
                break;
            }
        }

        System.out.println("Do you want to see the Customer Details ( YES / NO ) : ");
        String choice1 = sc.nextLine();

        if(choice1.equalsIgnoreCase("yes")){
            System.out.println("\nHere is the List of Customer Details : ");
            List<String> formattedAddresses = crmService.getAddress();
            formattedAddresses.forEach(System.out::println);
            System.out.println("\n");
        }
        sc.close();
    }

    /**
     * Validates multiple inputs to ensure no field is empty.
     *
     * @param inputs the inputs to be validated
     * @throws InvalidInputException if any input is null or empty
     */

    private static void validateInputs(String... inputs) throws InvalidInputException {
        for (String input : inputs) {
            if (input == null || input.trim().isEmpty()) {
                throw new InvalidInputException();
            }
        }
    }
}
