package com.github.coderodde.generallca.impl;

import com.github.coderodde.generallca.GeneralTree;
import com.github.coderodde.generallca.GeneralTreeNode;
import com.github.coderodde.generallca.LCAComputer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rodde
 */
public final class LCAComputerV3 implements LCAComputer {

    @Override
    public GeneralTreeNode computeLowestCommonAncestor(
            GeneralTree tree, 
            String... nodeNames) {
        GeneralTreeNode[] nodes = convertNamesToNodes(tree, nodeNames);
        GeneralTreeNode node = nodes[0];
        
        for (int i = 1; i < nodes.length; ++i) {
            
        }
        
    }

    private GeneralTreeNode[] convertNamesToNodes(GeneralTree tree, 
                                                  String... nodeNames) {
        GeneralTreeNode[] nodes = new GeneralTreeNode[nodeNames.length];
        
        for (int i = 0; i < nodeNames.length; ++i) {
            nodes[i] = tree.getNode(nodeNames[i]);
        }
        
        return nodes;
    }
}
