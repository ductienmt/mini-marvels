package com.login.swing;

import java.awt.AlphaComposite;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class PanelTransparent extends JPanel {

    private float alpha = 1f;
    private Component originalForm;
    private Component currentForm;

    public PanelTransparent() {
        setOpaque(false);
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
        repaint();
    }

    @Override
    public void paint(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        super.paint(grphcs);
    }

    public void showForm(Component com) {
        if (originalForm == null) {
            originalForm = com;
        }

        remove(currentForm);
        add(com);
        currentForm = com;
        repaint();
        revalidate();
    }

    public void removeForm() {
        if (originalForm != null) {
            remove(currentForm);
            add(originalForm);
            currentForm = originalForm;
            repaint();
            revalidate();
        }
    }
}
