package com.github.coderodde.generallca;

import com.github.coderodde.generallca.impl.LCAComputerV1;
import com.github.coderodde.generallca.impl.LCAComputerV2;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class BenchmarkApp {
    
    private static final int NUMBER_OF_TREE_NODES = 1000_000;
    private static final int NUMBER_OF_QUERIES = 100_000;
    private static final int MAXIMUM_QUERY_LENGTH = 50;
    
    private final long seed;
    private final GeneralTree tree = new GeneralTree();
    private final LCAComputer lcaComputerV1 = new LCAComputerV1();
    private final LCAComputer lcaComputerV2 = new LCAComputerV2();
    
    public BenchmarkApp(long seed) {
        this.seed = seed;
        buildTree();
    }
    
    public void warmup() {
        doRun(false);
    }
    
    public void benchmark() {
        doRun(true);
    }
    
    private void doRun(boolean print) {
        Random random = new Random(seed);
        List<GeneralTreeNode> result1 = new ArrayList<>(NUMBER_OF_QUERIES);
        List<GeneralTreeNode> result2 = new ArrayList<>(NUMBER_OF_QUERIES);
        String[][] queries = getQueries();
        
        long startTime = System.currentTimeMillis();
        
        for (int i = 0; i < NUMBER_OF_QUERIES; ++i) {
            result1.add(
                    lcaComputerV1.computeLowestCommonAncestor(
                            tree,
                            queries[i]));
        }
        
        long endTime = System.currentTimeMillis();
        
        if (print) {
            long duration = endTime - startTime;
            
            System.out.println(
                    lcaComputerV1.getClass().getSimpleName() 
                            + " in " 
                            + duration 
                            + " milliseconds.");
        }
        
        startTime = System.currentTimeMillis();
        
        for (int i = 0; i < NUMBER_OF_QUERIES; ++i) {
            result2.add(
                    lcaComputerV2.computeLowestCommonAncestor(
                            tree,
                            queries[i]));
        }
        
        endTime = System.currentTimeMillis();
        
        if (print) {
            long duration = endTime - startTime;
            
            System.out.println(
                    lcaComputerV2.getClass().getSimpleName() 
                            + " in " 
                            + duration 
                            + " milliseconds.");
            
            System.out.println("Algorithms agree: " + result1.equals(result2));
        }
    }
    
    private void buildTree() {
        Random random = new Random(this.seed);
        List<GeneralTreeNode> createdNodeList = new ArrayList<>();
        tree.addRootNode("Root");
        GeneralTreeNode root = tree.getNode("Root");
        createdNodeList.add(root);
        
        for (int i = 1; i < NUMBER_OF_TREE_NODES; ++i) {
            String childName = Integer.toString(i);
            int index = random.nextInt(createdNodeList.size());
            GeneralTreeNode parentNode = createdNodeList.get(index);
            tree.addNode(childName, parentNode.getName());
            createdNodeList.add(tree.getNode(childName));
        }
    }
    
    private String[][] getQueries() {
        String[][] queries = new String[NUMBER_OF_QUERIES][];
        
        for (int i = 0; i < NUMBER_OF_QUERIES; ++i) {
            queries[i] = generateQuery();
        }
        
        return queries;
    }
    
    private String[] generateQuery() {
        Random random = new Random(this.seed);
        int queryLength = random.nextInt(MAXIMUM_QUERY_LENGTH) + 1;
        String[] query = new String[queryLength];
        
        for (int i = 0; i < query.length; ++i) {
            query[i] = Integer.toString(random.nextInt(NUMBER_OF_TREE_NODES));
        }
        
        return query;
    }
}
