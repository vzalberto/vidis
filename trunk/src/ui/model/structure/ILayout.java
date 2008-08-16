package ui.model.structure;

public interface ILayout {

	
	public void setGuiContainer( IGuiContainer c );
	public void layout();
	
	public double getWidth();
	public double getHeight();
	public double getX();
	public double getY();

}
