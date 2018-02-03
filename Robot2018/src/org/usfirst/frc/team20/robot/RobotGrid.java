
import java.util.ArrayList;



public class RobotGrid {
//NO ARCS OVER 90 DEGREES
    private ArrayList<Position> path;
    private double precision;
//NO ARCS OVER 90 DEGREES
    public RobotGrid(double x, double y, double angle, double precision) {//precsionis in points per degree
        path = new ArrayList<Position>();
        path.add(new Position(x, y, angle));
        this.precision = precision;
    }
    public RobotGrid(double x, double y, double angle, double precision, double distance){
    	path = new ArrayList<Position>();
        path.add(new Position(x, y, angle, distance));
        this.precision = precision;
    }
    //NO ARCS OVER 90 DEGREES
    public void addLinearPoint(double x, double y, double angle) {//when initalizing x and y do not matter it is just a way to set the zero of the field and that zero can be wherever you want it to be
        if (angle >180)
            angle = -angle%180;
        path.add(new Position(x, y, angle, Math.sqrt(Math.abs(x-path.get(path.size()-1).getX())*Math.abs(x-path.get(path.size()-1).getX())+Math.abs(y-path.get(path.size()-1).getY())*Math.abs(y-path.get(path.size()-1).getY()))+path.get(path.size()-1).getDistance()));
    }
//NO ARCS OVER 90 DEGREES
    public void addPoint(double x, double y, double angle) {//NO ARCS OVER 90 DEGREES
        Position p1 = path.get(path.size() - 1);
        Position p2;
        Position p3 = new Position(x, y, angle);
        double tempX;
        double tempY;
        if (p1.getY()>p3.getY()){
        	tempX = p1.intersectX(p3);
        }else{
        	tempX = p3.intersectX(p1);
        }
        if(((Math.abs(p1.getAngle()) > 88 && Math.abs(p1.getAngle()) < 92) || (Math.abs(p1.getAngle())>268 && Math.abs(p1.getAngle())<272)) && ((Math.abs(p3.getAngle())>-2 && Math.abs(p3.getAngle()) <2) || (Math.abs(p3.getAngle()) >178 && Math.abs(p3.getAngle()) <182))){
        	System.out.println("right angle");
        	tempX = p1.getX();
        	tempY = p3.getY();
        }else if(((Math.abs(p3.getAngle()) > 88 && Math.abs(p3.getAngle()) < 92) || (Math.abs(p3.getAngle())>268 && Math.abs(p3.getAngle())<272)) && ((Math.abs(p1.getAngle())>-2 && Math.abs(p1.getAngle()) <2) || (Math.abs(p1.getAngle()) >178 && Math.abs(p1.getAngle()) <182))){
        	System.out.println("right angle");
        	tempX = p3.getX();
        	tempY = p1.getY();
        }else if (Math.abs(p1.getAngle()) == 90 || Math.abs(p1.getAngle()) == 270) {
            System.out.println("handled normal");
        	tempY = p3.getSlope() * (tempX - p3.getX()) + p3.getY();
        } else{
        	System.out.println("handled normal");
        tempY = p1.getSlope() * (tempX - p1.getX()) + p1.getY();
        }
        p2 = new Position(tempX, tempY);
        System.out.println(p2.toString());
        double pointAngle = p1.getAngle();
        for (double t = 1/(precision * Math.abs(angle-p1.getAngle()));t<=1;t +=1/(precision * Math.abs(angle-p1.getAngle()))){
            double pointX = Math.pow(1- t, 2)*p1.getX()+2*(1-t)*t*p2.getX()+Math.pow(t, 2)*p3.getX();
            double pointY = Math.pow(1- t, 2)*p1.getY()+2*(1-t)*t*p2.getY()+Math.pow(t, 2)*p3.getY();
            pointAngle = Math.toDegrees(Math.atan2(pointY-path.get(path.size()-1).getY(),pointX-path.get(path.size()-1).getX()));
            path.add(new Position(pointX,pointY,pointAngle, Math.sqrt(Math.pow(pointX-path.get(path.size()-1).getX(),2) + Math.pow(pointY-path.get(path.size()-1).getY(),2))+path.get(path.size()-1).getDistance()));
        }
    }
    public void addPoint(double x,double y, double angle, double x2, double y2){
    	Position p1 = path.get(path.size()-1);
    	Position p2 = new Position(x2,y2);
    	Position p3 = new Position(x,y,angle);
    	double pointAngle = p1.getAngle();
    	for (double t = 1/(precision * Math.abs(angle-p1.getAngle()));t<=1;t +=1/(precision * Math.abs(angle-p1.getAngle()))){
            double pointX = Math.pow(1- t, 2)*p1.getX()+2*(1-t)*t*p2.getX()+Math.pow(t, 2)*p3.getX();
            double pointY = Math.pow(1- t, 2)*p1.getY()+2*(1-t)*t*p2.getY()+Math.pow(t, 2)*p3.getY();
            pointAngle = Math.toDegrees(Math.atan2(pointY-path.get(path.size()-1).getY(),pointX-path.get(path.size()-1).getX()));
            path.add(new Position(pointX,pointY,pointAngle, Math.sqrt(Math.pow(pointX-path.get(path.size()-1).getX(),2) + Math.pow(pointY-path.get(path.size()-1).getY(),2))+path.get(path.size()-1).getDistance()));
        }
    }
    public void addRelativePoint(double distance, double angle){
    	if(distance > 1 + getDistance())
    	 		 path.add(new Position(distance,angle,true));
    }
//NO ARCS OVER 90 DEGREES
    public String toString() {
        String temp = "";
        for (Position pos : path) {
            temp += pos.toString() + "\n";
        }
        return temp;
    }
    public String toCode(){
    	String temp = "";
        for (Position pos : path) {
            temp += pos.toCode() + "\n";
        }
        return temp;
    }
//NO ARCS OVER 90 DEGREES
//needs work    
/*    private double atan3(double tan,double angle){
        double value = Math.toDegrees(tan);
        if (angle <90 && angle >= 0 )
            return value;
        if (angle >= 90 && angle < 180)
            return 180-value;
        if (angle >= -180 && angle <= -90)
            return -180 + value;
        if (angle > -90)
            return -value;
        return 360;
    }*/
    // average of both sides not just one
    public double getAngle(double distance){
        for (int i = 1; i < path.size()-1; i++)
            if(path.get(i).getDistance() > distance){
                return path.get(i-1).getAngle();
            }
        	return path.get(path.size()-1).getAngle();
    }
    public double getReverseAngle(double distance){
    	double angle = getAngle(distance);
    	if (angle>=0)
    		angle -= 180;
    	else if (angle <0)
    		angle += 180;
    	return angle;
    }
    public double getDistance(){
        return path.get(path.size()-1).getDistance();
    }
    public double speedMultiplier(double distance, double angle){
    	double angleTarget = getAngle(distance + 15);
    	return Math.abs(1-Math.abs((angleTarget-angle)/115));
    }
    public double getLeftIPS(double distance,double angle,double baseIPS, EncoderGyro change){//inches per second
    	double t = 10/baseIPS;
    	double leftDistance;
    	double angleChange;
    	if(baseIPS>0){
    		if (getAngle(distance+10)>90 && angle < -90){
     			angleChange = 180-getAngle(distance+10);
    			angleChange += 180 + angle;
    		}else if(angle > 90 && getAngle(distance + 10) < -90){
    			angleChange = 180-angle;
    			angleChange += 180 + getAngle(distance +10);
    		}else angleChange = getAngle(distance +10)-angle;
    	}else {
    		if (getReverseAngle(distance+10)>90 && angle < -90){
        		angleChange = 180-getReverseAngle(distance+10);
        		angleChange += 180 + angle;
        	}else if(angle > 90 && getReverseAngle(distance + 10) < -90){
        		angleChange = 180-angle;
        		angleChange += 180 + getReverseAngle(distance +10);
        	}else angleChange = getReverseAngle(distance +10)-angle;
    	}
    	leftDistance = change.angleToDistance(angleChange)+10;
    	return leftDistance /t;
    }
    public double getRightIPS(double distance,double angle,double baseIPS, EncoderGyro change){//inches per second
    	double t = 10/baseIPS;
    	double rightDistance;
    	double angleChange;
    	if(baseIPS>0){
    		if (getAngle(distance+10)>90 && angle < -90){
    		angleChange = 180-getAngle(distance+10);
    		angleChange += 180 + angle;
    		}else if(angle > 90 && getAngle(distance + 10) < -90){
    		angleChange = 180-angle;
    		angleChange += 180 + getAngle(distance +10);
    		}else angleChange = getAngle(distance +10)-angle;
    	}else {
    		if (getReverseAngle(distance+10)>90 && angle < -90){
        		angleChange = 180-getReverseAngle(distance+10);
        		angleChange += 180 + angle;
        	}else if(angle > 90 && getReverseAngle(distance + 10) < -90){
        		angleChange = 180-angle;
        		angleChange += 180 + getReverseAngle(distance +10);
        	}else angleChange = getReverseAngle(distance +10)-angle;
    	}
    	rightDistance =  change.angleToDistance(angleChange)-10;
    	return rightDistance /t;
    }
    
}
