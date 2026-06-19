package it.unibo.briscoola.view.api.popup;

import javax.swing.Popup;
import javax.swing.JRootPane;

/**
 * Class to use to get a personalized {@link Popup} based on the passed
 * element of enum {@link Popups}.
 *
 * @author Adam Paolo Razzino
 */
public interface PopupFactory {

    /**
     * Based on the enum element of type {@link Popups} deploys the correct {@link Popup}.
     *
     * @param root owner of the popup componed
     * @param popup of enum {@link Popups}
     * @param message is a {@link String} to show on the popup
     * @return a {@link Popup} element
     */
    Popup create(JRootPane root, Popups popup, String message);
}
