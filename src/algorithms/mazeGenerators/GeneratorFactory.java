package algorithms.mazeGenerators;

public class GeneratorFactory {
    private GeneratorFactory() {
    }

    public static IMazeGenerator createGenerator(String className) {
        switch (className) {
            case "MyMazeGenerator":
                return new MyMazeGenerator();
            case "SimpleMazeGenerator":
                return new SimpleMazeGenerator();
            default:
                throw new IllegalArgumentException("className should be real class name that implements IMazeGenerator");

        }
    }
}
