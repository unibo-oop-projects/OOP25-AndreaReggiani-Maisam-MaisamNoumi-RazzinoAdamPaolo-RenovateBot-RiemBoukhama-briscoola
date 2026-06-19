package it.unibo.briscoola.view.impl.popup;

import it.unibo.briscoola.view.api.popup.PopupFactory;
import it.unibo.briscoola.view.api.popup.Popups;

import javax.swing.Popup;
import javax.swing.JRootPane;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;

/**
 * Factory class that hallows the creation of a specific {@link Popup}
 * through the use of a {@link Popups} element and the
 * message to be displayed.
 *
 * @author Adam Paolo Razzino
 */
public class PopupFactoryImpl implements PopupFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Popup create(final JRootPane root, final Popups popup, final String message) {
        switch (popup) {
            case WINNER -> {
                return this.roundWinnerPopup(root, message);
            }
            case ENDGAME -> {
                return this.endGamePopup(root, message);
            }
        }
        return /*TODO*/ null;
    }

    private Popup roundWinnerPopup(final JRootPane root, final String message) {
        final int codePoint = 1096;
        final int rows = 0;
        final int cols = 1;
        final String trophy = new String(Character.toChars(codePoint));
        final JComponent component = new JPanel(new GridLayout(rows, cols));
        component.add(new JLabel(trophy));
        component.add(new JLabel(message));
        return javax.swing.PopupFactory.getSharedInstance().getPopup(
                root,
                component,
                0,
                0
        );
    }

    private Popup endGamePopup(final JRootPane root, final String message) {
        final int codePoint = 1098;
        final int rows = 0;
        final int cols = 1;
        final String trophy = new String(Character.toChars(codePoint));
        final JComponent component = new JPanel(new GridLayout(rows, cols));
        component.add(new JLabel(trophy));
        component.add(new JLabel(message));
        return javax.swing.PopupFactory.getSharedInstance().getPopup(
                root,
                null,
                0,
                0
        );
    }
}
