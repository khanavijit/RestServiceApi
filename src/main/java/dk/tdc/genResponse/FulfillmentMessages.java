package dk.tdc.genResponse;

public class FulfillmentMessages {
	private Text text;

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "ClassPojo [text = " + text + "]";
	}
}
