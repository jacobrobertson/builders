package com.jacobrobertson.builders;

import java.util.ArrayList;
import java.util.List;

import com.jacobrobertson.builders.Move.Direction;
import com.jacobrobertson.builders.Move.MoveType;

/**
 * Takes a move, and makes it happen, possibly returning false if the move was illegal.
 * 
 * @author Jacob
 */
public class MoveMaker implements Moves {

	private List<Rule> defaultRules = new ArrayList<Rule>();
	private BuilderMapImpl map;
	private List<Builder> builders = new ArrayList<Builder>();
	private int msBetweenTurns = 250;
	private boolean running = false;
	private MapDrawer drawer;
	private int turnsTaken = 0;
	
	public MoveMaker(BuilderMapImpl map, MapDrawer drawer, List<Builder> builders) {
		this.builders = builders;
		this.map = map;
		this.drawer = drawer;
		this.defaultRules = getDefaultRules();
	}
	public void start() {
		drawer.drawMap(map);
		running = true;
		while (running) {
			for (Builder builder: builders) {
				// TODO straighten out the priority here, and how it works, this still isn't quite right
				boolean moved = move(defaultRules, builder);
				if (!moved) {
					move(builder.getRules(), builder);
					 
				}
			}
			
			drawer.drawMap(map);
			System.out.println("===> Turns Taken: " + turnsTaken);
			turnsTaken++;
			try {
				Thread.sleep(msBetweenTurns);
			} catch (InterruptedException e) {
			}
		}
	}
	private boolean move(List<Rule> rules, Builder builder) {
		boolean moved = false;
		for (Rule rule: rules) {
			MoveContext mc = new MoveContext(map, builder, this);
			Move move = rule.chooseNextMove(mc);
			if (move != null) {
				moved = makeMove(move, builder);
				if (!moved) {
//					System.out.println("===> Illegal Move Attempt by " + builder.getName() + " > " + move);
				} else {
					break;
				}
			}
		}
		return moved;
	}
	public void stop() {
		running = false;
	}
	
	private boolean makeMove(Move move, Builder builder) {
		String name = builder.getName();
		
		MoveType mt = move.getMoveType();
		switch (mt) {
			case Mine: return mine(move, builder);
			case Place: return place(move, builder);
			case Walk: return walk(move, name);
			// TODO - once all builders are done with game, we can stop...
			case GameOver: return false;
		}
		
		return false;
	}
	private boolean mine(Move move, Builder builder) {
		return false;
	}
	private boolean fall(String builder) {
		// TODO we are assuming we've actually already vetted this out
		Point p = map.getBuilderPosition(builder);
		Point d = getPoint(p, Direction.Down);
		map.setBuilderPosition(builder, d);
		return true;
	}
	private boolean climb(String builder) {
		Point p = map.getBuilderPosition(builder);
		Block b = map.getBlock(p);
		if (b != null && b.getBehavior().isClimbable()) {
			Point u = getPoint(p, Direction.Up);
			map.setBuilderPosition(builder, u);
			return true;
		}
		return false;
	}
	private boolean walk(Move move, String builder) {
		
		if (move.getDirection() == Direction.Down) {
			return fall(builder);
		}
		if (move.getDirection() == Direction.Up) {
			return climb(builder);
		}
		
		Point p = map.getBuilderPosition(builder);
		Point n = getPoint(p, move.getDirection());
		
		// determine if it's out of bounds
		if (isOutOfBounds(n)) {
			return false;
		}
		
		// can't walk if there's nothing under us
		Point d = getPoint(p, Direction.Down);
		if (isEmpty(d)) {
			return false;
		}
		
		// determine if we can walk there or not...
		if (isWalkable(n)) {
			map.setBuilderPosition(builder, n);
			return true;
		}
		
		// determine if we can go up some stairs
		if (isStairs(p, n)) {
			Point u = getPoint(n, Direction.Up);
			map.setBuilderPosition(builder, u);
			return true;
		}
		
		
		return false;
	}
	public boolean isOutOfBounds(Point p) {
		if (p.x < 0) {
			return true;
		}
		if (p.y < 0) {
			return true;
		}
		if (p.x >= map.getWidth()) {
			return true;
		}
		if (p.y >= map.getHeight()) {
			return true;
		}
		return false;
	}
	public boolean isEmpty(Point p) {
		Block bd = map.getBlock(p);
		if (bd == null) {
			return true;
		}
		 
		return (bd.getType() == null);
	}
	/**
	 * TODO - we don't know if it's "stairs" unless we know a little more context - what direction are you coming from
	 */
	public boolean isStairs(Point builder, Point block) {

		// - if the space is "stairway" and the space above is walkable
		Block b = map.getBlock(block);
		if (b == null) {
			return false;
		}
		BlockBehavior bb = b.getBehavior();
		if (!bb.isStairway()) {
			return false;
		}
		
		Point aboveBlock = getPoint(block, Direction.Up);
		if (!isWalkable(aboveBlock)) {
			return false;
		}
		
		Point aboveBuilder = getPoint(builder, Direction.Up);
		if (isWalkable(aboveBuilder)) {
			return true;
		}
		
		return false;
	}
	/** 
	 * a space is walkable if
	* 	- the space is empty 
	* 	or
	* 	- the space is can stand in
	*/
	public boolean isWalkable(Point p) {
		if (isOutOfBounds(p)) {
			return false;
		}
		if (isEmpty(p)) {
			return true;
		}
		Block b = map.getBlock(p);
		BlockBehavior bb = b.getBehavior();
		if (bb.canStandIn()) {
			return true;
		}
		return false;
	}
	private boolean place(Move move, Builder builder) {
		return false;
	}
	public Point getPoint(Point p, Direction d) {
		switch (d) {
		case Up: return new Point(p.x, p.y - 1);
		case Down: return new Point(p.x, p.y + 1);
		case Left: return new Point(p.x - 1, p.y);
		case Right: return new Point(p.x + 1, p.y);
		}
		// ?? shouldn't have to have this line?
		return null;
	}
	
	private List<Rule> getDefaultRules() {
		List<Rule> rules = new ArrayList<Rule>();
		Rule fall = new Rule() {
			public Move chooseNextMove(MoveContext context) {
				BuilderMapImpl mapImpl = (BuilderMapImpl) context.getMap();
				Point p = mapImpl.getBuilderPosition(context);
				Block pb = mapImpl.getBlock(p);
				if (pb != null && pb.getBehavior().isClimbable()) {
					return null;
				} else {
					Point d = getPoint(p, Direction.Down);
					if (isEmpty(d)) {
						return Move.fall();
					} else {
						return null;
					}
				}
			}
		};
		rules.add(fall);
		return rules;
	}
	
}
