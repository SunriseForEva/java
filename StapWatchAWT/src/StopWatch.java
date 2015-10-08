import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class StopWatch extends JPanel implements Runnable,KeyListener
{
	private Timer myTimer1;
	public static final int ONE_SEC = 1000;   //time step in milliseconds
	public static final int TENTH_SEC = 100;

	private Font myClockFont;

	private JButton startBtn, stopBtn, resetBtn;
	private JLabel timeLbl;
	private JPanel topPanel, bottomPanel;

	private int clockTick;  	//number of clock ticks; tick can be 1.0 s or 0.1 s
	private double clockTime;  	//time in seconds
	private String clockTimeString;
	private String operation="plus";

	public StopWatch()
	{
		clockTick = 0;  		//initial clock setting in clock ticks
		clockTime = ((double)clockTick)/10.0;

		clockTimeString = new Double(clockTime).toString();
		myClockFont = new Font("Serif", Font.PLAIN, 50);

		timeLbl = new JLabel();
		timeLbl.setFont(myClockFont);
		timeLbl.setText(clockTimeString);

		startBtn = new JButton("Start");
		stopBtn = new JButton("Stop");
		resetBtn = new JButton("Reset");
		addKeyListener(this);
		this.setFocusable(true);
        this.requestFocusInWindow();


		stopBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				myTimer1.stop();
				setFocusable(true);
		        requestFocusInWindow();
			}
		});

		resetBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				clockTick = 0;
				clockTime = ((double)clockTick)/10.0;
				clockTimeString = new Double(clockTime).toString();
				timeLbl.setText(clockTimeString);
				setFocusable(true);
		       requestFocusInWindow();
			}
		});

	}//end of StopWatch constructor

	public void launchStopWatch()
	{
		topPanel = new JPanel();
		topPanel.setBackground(Color.orange);
		bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.yellow);
		topPanel.add(timeLbl);
		bottomPanel.add(startBtn);
		bottomPanel.add(stopBtn);
		bottomPanel.add(resetBtn);

		this.setLayout(new BorderLayout());

		add(topPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
		
		setSize(300,200);
		setBackground(Color.orange);

	}//end of launchClock

	

	@Override
	public void run() {
		Thread t=Thread.currentThread();
		
			
			startBtn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					myTimer1.start();
					setFocusable(true);
			        requestFocusInWindow();
				}
			});
			
			
			myTimer1 = new Timer(TENTH_SEC, new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					
					if(operation.equals("minus"))
					{
						clockTick--;
					}
					else
					{
						clockTick++;
					}
					
					clockTime = ((double)clockTick)/10.0;
					if(clockTime<=0)
					{
						myTimer1.stop();
						operation="plus";
					}
					clockTimeString = new Double(clockTime).toString();
					timeLbl.setText(clockTimeString);
					//System.out.println(clockTime);
				    }
				});
			
			System.out.println("Info "+t.getName());
			
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
		int keyCode = arg0.getKeyCode();
	    switch( keyCode ) { 
	        case KeyEvent.VK_UP:
	        	
	            operation="plus";
	            break;
	        case KeyEvent.VK_DOWN:
	        	
	        	 operation="minus";
	            break;
	        case KeyEvent.VK_LEFT:
	        	 operation="plus";
	            break;
	        case KeyEvent.VK_RIGHT :
	        	 operation="minus";
	            break;
	     }
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args)
	{
		MyTestFrame myTestFrame1 = new MyTestFrame();
		
		
	}

}//end of public class

//Testing Code

class MyTestFrame extends JFrame
{
	StopWatch StopWatch1,StopWatch2,StopWatch3,StopWatch4;

	public MyTestFrame()
	{
		super("My Stop Watch");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container myPane = getContentPane();
		myPane.setLayout(new GridLayout(0,2));
		StopWatch1 = new StopWatch();
		StopWatch2 = new StopWatch();
		StopWatch3 = new StopWatch();
		StopWatch4 = new StopWatch();
		
		StopWatch1.launchStopWatch();
		StopWatch2.launchStopWatch();
		StopWatch3.launchStopWatch();
		StopWatch4.launchStopWatch();
		
		
		Thread t1=new Thread(StopWatch1);
		Thread t2=new Thread(StopWatch2);
		Thread t3=new Thread(StopWatch3);
		Thread t4=new Thread(StopWatch4);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		/*try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}*/
		
		myPane.add(StopWatch1);
		myPane.add(StopWatch2);
		myPane.add(StopWatch3);
		myPane.add(StopWatch4);
		pack();
		setVisible(true);
	}
}