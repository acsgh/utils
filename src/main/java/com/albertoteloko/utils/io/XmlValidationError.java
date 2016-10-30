package com.albertoteloko.utils.io;

public class XmlValidationError {
	public enum Type {
		Warning, Error, FatalError;
	}

	public static String splitUpper(String string) {
		StringBuilder result = new StringBuilder();

		for (char character : string.toCharArray()) {
			if (Character.isUpperCase(character)) {
				if (result.length() != 0) {
					result.append(" ");
				}
			}
			result.append(character);
		}

		return result.toString();
	}

	private final Type type;
	private final int line;
	private final int position;
	private final String info;

	public XmlValidationError(Type type, int line, int position, String info) {
		this.type = type;
		this.line = line;
		this.position = position;
		this.info = info;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(splitUpper(type.toString()));
		builder.append(": ");
		builder.append("(Line: ").append(line).append(", Column: ").append(position).append(") ");
		builder.append(info);
		return builder.toString();
	}

}