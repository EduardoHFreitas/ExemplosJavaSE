package br.univel.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Painel extends JPanel {


	private static final Color BLACK = Color.BLACK;
	private Color altColor = Color.WHITE;
	private boolean alt = true;
	private int r, g, b = 0;
	private int step = 5;

	public Painel() {
		setBackground(BLACK);

		new Thread(() -> {
			for (;;) {
				try {
					Thread.sleep(20);
				} catch (Exception e) {
					e.printStackTrace();
				}
				r = g = r + step;
				if (r > 255 ) {
					r = g = 255;
					step = step * -1;
				}
				if (r < 0) {
					r = g = 0;
					step = step * -1;
				}
				altColor = new Color(r, g, b);
				repaint();
			}
		}).start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(altColor);
		int w = getWidth() - 1;
		int h = getHeight() - 1;

		g2.drawLine(0, 0, w, h);

		int wQuad = w / 8;
		int hQuad = h / 8;

		// {
		// boolean impar = true;
		// for (int contH = 0; contH < h; contH+=alturaQuadrado) {
		// for (int contW = 0; contW < w; contW+=larguraQuadrado*2) {
		// if (impar) {
		// g2.fillRect(contW, contH, larguraQuadrado, alturaQuadrado);
		// } else {
		// g2.fillRect(contW+larguraQuadrado, contH, larguraQuadrado,
		// alturaQuadrado);
		// }
		// }
		// impar = !impar;
		// }
		// }

		{
			boolean impar = alt;
			for (int contH = 0; contH < 8; contH++) {
				for (int contW = 0; contW < 8; contW++) {
					int x = impar ? contW * wQuad * 2 : contW * wQuad * 2
							+ wQuad;
					int y = contH * hQuad;
					g2.fillRect(x, y, wQuad, hQuad);
				}
				impar = !impar;
			}
		}
	}

}
