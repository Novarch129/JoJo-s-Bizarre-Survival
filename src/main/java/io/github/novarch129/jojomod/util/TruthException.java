package io.github.novarch129.jojomod.util;

import net.minecraft.crash.CrashReport;

public class TruthException extends RuntimeException {
    private final CrashReport crashReport;

    public TruthException(CrashReport crashReport) {
        this.crashReport = crashReport;
    }

    public CrashReport getCrashReport() {
        return this.crashReport;
    }

    public Throwable getCause() {
        return this.crashReport.getCrashCause();
    }

    public String getMessage() {
        return this.crashReport.getDescription();
    }
}
