package gocycle.vehicle;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

/** Registry of polymorphic creators; each creator implements the Factory Method. */
public final class VehicleFactoryImpl implements VehicleFactory {
    private final Map<VehicleType, VehicleCreator> creators = new EnumMap<>(VehicleType.class);

    public VehicleFactoryImpl() {
        creators.put(VehicleType.PEDAL_BIKE, new PedalBikeCreator());
        creators.put(VehicleType.E_BIKE, new ElectricBikeCreator());
        creators.put(VehicleType.E_SCOOTER, new ElectricScooterCreator());
    }

    @Override public Vehicle create(VehicleType type, String id) {
        VehicleCreator creator = creators.get(Objects.requireNonNull(type, "type"));
        if (creator == null) throw new IllegalArgumentException("Unsupported vehicle type: " + type);
        return creator.create(id);
    }
}
