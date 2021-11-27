package com.genesiscode.practiceasofour.models;

import com.genesiscode.practiceasofour.models.utils.Category;
import com.genesiscode.practiceasofour.models.utils.PokerElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Poker {
    //inputs of the user
    private int degreesOfFreedom;
    private int levelAcceptance;
    private List<PokerElement> pokerElements;

    private static final int FIRST_ELEMENT = 0;

    public Poker() {
        pokerElements = new ArrayList<>();
    }

    public void addPokerElement(PokerElement element) {
        pokerElements.add(element);
    }

    public PokerElement getFirstElement() {
        return pokerElements.get(FIRST_ELEMENT);
    }

    public void clear() {
        pokerElements.forEach(System.out::println);
        pokerElements.clear();
    }

    public void start() {

    }

    public List<Category> getCategories() {
        return pokerElements.stream()
                .map(PokerElement::getCategory)
                .distinct()
                .collect(Collectors.toList());
    }
}
