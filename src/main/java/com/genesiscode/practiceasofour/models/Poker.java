package com.genesiscode.practiceasofour.models;

import com.genesiscode.practiceasofour.models.formulas.DistributionX2;
import com.genesiscode.practiceasofour.models.formulas.KeyDistributionX2;
import com.genesiscode.practiceasofour.models.utils.Category;
import com.genesiscode.practiceasofour.models.utils.Decimal;
import com.genesiscode.practiceasofour.models.utils.PokerElement;
import com.genesiscode.practiceasofour.models.utils.PokerUtils;
import com.genesiscode.practiceasofour.views.panels.rows.RowPoker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Poker {
    //inputs of the user
    private int degreesOfFreedom;
    private int levelAcceptance;
    private final List<PokerElement> pokerElements;

    private static final int FIRST_ELEMENT = 0;
    private final DistributionX2 distributionX2;
    private double resultFinal;

    public Poker() {
        pokerElements = new ArrayList<>();
        distributionX2 = DistributionX2.getInstance();
    }

    public void addPokerElement(PokerElement element) {
        pokerElements.add(element);
    }

    public PokerElement getFirstElement() {
        return pokerElements.get(FIRST_ELEMENT);
    }
    public double getLevelAcceptanceInDecimals() {
        return (double) levelAcceptance / 100;
    }
    public void setLevelAcceptance(int levelAcceptance) {
        this.levelAcceptance = levelAcceptance;
    }
    public void setDegreesOfFreedom(int degreesOfFreedom) {
        this.degreesOfFreedom = degreesOfFreedom;
    }
    public int getDegreesOfFreedom() {
        return degreesOfFreedom;
    }
    public double getResultFinal() {
        return resultFinal;
    }

    public void clear() {
        pokerElements.clear();
    }

    public ObservableList<RowPoker> getTableResult() {
        ObservableList<RowPoker> rows = FXCollections.observableArrayList();
        int countDecimals = PokerUtils.getPartDecimal(getFirstElement().getTextNumber()).length();
        resultFinal = 0;
        for (Category category : getAllCategories()) {
            double columnTwo = PokerUtils.getItsProbability(category, countDecimals);
            int columnThree = getFrequencyOfTheCategory(category);
            double fe = columnTwo * pokerElements.size();
            double feShorted = Decimal.getDecimal(3, fe);
            String columnFour = String.format("%s*%s=%s", columnTwo, pokerElements.size(), feShorted);

            double result = Math.pow(fe - columnThree, 2) / fe;
            double columnFive = Decimal.getDecimal(5, result);
            resultFinal += columnFive;
            rows.add(new RowPoker(category.getDescription(), columnTwo, columnThree, columnFour, columnFive));
        }
        return rows;
    }

    public int getFrequencyOfTheCategory(Category category) {
        return (int) pokerElements.stream()
                .filter(pokerElement -> pokerElement.getCategory() == category)
                .count();
    }

    public List<Category> getAllCategories() {
        Category[] values = Category.values();
        return Arrays.stream(values).collect(Collectors.toList());
    }

    public double getResultAlpha() {
        return distributionX2.getValue(new KeyDistributionX2
                (getLevelAcceptanceInDecimals(), getDegreesOfFreedom()));
    }
}
