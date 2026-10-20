package gocycle.vehicle;
/**
 * 
 * ElectricBike: electric bicycle vehicle
 */
public final class ElectricBike extends ElectricVehicle{

    public ElectricBike(String id){
        super(id, 100); //default battery percent = 100
    }
    public ElectricBike(String id, int batteryPercent){
        super(id, batteryPercent);
    }

}
