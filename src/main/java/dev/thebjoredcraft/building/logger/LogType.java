package dev.thebjoredcraft.building.logger;

public enum LogType {
    WARN("[WARN]"),
    ERROR("[ERROR]"),
    INFO("[INFO]"),
    MESSAGE(""),
    OTHER("");

    public final String prefix;
    LogType(String prefix){
     this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }
}
