package by.epam.roulette.controller;

public class PathType {
	private String path;
	private TypeAction type;
	
	public PathType(String path, TypeAction type) {
		this.path = path;
		this.type = type;
	}
	
	public String getPath() {
		return path;
	}
	
	public TypeAction getType() {
		return type;
	}
}
