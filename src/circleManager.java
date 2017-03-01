import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class circleManager extends Application{

	private int circleX = 300;
	private int circleY = 300;
	public static double circleRad = 150;
	private long sceneW = 600;
	private long sceneH = 600;
	public static int repeatCounter = 1;
	private int pIn = 0;
	private int pOut = 0;
	private int pOn = 0;
	private int pointX;
	private int pointY;
	public int rngRange = 600;
	private String tfx;
	public static boolean debug = false;
	
	Pane pane = new Pane();
	Scene scene = new Scene(pane, sceneW, sceneH);
	
	
	
	public void start(Stage primaryStage) throws Exception {
		
		
		
		Circle circle = new Circle();
		circle.setCenterX(circleX);
		circle.setCenterY(circleY);
		circle.setRadius(circleRad);
		circle.setStroke(Color.BLACK);
		circle.setStrokeWidth(1);
		circle.setFill(null);
		
		
		
		pane.getChildren().add(circle);
		
	
		
		
		
		
		for (int i = 0 ; repeatCounter > i ; i++) {
			
			this.setPointLocation((int)Math.ceil(Math.random()*rngRange), (int)Math.ceil(Math.random()*rngRange));
			
			this.newPoint(pointX, pointY);
			
			
		}

		
		
		
		this.setResult();
		System.out.println(tfx);
		double g = Math.PI - ((sceneW*sceneH)*pIn) / ((pIn+pOut+pOn)*circleRad*circleRad);
		primaryStage.setTitle(tfx + " Diffrence: " + g); // Set the stage title
		primaryStage.setScene(scene); 
		primaryStage.setResizable(false);
		primaryStage.show(); 
	}

	
	
	public void newPoint(int x , int y) {	//Generate a new point
		
		Circle po = new Circle();
		po.setStrokeWidth(0.8);
		po.setRadius(1);
		po.setCenterX(x);
		po.setCenterY(y);
		po.setStroke(Color.BLACK);
		po.setFill(null);
		
		
		
		if ((x - circleX)*(x - circleX) + (y - circleY)*(y - circleY) < circleRad*circleRad) {
			
			pIn++;
			po.setStroke(Color.LIGHTCORAL);
			if (debug) {
				System.out.println("inside :" + pIn);
			}
		}
		
		else if ((x - circleX)*(x - circleX) + (y - circleY)*(y - circleY) == circleRad*circleRad) {
			
			pOn++;
			po.setStroke(Color.GREEN);
			if (debug) {
				System.out.println("on: " + pOn);
			}
		}
		
		else {
			
			pOut++;
			po.setStroke(Color.BLUE);
			if (debug) {
				System.out.println("out: " + pOut);
			}
		}
		
		if (debug) {
		System.out.println("inside :" + pIn);
		System.out.println("on: " + pOn);
		System.out.println("out: " + pOut);
		}
		pane.getChildren().add(po);
	}
	
	public void setRepeatTime(int timeR) {
		
		repeatCounter = timeR;
		
	}
	
	public void setPointLocation(int x , int y) {
		
		pointX = x;
		pointY = y;
		
	}
	
	public void setResult() {
		//(sceneW*sceneH)*pIn / (pIn+pOut+pOn)*circleRad*circleRad
		tfx = ("Pi IS: " + (double)((sceneW*sceneH)*pIn) / ((pIn+pOut+pOn)*circleRad*circleRad) );
		
	}
	
	
}
