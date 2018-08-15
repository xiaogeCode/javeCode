package russiaCuteGame;
import java.awt.Dialog;
import java.awt.Dimension;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AlertDialog{
	JDialog dialog= null;
	private JLabel label = new JLabel();
	int secends = 0;
	public AlertDialog(){
		
	}
	public void showDialog(JFrame father, String message, int sec){
		secends = sec; 
		
		label.setText(message); 
		label.setBounds(150,6,200,20);  
		
		dialog = new JDialog(father, true);  
		dialog.setTitle("将在"+sec+"秒内消失");
		dialog.setLayout(null);  
		dialog.add(label);

		ScheduledExecutorService s = Executors.newSingleThreadScheduledExecutor();
		 s.scheduleAtFixedRate(new Runnable() {
			 @Override 
		      public void run() { 
		    	  // TODO Auto-generated method stub  
				 AlertDialog.this.secends--;  
				 if(AlertDialog.this.secends == 0) {  
	                	AlertDialog.this.dialog.dispose(); 
	             }else {  
	                     dialog.setTitle("将在"+secends+"秒内消失");
	             }   
			 }
		  }, 1, 1, TimeUnit.SECONDS);       
		 
		 dialog.pack();
		 dialog.setSize(new Dimension(350,100));  
		 dialog.setLocationRelativeTo(father);
		 dialog.setVisible(true);
	} 

}
