import enums.ContractType;
import enums.CustomerType;
import enums.State;
import models.Contract;
import models.Customer;
import models.Subscription;
import repositories.ContractRepository;
import repositories.CustomerRepository;
import repositories.SubscriptionRepository;
import repositories.impl.ContractJpaRepository;
import repositories.impl.CustomerJpaRepository;
import repositories.impl.SubscriptionJpaRepository;
import services.TelecomService;
import services.impl.TelecomServiceImpl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


// Products field added to Service
public class Main {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final TelecomService service = new TelecomServiceImpl();
    public static void main(String[] args) throws ParseException {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to Telecom Service Management System");
            System.out.println("Choose an option:");
            System.out.println("1. Customer Operations");
            System.out.println("2. Contract Operations");
            System.out.println("3. Subscription Operations");
            System.out.println("4. Exit");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    customerOperationsMenu(scanner);
                    break;
                case 2:
                    contractOperationsMenu(scanner);
                    break;
                case 3:
                    subscriptionOperationsMenu(scanner);
                    break;
                case 4:
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void customerOperationsMenu(Scanner scanner) throws ParseException {
        while (true) {
            System.out.println("Customer Operations Menu:");
            System.out.println("1. Create Customer");
            System.out.println("2. Update Customer");
            System.out.println("3. Delete Customer");
            System.out.println("4. Find Customer by ID");
            System.out.println("5. List All Customers");
            System.out.println("6. Back to Main Menu");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    // create customer
                    System.out.println("Enter customer id:");
                    int customerId = scanner.nextInt();


                    System.out.println("Enter customer type:");
                    String customerTypeInput = scanner.nextLine().toUpperCase();

                    // Directly set the customer type enum using valueOf method
                    CustomerType customerType;

                    System.out.println("Enter customer created date (YYYY-MM-DD):");
                    String inputDate = scanner.nextLine();
                    Date createdDate = dateFormat.parse(inputDate);

                    System.out.println("Enter customer state:");
                    String stateInput = scanner.nextLine().toUpperCase();

                    // Directly set the state enum using valueOf method
                    State state;
                    try {
                        state = State.valueOf(stateInput);
                        customerType = CustomerType.valueOf(customerTypeInput);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid state input!");
                        return; //  input is invalid
                    }

                    Customer customer = new Customer(customerId,customerType ,createdDate, state, null, null);
                    service.createCustomer(customer);
                    System.out.println("Customer created successfully!");
                    break;
                case 2:
                    // update customer
                    System.out.println("Enter customer ID to update:");
                    int customerIdToUpdate = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    System.out.println("Enter new customer type:");
                    CustomerType newCustomerType = CustomerType.valueOf(scanner.nextLine().toUpperCase());

                    System.out.println("Enter new customer state:");
                    State newState = State.valueOf(scanner.nextLine().toUpperCase());

                    // Fetch customer by ID
                    Optional<Customer> optionalCustomer = service.findCustomerById(customerIdToUpdate);
                    if (optionalCustomer.isPresent()) {
                        Customer customerToUpdate = optionalCustomer.get();
                        customerToUpdate.setType(newCustomerType);
                        customerToUpdate.setState(newState);
                        service.updateCustomer(customerToUpdate);
                        System.out.println("Customer updated successfully!");
                    } else {
                        System.out.println("Customer not found!");
                    }
                    break;
                case 3:
                    // Implement delete customer functionality
                    System.out.println("Enter customer ID to delete:");
                    int customerIdToDelete = scanner.nextInt();
                    scanner.nextLine(); // Consume newline


                    service.deleteCustomerById(customerIdToDelete);
                    System.out.println("Customer deleted successfully!");
                    break;
                case 4:
                    // Implement find customer by ID functionality
                    System.out.println("Enter customer ID to find:");
                    int customerIdToFind = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    Optional<Customer> foundCustomer = service.findCustomerById(customerIdToFind);
                    if (foundCustomer.isPresent()) {
                        System.out.println("Customer found: " + foundCustomer.get());
                    } else {
                        System.out.println("Customer not found!");
                    }
                    break;

// hala nuk e kom analizu mir kshtu qe qetash pe kqyri kapak edhe di qka met than besoj

                // oki
                case 5:
                    // Implement list all customers functionality
                    Optional<List<Customer>> customersOptional = service.findAllCustomers();
                    if (customersOptional.isPresent()) {
                        List<Customer> customers = customersOptional.get(); // Extract the list from Optional
                        System.out.println("List of Customers:");
                        for (Customer c : customers) {
                            System.out.println(c);
                        }
                    } else {
                        System.out.println("No customers found!");
                    }
                    break;
                case 6:
                    return; // Return to main menu
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void contractOperationsMenu(Scanner scanner) throws ParseException {
        while(true){
            System.out.println("Contract Operations Menu:");
            System.out.println("1. Create Contract");
            System.out.println("2. Update Contract");
            System.out.println("3. Delete Contract");
            System.out.println("4. Find Contract by ID");
            System.out.println("5. List All Contract");
            System.out.println("6. Back to Main Menu");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (option){
                case 1:
                    //create contract
                    System.out.println("Enter customer id:");
                    int contractId = scanner.nextInt();

                    System.out.println("Enter contract type:");
                    ContractType contractType = ContractType.valueOf(scanner.nextLine().toUpperCase());

                    System.out.println("Enter customer created date (YYYY-MM-DD):");
                    String inputDate = scanner.nextLine();
                    Date createdDate = dateFormat.parse(inputDate);

                    System.out.println("Enter contract state:");
                    State state = State.valueOf(scanner.nextLine().toUpperCase());


                    Contract contract = new Contract(contractId,contractType, createdDate, state, null, null, null);
                    service.createContract(contract);
                    System.out.println("Contract created successfully!");
                    break;
                case 2:
                    //update contract
                    System.out.println("Enter contract ID to update:");
                    int contractIdToUpdate = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    System.out.println("Enter new contract type:");
                    ContractType newContractType = ContractType.valueOf(scanner.nextLine().toUpperCase());

                    System.out.println("Enter new contract state:");
                    State newState = State.valueOf(scanner.nextLine().toUpperCase());

                    // Fetch contract by ID
                    Optional<Contract> optionalContract = service.findContractById(contractIdToUpdate);
                    if (optionalContract.isPresent()) {
                        Contract contractToUpdate = optionalContract.get();
                        contractToUpdate.setType(newContractType);
                        contractToUpdate.setState(newState);
                        service.updateContract(contractToUpdate);
                        System.out.println("Contract updated successfully!");
                    } else {
                        System.out.println("Contract not found!");
                    }
                    break;
                case 3:
                    // delete contract
                    System.out.println("Enter contract ID to delete:");
                    int contractIdToDelete = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    service.deleteContractById(contractIdToDelete);
                    System.out.println("Contract deleted successfully!");
                    break;
                case 4:
                    // find contract
                    System.out.println("Enter contract ID to find:");
                    int contractIdToFind = scanner.nextInt();
                    scanner.nextLine(); // Consume newline


                    Optional<Contract> foundContract = service.findContractById(contractIdToFind);
                    if (foundContract.isPresent()) {
                        System.out.println("Contract found: " + foundContract.get());
                    } else {
                        System.out.println("Contract not found!");
                    }
                    break;
                case 5:
                    //list all contracts
                    Optional<List<Contract>> contractsOptional = service.findAllContracts();
                    if (contractsOptional.isPresent()) {
                        List<Contract> contracts = contractsOptional.get(); // Extract the list from Optional
                        System.out.println("List of Contracts:");
                        for (Contract c : contracts) {
                            System.out.println(c);
                        }
                    } else {
                        System.out.println("No contracts found!");
                    }
                    break;
                case 6:
                    return; // Return to main menu



            }
        }
    }

    private static void subscriptionOperationsMenu(Scanner scanner) throws ParseException {
        while(true){
            System.out.println("Subscription Operations Menu:");
            System.out.println("1. Create Subscription");
            System.out.println("2. Update Subscription");
            System.out.println("3. Delete Subscription");
            System.out.println("4. Find Subscription by ID");
            System.out.println("5. List All Subscription");
            System.out.println("6. Back to Main Menu");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (option){
                case 1:
                    //create subscription
                    System.out.println("Enter customer id:");
                    int subscriptionId = scanner.nextInt();

                    System.out.println("Enter phone number:");
                    String phoneNumber = scanner.nextLine();

                    System.out.println("Enter customer created date (YYYY-MM-DD):");
                    String inputDate = scanner.nextLine();
                    Date createdDate = dateFormat.parse(inputDate);

                    System.out.println("Enter subscription state:");
                    State state = State.valueOf(scanner.nextLine().toUpperCase());

                    Subscription subscription = new Subscription(subscriptionId,phoneNumber, createdDate, state, null, null);
                    service.createSubscription(subscription);
                    System.out.println("Subscription created successfully!");
                    break;
                case 2:
                    //update subscription
                    System.out.println("Enter subscription ID to update:");
                    int subscriptionIdToUpdate = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    System.out.println("Enter new phone number:");
                    String newPhoneNumber = scanner.nextLine();

                    System.out.println("Enter new subscription state:");
                    State newState = State.valueOf(scanner.nextLine().toUpperCase());

                    // Fetch subscription by ID
                    Optional<Subscription> optionalSubscription = service.findSubscriptionById(subscriptionIdToUpdate);
                    if (optionalSubscription.isPresent()) {
                        Subscription subscriptionToUpdate = optionalSubscription.get();
                        subscriptionToUpdate.setPhoneNumber(newPhoneNumber);
                        subscriptionToUpdate.setState(newState);
                        service.updateSubscription(subscriptionToUpdate);
                        System.out.println("Subscription updated successfully!");
                    } else {
                        System.out.println("Subscription not found!");
                    }
                    break;
                case 3:
                    //delete subscription
                    System.out.println("Enter subscription ID to delete:");
                    int subscriptionIdToDelete = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    service.deleteSubscriptionById(subscriptionIdToDelete);
                    System.out.println("Subscription deleted successfully!");
                    break;
                case 4:
                    //find by id
                    System.out.println("Enter subscription ID to find:");
                    int subscriptionIdToFind = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    Optional<Subscription> foundSubscription = service.findSubscriptionById(subscriptionIdToFind);
                    if (foundSubscription.isPresent()) {
                        System.out.println("Subscription found: " + foundSubscription.get());
                    } else {
                        System.out.println("Subscription not found!");
                    }
                    break;
                case 5:
                    //list all subscriptions
                    Optional<List<Subscription>> subscriptionsOptional = service.findAllSubscriptions();
                    if (subscriptionsOptional.isPresent()) {
                        List<Subscription> subscriptions = subscriptionsOptional.get();
                        System.out.println("List of Subscriptions:");
                        for (Subscription s : subscriptions) {
                            System.out.println(s);
                        }
                    } else {
                        System.out.println("No subscriptions found!");
                    }
                    break;
                case 6:
                    return; // Return to main menu

            }
        }

    }
}

