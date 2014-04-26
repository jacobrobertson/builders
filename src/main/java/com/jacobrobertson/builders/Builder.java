package com.jacobrobertson.builders;

import java.util.ArrayList;
import java.util.List;

public class Builder {

	private String name;
	private Backpack backpack;
	private List<Rule> rules;
	
	public Builder(String name) {
		this(name, new ArrayList<Rule>());
	}
	public Builder(String name, List<Rule> rules) {
		this.name = name;
		this.backpack = new Backpack();
		this.rules = rules;
	}
	public String getName() {
		return name;
	}
	public Backpack getBackpack() {
		return backpack;
	}
	public List<Rule> getRules() {
		return rules;
	}
	public void addRule(Rule rule) {
		rules.add(rule);
	}
}
