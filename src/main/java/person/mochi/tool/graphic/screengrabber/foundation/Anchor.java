package person.mochi.tool.graphic.screengrabber.foundation;

/**
 * Anchor is a class that shows system your rectangle start point.
 * @author zhangyimeng
 *
 */
public class Anchor {

	private int x;
	private int y;
	private boolean prefab;
	private int anchorType;
	public static Anchor CENTER = new Anchor(1);
	
	private Anchor(int anchorType) {
		prefab = true;
		this.anchorType = anchorType;
	}
	
	/**
	 * you do not need to invoke this function.
	 * @param fullWidth
	 * @param fullHeight
	 * @param width
	 * @param height
	 */
	public void initialize(int fullWidth, int fullHeight, int width, int height) {
		if(prefab) {
			switch(anchorType) {
			case 1:
				x = (fullWidth / 2) - (width / 2);
				y = (fullHeight / 2) - (height / 2);
				break;
			}
		}
	}
	
	public Anchor(int x, int y) {
		this.setX(x);
		this.setY(y);
		prefab = false;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
}
