package gui;

import utils.Range;
import utils.StdDraw;

public class Graph_GUI {
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {

		// window size
		StdDraw.setCanvasSize(width, height);

		// rescale the coordinate system
		StdDraw.setXscale(rx.get_min(), rx.get_max());
		StdDraw.setYscale(ry.get_min(), ry.get_max());
		double x_steps = Math.abs(rx.get_max() - rx.get_min()) / resolution;

		// vertical lines
		StdDraw.setPenColor(Color.LIGHT_GRAY);
		for (int i = (int) (rx.get_min()); i < rx.get_max(); i++) {
			StdDraw.line(i, ry.get_min(), i, ry.get_max());
		}

		// horizontal lines
		for (int i = (int) (ry.get_min()); i < ry.get_max(); i++) {
			StdDraw.line(rx.get_min(), i, rx.get_max(), i);
		}

		// define pen, font and color
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.setPenRadius(0.005);
		StdDraw.setFont(new Font("TimesRoman", Font.BOLD, 12));

		// x axis
		StdDraw.line(rx.get_min(), 0, rx.get_max(), 0);
		StdDraw.line(0, ry.get_min(), 0, ry.get_max());
		for (int i = (int) rx.get_min(); i <= rx.get_max(); i++) {
			StdDraw.text(i, -0.5, Integer.toString(i));
		}

		// y axis
		StdDraw.line(0, ry.get_min(), 0, ry.get_max());
		for (double i = ry.get_min(); i <= ry.get_max(); i++) {
			StdDraw.text(-0.5, i, Double.toString(i));
		}

		Color[] color = { Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE, Color.red, Color.GREEN, Color.PINK };

		// draw the functions
		for (int i = 0; i < this.fc.size(); i++) {
			StdDraw.setPenColor(color[i % 7]); // Selects a different color in each iteration
			for (double j = rx.get_min(); j < rx.get_max(); j += x_steps) {
				StdDraw.line(j, this.fc.get(i).f(j), j + x_steps, this.fc.get(i).f(j + x_steps));
			}
		}

	}

}
