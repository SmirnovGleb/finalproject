package by.epam.roulette.controller;

/**
 * The Class PathType.
 */
public class PathType {
	private String path;
	private TypeAction type;

	/**
	 * Instantiates a new path type.
	 *
	 * @param path
	 *            the path
	 * @param type
	 *            the type
	 */
	public PathType(String path, TypeAction type) {
		this.path = path;
		this.type = type;
	}

	/**
	 * Gets the path.
	 *
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public TypeAction getType() {
		return type;
	}
}
