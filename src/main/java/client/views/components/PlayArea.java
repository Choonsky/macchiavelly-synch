package client.views.components;

import client.CardEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.FlowPane;
import server.models.CardSet;
import server.models.cards.Card;

import java.util.ArrayList;

public class PlayArea implements EventHandler<CardEvent> {
    private FlowPane setsArea;
    private CardSetView placeholderSet;
    private ArrayList<CardSetView> setViews;

    public PlayArea(FlowPane setsArea) {
        this.setsArea = setsArea;
        setViews = new ArrayList<>();
        createPlaceholderSet();
    }

    public void setActive(boolean active) {
        for (CardSetView setView : setViews) {
            setView.setReceiverMode(active);
        }
    }

    public void setActive(Card card) {
        for (CardSetView setView : setViews) {
            setView.setReceiverMode(card);
        }
    }

    public void addSet(CardSet cardSet) {
        CardSetView cardSetView = new CardSetView(cardSet);
        setsArea.getChildren().add(cardSetView);
    }

    public void removeSet(CardSetView cardSetView) {
        setsArea.getChildren().remove(cardSetView);
    }

    public void addCard(Card card) {
        addSet(new CardSet(card));
    }

    private void createPlaceholderSet() {
        this.placeholderSet = new CardSetView();
        this.setsArea.getChildren().add(placeholderSet);

        placeholderSet.setCardEventHandler(this);

        setViews.add(placeholderSet);
    }

    @Override
    public void handle(CardEvent event) {

        CardSetView changedSetView = event.getParentCardSetView();
        if (event.getEventType() == CardEvent.CARD_ADDED) {
            // If user places a card on the placeholder set, we create a new placeholder and leave the old on the table.
            if (changedSetView == placeholderSet) {
                createPlaceholderSet();
            }
        } else if (event.getEventType() == CardEvent.CARD_REMOVED) {
            // Remove empty set from play area.
            if (changedSetView.isEmpty()) {
                removeSet(changedSetView);
            }
        } else if (event.getEventType() == CardEvent.CARD_SELECTED) {

        }
    }
}