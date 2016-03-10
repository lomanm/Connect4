package c4model;

public class C4Player {
    private String color;
    
	public C4Player(){				// constructor for App or Console view (no options set)
	}
	public C4Player(String color){	// constructor for Web view (options already set)
		this.color = color;
	}
	public void setColor(String color){
		this.color = color;
	}
	public String getColor(){
		return color;
	}
}
