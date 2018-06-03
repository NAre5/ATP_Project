package algorithms.search;

import algorithms.mazeGenerators.IMazeGenerator;

public class SearchAlgorithmFactory {
    private SearchAlgorithmFactory(){}
    public static ISearchingAlgorithm createAlgorithm(String className) {
        try{
            return (ISearchingAlgorithm)Class.forName("algorithms.search." + className).getConstructor().newInstance();
        }catch (Exception e)
        {
            throw new IllegalArgumentException("Illegal className: should be class that implements ISearchingAlgorithm");
        }
    }

}
