// Vehicle.java
class Vehicle {
    private String licensePlate;
    private String ownerName;
    private int hoursParked;

    // Constructor
    public Vehicle(String licensePlate, String ownerName, int hoursParked) {
        this.licensePlate = licensePlate;
        this.ownerName = ownerName;
        this.hoursParked = hoursParked;
    }

    // Getter methods
    public String getLicensePlate() {
        return licensePlate;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public int getHoursParked() {
        return hoursParked;
    }

    // Setter methods (if needed, though not explicitly required by the problem for all fields)
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setHoursParked(int hoursParked) {
        this.hoursParked = hoursParked;
    }

    @Override
    public String toString() {
        return "License: " + licensePlate + ", Owner: " + ownerName + ", Hours: " + hoursParked;
    }
}

// ParkingLot.java
class ParkingLot {
    private Vehicle[] vehicles;
    private int currentVehicles; // Tracks how many vehicles are currently parked
    private final int MAX_CAPACITY = 5;

    // Constructor
    public ParkingLot() {
        vehicles = new Vehicle[MAX_CAPACITY];
        currentVehicles = 0;
    }

    // Method to park a new vehicle
    public boolean parkVehicle(Vehicle newVehicle) {
        if (currentVehicles < MAX_CAPACITY) {
            vehicles[currentVehicles] = newVehicle;
            currentVehicles++;
            System.out.println("Vehicle " + newVehicle.getLicensePlate() + " parked successfully.");
            return true;
        } else {
            System.out.println("Parking lot is full. Cannot park vehicle " + newVehicle.getLicensePlate());
            return false;
        }
    }

    // Method to remove a vehicle by license plate
    public boolean removeVehicle(String licensePlateToRemove) {
        for (int i = 0; i < currentVehicles; i++) {
            if (vehicles[i].getLicensePlate().equals(licensePlateToRemove)) {
                // Shift remaining vehicles to avoid gaps
                for (int j = i; j < currentVehicles - 1; j++) {
                    vehicles[j] = vehicles[j + 1];
                }
                vehicles[currentVehicles - 1] = null; // Free up the last position
                currentVehicles--;
                System.out.println("Vehicle " + licensePlateToRemove + " removed successfully.");
                return true;
            }
        }
        System.out.println("Vehicle with license plate " + licensePlateToRemove + " not found.");
        return false;
    }

    // Method to display details of all parked vehicles
    public void displayAllParkedVehicles() {
        if (currentVehicles == 0) {
            System.out.println("Parking lot is empty.");
            return;
        }
        System.out.println("\n--- Parked Vehicles ---");
        for (int i = 0; i < currentVehicles; i++) {
            System.out.println(vehicles[i]);
        }
        System.out.println("-----------------------\n");
    }
}

// Main.java (or you can put the main method directly in ParkingLot.java for simplicity in a single file)
class Main {
    public static void main(String[] args) {
        // 4. In your main method:
        // Create a ParkingLot instance
        ParkingLot myParkingLot = new ParkingLot();

        // Park these vehicles:
        System.out.println("--- Parking Vehicles ---");
        myParkingLot.parkVehicle(new Vehicle("ABC123", "John Doe", 2));
        myParkingLot.parkVehicle(new Vehicle("XYZ789", "Jane Smith", 4));
        myParkingLot.parkVehicle(new Vehicle("LMN456", "Bob Brown", 1));

        // Display all parked vehicles.
        myParkingLot.displayAllParkedVehicles();

        // Remove the vehicle with license "XYZ789".
        System.out.println("--- Removing Vehicle ---");
        myParkingLot.removeVehicle("XYZ789");

        // Display all parked vehicles.
        myParkingLot.displayAllParkedVehicles();

        // Test parking when full (optional, to demonstrate max capacity)
        System.out.println("--- Testing Full Parking ---");
        myParkingLot.parkVehicle(new Vehicle("PQR000", "Alice White", 3));
        myParkingLot.parkVehicle(new Vehicle("STU111", "Charlie Black", 5));
        myParkingLot.parkVehicle(new Vehicle("VWX222", "David Green", 2)); // This one should fail

        myParkingLot.displayAllParkedVehicles();

        // Test removing a non-existent vehicle
        System.out.println("--- Testing Remove Non-Existent ---");
        myParkingLot.removeVehicle("NONEXISTENT");
    }
}