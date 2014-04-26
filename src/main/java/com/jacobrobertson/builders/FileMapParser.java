package com.jacobrobertson.builders;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class FileMapParser {

	private String[] lines;
	
	public FileMapParser(InputStream in) throws IOException {
		 StringBuilder buf = new StringBuilder();
		 int read;
		 while ((read = in.read()) >= 0) {
			 buf.append((char) read);
		 }
		 lines = buf.toString().split("\n");
	}
	
	public void generate(BuilderMapImpl map, List<Builder> builders) {
		int y = 0;
		for (String line: lines) {
			for (int x = 0; x < line.length(); x++) {
				char c = line.charAt(x);
				if (Character.isDigit(c)) {
					int b = Integer.parseInt(String.valueOf(c)) - 1;
					map.setBuilderPosition(builders.get(b).getName(), new Point(x, y));
				} else {
					MapData data = parseMapData(c);
					if (data != null) {
						map.setMapData(x, y, data);
					}
				}
			}
			y++;
		}
	}
	public MapData parseMapData(char c) {
		Block b;
		if (c == '*') {
			b = Blocks.bush();
		} else if (c == 'X') {
			b = Blocks.rock();
		} else {
			return null;
		}
		return new MapData(b);
	}
	
}
