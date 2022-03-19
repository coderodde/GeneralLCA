package com.github.coderodde.generallca;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LCAComputerTest {

    final LCAComputer lcaComputer;
    
    public LCAComputerTest(LCAComputer lcaComputer) {
        this.lcaComputer = lcaComputer;
    }
    
    @Test
    public void testComputeLowestCommonAncestor() {
        GeneralTree tree = new GeneralTree();
        
        tree.addRootNode("A");
        
        tree.addNode("B", "A");
        tree.addNode("C", "A");
        
        tree.addNode("D", "B");
        tree.addNode("E", "B");
        
        tree.addNode("F", "C");
        tree.addNode("G", "C");
        
        tree.addNode("H", "G");
        tree.addNode("I", "G");
        tree.addNode("J", "G");
        
        //                  A
        //                 / \
        //                /   \
        //               B     C
        //              / \   / \
        //             D   E F   G
        //                      /|\
        //                     H I J
        
        assertEquals(tree.getNode("A"), 
                     lcaComputer.computeLowestCommonAncestor(tree, "D", "H"));
        
        assertEquals(tree.getNode("G"), 
                     lcaComputer.computeLowestCommonAncestor(tree,
                                                             "I",
                                                             "H", 
                                                             "J"));
        
        assertEquals(tree.getNode("C"), 
                     lcaComputer.computeLowestCommonAncestor(tree, "F", "G"));
        
        assertEquals(tree.getNode("A"), 
                     lcaComputer.computeLowestCommonAncestor(
                             tree,
                             "D", "H", "G", "F"));
    }    
}
