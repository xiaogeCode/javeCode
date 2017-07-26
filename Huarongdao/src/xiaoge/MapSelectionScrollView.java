package xiaoge;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ScrollPane;

import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class MapSelectionScrollView extends JPanel {

	JPanel pane = new JPanel(new BorderLayout());
	ScrollPane sp = new ScrollPane();
	public MapSelectionScrollView(){
		super();
		setSize(200, 100);
		setLocation(0,0);
		setBackground(new Color(100,100,100));
		
		add(sp);
		
		//setVisible(true);
		//setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		//setDefaultCloseOperation(WindowConstans.DISPOSE_ON_CLOSE);
	}
}
