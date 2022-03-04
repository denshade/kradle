package info.thelaboflieven.kradle.jardependencies;

public class JarDependencyOptions {
    private boolean parallel = true;
    private boolean overwriteIfFileExist = true;

    public boolean isParallel() {
        return parallel;
    }

    public JarDependencyOptions setParallel(boolean parallel) {
        this.parallel = parallel;
        return this;
    }

    public boolean isOverwriteIfFileExist() {
        return overwriteIfFileExist;
    }

    public JarDependencyOptions setOverwriteIfFileExist(boolean overwriteIfFileExist) {
        this.overwriteIfFileExist = overwriteIfFileExist;
        return this;
    }
}
