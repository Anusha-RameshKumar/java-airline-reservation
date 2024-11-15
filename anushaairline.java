import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

class Flight {
    private String flightNumber;
    private String origin;
    private String destination;
    private String departureTime;
    private int totalSeats;
    private int firstclassSeat;
    private int normalclassSeat;
    private double firstclassPrice;
    private double normalclassPrice;
    private boolean isAvailable;

public Flight(String flightNumber, String origin, String destination, String           departureTime, int totalSeats, int firstclassSeat, int normalclassSeat, double firstclassPrice, double normalclassPrice) {
this.flightNumber = flightNumber;
this.origin = origin;
this.destination = destination;
this.departureTime = departureTime;
this.totalSeats = totalSeats;
this.firstclassSeat = firstclassSeat;
this.normalclassSeat = normalclassSeat;
this.firstclassPrice = firstclassPrice;
this.normalclassPrice = normalclassPrice;
this.isAvailable = true;
}

public String getFlightNumber() { return flightNumber; }
public String getOrigin() { return origin; }
public String getDestination() { return destination; }
public String getDepartureTime() { return departureTime; }
public int getFirstclassSeat () {return firstclassSeat; }
public int getNormalclassSeat() {return normalclassSeat; }
public double getFirstclassPrice() { return firstclassPrice; }
public double getNormalclassPrice() { return normalclassPrice; }
public boolean isAvailable() { return isAvailable; }
public void setAvailable(boolean available) { isAvailable = available; }
public void setOrigin(String origin) {
this.origin = origin;
}
public void setDestination(String destination) {
this.destination = destination;
}

public void setDepartureTime(String departureTime) {
this.departureTime = departureTime;
}
@Override
public String toString() {
return String.format("Flight %s: %s to %s at %s\nTotal seats: %d\n  First class seats: %d\n  Normal class seats: %d\n  Price: First class - %.2f, Normal class - %.2f\n Available: %s",
flightNumber, origin, destination, departureTime, totalSeats, firstclassSeat, normalclassSeat, firstclassPrice, normalclassPrice, isAvailable);
}
}

class Passenger {
private String name;
private String gender;
private String dob;
private String email;
private String phoneNumber;
public Passenger(String name, String gender, String dob, String email, String phoneNumber) {
this.name = name;
this.gender = gender;
this.dob = dob;
this.email = email;
this.phoneNumber = phoneNumber;
}

public String getName() { return name; }

@Override
public String toString() {
return String.format("Passenger: %s, Gender: %s, DOB: %s, Email: %s, Phone: %s", name, gender, dob, email, phoneNumber);
}
}

class Booking {
private Flight flight;
private List<Passenger> passengers;
private String bookingReference;
private double totalAmountPaid;
public Booking(Flight flight, List<Passenger> passengers, String bookingReference) {
this.flight = flight;
this.passengers = passengers;
this.bookingReference = bookingReference;

}

public void processPayment(double amount) {
this.totalAmountPaid = amount;
}

public double getTotalAmountPaid() {
return totalAmountPaid;
}

@Override
public String toString() {
StringBuilder passengerNames = new StringBuilder();
for (Passenger passenger : passengers) {
passengerNames.append(passenger.getName()).append(", ");
}
return String.format("Booking Reference: %s, Passengers: %s booked on %s, Total Amount Paid: %.2f",
bookingReference, passengerNames.toString(), flight.getFlightNumber(), totalAmountPaid);
}
}
class Report {
public static void generateBookingReport(List<Booking> bookings) {
System.out.println("Total Bookings: " + bookings.size());
double totalRevenue = bookings.stream().mapToDouble(Booking::getTotalAmountPaid).sum();
System.out.printf("Total Revenue Generated: %.2f\n", totalRevenue);
}

public static void generateFlightReport(List<Flight> flights, List<Booking> bookings) {
System.out.println("Flight Statistics:");
for (Flight flight : flights) {
long bookingsForFlight = bookings.stream()
.filter(b -> b.toString().contains(flight.getFlightNumber()))
.count();
System.out.printf("Flight %s: %d bookings\n", flight.getFlightNumber(), bookingsForFlight);
}
}
}
class Complaint {
private String bookingReference;
private String description;
public Complaint(String bookingReference, String description) {
this.bookingReference = bookingReference;
this.description = description;
}

public String getBookingReference() {
return bookingReference;
}

public String getDescription() {
return description;
}

@Override
public String toString() {
return String.format("Complaint for Booking %s: %s", bookingReference, description);
}
}

