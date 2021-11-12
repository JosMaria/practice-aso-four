package com.genesiscode.practiceasofour.models.formulas;

import java.util.HashMap;
import java.util.Map;

public class DistributionX2 {

    private static DistributionX2 distributionX2;

    private Map<KeyDistributionX2, Double> map;

    private DistributionX2() {
        map = new HashMap<>();
        loadData();
    }

    public static synchronized DistributionX2 getInstance() {
        if (distributionX2 == null) {
            distributionX2 = new DistributionX2();
        }
        return distributionX2;
    }

    private void loadData() {
        //load data to map
        map.put(new KeyDistributionX2(0.10, 3), 6.251);
        map.put(new KeyDistributionX2(0.05, 3), 7.815);
        map.put(new KeyDistributionX2(0.025, 3), 9.348);
        map.put(new KeyDistributionX2(0.01, 3), 11.345);
        map.put(new KeyDistributionX2(0.005, 3), 12.838);
        map.put(new KeyDistributionX2(0.001, 3), 16.266);
        // continue
    }

    public double getValue(KeyDistributionX2 key) {
        return map.getOrDefault(key, -1.0);

    }
}
