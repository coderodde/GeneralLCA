package com.github.coderodde.generallca;

/**
 * This interface specifies the API for methods computing multiple node LCAs.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Mar 19, 2022)
 */
public interface LCAComputer {
    
    GeneralTreeNode computeLowestCommonAncestor(GeneralTree tree,
                                                String... nodeNames);
}
