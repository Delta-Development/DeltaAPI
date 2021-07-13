package club.deltapvp.deltacore.api.utilities.version;

public enum ServerVersion {

    V1_17,
    V1_16,
    V1_15,
    V1_14,
    V1_13,
    V1_12,
    V1_11,
    V1_10,
    V1_9,
    V1_8,
    V1_7;

    public static ServerVersion fromServerPackageName(String packageName) {
        packageName = packageName.split("\\.")[3];
        packageName = packageName.substring(0, packageName.length() - 3); // removes the _R1 for example
        try {
            return valueOf(packageName.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Unknown version: " + packageName);
            return null;
        }
    }

    public boolean isAtLeast(ServerVersion version) {
        return version != null && ordinal() <= version.ordinal();
    }

    public boolean isAtMost(ServerVersion version) {
        return version != null && ordinal() >= version.ordinal();
    }
}
