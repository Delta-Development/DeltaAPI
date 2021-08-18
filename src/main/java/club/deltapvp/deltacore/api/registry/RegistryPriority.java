package club.deltapvp.deltacore.api.registry;

import lombok.Getter;

public enum RegistryPriority {
    HIGHEST(1), // First to register
    HIGH(2),
    NORMAL(3),
    LOW(4),
    LOWEST(5); // Last to register

    @Getter
    private final int priority;

    RegistryPriority(int priority) {
        this.priority = priority;
    }
}
