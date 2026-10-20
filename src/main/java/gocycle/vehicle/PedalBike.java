package gocycle.vehicle;
/**
 * 
 * PedalBike - non-electrice bikes with pedals
 */
public final class PedalBike extends Vehicle{

    public PedalBike(String id){
        super(id);
    }
    @Override
    public boolean isRentable() {
        return getStatus() == VehicleStatus.AVAILABLE;
    }

}
