package client.views.components;

import client.ViewHelper;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import server.models.cards.Card;

public class CardView extends ImageView implements Comparable<CardView> {
    /**
     * PUBLIC STATIC FINALS
     */
    public static final int CARD_PREF_WIDTH = 120;
    public static final int CARD_PREF_HEIGHT = 140;

    /**
     * PRIVATES
     */
    private CardSetView parentSet;
    private Card card;
    private boolean selected = false;
    private boolean valid = true;
    private boolean newcomer = false;

    /**
     * CONSTRUCTOR
     *
     * @param card
     */
    public CardView(Card card) {
        super();
        Image image = ViewHelper.getImage(card.getImgUrl());
        setImage(image);

        setCard(card);
        setFitWidth(CARD_PREF_WIDTH);
        setFitHeight(CARD_PREF_HEIGHT);
    }

    /**
     * GETTERS
     */
    /**
     * returns this cardView's card
     *
     * @return
     */
    public Card getCard() {
        return card;
    }

    /**
     * @return
     */
    public CardSetView getParentSet() {
        return parentSet;
    }

    /**
     * SETTERS
     */
    /**
     * sets the given card as this cardView's card
     *
     * @param card
     */
    public void setCard(Card card) {
        this.card = card;
    }

    /**
     * sets this cardView's selected
     *
     * @param selected
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
        updateEffect();
    }


    /**
     * sets this cardView's valid
     *
     * @param valid
     */
    public void setValid(boolean valid) {
        this.valid = valid;
        updateEffect();
    }


    /**
     * @param parentSet
     */
    public void setParentSet(CardSetView parentSet) {
        this.parentSet = parentSet;
    }

    /**
     * sets the given newcomer value this cardView's newcomer value
     *
     * @param newcomer
     */
    public void setNewcomer(boolean newcomer) {
        this.newcomer = newcomer;
        updateEffect();
    }

    /**
     * @return
     */
    public boolean isSelected() {
        return selected;
    }


    /**
     * @return
     */
    public boolean isValid() {
        return valid;
    }

    /**
     *
     */
    public void removeFromParentSet() {
        getParentSet().removeCard(this);
    }

    /**
     *
     */
    private void updateEffect() {
        if (selected) {
            this.setEffect(ViewEffects.selectedCardEffect);
        } else {
            if (valid) {
                if (newcomer) {
                    this.setEffect(ViewEffects.newCardInSetEffect);
                } else {
                    this.setEffect(ViewEffects.defaultEffect);
                }
            } else {
                this.setEffect(ViewEffects.invalidEffect);
            }
        }
    }

    /**
     * customized compareTo() method for comparing card in this cardView with card in another cardView
     *
     * @param otherView
     * @return
     */
    @Override
    public int compareTo(CardView otherView) {
        return card.compareTo(otherView.card);
    }
}