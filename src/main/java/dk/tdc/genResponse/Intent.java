package dk.tdc.genResponse;

public class Intent {
	private String name;

	private String displayName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Override
	public String toString() {
		return "ClassPojo [name = " + name + ", displayName = " + displayName + "]";
	}
}
