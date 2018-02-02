

public class Position implements Comparable{
    private double x;
    private double y;
    private double angle;
    private double slope;
    private double distance;
    public Position(double x,double y , double angle){
        this.x = x;
        this.y = y;
        this.angle = angle;
        if(angle%180 == 90 || angle%180 == -90){
            slope = Double.MAX_VALUE;
        }else
        slope = Math.tan(Math.toRadians(angle));
        distance = 0;
    }
    public Position(double x, double y, double angle, double distance){
        this.x = x;	
        this.y = y;
        this.angle = angle;
        this.distance = distance;
        if(angle%180 == 90 || angle%180 == -90){
            slope = Double.MAX_VALUE;
        } else {
        	slope = Math.tan(Math.toRadians(angle));
    	}
    }
    
    public Position(double x,double y){
        this.x = x;
        this.y = y;
    }
    public Position(double distance, double angle , boolean value){
    	this.distance = distance;
    	this.angle = angle;
    }
    
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public double getAngle(){
        return angle;
    }
    public double getSlope(){
        return slope;
    }
    public double getDistance(){
        return distance;
    }

    @Override
    public int compareTo(Object o) {
       Position temp = (Position) o;
       return (int)(temp.getX()-x);
    }
    @Override
    public String toString(){
        return " x " + x+ " y " + y + " angle " + angle + " slope " + slope + " distance " + distance;
    }
    public String toCode(){
    	return "name.addRelativePoint(" + distance + "," + angle + ",true);";
    }
    public double intersectX(Position other){
        double returnValue;
        if(slope == Double.MAX_VALUE)
            returnValue = x;
        else if (other.getSlope() == Double.MAX_VALUE)
            returnValue = other.getX();
        else if (slope == other.getSlope())
            returnValue = x;
        else
            returnValue = (slope*x+y-other.getY()+other.getSlope()*other.getX())/(other.getSlope()-slope);
        return returnValue;
    }
    

    
}
