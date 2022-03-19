package com.github.coderodde.generallca;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author rodde
 */
public final class GeneralTree {
    
    private final Map<String, GeneralTreeNode> treeNodeMap = new HashMap<>();
    private GeneralTreeNode root;
    
    public GeneralTreeNode getNode(String name) {
        return treeNodeMap.get(name);
    }
    
    public void addRootNode(String name) {
        Objects.requireNonNull(name, "The node name is null.");
        
        if (treeNodeMap.containsKey(name)) {
            throw new IllegalArgumentException(
                    "The node named '" + name + "' is already in this tree.");
        }
        
        root = new GeneralTreeNode(name, 0, null);
        treeNodeMap.put(name, root);
    }
    
    public void addNode(String childNodeName, String parentNodeName) {
        GeneralTreeNode parentNode = treeNodeMap.get(parentNodeName);
        Objects.requireNonNull(parentNode, "The parent node is null.");
        
        GeneralTreeNode childNode = 
                new GeneralTreeNode(
                        childNodeName, 
                        parentNode.getDepth() + 1, 
                        parentNode);
        
        treeNodeMap.put(childNodeName, childNode);
    }
    
    public GeneralTreeNode getRoot() {
        return root;
    }
}
