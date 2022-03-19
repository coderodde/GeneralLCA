package com.github.coderodde.generallca;

public final class Demo {
    
    public static void main(String[] args) {
        long seed = System.currentTimeMillis();
        System.out.println("--- Seed = " + seed + " ---");
        
        BenchmarkApp app = new BenchmarkApp(seed);
        
        System.out.println(">>> Warming up...");
        app.warmup();
        
        System.out.println(">>> Warming up done.");
        System.out.println();
        
        System.out.println(">>> Benchmarking...");
        app.benchmark();
        
        System.out.println(">>> Benchmarking done.");
    }
}
