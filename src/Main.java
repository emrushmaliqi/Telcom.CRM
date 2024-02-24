import enums.*;
import models.Contract;
import models.Customer;
import models.Product;
import models.Subscription;
import models.contacts.BusinessContact;
import models.contacts.Contact;
import models.contacts.IndividualContact;
import models.services.Data;
import models.services.SimCard;
import models.services.Sms;
import models.services.Voice;
import services.TelecomService;
import services.impl.TelecomServiceImpl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


// Products field added to Service
public class Main {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final TelecomService service = new TelecomServiceImpl();

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws ParseException {

        while (true) {
            System.out.println("Welcome to Telecom Service Management System");
            System.out.println("Choose an option:");
            System.out.println("1. Customer Operations");
            System.out.println("2. Contract Operations");
            System.out.println("3. Subscription Operations");
            System.out.println("4. Filter Operations");
            System.out.println("5. Exit");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
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
                        filterOperations(scanner);
                    case 5:
                        System.out.println("Exiting... Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (ParseException e) {
                System.out.println("Something went wrong!");
            }
        }
    }

    private static void filterOperations(Scanner scanner) {
        while (true) {
            System.out.println("1. Filter by cheaper than 5 euro");
            System.out.println("2. Filter subscribers that have purchased a specific product");
            System.out.println("3. Filter products that will expire in the next x (parameter) days");
            System.out.println("4. Back to Main Menu");

            int option = scanner.nextInt();

            scanner.nextLine();
            switch (option) {
                case 1:
                    Optional<List<Product>> products = service.findProductsCheaperThanX(5);
                    products.ifPresent(productList -> productList.forEach(System.out::println));
                    break;
                case 2:
                    System.out.println("Work in progress...");
                    break;
                case 3:
                    Optional<List<Product>> productList = service.findProductsExpiringInXDays(20);
                    productList.ifPresent(products1-> products1.forEach(System.out::println));
                    break;
                case 4:
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
                int customerId;
                    // create customer
                    System.out.println("Enter customer id:");
                    try {
                        customerId = scanner.nextInt();
                    }
                    catch (InputMismatchException  e)
                    {
                        System.out.println("Invalid input!");
                        return;
                    }

                    if(service.findCustomerById(customerId).isPresent()) {
                        System.out.println("Customer already exists!");
                        return; // customer already exists
                    }

                    scanner.nextLine(); // Consume newline

                    System.out.println("Enter customer type: y for INDIVIDUAL, anything for BUSINESS");
                    String customerTypeInput = scanner.nextLine().toUpperCase();

                    CustomerType customerType = customerTypeInput.equals("Y") ? CustomerType.INDIVIDUAL : CustomerType.BUSINESS;

                    System.out.println("Enter customer state: ACTIVE or INACTIVE or DEACTIVE");
                    String stateInput = scanner.nextLine().toUpperCase();

                    State state;
                    try {
                        state = State.valueOf(stateInput);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid state input!");
                        return; //  input is invalid
                    }


                    Customer customer = new Customer(customerId,customerType ,new Date(), state, null, new ArrayList<>());
                    Contact contact = createContact(customer);
                    customer.setContact(contact);
                    service.createCustomer(customer);
                    System.out.println("Customer created successfully!");
                    break;
                case 2:
                    // update customer
                    System.out.println("Enter customer ID to update:");
                    int customerIdToUpdate;

                    try {
                        customerIdToUpdate = scanner.nextInt();
                    }
                    catch (InputMismatchException  e)
                    {
                        System.out.println("Invalid input!");
                        return;
                    }
                    Optional<Customer> optionalCustomer = service.findCustomerById(customerIdToUpdate);
                    scanner.nextLine(); // Consume newline
                    if(!optionalCustomer.isPresent())
                    {
                        System.out.println("Customer not found!");
                        return;
                    }
                    System.out.println("Enter new customer state: ACTIVE or INACTIVE or DEACTIVE");
                    State newState;

                    try {
                        newState = State.valueOf(scanner.nextLine().toUpperCase());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid state input!");
                        return; //  input is invalid
                    }

                    Customer customerToUpdate = optionalCustomer.get();
                    customerToUpdate.setState(newState);
                    service.updateCustomer(customerToUpdate);
                    System.out.println("Customer updated successfully!");
                    break;
                case 3:
                    // Implement delete customer functionality
                    System.out.println("Enter customer ID to delete:");
                    int customerIdToDelete;
                    try {
                        customerIdToDelete = scanner.nextInt();
                    }
                    catch (InputMismatchException  e)
                    {
                        System.out.println("Invalid input!");
                        return;
                    }
                    scanner.nextLine(); // Consume newline


                    if(service.deleteCustomerById(customerIdToDelete))
                        System.out.println("Customer deleted successfully!");
                    else
                        System.out.println("Customer not found!");
                    break;
                case 4:
                    // Implement find customer by ID functionality
                    System.out.println("Enter customer ID to find:");
                    int customerIdToFind;
                    try {
                        customerIdToFind = scanner.nextInt();
                    }
                    catch (InputMismatchException  e)
                    {
                        System.out.println("Invalid input!");
                        return;
                    }

                    scanner.nextLine(); // Consume newline

                    Optional<Customer> foundCustomer = service.findCustomerById(customerIdToFind);
                    if (foundCustomer.isPresent()) {
                        System.out.println("Customer found: " + foundCustomer.get());
                    } else {
                        System.out.println("Customer not found!");
                    }
                    break;

                case 5:
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
                    System.out.print("Enter contract id: ");
                    int contractId;
                    try {
                        contractId = scanner.nextInt();
                    }
                    catch (InputMismatchException  e)
                    {
                        System.out.println("Invalid input!");
                        return;
                    }
                    scanner.nextLine(); // Consume newline

                    if(service.findCustomerById(contractId).isPresent()) {
                        System.out.println("Contract already exists!");
                        return; // customer already exists
                    }

                    System.out.println("Enter customer id: ");

                    int customerId = scanner.nextInt();
                    scanner.nextLine();
                    Optional<Customer> customerOptional = service.findCustomerById(customerId);
                    if(!customerOptional.isPresent())
                    {
                        System.out.println("Customer not found!");
                        return;
                    }

                    Customer customer = customerOptional.get();

                    System.out.println("Enter contract type: y for PREPAID, anything for POSTPAID");
                    String contractTypeInput = scanner.nextLine().toUpperCase();

                    ContractType contractType = contractTypeInput.equals("Y") ? ContractType.PREPAID : ContractType.POSTPAID;

                    System.out.println("Enter customer state: ACTIVE or INACTIVE or DEACTIVE");
                    String stateInput = scanner.nextLine().toUpperCase();
                    State state;
                    try {
                        state = State.valueOf(stateInput);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid state input!");
                        return; //  input is invalid
                    }

                    Contract contract = new Contract(contractId,contractType, new Date(), state, new ArrayList<>(), customer, null);
                    Contact contact = createContact(contract);
                    contract.setContact(contact);
                    service.createContract(contract);
                    System.out.println("Contract created successfully!");
                    break;
                case 2:
                    //update contract
                    System.out.println("Enter contract ID to update:");
                    int contractIdToUpdate;
                    try {
                        contractIdToUpdate = scanner.nextInt();
                    }
                    catch (InputMismatchException  e)
                    {
                        System.out.println("Invalid input!");
                        return;
                    }
                    scanner.nextLine(); // Consume newline

                    Optional<Contract> optionalContract = service.findContractById(contractIdToUpdate);
                    if(!optionalContract.isPresent()) {
                        System.out.println("Contract not found!");
                        return; // contract not found
                    }
                    Contract contractToUpdate = optionalContract.get();

                    System.out.print("Enter new contract type PREPAID or POSTPAID (empty to skip): ");
                    try {
                        String type = scanner.nextLine().toUpperCase();
                        if(!type.isEmpty()) {
                            ContractType newContractType = ContractType.valueOf(type);
                            contractToUpdate.setType(newContractType);
                        }
                    } catch (IllegalArgumentException e){
                        System.out.println("Invalid contract type! (No changes made)");
                    }
                    System.out.print("Enter new contract state ACTIVE, INACTIVE or DEACTIVE (empty to skip): ");
                    try {
                        String updatedState = scanner.nextLine().toUpperCase();
                        if(!updatedState.isEmpty()) {
                            State newState = State.valueOf(updatedState);
                            contractToUpdate.setState(newState);
                        }
                    } catch (IllegalArgumentException e){
                        System.out.println("Invalid contract type! (No changes made)");
                    }

                    service.updateContract(contractToUpdate);
                    System.out.println("Contract updated successfully!");
                    break;
                case 3:
                    // delete contract
                    System.out.println("Enter contract ID to delete:");
                    int contractIdToDelete;
                    try {
                        contractIdToDelete = scanner.nextInt();
                    }
                    catch (InputMismatchException  e)
                    {
                        System.out.println("Invalid input!");
                        return;
                    }
                    scanner.nextLine(); // Consume newline

                    System.out.printf("Contract deleted %s!%n", service.deleteContractById(contractIdToDelete) ? "succesfuly" : "failed");
                    break;
                case 4:
                    // find contract
                    System.out.println("Enter contract ID to find:");
                    int contractIdToFind;

                    try {
                        contractIdToFind = scanner.nextInt();
                    }
                    catch (InputMismatchException  e)
                    {
                        System.out.println("Invalid input!");
                        return;
                    }
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
                    System.out.print("Enter subscription id: ");
                    int subscriptionId;

                    try {
                        subscriptionId = scanner.nextInt();
                    }
                    catch (InputMismatchException  e)
                    {
                        System.out.println("Invalid input!");
                        return;
                    }

                    if(service.findSubscriptionById(subscriptionId).isPresent()) {
                        System.out.println("Subscription already exists!");
                        return;
                    }

                    System.out.print("Enter contract id: ");
                    int contractId;

                    try {
                        contractId = scanner.nextInt();
                    }
                    catch (InputMismatchException  e)
                    {
                        System.out.println("Invalid input!");
                        return;
                    }
                    scanner.nextLine(); // Consume newline

                    Optional<Contract> contractOptional = service.findContractById(contractId);

                    if(!contractOptional.isPresent()) {
                        System.out.println("Contract not found!");
                        return;
                    }

                    Contract contract = contractOptional.get();



                    System.out.print("Enter phone number (+3834(4|5|6){1-9}xxxxx): ");
                    String phoneNumber = scanner.nextLine();


                    System.out.println("Enter subscription state: ACTIVE or INACTIVE or DEACTIVE");
                    String stateInput = scanner.nextLine().toUpperCase();

                    State state;
                    try {
                        state = State.valueOf(stateInput);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid state input!");
                        return; //  input is invalid
                    }
                    List<OptionalServiceType> optionalServiceTypes = new ArrayList<>();
                    System.out.print("Type y to add Data as a service: ");
                    String addData = scanner.nextLine().toUpperCase();

                    if(addData.equals("Y")) {
                        optionalServiceTypes.add(OptionalServiceType.DATA);
                    }

                    System.out.print("Type y to add Messages as a service: ");
                    String addMessages = scanner.nextLine().toUpperCase();

                    if(addMessages.equals("Y")) {
                        optionalServiceTypes.add(OptionalServiceType.SMS);
                    }

                    Optional<List<Product>> optionalProducts = service.findAllProducts();
                    List<Product> productList = optionalProducts.orElseGet(ArrayList::new);
                    if(!optionalServiceTypes.contains(OptionalServiceType.DATA))
                        productList = productList.stream().filter(x -> x.getServices().stream().noneMatch(y -> y.getType().getClass() == Data.class)).collect(Collectors.toList());
                    if(!optionalServiceTypes.contains(OptionalServiceType.SMS))
                        productList = productList.stream().filter(x -> x.getServices().stream().noneMatch(y -> y.getType().getClass() == Sms.class)).collect(Collectors.toList());

                    List<Product> productsWithSim = productList.stream().filter(x -> x.getServices().stream().anyMatch(y -> y.getType() instanceof SimCard)).collect(Collectors.toList());

                    List<Product> productsWithVoice = productList.stream().filter(x -> x.getServices().stream().anyMatch(y -> y.getType() instanceof Voice)).collect(Collectors.toList());

                    System.out.println("Available products with SimCard:");
                    productsWithSim.forEach(System.out::println);


                    System.out.print("Enter product with SimCard id: ");
                    int simId;
                    try {
                        simId = scanner.nextInt();
                    }
                    catch (InputMismatchException  e) {
                        System.out.println("Invalid input!");
                        return; // input is invalid
                    }
                    scanner.nextLine(); // Consume newline

                    boolean isValidId = false;

                    Product productWithSim = null;

                    for(Product product : productsWithSim) {
                        if(product.getId() == simId) {
                            isValidId = true;
                            productWithSim = product;
                            break;
                        }
                    }

                    if(!isValidId) {
                        System.out.println("Wrong product id!");
                        return;
                    }



                    List<Product> subscriptionProducts = new ArrayList<>();
                    subscriptionProducts.add(productWithSim);

                    if(!productsWithVoice.contains(productWithSim)) {
                        System.out.println("Available products with Voice:");
                        productsWithVoice.forEach(System.out::println);
                        System.out.print("Enter product with Voice id: ");
                        int voiceId;
                        try {
                            voiceId = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input!");
                            return;
                        }
                        Product productWithVoice = null;
                        scanner.nextLine(); // Consume newline
                        for(Product product : productsWithVoice) {
                            if(product.getId() == voiceId) {
                                productWithVoice = product;
                                break;
                            }
                        }

                        if(productWithVoice == null) {
                            System.out.println("Wrong product id!");
                            return;
                        }
                        subscriptionProducts.add(productWithVoice);
                    }

                    Subscription subscription = new Subscription(subscriptionId,phoneNumber, new Date(), state, contract, subscriptionProducts, optionalServiceTypes,  null);
                    Contact contact = createContact(subscription);
                    subscription.setContact(contact);
                    service.createSubscription(subscription);
                    System.out.println("Subscription created successfully!");
                    break;
                case 2:
                    //update subscription
                    System.out.println("Enter subscription ID to update:");
                    int subscriptionIdToUpdate = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    Optional<Subscription> optionalSubscription = service.findSubscriptionById(subscriptionIdToUpdate);

                    if(!optionalSubscription.isPresent())
                    {
                        System.out.println("Subscription not found");
                        return;
                    }
                    Subscription subscriptionToUpdate = optionalSubscription.get();

                    System.out.print("Enter new subscription state (empty to not change) ACTIVE, INACTIVE or DEACTIVE: ");
                    try {
                        String stateIn = scanner.nextLine().toUpperCase();
                        if(!stateIn.isEmpty())
                            subscriptionToUpdate.setState(State.valueOf(stateIn));
                    }
                    catch (IllegalArgumentException e) {
                        System.out.println("Wrong state!");
                        return;
                    }

                    // Fetch subscription by ID

                    System.out.printf("Subscription updated %s!%n", service.updateSubscription(subscriptionToUpdate) ? "succesfuly" : "failed");
                    break;
                case 3:
                    //delete subscription
                    System.out.print("Enter subscription ID to delete: ");
                    int subscriptionIdToDelete;
                    try {
                        subscriptionIdToDelete = scanner.nextInt();
                    }
                    catch (InputMismatchException e) {
                        System.out.println("Id should be a number");
                        return;
                    }
                    scanner.nextLine(); // Consume newline


                    System.out.printf("Subscription deleted %s!%n", service.deleteSubscriptionById(subscriptionIdToDelete) ? "succesfuly" : "failed");
                    break;
                case 4:
                    //find by id
                    System.out.print("Enter subscription ID to find: ");
                    int subscriptionIdToFind;
                    try {
                        subscriptionIdToFind = scanner.nextInt();
                    }
                    catch (InputMismatchException e) {
                        System.out.println("Id should be a number");
                        return;
                    }
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
                    return;

            }
        }
    }

