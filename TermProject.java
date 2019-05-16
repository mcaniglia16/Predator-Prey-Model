/**
* 			Term Project - Predator Prey model. Fox, Rabbit, and Carrot Populations
*
* 				Authors: Josh Rosenfeld & Marco Caniglia
*				Current version written: May 2019
* 
*/

// Import all packages
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.util.Scanner;
import org.math.plot.*;
import org.math.plot.plotObjects.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;  // For the JOptionPane class
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

public class TermProject{
		/*  Declare constants and parameters
			All constants are /day
		*/
		public static final double dt = 1.;        			// Time step (in days)
		public static final double tFinal = 36525.0;   		// Total time of simulation (in days)
		public static final double capFox = 18.0;       	// Carrying capacity, in thousands, of foxes
		public static final double capRabbit = 40.0;		// Carrying capacity, in thousands, of rabbits
		public static final double capCarrot = 100.0;		// Carrying capaciy, in thousands, of carrots
		
		public static double birthFox = 0.0001;				// Birthrate of foxes without prey (rabbits)
		public static double birthRabbit = 0.0001;			// Birthrate of rabbits	without vegetation (carrots)			
		public static double growCarrot = 0.0022;			// Growing rate of carrots			
		public static double deathFox = 0.0005;				// Deathrate of foxes
		public static double deathRabbit = 0.0005;			// Deathrate of rabbits
		public static double foxHuntRate = 	0.0001;			// Rate at which foxes kills and eats rabbits per encounter
		public static double rabbitEatRate = 0.003;			// Rate at which rabbits eat carrots
		public static double foxGrowRate = 0.000175;		// Rate at which foxes increase by consuming rabbits per encounter
		public static double rabbitGrowRate = 0.00012;		// Rate at which rabbits increase by consuming carrots
		public static double chemicalSoilKillRate = 0.0015; // Rate at which the chemicals kill carrots
		public static double furHuntersKillRate = 0.06;		// Rate at which the hunters kill the foxes for their fur
	
 
		////////////////////////start main method///////////////////////////
	public static void main(String[] args)
    {
		////////////////////////open file to store data/////////////////////
		
		String project = "FoxVsRabbitsOutput.txt";
		PrintWriter outputFile = null;
		try
		{
			outputFile = new PrintWriter(new FileOutputStream(project, false));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File error. Program Fails.");
			System.exit(0);
		}
			
		///////////initialize main vaiables for Euler's method//////////////
		//Populations:
		
		double Fox;				// Population of Foxes
        double Rabbit;			// Population of Rabbits
        double Carrot;			// Population of Carrots
        double time;			// Time of the simulation (in days)
		
        Fox = 5.;				// *thousand foxes
        Rabbit = 10.;			// thousand rabbits
        Carrot = 40.;			// thousand carrots
        time = 0.;
		
		///////////////////Print initial values to file using c-style printf////////////////
        outputFile.printf("%2.4f\t%6.1f\t%6.1f\t%6.1f\n",Fox,Rabbit,Carrot,time);
		
		/////////////////////Use Euler's method to evolve the solution//////////////////////
      
        int N = (int)(tFinal / dt) + 1;				//Max number of steps

		double[]timeArr = new double[N];
		double[]FoxArr = new double[N];
		double[]RabbitArr = new double[N];
		double[]CarrotArr = new double[N];
		
		
		////////////////////////real time graph using XChart library/////////////////////////
		
			SwingWrapper<XYChart> sw;				//Swing Wrapper class in XChart library
			XYChart chart;
		
			chart = QuickChart.getChart("Fox & Rabbit Real Time Plot", "Time", "Value", new String[] {"Fox", "Rabbit", "Carrot"}, new double[] { 0 }, new double[][] {{0}, {0}, {0}});
			chart.getStyler().setLegendVisible(true);
			chart.getStyler().setXAxisTicksVisible(true);
 
			sw = new SwingWrapper<XYChart>(chart);
			sw.displayChart();
		
			double[][] mostRecentDataSet = new double[3][100];
			int range = 5000;						//range for real time graph		
		
		////////////////////////////// Euler loop ////////////////////////////////////////////
		/*** 
		
		Logistic Growth:
		(cap-popsize)/cap = limits the growth of the population near maxima.
							At maximum, population does not grow much, but at minimum pop grows fast
	
		***/
		
		
		for (int i = 1; i < N; i++) 
		{
			
			Fox = Fox + ((capFox-Fox)/capFox)*((birthFox*Fox + foxGrowRate*Fox*Rabbit)*dt) - (deathFox*Fox*dt);
			if(i>=(80*365.25) && i<=((80*365.25)+14)){
				Fox -= (furHuntersKillRate*Fox*dt);				//Fox population is hunted for their fur for 2 weeks starting at year 80
			}				
			FoxArr[i] = Fox;
			
		
            Rabbit = Rabbit + ((capRabbit-Rabbit)/capRabbit) * ((Rabbit*birthRabbit + rabbitGrowRate*Rabbit*Carrot)*dt) - (foxHuntRate*Fox*Rabbit*dt) - (deathRabbit*Rabbit*dt);
			RabbitArr[i] = Rabbit;
			
			
            Carrot = Carrot + ((capCarrot-Carrot)/capCarrot) * (growCarrot*Carrot*dt) - (rabbitEatRate*Rabbit*dt);
			if(i>=(50*365.25) && i<=(51*365.25)){
				Carrot -= Carrot*chemicalSoilKillRate;			//Chemicals in the soil killing carrots for a year form year 50 until year 51
			}				
			CarrotArr[i] = Carrot;
			
			
            time = time + dt;
			timeArr[i] = time;
			
			try { Thread.sleep(1); } catch(Exception e) {}		//SLEEP used to slow down the real time graph.
			
			if (i>range && (i%10 == 0)) {
				double[] ta = Arrays.copyOfRange(timeArr, i-range, i);
				
				chart.updateXYSeries("Fox", ta, Arrays.copyOfRange(FoxArr, i-range, i), null);						//adds the Fox line to graph
				chart.updateXYSeries("Rabbit", ta, Arrays.copyOfRange(RabbitArr, i-range, i), null);				//adds the Rabbit line
				chart.updateXYSeries("Carrot", ta, Arrays.copyOfRange(CarrotArr, i-range, i), null);				//adds the Carrot line
				sw.repaintChart();

			}
			
		}
	
		plot(timeArr, FoxArr, RabbitArr, CarrotArr);
	}
	
