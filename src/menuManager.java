
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class menuManager extends Application{

	
	GridPane grid = new GridPane();

	
	Scene scene = new Scene(grid, 720, 160);
	private String fb;
	
	public void start(Stage primaryStage) throws Exception {
		
		if (triangleManager.debug == true) {
    		triangleManager.debug = false;
    	}
    	else {triangleManager.debug = false;}
    	
    	if (circleManager.debug == true) {
    		circleManager.debug = false;
    	}
    	else {circleManager.debug = false;}
		
		
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(3);
		
		Label HowDo = new Label("After a program completes its course you'll be automaticly returned to the menu after a while\nIn other words I used Thread.Sleep");
		GridPane.setConstraints(HowDo,0,5);
		grid.getChildren().add(HowDo);
				
		
		Label TF = new Label("How many Rolls?");
		GridPane.setConstraints(TF,1,0);
		grid.getChildren().add(TF);
		
		TextField TFRolls = new TextField();
		TFRolls.setPrefColumnCount(10);
		if (fb == null) {
			TFRolls.setText("100000");
			fb = TFRolls.getText();
		}
		else {TFRolls.setText(fb);}
		GridPane.setConstraints(TFRolls, 0, 0);
		grid.getChildren().add(TFRolls);
		HBox hb = new HBox();
		
		
		CheckBox bDebug = new CheckBox();
		GridPane.setConstraints(bDebug,1,1);
		bDebug.setText("Log information on console?");
		grid.getChildren().add(bDebug);
		
		CheckBox randomRad = new CheckBox();
		GridPane.setConstraints(randomRad ,1,2);
		randomRad .setText("Random radius on Circle PI");
		grid.getChildren().add(randomRad );
		
		
		Button bCircle = new Button("Run Circle PI finder");
		GridPane.setConstraints(bCircle,0 ,1);
		grid.getChildren().add(bCircle);
		
		Button bTri = new Button("Run Triangle Area");
		GridPane.setConstraints(bTri,0,2);
		grid.getChildren().add(bTri);
		
		
		
		
		
		
		//	hb.getChildren().addAll(TF, TFRolls);
		//	hb.setSpacing(20);		
		//	grid.getChildren().add(header);
		
		grid.getChildren().add(hb);
		
		primaryStage.setTitle("Menu");
		primaryStage.setScene(scene); 
		primaryStage.setResizable(false);
		primaryStage.show(); 
		
		
		TFRolls.setOnAction(new EventHandler<ActionEvent>() {
		      public void handle(ActionEvent e) {
		        System.out.println(TFRolls.getText());
		        
		        fb = TFRolls.getText();
		        
		        triangleManager.pointAmount = Integer.parseInt(TFRolls.getText());
		        circleManager.repeatCounter = Integer.parseInt(TFRolls.getText());
		      }
		    });
		
		
		bDebug.setOnAction(new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent e) {
		        
		    	if (triangleManager.debug != true) {
		    		triangleManager.debug = true;
		    	}
		    	else {triangleManager.debug = false;}
		    	
		    	if (circleManager.debug != true) {
		    		circleManager.debug = true;
		    	}
		    	else {circleManager.debug = false;}
		    	
		    }
		}); 
		

		randomRad.setOnAction(new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent e) {
		        
		   
		    	circleManager.circleRad = Math.random()*220 + 50;
		    	
		    }
		}); 
		
		
		
		bCircle.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            
            public void handle(ActionEvent event) {
                
            	circleManager CT = new circleManager();
            	menuManager mm = new menuManager();
            	try {
            		
            		
            		circleManager.repeatCounter = Integer.parseInt(TFRolls.getText());
					CT.start(primaryStage);
					int wait = circleManager.repeatCounter/6;
					if (wait > 5000) {wait = 5000;}
					if (wait < 700) {wait = 2500;}
					Thread.sleep(wait);
					mm.start(primaryStage);
					
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}
               
            }
        });
		
		
		
		bTri.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            
            public void handle(ActionEvent event) {
               
            	triangleManager TM = new triangleManager();
            	menuManager mm = new menuManager();
            	try {
            		
            		triangleManager.pointAmount = Integer.parseInt(TFRolls.getText());
            		
					TM.start(primaryStage);
					int wait = triangleManager.pointAmount/6;
					if (wait > 6000) {wait = 7500;}
					if (wait < 800) {wait = 2500;}
					Thread.sleep(wait);
					
					mm.start(primaryStage);
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}
                
            }
        });
	}

}
