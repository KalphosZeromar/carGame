import java.io.*;
import javax.imageio.ImageIO;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.*;

public class car extends JPanel implements ActionListener,KeyListener{
	public BufferedImage img, rimg;
	public String pctrl1,pctrl2,pctrl3;
	public double angle = 1.57079633,rad = 0.39269908, sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
	public int ddir, keyCode;
	public Color c0 = new Color(60,60,60);
	public Color c1 = Color.green, c2 = Color.cyan , c3 = Color.yellow , c4 = Color.magenta;
	public car(String filename, int dir){
		try {
			img = ImageIO.read(getClass().getResource(filename));
			rimg = rotatoImago(img);
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, "ERROR image not found");
		}
		ddir = dir;
		Timer time = new Timer(33, this);
		//System.out.print(img);
		repaint();
		/*try{Thread.sleep(4000);}
		catch(Exception e) {
		}*/
		repaint();
		time.start();
	}
	public Dimension getsize() {
		return img == null ? new Dimension(200, 200): new Dimension(img.getWidth(), img.getHeight());
	}
	@Override
	public void paintComponent(Graphics g) {
		//System.out.println("paint "+img);
		super.paintComponent(g);
		g.setColor(c0);
		g.fillRect(50, 100, 750, 500);
		g.setColor(c1); 
		g.fillRect(150,200,550,300);
		g.setColor( c2 );
		g.drawRect( 50, 100, 750, 500 );
		g.drawRect( 150, 200, 550, 300 );
		g.setColor( c3 );
		g.drawRect( 100, 150, 650, 400 );
		g.setColor( c4 );
		g.drawLine( 425, 500, 425, 600 );
		if (rimg != null) {
			//System.out.println("turn "+img);
            Graphics2D g2d = (Graphics2D) g.create();
            int x = (getWidth() - rimg.getWidth()) / 2;
            int y = (getHeight() - rimg.getHeight()) / 2;
            g2d.drawImage(rimg, x, y, this);
            g2d.dispose();
        }
		//g.drawImage(img,100,100, this);
		//repaint();
	}
	/*public time {
		repaint();
	}*/
	@Override
    public void keyTyped(KeyEvent e) {
		int keyCode = e.getKeyCode();
        System.out.println("Key Pressed: " + keyCode);
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println("repaint "+img);
		if (keyCode == KeyEvent.VK_A) {
            ddir = 1;
        }
        else if (keyCode == KeyEvent.VK_D) {
        	ddir = 2;
        }
		switch(ddir) {
		case 0://no turn
			break;
		case 1://turn left
			angle-=rad;
			break;
		case 2://turn right
			angle+=rad;
			break;
		}
		rimg = rotatoImago(img);
		repaint();
	}
    public void keyPressed(KeyEvent e) {
        keyCode = e.getKeyCode();
        System.out.println("Key Pressed: " + keyCode);
    }
	public BufferedImage rotatoImago(BufferedImage img) {
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);
        BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2, (newHeight - h) / 2);
        int x = w / 2;
        int y = h / 2;
        at.rotate(angle, x, y);
        g2.setTransform(at);
        g2.drawImage(img, 0, 0, this);
        g2.dispose();
        return rotated;
    }
	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
        System.out.println("Key Released: " + keyCode);
	}
}