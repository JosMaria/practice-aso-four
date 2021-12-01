package com.genesiscode.practiceasofour.models;

import java.util.ArrayList;
import java.util.List;

public class Gap {

    public List<Integer> getList(int[] arrayGiven) {
        List<Integer> result = new ArrayList<>();
        int counter = 0;

        for (int i = 0; i <= arrayGiven.length; i++) {
            if (i == arrayGiven.length) {
                if (counter != 0) {
                    result.add(counter);
                }
            } else {
                if (arrayGiven[i] == 1) {
                    if (counter != 0) {
                        result.add(counter);
                        counter = 0;
                    } else {
                        if (result.isEmpty()) {
                            if (i - 1 >= 0) {
                                if (arrayGiven[i-1] == 1) {
                                    result.add(0);
                                }
                            }
                        } else {
                            Integer ultElementAdded = result.get(result.size()-1);
                            if (ultElementAdded != 0) {
                                if (arrayGiven[i-1] == 1) {
                                    result.add(0);
                                }
                            }
                        }
                    }
                } else {
                    counter++;
                }
            }
        }
        return result;
    }

}
