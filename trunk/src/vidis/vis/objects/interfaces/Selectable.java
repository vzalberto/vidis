package vidis.vis.objects.interfaces;

import vidis.vis.util.Vector4d;

public interface Selectable {
	public void setSelected(boolean selected);
	public boolean isSelected();
	
	public boolean isHit(Vector4d point);
	
}
