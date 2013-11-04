import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-11-4
 * Time: 上午11:57
 * To change this template use File | Settings | File Templates.
 */

class SwingConsole {
    public static void run( final JFrame f,
                            final int width, final int height,
                            final String str) {
        SwingUtilities.invokeLater(new Runnable() {//此出创建一个继承自Runnable的匿名类对象
            public void run() {
                f.setTitle(str);
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setSize(width, height);
                f.setVisible(true);
                System.out.println("test");
            }
        });
    }
}

class SineDraw extends JPanel{
    private static final int SCALEFACTOR = 200;
    private int cycles;
    private int points;
    private double[] sines;
    private int[] pts;
    public SineDraw() { setCycles(5); }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);//如果写成super.paintComponents();原来的痕迹不会擦除
        int maxWidth = getWidth();
        double hstep = (double)maxWidth / (double)points;
        int maxHeight = getHeight();
        pts = new int[points];
        for (int i=0; i<points; i++) {
            pts[i] = (int)(sines[i] * maxHeight/2 * .95 + maxHeight/2);
        }
        g.setColor(Color.BLACK);
        for (int i=1; i<points; i++) {
            int x1 = (int)((i-1) * hstep);
            int x2 = (int)(i * hstep);
            int y1 = pts[i-1];
            int y2 = pts[i];
            g.drawLine(x1, y1, x2, y2);
        }
    }
    public void setCycles(int newCycles) {
        cycles = newCycles;
        points = SCALEFACTOR * cycles * 2;
        sines = new double[points];
        for (int i=0; i<points; i++) {
            double radians = (Math.PI / SCALEFACTOR) * i;
            sines[i] = Math.sin(radians);
        }
        repaint();
    }
}

public class SineWave extends JFrame {
    private SineDraw sines = new SineDraw();
    private JSlider adjustCycles = new JSlider(1, 30, 5);
    public SineWave() {
        add(sines);
        adjustCycles.addChangeListener( new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                //To change body of implemented methods use File | Settings | File Templates.
                sines.setCycles(((JSlider)changeEvent.getSource()).getValue());
            }
        });
        add(BorderLayout.SOUTH,adjustCycles);
    }
    public static void main(String[] args) {
        SwingConsole.run(new SineWave(), 700, 400, "Fuck Wellgain");
    }
}
