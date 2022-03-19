package com.github.coderodde.generallca.impl;

import com.github.coderodde.generallca.GeneralTree;
import com.github.coderodde.generallca.GeneralTreeNode;
import com.github.coderodde.generallca.LCAComputer;
import java.util.Arrays;

/**
 *
 * @author rodde
 */
public final class LCAComputerV2 implements LCAComputer {

    @Override
    public GeneralTreeNode computeLowestCommonAncestor(GeneralTree tree,
                                                       String... nodeNames) {
        GeneralTreeNode[] nodes = new GeneralTreeNode[nodeNames.length];
        
        for (int i = 0; i < nodes.length; ++i) {
            nodes[i] = tree.getNode(nodeNames[i]);
        }
        
        Arrays.sort(
                nodes,
                (n1, n2) -> { 
                    return Integer.compare(n1.getDepth(), n2.getDepth());
                });
        
        int minimumDepth = nodes[0].getDepth();
        
        for (int i = 1; i < nodes.length; ++i) {
            while (nodes[i].getDepth() > minimumDepth) {
                nodes[i] = nodes[i].getParent();
            }
        }
        
        // Here, nodes points to the visited nodes at the minimum depth:
        while (!visitingOnlyOneNode(nodes)) {
            for (int i = 0; i < nodes.length; ++i) {
                nodes[i] = nodes[i].getParent();
            }
        }
        
        return nodes[0];
    }
    
    private boolean visitingOnlyOneNode(GeneralTreeNode[] nodes) {
        for (int i = 0; i < nodes.length - 1; ++i) {
            if (nodes[i] != nodes[i + 1]) {
                return false;
            }
        }
        
        return true;
    }
}
