//package com.github.coderodde.generallca;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Scanner;
//
//public final class Demo {
//    
//    private static final Map<String, GeneralTreeNode> TREE_MAP = new HashMap<>();
//    private static final LCAComputer LCA_COMPUTER = new LCAComputer();
//    
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        
//        while (true) {
//            String cmd = scanner.nextLine().trim();
//            
//            if (cmd.equals("q")) {
//                System.out.println("Bye!");
//                return;
//            }
//            
//            String[] tokens = cmd.split("\\s+");
//            boolean commandProcessed = false;
//            
//            if (tokens.length == 2) {
//                commandProcessed = handleDualTokenCommand(tokens);
//            } else if (tokens.length == 4) {
//                commandProcessed = handleFourTokenCommand(tokens);
//            }
//            
//            if (!commandProcessed) {
//                System.out.println("LCA: "+ handleQuery(tokens));
//            }
//        }
//    }
//    
//    private static String handleQuery(String[] tokens) {
//        GeneralTreeNode[] nodes = new GeneralTreeNode[tokens.length];
//        int index = 0;
//        
//        for (String token : tokens) {
//            nodes[index++] = TREE_MAP.get(token);
//        }
//        
//        return LCA_COMPUTER.computeLowestCommonAncestor(nodes).getName();
//    }
//    
//    private static boolean handleDualTokenCommand(String[] tokens) {
//        if (tokens[0].equals("add")) {
//            GeneralTreeNode treeNode = new GeneralTreeNode(tokens[1]);
//            TREE_MAP.put(tokens[1], treeNode);
//            return true;
//        }
//        
//        return false;
//    }
//    
//    private static boolean handleFourTokenCommand(String[] tokens) {
//        if (tokens[0].equals("connect") && tokens[2].equals("to")) {
//            GeneralTreeNode child = TREE_MAP.get(tokens[1]);
//            GeneralTreeNode parent = TREE_MAP.get(tokens[3]);
//            child.setParent(parent);
//            return true;
//        }
//        
//        return false;
//    }
//}