public class B {
private static List<Flight> flights = new ArrayList<>();
private static List<Booking> bookings = new ArrayList<>();
private static List<Passenger> passengers = new ArrayList<>();
private static List<Complaint> complaints = new ArrayList<>();
//private static List<SupportTicket> supportTickets = new ArrayList<>();
private static AtomicInteger bookingCounter = new AtomicInteger(1000);
// private static AtomicInteger ticketCounter = new AtomicInteger(1000);
//private static boolean isLoggedIn = false;
public static void main(String[] args){
Scanner scanner = new Scanner(System.in);

initializeFlights();
while(true) {
System.out.println("\n1. Search Flights");
System.out.println("2. Book Flight");
System.out.println("3. Check-In");
System.out.println("4. Add Flight");
System.out.println("5. Update Flight");
System.out.println("6. Delete Flight");
System.out.println("7. View All Flights");
System.out.println("8. Manage Passenger Profiles");
System.out.println("9. Generate Reports");
System.out.println("10. Admin Dashboard");
System.out.println("11. File a Complaint");
System.out.println("12. Logout");
System.out.println("13. Exit");
System.out.print("Choose an option: ");
int choice = scanner.nextInt();
scanner.nextLine();

switch (choice) {
case 1:
searchFlights(scanner);
break;
case 2:
bookFlight(scanner);
break;
case 3:
checkIn(scanner);
break;
case 4:
addFlight(scanner);
break;
case 5:
updateFlight(scanner);
break;
case 6:
deleteFlight(scanner);
break;
case 7:
viewAllFlights();
break;
case 8:
managePassengerProfiles(scanner);
break;

case 9:
generateReports(scanner);
break;
case 10:
adminDashboard(scanner);
break;
case 11:
fileComplaint(scanner); // New method for filing complaints
break;
case 12:
System.out.println("do");
case 13:
System.out.println("Happy journey...");
return;
default:
System.out.println("Invalid option. Please try again.");
}
}
}

private static void initializeFlights() {
flights.add(new Flight("AI2024", "Coimbatore", "Chennai", "2024-10-25 10:00", 300, 130, 170, 5599.00, 2980.00));
flights.add(new Flight("IX2024", "Chennai", "Bangalore", "2024-10-26 11:00", 230, 90, 140, 6870.00, 1678.00));
flights.add(new Flight("DG2024", "Mumbai", "Delhi", "2024-10-27 12:00", 330, 100, 230, 7750.00, 3678.00));
}
private static void searchFlights(Scanner scanner) {
System.out.print("Enter origin: ");
String origin = scanner.nextLine();
System.out.print("Enter destination: ");
String destination = scanner.nextLine();

System.out.println("Available flights:");
for (Flight flight : flights) {
if (flight.getOrigin().equalsIgnoreCase(origin) && flight.getDestination().equalsIgnoreCase(destination) && flight.isAvailable()) {
System.out.println(flight);
}
}
}


private static void bookFlight(Scanner scanner) {
System.out.print("Enter flight name: ");
String flightNumber = scanner.nextLine();

Flight selectedFlight = flights.stream()
.filter(flight -> flight.getFlightNumber().equalsIgnoreCase(flightNumber))
.findFirst()
.orElse(null);
if (selectedFlight != null && selectedFlight.isAvailable()) {
System.out.print("Enter total number of passengers: ");
int totalPassengers = scanner.nextInt();

// Collect the number of first class and normal class seats
System.out.print("Enter number of first class seats: ");
int firstClassCount = scanner.nextInt();
System.out.print("Enter number of normal class seats: ");
int normalClassCount = scanner.nextInt();
scanner.nextLine(); // Consume newline

// Validate seat availability
if (firstClassCount > selectedFlight.getFirstclassSeat() || normalClassCount > selectedFlight.getNormalclassSeat() ||
(firstClassCount + normalClassCount) != totalPassengers) {
System.out.println("Not enough seats available or total does not match.");
return;
}

List<Passenger> passengers = new ArrayList<>();

// Collect passenger details for first class
for (int i = 0; i < firstClassCount; i++) {
System.out.println("Entering details for first class passenger " + (i + 1) + ":");
passengers.add(getPassengerDetails(scanner));
}

// Collect passenger details for normal class
for (int i = 0; i < normalClassCount; i++) {
System.out.println("Entering details for normal class passenger " + (i + 1) + ":");
passengers.add(getPassengerDetails(scanner));
}

String bookingReference = "REF" + bookingCounter.getAndIncrement();
Booking booking = new Booking(selectedFlight, passengers, bookingReference);
selectedFlight.setAvailable(false); // Mark flight as booked

// Calculate total payment
double totalAmount = (firstClassCount * selectedFlight.getFirstclassPrice()) +
(normalClassCount * selectedFlight.getNormalclassPrice());

// Process payment
System.out.print("Total amount due: " + totalAmount + ". Enter amount to pay: ");
double amountPaid = scanner.nextDouble();
scanner.nextLine();
booking.processPayment(amountPaid);

bookings.add(booking);
System.out.println("Booking confirmed: " + booking);
sendNotification("Booking confirmed successfully for " + passengers.size() + " passenger(s).");
} else {
System.out.println("Flight not found or already booked.");
}
}

private static Passenger getPassengerDetails(Scanner scanner) {
System.out.print("Enter your name: ");
String name = scanner.nextLine();
System.out.print("Enter your gender (female/male): ");
String gender = scanner.nextLine();
System.out.print("Enter your date of birth (YYYY-MM-DD): ");
String dob = scanner.nextLine();
System.out.print("Enter your email: ");
String email = scanner.nextLine();

System.out.print("Enter your phone number: ");
String phoneNumber = scanner.nextLine();

return new Passenger(name, gender, dob, email, phoneNumber);
}

private static void checkIn(Scanner scanner) {
System.out.print("Enter booking reference: ");
String reference = scanner.nextLine();

Booking booking = bookings.stream()
.filter(b -> b.toString().contains(reference))
.findFirst()
.orElse(null);

if (booking != null) {
System.out.println("Check-In successful for: " + booking);
sendNotification("Check-In successful for booking reference: " + reference);
} else {
System.out.println("Booking not found.");
}
}

private static void addFlight(Scanner scanner) {
System.out.print("Enter flight number: ");
String flightNumber = scanner.nextLine();
System.out.print("Enter origin: ");
String origin = scanner.nextLine();
System.out.print("Enter destination: ");
String destination = scanner.nextLine();
System.out.print("Enter departure time (YYYY-MM-DD HH:MM): ");
String departureTime = scanner.nextLine();
System.out.print("Enter total seats: ");
int totalSeats = scanner.nextInt();
System.out.print("Enter first class seats: ");
int firstclassSeat = scanner.nextInt();
System.out.print("Enter normal class seats: ");
int normalclassSeat = scanner.nextInt();
System.out.print("Enter first class price: ");
double firstclassPrice = scanner.nextDouble();
System.out.print("Enter normal class price: ");
double normalclassPrice = scanner.nextDouble();
scanner.nextLine(); // Consume newline

Flight newFlight = new Flight(flightNumber, origin, destination, departureTime, totalSeats, firstclassSeat, normalclassSeat, firstclassPrice, normalclassPrice);
flights.add(newFlight);
System.out.println("Flight added successfully: " + newFlight);
sendNotification("New flight added: " + flightNumber);
}

private static void updateFlight(Scanner scanner) {
System.out.print("Enter flight number to update: ");
String flightNumber = scanner.nextLine();

Flight flightToUpdate = flights.stream()
.filter(flight -> flight.getFlightNumber().equalsIgnoreCase(flightNumber))
.findFirst()
.orElse(null);

if (flightToUpdate != null) {
System.out.print("Enter new origin (current: " + flightToUpdate.getOrigin() + "): ");
String  newOrigin = scanner.nextLine();
flightToUpdate.setOrigin(newOrigin);
System.out.print("Enter new destination (current: " + flightToUpdate.getDestination() + "): ");
String newDestination = scanner.nextLine();
flightToUpdate.setDestination(newDestination);
System.out.print("Enter new departure time (current: " + flightToUpdate.getDepartureTime() + "): ");
String newDepartureTime = scanner.nextLine();
flightToUpdate.setDepartureTime(newDepartureTime);

flightToUpdate.setAvailable(false); // Mark as unavailable for update
System.out.println("Flight updated successfully: " + flightToUpdate);
sendNotification("Flight updated: " + flightNumber);
} else {
System.out.println("Flight not found.");
}
}

private static void deleteFlight(Scanner scanner) {
System.out.print("Enter flight number to delete: ");
String flightNumber = scanner.nextLine();

Flight flightToDelete = flights.stream()
.filter(flight -> flight.getFlightNumber().equalsIgnoreCase(flightNumber))
.findFirst()
.orElse(null);

if (flightToDelete != null) {
flights.remove(flightToDelete);
System.out.println("Flight deleted successfully.");
sendNotification("Flight deleted: " + flightNumber);
} else {
System.out.println("Flight not found.");
}
}

private static void viewAllFlights() {
System.out.println("All Flights:");
for (Flight flight : flights) {
System.out.println(flight);
}
}

private static void managePassengerProfiles(Scanner scanner) {
while (true) {
System.out.println("\n1. Add Passenger Profile");
System.out.println("2. View Passenger Profiles");
System.out.println("3. Update Passenger Profile");
System.out.println("4. Delete Passenger Profile");
System.out.println("5. Back to Main Menu");
System.out.print("Choose an option: ");
int choice = scanner.nextInt();
scanner.nextLine();
switch (choice) {
case 1:
addPassengerProfile(scanner);
break;
case 2:
viewPassengerProfiles();
break;
case 3:
updatePassengerProfile(scanner);
break;
case 4:
deletePassengerProfile(scanner);
break;
case 5:
return;
default:
System.out.println("Invalid option. Please try again.");
}
}
}

private static void addPassengerProfile(Scanner scanner) {
System.out.print("Enter name: ");
String name = scanner.nextLine();
System.out.print("Enter gender (female/male): ");
String gender = scanner.nextLine();
System.out.print("Enter date of birth (YYYY-MM-DD): ");
String dob = scanner.nextLine();
System.out.print("Enter email: ");
String email = scanner.nextLine();
System.out.print("Enter phone number: ");
String phoneNumber = scanner.nextLine();

Passenger passenger = new Passenger(name, gender, dob, email, phoneNumber);
passengers.add(passenger);
System.out.println("Passenger profile added successfully.");
sendNotification("New passenger profile added: " + name);
}

private static void viewPassengerProfiles() {
System.out.println("Passenger Profiles:");
for (Passenger passenger : passengers) {
System.out.println(passenger);
}
}

private static void updatePassengerProfile(Scanner scanner) {
System.out.print("Enter name of passenger to update: ");
String name = scanner.nextLine();

Passenger passengerToUpdate = passengers.stream()
.filter(p -> p.getName().equalsIgnoreCase(name))
.findFirst()
.orElse(null);
if (passengerToUpdate != null) {
System.out.print("Enter new name: ");
String newName = scanner.nextLine();
System.out.print("Enter new gender: ");
String newGender = scanner.nextLine();
System.out.print("Enter new DOB: ");
String newDob = scanner.nextLine();
System.out.print("Enter new email: ");
String newEmail = scanner.nextLine();
System.out.print("Enter new phone number: ");
String newPhoneNumber = scanner.nextLine();

passengers.remove(passengerToUpdate);
Passenger updatedPassenger = new Passenger(newName, newGender, newDob, newEmail, newPhoneNumber);
passengers.add(updatedPassenger);
System.out.println("Passenger profile updated successfully.");
sendNotification("Passenger profile updated: " + newName);
} else {
System.out.println("Passenger not found.");
}
}

private static void deletePassengerProfile(Scanner scanner) {
System.out.print("Enter name of passenger to delete: ");
String name = scanner.nextLine();
Passenger passengerToDelete = passengers.stream()
.filter(p -> p.getName().equalsIgnoreCase(name))
.findFirst()
.orElse(null);

if (passengerToDelete != null) {
passengers.remove(passengerToDelete);
System.out.println("Passenger profile deleted successfully.");
sendNotification("Passenger profile deleted: " + name);
} else {
System.out.println("Passenger not found.");
}
}
private static void generateReports(Scanner scanner) {
System.out.println("Select report type:");
System.out.println("1. Booking Report");
System.out.println("2. Flight Report");
System.out.print("Choose an option: ");
int reportChoice = scanner.nextInt();
scanner.nextLine();
switch (reportChoice) {
case 1:
Report.generateBookingReport(bookings);
break;
case 2:
Report.generateFlightReport(flights, bookings);
break;
default:
System.out.println("Invalid option. Please try again.");
}
}

private static void adminDashboard(Scanner scanner) {
while (true) {
System.out.println("\nAdmin Dashboard");
System.out.println("1. View Flight Statistics");
System.out.println("2. View Booking Statistics");
System.out.println("3. Back to Main Menu");
System.out.print("Choose an option: ");
int choice = scanner.nextInt();
scanner.nextLine();

switch (choice) {
case 1:
Report.generateFlightReport(flights, bookings);
break;
case 2:
Report.generateBookingReport(bookings);
break;
case 3:
return;
default:
System.out.println("Invalid option. Please try again.");
}
}
}

private static void fileComplaint(Scanner scanner) {
System.out.print("Enter your booking reference: ");
String bookingReference = scanner.nextLine();
System.out.print("Describe your complaint: ");
String description = scanner.nextLine();

Complaint complaint = new Complaint(bookingReference, description);
complaints.add(complaint);
System.out.println("Complaint filed successfully: " + complaint);
}
private static void sendNotification(String message) {
System.out.println("** NOTIFICATION **: " + message);
}
}
