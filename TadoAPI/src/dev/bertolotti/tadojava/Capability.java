package dev.bertolotti.tadojava;

public class Capability {
	private String type;
	private String key;
	private Object value;

	public String getType() {
		return type;
	}

	public String getKey() {
		return key;
	}

	public Object getValue() {
		return value;
	}

	public Capability(String type, String key, Object value) {
		super();
		this.type = type;
		this.key = key;
		this.value = value;
	}

	@Override
	public String toString() {
		return "Capability [type=" + type + ", key=" + key + ", value=" + value + "]";
	}
}