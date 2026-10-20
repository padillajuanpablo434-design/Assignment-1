package gocycle.vehicle;
/**
 * 
 * VehicleFactoryImpl: implements Vehicle Factory
 */
public final class VehicleFactoryImpl implements VehicleFactory{
    @Override
    public Vehicle create(VehicleType type, String id) {
        switch (type) {
            case PEDAL_BIKE:
                return new PedalBike(id);
            case E_BIKE:
                return new ElectricBike(id, 100);
            case E_SCOOTER:
                return new ElectricScooter(id, 100);
            default:
                throw new IllegalArgumentException("Unsupported vehicle type: " + type);
        }
    }
}
