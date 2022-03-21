package brick;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class mapgame {
	public int map[][];
	public int width;
	public int higth;
	public mapgame(int row,int col)
	{
		map=new int[row][col];
		for(int i=0;i<map.length;i++)
		{
			for(int j=0;j<map[0].length;j++)
			{
				map[i][j]=1;
			}
		}
		width=540/col;
		higth=150/row;
	}
	public void draw(Graphics2D g)
	{
		for(int i=0;i<map.length;i++)
		{
			for(int j=0;j<map[0].length;j++)
			{
				if(map[i][j]>0)
				{
					g.setColor(Color.WHITE);
					g.fillRect(j*width+37, i*higth+50,width,higth);
					
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j*width+37, i*higth+50,width,higth);
					
				}
			}
		}
	}
	public void setbrick(int val,int row,int col)
	{
		map[row][col]=val;
	}

}
