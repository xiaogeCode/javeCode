package xiaoge;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.PixelGrabber;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.awt.AWTUtilities; 

public class AlpahBGMain extends JFrame{

	/**
	 * @param args
	 */
	ImageIcon ii1 = new ImageIcon("E:\\pic\\ͼƬ\\betterfly.png");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		new AlpahBGMain();
	}
	public AlpahBGMain(){
		setUndecorated(true);
		setSize( ii1.getIconWidth(), ii1.getIconHeight());
		setLocation(0, 0);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		//setResizable(false);
		//setBackground(new Color(0, 0, 0, 0));
		getRootPane().setDoubleBuffered(false);
		setOpacity((float) 0.8);
		//Shape  shape = new RoundRectangle2D.Double(0d, 0d, getWidth(), getHeight(), 200, 200); 
		//com.sun.awt.AWTUtilities.setWindowOpacity(this,(float) 0.5);
		//com.sun.awt.AWTUtilities.setWindowOpaque(this,false);
		//AWTUtilities.setWindowShape(this, shape);  

		Polygon polygon = new Polygon();
        polygon.addPoint(0, 200);
        polygon.addPoint(100, 0);
        polygon.addPoint(200, 200);
        
		Ellipse2D.Double theCircle = new Ellipse2D.Double(0, 100, 1.0*200, 1.0*200);
		GeneralPath path = new GeneralPath();
		path.append(polygon, true);
		path.append(theCircle, true);
		//setShape(path);
		
		AWTUtilities.setWindowShape(this, getImageShape(ii1.getImage()));
		



		JPanel p = new JPanel() {
			public void paintComponent(Graphics g){
				//@Override
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g; 
				//ImageIcon ii1 = new ImageIcon("E:\\pic\\ͼƬ\\betterfly.png");
				g2.drawImage(ii1.getImage(), 0, 0, this.getWidth() ,this.getHeight(),null);
				
			}
		};
		p.setLayout(null); 
		setContentPane(p);
		JButton b1=new JButton("exit");
		b1.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e){ 
				System.exit(0);
			}
		});
		b1.setBounds(220,80,60,25);
		b1.setOpaque(false);
		p.add(b1);


		
		


		
		
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void paint(Graphics g){
		//@Override
		super.paint(g);
		//Graphics2D g2 = (Graphics2D) g; 
		//ImageIcon ii1 = new ImageIcon("E:\\pic\\ͼƬ\\betterfly.png");
		//g2.drawImage(ii1.getImage(), 0, 0, this.getWidth() ,this.getHeight(),null);
		
	}


/** 


     * ��Imageͼ��ת��ΪShapeͼ�� 


     *  


     * @param img 


     * @param isFiltrate 


     * @return Imageͼ���Shapeͼ�α�ʾ 


     * @author Hexen 


     */ 


    public Shape getImageShape(Image img) { 
    	ArrayList<Integer> x = new ArrayList<Integer>();
        ArrayList<Integer> y = new ArrayList<Integer>(); 

        int width = img.getWidth(null);// ͼ���� 
        int height = img.getHeight(null);// ͼ��߶�  
        // ɸѡ���� 
        // ���Ȼ�ȡͼ�����е�������Ϣ 
        PixelGrabber pgr = new PixelGrabber(img, 0, 0, -1, -1, true); 
        try { 
            pgr.grabPixels(); 
        } catch (InterruptedException ex) { 
            ex.getStackTrace(); 
        } 

        int pixels[] = (int[]) pgr.getPixels();  
        // ѭ������ 
        for (int i = 0; i < pixels.length; i++) { 
            // ɸѡ������͸�������ص�������뵽����ArrayList x��y�� 
            int alpha = getAlpha(pixels[i]); 
            if (alpha == 0) { 
                continue; 
            } else { 
                x.add(i % width > 0 ? i % width - 1 : 0); 
                y.add(i % width == 0 ? (i == 0 ? 0 : i / width - 1) : i / width); 
            } 
        }  

        // ����ͼ����󲢳�ʼ��(0Ϊ͸��,1Ϊ��͸��) 
        int[][] matrix = new int[height][width]; 
        for (int i = 0; i < height; i++) { 
            for (int j = 0; j < width; j++) { 
                matrix[i][j] = 0; 
            } 
        } 
        // ��������ArrayList�еĲ�͸��������Ϣ 
        for (int c = 0; c < x.size(); c++) { 
            matrix[y.get(c)][x.get(c)] = 1; 
        } 
        /* 
         * ��һˮƽ"ɨ��"ͼ������ÿһ�У�����͸������������ΪRectangle���ٽ�ÿһ�е�Rectangleͨ��Area���rec������кϲ��� 
         * ����γ�һ��������Shapeͼ�� 
         */ 
        Area rec = new Area(); 
        int temp = 0; 
        for (int i = 0; i < height; i++) { 
            for (int j = 0; j < width; j++) { 
                if (matrix[i][j] == 1) { 
                    if (temp == 0) 
                        temp = j; 
                    else if (j == width) { 
                        if (temp == 0) { 
                            Rectangle rectemp = new Rectangle(j, i, 1, 1); 
                            rec.add(new Area(rectemp)); 
                        } else { 
                            Rectangle rectemp = new Rectangle(temp, i, j - temp, 1); 
                            rec.add(new Area(rectemp)); 
                            temp = 0; 
                        } 
                    } 
                } else { 
                    if (temp != 0) { 
                        Rectangle rectemp = new Rectangle(temp, i, j - temp, 1); 
                        rec.add(new Area(rectemp)); 
                        temp = 0; 
                    } 
                } 
            } 
            temp = 0; 
            } 
        return rec; 
    } 

/** 


     * ��ȡ���ص�Alphaֵ 
     *  
     * @param pixel 
     * @return ���ص�Alphaֵ 
     */ 
    private int getAlpha(int pixel) { 
        return (pixel >> 24) & 0xff; 
    } 


}
