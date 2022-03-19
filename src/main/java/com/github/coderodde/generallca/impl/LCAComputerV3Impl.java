package com.github.coderodde.generallca.impl;

import com.github.coderodde.generallca.GeneralTree;
import com.github.coderodde.generallca.GeneralTreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rodde
 */
public class LCAComputerV3Impl {
 
    private static final class WalkData {
        List<String> labels = new ArrayList<>();
        List<Integer> depths = new ArrayList<>();
        List<Integer> depthDeltas = new ArrayList<>();
    }
    
    private static final class RMQTable {
        
        int[] yValues;
        int[][] blocks;
        
        int get(int i, int j) {
            int blockIndex1 = getBlockIndex(i);
            int blockIndex2 = getBlockIndex(j);
            
            if (blockIndex1 != blockIndex2) {
                return handleCaseDifferentBlocks();
            } else {
                return handleCaseSameBlock();
            }
        }
        
        private int getBlockIndex(int index) {
            int blockSize = blocks[0].length;
            return index / blockSize;
        }
        
        private int handleCaseDifferentBlocks() {
            return -1;
        }
        
        private int handleCaseSameBlock() {
            return -1;
        }
    }
    
    private RMQTable table;
    
    public GeneralTreeNode preprocess(GeneralTree tree) {
        WalkData walkData = getWalkData(tree);
        List<String> labels = walkData.labels;
        List<Integer> depths = walkData.depths;
        
        int b = getB(labels.size());
        this.table = splitIntoBlocks(depths);
        
        throw new UnsupportedOperationException();
    }
    
    public GeneralTreeNode getLowestCommonAncestor(GeneralTreeNode node1,
                                                   GeneralTreeNode node2) {
        return null;
    }
    
    private RMQTable splitIntoBlocks(List<Integer> depthList) {
        int b = getB(depthList.size());
        int numberOfBlocks = depthList.size() / b;
        
        RMQTable table = new RMQTable();
        table.blocks = new int[numberOfBlocks][];
        table.yValues = new int[numberOfBlocks];
        
        for (int i = 0; i < table.blocks.length - 1; ++i) {
            table.blocks[i] = new int[b];
        }
        
        table.blocks[table.blocks.length - 1] = 
                new int[b + table.blocks.length % b];
        
        int blockStartIndex = 0;
        
        for (int i = 0; i < table.yValues.length; ++i, blockStartIndex += b) {
            table.yValues[i] = findBlockMinimum(table.blocks[i],
                                                blockStartIndex);
        }
        
        return table;
    }
    
    private int findBlockMinimum(int[] block, int blockStartIndex) {
        int minIndex = blockStartIndex;
        int min = block[0];
        
        for (int i = 1; i < block.length; ++i) {
            int currentInt = block[i];
            
            if (min > currentInt) {
                min = currentInt;
                minIndex = i;
            }
        }
        
        return minIndex;
    }
    
    private static int getB(int n) {
        return ((int)(Math.floor(Math.log(n) / Math.log(2.0)))) / 2;
    }
    
    private static WalkData getWalkData(GeneralTree tree) {
        WalkData walkData = new WalkData();
        walk(tree.getRoot(), walkData);
        loadDepthDeltas(walkData);
        return walkData;
    }
    
    private static void loadDepthDeltas(WalkData walkData) {
        for (int i = 0; i < walkData.depths.size() - 1; ++i) {
            int depth1 = walkData.depths.get(i);
            int depth2 = walkData.depths.get(i + 1);
            walkData.depthDeltas.add(depth1 < depth2 ? 1 : 0);
        }
    }
    
    private static void walk(GeneralTreeNode node, WalkData walkData) {
        walkData.labels.add(node.getName());
        walkData.depths.add(node.getDepth());
        
        for (GeneralTreeNode child : node.getChildren()) {
            walk(child, walkData);
        }
        
        walkData.labels.add(node.getName());
        walkData.depths.add(node.getDepth());
    }   
}
