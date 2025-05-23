class Vehicle{
	private String licensePlate;
	private String ownerName;
	private int hoursParked;
	
	Vehicle(String licensePlate,String ownerName,int hoursParked){
		this.licensePlate=licensePlate;
		this.ownerName=ownerName;
		this.hoursParked=hoursParked;
	}
	String getLicenseplate(){
		return licensePlate;
	}
	String getOwnerName(){
		return ownerName;
	}
	int getHoursParked(){
		return hoursParked;
	}
}

class ParkingLot{
	private Vehicle[] vehiarray=new Vehicle[5];
	private int vehicount=0;
	
	 void addvehicle(Vehicle vehi){
		if(vehicount < vehiarray.length){
			vehiarray[vehicount]=vehi;
			vehicount++;
		}
		else{
			System.out.println("Parking Lot is full; can't add more vehicles");
		}
	}
	
	void removevehicle(String licensePlate ){
		for(int i=0; i<vehicount;i++){
			if(vehiarray[i].getLicenseplate().equals(licensePlate)){
				for(int j=i;j<vehicount-1;j++){
					vehiarray[j]=vehiarray[j+1];
				}
				vehiarray[vehicount-1]=null;
				vehicount--;
				return;
			}
		}
		System.out.println("Licence Plate "+licensePlate+" is not founded");
	}
	void display(){
		for(int i=0;i<vehicount;i++){
			System.out.println("Licence: "+vehiarray[i].getLicenseplate()+", Owner: "+vehiarray[i].getOwnerName()
			+", Hours: "+vehiarray[i].getHoursParked());
		}
		if (vehicount==0){
			System.out.println("The Parking Lot is empty");
		}
	}
}
 
class parkingprogrm{
	public static void main(String[]args){
		ParkingLot pl=new ParkingLot();
		
		pl.addvehicle(new Vehicle("ABC123","John Doe",2));
		pl.addvehicle(new Vehicle("XYZ789","Jane Smith",4));
		pl.addvehicle(new Vehicle("LMN456","Bob Brown",1));
		
		pl.removevehicle("XYZ789");
		pl.display();
	}
}