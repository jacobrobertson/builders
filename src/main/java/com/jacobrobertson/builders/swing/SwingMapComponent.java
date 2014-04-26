package com.jacobrobertson.builders.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.JFrame;

import com.jacobrobertson.builders.Block;
import com.jacobrobertson.builders.Blocks;
import com.jacobrobertson.builders.BuilderMapImpl;
import com.jacobrobertson.builders.MapData;
import com.jacobrobertson.builders.MapDrawer;
import com.jacobrobertson.builders.Point;

public class SwingMapComponent extends JComponent implements MapDrawer {

	private JFrame frame;
	private BuilderMapImpl map;
	
	public SwingMapComponent(String title) {
		frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(this);
		frame.setBounds(100, 100, 1000, 500);
		frame.setVisible(true);
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		if (map == null) {
			return;
		}
		
		int w = map.getWidth();
		int h = map.getHeight();

		int blockWidth = (int) (frame.getContentPane().getWidth() * 1) / w;
		int blockHeight =(int) (frame.getContentPane().getHeight()* 1) / h;
		
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				MapData mapData = map.getMapData(x, y);
				Block blockData;
				if (mapData == null) {
					blockData = null; 
				} else {
					blockData = mapData.getBlock();
				}
				paintBlock(g2, blockData, x, y, blockWidth, blockHeight);
			}
		}

		for (String builderName: map.getBuilderNames()) {
			Point p = map.getBuilderPosition(builderName);
			paintBuilder(g2, builderName, p, blockWidth, blockHeight);
		}
		
	}
	
	private Color[] getBlockColors(Block block) {
		// assume type == color, and scale to screen size
		String blockType;
		if (block == null) {
			blockType = null;
		} else {
			blockType = block.getType();
		}
		if (Blocks.Rock.equals(blockType)) {
			return new Color[] {Color.gray, Color.blue};
		} else if (Blocks.Bush.equals(blockType)) {
			return new Color[] {Color.green, Color.DARK_GRAY};
		} else {
			return new Color[] {Color.black, Color.black};
		}
	}
	private void paintBuilder(Graphics2D g2, String builder, Point p, int blockWidth, int blockHeight) {
		int builderSizeOffset = blockWidth / 10;
		g2.setColor(Color.pink);
		g2.fillRect(p.x * blockWidth + builderSizeOffset, p.y * blockHeight, blockWidth - (builderSizeOffset * 2), blockHeight);
		g2.setColor(Color.red);
		g2.drawRect(p.x * blockWidth + builderSizeOffset, p.y * blockHeight, blockWidth - (builderSizeOffset * 2), blockHeight);
	}
	private void paintBlock(Graphics2D g2, Block block, int x, int y, int blockWidth, int blockHeight) {
		Color[] colors = getBlockColors(block);
		g2.setColor(colors[0]);
		g2.fillRect(x * blockWidth, y * blockHeight, blockWidth, blockHeight);
		g2.setColor(colors[1]);
		g2.drawRect(x * blockWidth, y * blockHeight, blockWidth, blockHeight);
	}
	
	public void drawMap(BuilderMapImpl map) {
		this.map = map;
		frame.repaint();
	}
	
}
