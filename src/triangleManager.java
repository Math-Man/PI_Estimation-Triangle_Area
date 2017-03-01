import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class triangleManager extends Application{

	private double l1Sx = 1;
	private double l2Sx = 1;
	private double l3Sx = 1;
	private double l1Ex = 1;
	private double l2Ex = 1;
	private double l3Ex = 1;
	private double l1Sy = 1;
	private double l2Sy = 1;
	private double l3Sy = 1;
	private double l1Ey = 1;
	private double l2Ey = 1;
	private double l3Ey = 1;
	
	private double SceneW = 600;
	private double SceneH = 600;
	
	public static int pointAmount = 1;
	private int pointIn = 0;
	private int pointOut = 0;
	private int pointOn = 0;
	private int pointCA = 0;
	
	private double EAotT = 0;
	private double AAotT = 0;
	
	public static boolean debug = false;
	
	Pane pane = new Pane();
	Scene scene = new Scene(pane, SceneW, SceneH);
	
	public void start(Stage primaryStage) throws Exception {
		
		
		Line li1 = new Line();
		Line li2 = new Line();
		Line li3 = new Line();
		
		this.setLines();	//Sets position Lines
	
		li1.setStartX(l1Sx);
		li1.setStartY(l1Sy);
		li1.setEndX(l1Ex);
		li1.setEndY(l1Ey);
		
		li2.setStartX(l2Sx);
		li2.setStartY(l2Sy);
		li2.setEndX(l2Ex);
		li2.setEndY(l2Ey);
		
		li3.setStartX(l3Sx);
		li3.setStartY(l3Sy);
		li3.setEndX(l3Ex);
		li3.setEndY(l3Ey);
		
		li1.setStroke(Color.RED);
		li3.setStroke(Color.GREEN);
		li2.setStroke(Color.BLUE);
		
		pane.getChildren().add(li1);
		pane.getChildren().add(li2);
		pane.getChildren().add(li3);
		
		primaryStage.setTitle("Triangle stuff or whatever...");
		
		for (int i = 0 ; i < pointAmount ; i++) {
		
		this.newPoint(Math.random()*SceneW,Math.random()*SceneH);
		
		
		}
		
		if (debug) {System.out.println(
					"\nTriangle @:" + "(" + l1Sx + " , " + l1Sy + ")" + "(" + l2Sx + " , " + l2Sy + ")" + "(" + l3Sx + " , " + l3Sy + ")"
					+"\nPoints Inside: " + pointIn + " Points On " + pointOn + " Points Out " + pointOut 				    
				    +"\nEstem Area: " + this.estemArea()
				    +"\nActual Area: " + this.actualArea()
				    +"\nDiffrence: " + (AAotT - EAotT) );}
		
		primaryStage.setTitle("est: " + this.estemArea() + " actual: " + this.actualArea() + " Diffrence: " + (AAotT - EAotT));
		primaryStage.setScene(scene); 
		primaryStage.setResizable(false);
		primaryStage.show(); 
	}

	
	

	public void newPoint(double x , double y) {	//Generate a new point
		
		Circle po = new Circle();
		po.setStrokeWidth(1);
		po.setRadius(0.8);
		po.setCenterX(x);
		po.setCenterY(y);
		po.setStroke(Color.BLACK);
		po.setFill(null);
	
		
		//HARDCORE MATH!!!1!!!11!!
		
		double A = ((l2Sy - l3Sy) * (x - l3Sx) + (l3Sx - l2Sx) * (y - l3Sy)) /
				((l2Sy - l3Sy)*(l1Sx - l3Sx) + (l3Sx - l2Sx)*(l1Sy - l3Sy));
		
		double B = ((l3Sy - l1Sy) * (x - l3Sx) + (l1Sx - l3Sx) * (y - l3Sy)) /
			       ((l2Sy - l3Sy)*(l1Sx - l3Sx) + (l3Sx - l2Sx)*(l1Sy - l3Sy));
		
		double C = 1.0f - A - B;
		
		
	//	System.out.println(A+ " "+ " " + B + " "+ C);	//IF ALL A AND B AND C ARE GREATER THAN 0 ITS INSIDE
		
		//ITS OVER!
				
				
		if (A > 0 & B > 0 & C > 0) {
			
			po.setStroke(Color.DARKORANGE);
			pointIn++;
			
			if (debug) {
				pointCA = pointIn + pointOn + pointOut;
				System.out.println("Point #" + pointCA + " Point IN #"+ pointIn + " @ : " + A + " " + " " + B + " "+ C);
			}
			
		}
		
		else if (A == 0 & B == 0 & C == 0) {
			
			po.setStroke(Color.AQUA);
			pointOn++;
			
			if (debug) {
				pointCA = pointIn + pointOn + pointOut;
				System.out.println("Point #" + pointCA + " Point ON #"+ pointOn + " @ : " + A + " " + " " + B + " "+ C);
			}
			
		}
		
		else {
			
			po.setStroke(Color.CADETBLUE);
			pointOut++;
			
			if (debug) {
				pointCA = pointIn + pointOn + pointOut;
				System.out.println("Point #" + pointCA + " Point OUT #"+ pointOut + " @ : " + A + " " + " " + B + " "+ C);
			}
			
		}
		
		
		pane.getChildren().add(po);
	
	}
	
	
	
	
	public void setLines() {
		
		l1Sx = Math.random()*600;
		l1Ex = Math.random()*600;
		
		l2Sx = l1Ex;
		l2Ex = Math.random()*600;
		
		l3Sx = l2Ex;
		l3Ex = l1Sx;
		
		l1Sy = Math.random()*600;
		l1Ey = Math.random()*600;
		
		l2Sy = l1Ey;
		l2Ey = Math.random()*600;
		
		l3Sy = l2Ey;
		l3Ey = l1Sy;
		
	}



	public double estemArea() {	//Calculate Estimated area
		
		EAotT = ( ( (SceneW*SceneH) * (pointIn) ) / (pointIn + pointOn + pointOut) );
		
		
		return EAotT;
		
	}
	
	public double actualArea() {
		
		AAotT = 0.5 * Math.abs(l1Sx*l2Sy+l2Sx*l3Sy+l3Sx*l1Sy-(l1Sy*l2Sx+l2Sy*l3Sx+l3Sy*l1Sx)); //Yeah..Its ugly I know
		return AAotT;
	}
		
}
