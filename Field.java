package Package6;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
@SuppressWarnings("serial")
public class Field extends JPanel
{
    private boolean paused;
    ArrayList<BouncingBall> balls = new ArrayList<BouncingBall>(10);
    private Timer repaintTimer = new Timer(10, new ActionListener()
    {
        public void actionPerformed(ActionEvent ev)
        {
            repaint();
        }
    });
    public Field()
    {
        setBackground(Color.WHITE);
        this.addMouseListener(new Field.MouseHandler());
        this.addMouseMotionListener(new Field.MouseMotionHandler());
        repaintTimer.start();
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D canvas = (Graphics2D) g;
        for (BouncingBall ball: balls)
        {
            ball.paint(canvas);
        }
    }
    public void addBall()
    {
        balls.add(new BouncingBall(this));
    }
    public synchronized void pause()
    {
        paused = true;
    }
    public synchronized void resume()
    {
        paused = false;
        notifyAll();
    }
    public synchronized void canMove(BouncingBall ball) throws InterruptedException
    {
        if (paused)
        {
            wait();
        }
    }
    public class MouseHandler extends MouseAdapter
    {
        public MouseHandler()
        {

        }

        public void mouseClicked(MouseEvent ev)
        {

        }

        public synchronized void mousePressed(MouseEvent ev)
        {
            for (int i = 0; i < balls.size(); i++)
            {
                if ((Math.abs(ev.getX() - balls.get(i).x)) <= balls.get(i).radius && (Math.abs(ev.getY() - balls.get(i).y)) <= balls.get(i).radius)
                {
                    pause();
                }
            }
        }

        public void mouseReleased(MouseEvent ev)
        {
            resume();
        }
    }
    public class MouseMotionHandler implements MouseMotionListener
    {
        public MouseMotionHandler()
        {

        }

        public void mouseMoved(MouseEvent ev)
        {

        }

        public void mouseDragged(MouseEvent ev)
        {

        }
    }
}


