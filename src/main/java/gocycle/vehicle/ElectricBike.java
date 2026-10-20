package gocycle.vehicle;

public final class ElectricBike extends ElectricVehicle{

    public ElectricBike(String id){
        super(id, 100); //default battery percent = 100
    }
    public ElectricBike(String id, int batteryPercent){
        super(id, batteryPercent);
    }
    @Override
    public boolean isRentable() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isRentable'");
    }
    
}