    public static Contact createContact(Customer customer) {
        if(customer.getType() == CustomerType.INDIVIDUAL) {
            System.out.println("Enter contact first name: ");
            String name = scanner.nextLine();

            System.out.println("Enter contact last name: ");
            String lastName = scanner.nextLine();
            // add dob
            Date dob;

            System.out.println("Enter customer date of birth (YYYY-MM-DD):");
            String inputDate = scanner.nextLine();
            while(true) {
                try {
                    dob = dateFormat.parse(inputDate);
                    break;
                }
                catch (ParseException e) {
                    System.out.println("Invalid date format. Please enter the date in the format YYYY-MM-DD: ");
                    inputDate = scanner.nextLine();
                }
            }
            return new IndividualContact(customer.getId(), ContactType.CU, new Date(), customer.getState(),name, lastName, dob);
        }

        System.out.println("Enter contact customer name: ");

        String customerName = scanner.nextLine();
        return new BusinessContact(customer.getId(), ContactType.CU, new Date(), customer.getState(), customerName);
    }

    public static Contact createContact(Contract contract) {
        System.out.println("Enter contact first name: ");
        String name = scanner.nextLine();

        System.out.println("Enter contact last name: ");
        String lastName = scanner.nextLine();
        Date dob;
        System.out.println("Enter customer date of birth (YYYY-MM-DD):");
        String inputDate = scanner.nextLine();
        while(true) {
            try {
                dob = dateFormat.parse(inputDate);
                break;
            }
            catch (ParseException e) {
                System.out.println("Invalid date format. Please enter the date in the format YYYY-MM-DD: ");
                inputDate = scanner.nextLine();
            }
        }
        return new IndividualContact(contract.getId(), ContactType.CO, new Date(), contract.getState(), name, lastName, dob); // add dob
    }

    public static Contact createContact(Subscription subscription) {
        System.out.println("Enter contact first name: ");
        String name = scanner.nextLine();

        System.out.println("Enter contact last name: ");
        String lastName = scanner.nextLine();
        Date dob;
        System.out.println("Enter customer date of birth (YYYY-MM-DD):");
        String inputDate = scanner.nextLine();
        while(true) {
            try {
                dob = dateFormat.parse(inputDate);
                break;
            }
            catch (ParseException e) {
                System.out.println("Invalid date format. Please enter the date in the format YYYY-MM-DD: ");
                inputDate = scanner.nextLine();
            }
        }
        return new IndividualContact(subscription.getId(), ContactType.CO, new Date(), subscription.getState(), name, lastName, dob); // add dob
    }
}