        //////////////////// method to plot the data////////////////////////////
							
							
	public static void plot(double[]time, double[] Fox, double[]Rabbit, double[] Carrot){
		
        int i = 0;

        ////////////////////// Create PlotPanel /////////////////////////
        Plot2DPanel plot = new Plot2DPanel();

        /////////////////// Define the legend position///////////////////
        plot.addLegend("SOUTH");

        // Add a line plot to the PlotPanel
        plot.addLinePlot("Fox", Color.RED, Fox);
        plot.addLinePlot("Rabbit", Color.BLUE, Rabbit);
        plot.addLinePlot("Carrot", Color.ORANGE, Carrot);
        plot.setAxisLabel(0,"Time (days)");
		
		
        plot.getAxis(0).setLabelPosition(0.5,-0.1);
        plot.setAxisLabel(1,"Population (thousands)");
		
        BaseLabel title = new BaseLabel("Fox&Rabbit Predator Prey Model", Color.BLACK, 0.5, 1.1);
        title.setFont(new Font("Arial", Font.BOLD, 25));
        plot.addPlotable(title);

        ////////////////////// Add maxima////////////////////////////////
        String[] scales = new String[2];
        scales = plot.getAxisScales();
       
        double maxF = Fox[Fox.length-1];
        double maxR = Rabbit[Rabbit.length-1];
        double maxC = Carrot[Carrot.length-1];
        double maxT = time[time.length-1];
		
        String labelF = new String("Max: "+maxF);
        plot.addLabel(labelF,Color.RED, maxT*1.1, maxF);
        String labelR = new String("Max: "+maxR);
        plot.addLabel(labelR,Color.BLUE, maxT*1.1, maxR);
        String labelC = new String("Max: "+maxC);
        plot.addLabel(labelC,Color.ORANGE, maxT*1.1, maxC);


        JFrame frame = new JFrame("Output of Fox.java");
        frame.setSize(525, 525);
        frame.setContentPane(plot);
        frame.setVisible(true);
	}

}
		
		//useful links for the XChart library:
		//Class XYChart https://knowm.org/javadocs/xchart/org/knowm/xchart/XYChart.html
		//Class QuickChart https://knowm.org/javadocs/xchart/org/knowm/xchart/QuickChart.html
		
		
		
		
		

		