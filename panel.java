package brick;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class panel extends JPanel implements ActionListener,KeyListener {
	static final int w=600;
	static final int h=600;
	static final int unit=25;
	int posx=4*unit;
	final int delay=8;
	int dirx=-1;
	int diry=-2;
	int posy=15*unit;
	int panx=250;
	int totalbrick=21;
	int score;
	Timer timer;
	boolean run=false;
	mapgame m;
	public panel() {
		// TODO Auto-generated constructor stub
		setPreferredSize(new Dimension(w,h));
		setBackground(Color.black);
		setFocusable(true);
		addKeyListener(this);
		m=new mapgame(3,7);
		timer=new Timer(delay,this);
		timer.start();
	}
 @Override
	public void paint(Graphics g) {
		super.paint(g);
		//bordr
		g.setColor(Color.yellow);
		g.fillRect(0,0, 4, h);
		g.fillRect(596,0, 4, h);
		g.fillRect(0, 0, w, 4);
		//draw
		m.draw((Graphics2D)g);
		//ball
		g.setColor(Color.red);
		g.fillOval(posx,posy,unit,unit);
		//pane
		g.setColor(Color.green);
		g.fillRect(panx,590,90,10);
		//score
		g.setColor(Color.white);
		g.setFont(new Font("Ink free",Font.BOLD,30));
		g.drawString(""+score,540,25);
		//game over
		if(posy>600)
		{
			run=false;
			dirx=0;
			diry=0;
			g.setColor(Color.red);
			g.setFont(new Font("Ink free",Font.BOLD,30));
			g.drawString("gameover,score:"+score,190,300);
			
			g.setColor(Color.green);
			g.setFont(new Font("Ink free",Font.BOLD,30));
			g.drawString("press Enter to retart",190,350);
		}
		//win
		if(totalbrick<=0)
		{
			run=false;
			dirx=0;
			diry=0;
			g.setColor(Color.red);
			g.setFont(new Font("Ink free",Font.BOLD,30));
			g.drawString("you win",190,300);
			
			g.setColor(Color.green);
			g.setFont(new Font("Ink free",Font.BOLD,30));
			g.drawString("press Enter to retart",190,350);
			
		}
	}
 public void move()
 {
	 posx=posx+dirx;
	 posy=posy+diry;
	 if(posx<4)
	 {
		dirx=-dirx;
	 }
	 if(posy<0)
	 {
		 diry=-diry;
	 }
	 if(posx>580)
	 {
		 dirx=-dirx;
	 }
	 if(new Rectangle(posx,posy,unit,unit).intersects(new Rectangle(panx,590,90,10)))
	 {
		 diry=-diry;
	 }
 }

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		
		case KeyEvent.VK_RIGHT:
			if(panx>=505)
			{
				panx=505;
			}else {
				panx=panx+40;
				run=true;
			}
			break;
		case KeyEvent.VK_LEFT:
			if(panx<=4)
			{
				panx=4;
			}else {
				panx=panx-40;
				run=true;
			}
			break;
		case KeyEvent.VK_ENTER:
			if(!run)
			{
				run=true;
				 posx=4*unit;
				 dirx=-1;
				 diry=-2;
				posy=15*unit;
				 panx=250;
				totalbrick=21;
				score=0;
				m=new mapgame(3,7);
				repaint();
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(run)
		{
			move();
			
			a: for(int i=0;i<m.map.length;i++)
			{
				for(int j=0;j<m.map[0].length;j++)
				{
					if(m.map[i][j]>0)
					{
						int w=m.width;
						int h=m.higth;
						int bw=j*m.width+37;
						int bh=i*m.higth+50;
					Rectangle ra=new Rectangle(bw,bh,w,h);
					Rectangle rb=new Rectangle(posx,posy,unit,unit);
					if(rb.intersects(ra))
					{
						m.setbrick(0, i, j);
						score=score+5;
						totalbrick--;
						if(posx+19<=ra.x || posx+1>=ra.x+ra.width)
						{
							dirx=-dirx;
						}else {
							diry=-diry;
						}
						break a;
					}
					}
				}
			}
		}
		repaint();
		
	}
	

}
