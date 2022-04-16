package it.futurecraft.futureapi.util;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

/**
 * Represents the protocol version the server is running on.
 * <p>Can be used only in Bukkit.</p>
 */
public enum ProtocolVersion {
    v1_18_2(758, "1.18.2"),
    v1_18_1(757, "1.18.1"),
    v1_18(757, "1.18"),
    v1_17_1(756, "1.17.1"),
    v1_17(755, "1.17"),
    v1_16_5(754, "1.16.5"),
    v1_16_4(754, "1.16.4"),
    v1_16_3(753, "1.16.3"),
    v1_16_2(751, "1.16.2"),
    v1_16_1(736, "1.16.1"),
    v1_16(735, "1.16"),
    v1_15_2(578, "1.15.2"),
    v1_15_1(575, "1.15.1"),
    v1_15(573, "1.15"),
    v1_14_4(498, "1.14.4"),
    v1_14_3(490, "1.14.3"),
    v1_14_2(485, "1.14.2"),
    v1_14_1(480, "1.14.1"),
    v1_14(477, "1.14"),
    v1_13_2(404, "1.13.2"),
    v1_13_1(401, "1.13.1"),
    v1_13(393, "1.13"),
    v1_12_2(340, "1.12.2"),
    v1_12_1(338, "1.12.1"),
    v1_12(335, "1.12"),
    v1_11_2(316, "1.11.2"),
    v1_11_1(315, "1.11.1"),
    v1_11(315, "1.11"),
    v1_10_2(210, "1.10.2"),
    v1_10_1(210, "1.10.1"),
    v1_10(210, "1.10"),
    v1_9_4(110, "1.9.4"),
    v1_9_2(109, "1.9.2"),
    v1_9_1(108, "1.9.1"),
    v1_9(107, "1.9"),
    v1_8_9(47, "1.8.9"),
    v1_8_8(47, "1.8.8"),
    v1_8_7(47, "1.8.7"),
    v1_8_6(47, "1.8.6"),
    v1_8_5(47, "1.8.5"),
    v1_8_4(47, "1.8.4"),
    v1_8_3(47, "1.8.3"),
    v1_8_2(47, "1.8.2"),
    v1_8_1(47, "1.8.1"),
    v1_8(47, "1.8"),
    UNKOWN(-1, "UNKOWN");

    /**
     * The id of the version.
     */
    private final int id;

    /**
     * The name of the version.
     */
    private final String name;

    /**
     * The protocol version.
     *
     * @param id   The id of the version.
     * @param name The name of the version.
     */
    ProtocolVersion(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Get the protocol version from the server instance.
     *
     * @return The protocol version of the server.
     */
    public static ProtocolVersion getCurrentVersion() {
        Class<?> server = Bukkit.getServer().getClass();
        String packageName = server.getPackage().getName();

        String version = packageName
                .substring(packageName.lastIndexOf('.') + 1)
                .replace("_", "");

        try {
            return valueOf(version);
        } catch (IllegalArgumentException e) {
            return UNKOWN;
        }
    }

    /**
     * Gets the id of the version.
     *
     * @return The id of the version.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the name of the version.
     *
     * @return The name of the version.
     */
    public String getName() {
        return name;
    }

    /**
     * Check whether the version is newer than the other.
     *
     * @param version The version to compare to.
     * @return {@code true} if the version is newer than the other, {@code false} otherwise.
     */
    public boolean isNewer(@NotNull ProtocolVersion version) {
        return this.id > version.id;
    }

    /**
     * Check whether the version is older than the other.
     *
     * @param version The version to compare to.
     * @return {@code true} if the version is older than the other, {@code false} otherwise.
     */
    public boolean isOlder(@NotNull ProtocolVersion version) {
        return this.id < version.id;
    }

    /**
     * Check whether the version is the same as the other.
     *
     * @param version The version to compare to.
     * @return {@code true} if the version is the same as the other, {@code false} otherwise.
     */
    public boolean isSame(@NotNull ProtocolVersion version) {
        return this.id == version.id;
    }

    /**
     * Check whether the version is newer or same as the other.
     *
     * @param version The version to compare to.
     * @return {@code true} if the version is newer or same as the other, {@code false} otherwise.
     */
    public boolean isNewerOrSame(@NotNull ProtocolVersion version) {
        return this.id >= version.id;
    }

    /**
     * Check whether the version is older or same as the other.
     *
     * @param version The version to compare to.
     * @return {@code true} if the version is older or same as the other, {@code false} otherwise.
     */
    public boolean isOlderOrSame(@NotNull ProtocolVersion version) {
        return this.id <= version.id;
    }
}
