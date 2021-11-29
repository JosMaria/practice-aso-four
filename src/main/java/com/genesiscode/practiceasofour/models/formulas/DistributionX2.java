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
        map.put(new KeyDistributionX2(0.10, 2), 4.605);
        map.put(new KeyDistributionX2(0.05, 2), 5.991);
        map.put(new KeyDistributionX2(0.025, 2), 7.378);
        map.put(new KeyDistributionX2(0.01, 2), 9.210);
        map.put(new KeyDistributionX2(0.005, 2), 10.597);
        map.put(new KeyDistributionX2(0.001, 2), 13.816);

        map.put(new KeyDistributionX2(0.10, 3), 6.251);
        map.put(new KeyDistributionX2(0.05, 3), 7.815);
        map.put(new KeyDistributionX2(0.025, 3), 9.348);
        map.put(new KeyDistributionX2(0.01, 3), 11.345);
        map.put(new KeyDistributionX2(0.005, 3), 12.838);
        map.put(new KeyDistributionX2(0.001, 3), 16.266);

        map.put(new KeyDistributionX2(0.10, 4), 7.779);
        map.put(new KeyDistributionX2(0.05, 4), 9.488);
        map.put(new KeyDistributionX2(0.025, 4), 11.143);
        map.put(new KeyDistributionX2(0.01, 4), 13.277);
        map.put(new KeyDistributionX2(0.005, 4), 14.860);
        map.put(new KeyDistributionX2(0.001, 4), 18.467);

        map.put(new KeyDistributionX2(0.10, 6), 10.645);
        map.put(new KeyDistributionX2(0.05, 6), 12.592);
        map.put(new KeyDistributionX2(0.025, 6), 14.449);
        map.put(new KeyDistributionX2(0.01, 6), 16.812);
        map.put(new KeyDistributionX2(0.005, 6), 18.548);
        map.put(new KeyDistributionX2(0.001, 6), 22.458);
        // continue
    }

    public double getValue(KeyDistributionX2 key) {
        return map.getOrDefault(key, -1.0);

    }
}
