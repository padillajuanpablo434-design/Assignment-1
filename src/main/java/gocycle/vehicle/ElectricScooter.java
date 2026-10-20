package gocycle.vehicle;

public final class ElectricScooter extends ElectricVehicle{

    public ElectricScooter(String id){
        super(id, 100);//default battery percent = 100
    }

    public ElectricScooter(String id, int batteryPercent){
        super(id, batteryPercent); 
    }

    @Override
    public boolean isRentable() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isRentable'");
    }
    
}
