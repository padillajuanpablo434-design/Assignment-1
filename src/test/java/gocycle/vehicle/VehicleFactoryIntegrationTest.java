package gocycle.vehicle;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VehicleFactoryIntegrationTest {
    @Test void factoryCreatesAllSupportedTypes() {
        VehicleFactory factory = new VehicleFactoryImpl();
        assertInstanceOf(PedalBike.class, factory.create(VehicleType.PEDAL_BIKE, "P1"));
        assertInstanceOf(ElectricBike.class, factory.create(VehicleType.E_BIKE, "E1"));
        assertInstanceOf(ElectricScooter.class, factory.create(VehicleType.E_SCOOTER, "S1"));
    }
    @Test void invalidBatteryOnConstructionIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> new ElectricBike("E1", -1));
        assertThrows(IllegalArgumentException.class, () -> new ElectricScooter("S1", 101));
    }
    @Test void lowBatteryCannotBeRented() {
        assertFalse(new ElectricBike("E1", 19).isRentable());
        assertTrue(new ElectricBike("E2", 20).isRentable());
    }
}
