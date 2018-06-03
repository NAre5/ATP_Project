package algorithms.mazeGenerators;

public class GeneratorFactory {
    private GeneratorFactory() {
    }

    public static IMazeGenerator createGenerator(String className) {
        try{
            return (IMazeGenerator)Class.forName("algorithms.mazeGenerators." + className).getConstructor().newInstance();
        }catch (Exception e)
        {
            throw new IllegalArgumentException("Illegal className: should be class that implements IMazeGenerator");
        }
    }

}
