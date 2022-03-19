package com.github.coderodde.generallca.impl;

import com.github.coderodde.generallca.GeneralTree;
import com.github.coderodde.generallca.GeneralTreeNode;
import com.github.coderodde.generallca.LCAComputer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * This class provides a method for computing general lowest common ancestors.
 * For each query node {@code n}, the algorithm computes the path from 
 * {@code n} to the root node. Then, it marches along all the paths upwards 
 * towards the root node incrementing the counts of each visited nodes. The 
 * first node whose counter reaches the number of query nodes, is the LCA.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Mar 18, 2022)
 */
public final class LCAComputerV1 implements LCAComputer {
    
    public GeneralTreeNode computeLowestCommonAncestor(GeneralTree tree,
                                                       String... nodeNames) {
        List<List<GeneralTreeNode>> paths = new ArrayList<>(nodeNames.length);
        
        for (int i = 0; i < nodeNames.length; ++i) {
            GeneralTreeNode node = tree.getNode(nodeNames[i]);
            Objects.requireNonNull(
                    node,
                    "There is no node with name '" + nodeNames[i] + "'.");
            
            paths.add(getPathToRoot(node));
        }
        
        Map<GeneralTreeNode, Integer> visitedMap = new HashMap<>();
        
        for (List<GeneralTreeNode> path : paths) {
            for (GeneralTreeNode node : path) {
                visitedMap.put(node, visitedMap.getOrDefault(node, 0) + 1);
                
                if (visitedMap.get(node) == nodeNames.length) {
                    return node;
                }
            }
        }
        
        throw new IllegalStateException("Should not get here.");
    }
    
    private List<GeneralTreeNode> getPathToRoot(GeneralTreeNode node) {
        List<GeneralTreeNode> path = new ArrayList<>();
        
        while (node != null) {
            path.add(node);
            node = node.getParent();
        }
        
        return path;
    }
}
