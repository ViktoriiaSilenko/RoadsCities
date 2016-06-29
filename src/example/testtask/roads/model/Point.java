package example.testtask.roads.model;

public class Point {
	
	private int x;
	private int y;
	
	public Point(int x, int y) {
		if (x < 0 || x > Integer.MAX_VALUE || y < 0 || y > Integer.MAX_VALUE) {
			throw new IllegalArgumentException("x or y < 0 or > " + Integer.MAX_VALUE);
		}
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		if (x < 0 || x > Integer.MAX_VALUE) {
			throw new IllegalArgumentException("x < 0 or > " + Integer.MAX_VALUE);
		}
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		if (y < 0 || y > Integer.MAX_VALUE) {
			throw new IllegalArgumentException("y < 0 or > " + Integer.MAX_VALUE);
		}
		this.y = y;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}

}
