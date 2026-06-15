package it.unibo.briscoola.view.api;

public interface CardView {

    /**
     * Mostra la carta a schermo, se uno dei parametri è null mostra il retro.
     * @param seed  Il seme della carta.
     * @param value Il valore della carta.
     */
    void renderCard(String seed, String value);
}
