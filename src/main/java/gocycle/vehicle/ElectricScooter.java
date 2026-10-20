package gocycle.vehicle;
/**
 * 
 * ElectricScooter: electric scooter vehicle
 */
public final class ElectricScooter extends ElectricVehicle{

    public ElectricScooter(String id){
        super(id, 100);//default battery percent = 100
    }

    public ElectricScooter(String id, int batteryPercent){
        super(id, batteryPercent); 
    }
    
}
