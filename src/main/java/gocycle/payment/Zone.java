package gocycle.payment;

import java.util.Objects;
import java.util.Set;

/** Named payment zone with a configurable whitelist of supported payment types. */
public class Zone {
    private final String id;
    private final String name;
    private final Set<Class<? extends PaymentMethod>> allowedMethods;

    public Zone(String id, String name, Set<Class<? extends PaymentMethod>> allowedMethods) {
        this.id = Objects.requireNonNull(id, "id");
        this.name = Objects.requireNonNull(name, "name");
        this.allowedMethods = Set.copyOf(Objects.requireNonNull(allowedMethods, "allowedMethods"));
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public Set<Class<? extends PaymentMethod>> getAllowedMethods() { return allowedMethods; }

    public boolean allows(PaymentMethod method) {
        if (method == null) return false;
        return allowedMethods.stream().anyMatch(type -> type.isInstance(method));
    }
}
