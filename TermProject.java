/**
 Term Project - Predator Prey model

 Authors: Josh Rosenfeld & Marco Caniglia
 Current version written: May 2019
 Description: 
 */

// Import packages
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

public class TermProject{
		/*  Declare constants and parameters
			All constants are /day
		*/
		public static final double dt = 1.;        			// Time step (in days)
		public static final double tFinal = 20000.0;   		// Total time of simulation (in days)
		public static final double capFox = 20;       		// Carrying capacity, in thousands, of foxes
		public static final double capRabbit = 40;    		// Carrying capacity, in thousands, of rabbits
		public static final double capCarrot = 100;   		// Carrying capaciy, in thousands, of carrots
		
		
		public static double birthFox = 0.001;				// Birthrate of foxes				//gestation period for foxes ~50 days
		public static double birthRabbit = 0.001;			// Birthrate of rabbits				//gestation period for rabbits ~25 days
		public static double growCarrot = 0.001;			// Growing rate of carrots			//~70 days for carrots to mature
		public static double deathFox = 0.003;				// Deathrate of foxes
		public static double deathRabbit = 0.0005;			// Deathrate of rabbits
		public static double foxHuntRate = 	0.0005;			// Rate at which foxes eat rabbits
		public static double rabbitEatRate = 0.0035;		// Rate at which rabbits eat carrots
		public static double foxGrowRate = 0.001;			// Rate at which foxes increase by consuming rabbits
		public static double rabbitGrowRate = 0.0001;		//tte at which rabbits increase by consuming carrots
	
	 
		////////////////////////start main method///////////////////////////
	public static void main(String[] args)
    {
		//////////////open file to store data///////////////
		
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
			
		////////////initialize main vaiables for Euler's method////////////////
		//Populations:
		
		double Fox;		// Population of Foxes
        double Rabbit;	// Population of Rabbits
        double Carrot;	// Population of Carrots
        double time;	// Time of the simulation (in days)

        Fox = 5.;		// *thousand foxes
        Rabbit = 10.;	// thousand rabbits
        Carrot = 20.;	// thousand carrots
        time = 0.;
		
		///////////////////Print initial values to file using c-style printf////////////////
        outputFile.printf("%2.4f\t%6.1f\t%6.1f\t%6.1f\n",Fox,Rabbit,Carrot,time);
		
		///////////////////Using Euler's method to evolve the solution//////////////////////
       
	   
        /////////////////// Max number of steps/////////////////////////////////////////////
        int N = (int)(tFinal / dt) + 1;

        //////////////////////////// Euler loop ////////////////////////////////////////////
		/* 
		
		Logistic Growth:
		(cap-popsize)/cap = limits the growth of the population near maxima.
							At maximum, population does not grow much, but at minimum pop grows fast
	
		*/
		
		double[]timeArr = new double[N];
		double[]FoxArr = new double[N];
		double[]RabbitArr = new double[N];
		double[]CarrotArr = new double[N];
		
        for (int i = 1; i < N; i++)
		{
			Fox = Fox + ((capFox-Fox)/capFox)*((birthFox*Fox + foxGrowRate*Fox*Rabbit)*dt) - (deathFox*Fox*dt);
			FoxArr[i] = Fox;
			
            Rabbit = Rabbit + ((capRabbit-Rabbit)/capRabbit) * ((Rabbit*birthRabbit + rabbitGrowRate*Rabbit*Carrot)*dt) - (foxHuntRate*Fox*Rabbit*dt) - (deathRabbit*Rabbit*dt);
			RabbitArr[i] = Rabbit;
			
            Carrot = Carrot + ((capCarrot-Carrot)/capCarrot) * (growCarrot*Carrot*dt) - (rabbitEatRate*Rabbit*dt);
			CarrotArr[i] = Carrot;
			
            time = time + dt;
			timeArr[i] = time;
			
			
			//if(i%20==0){
				//System.out.println("Foxes: " + (Fox) + " \tRabbits: " + (Rabbit) + "\tCarrots: " + (Carrot) + "\tTime:" + time);
			//}
		}
	
		plot(timeArr, FoxArr, RabbitArr, CarrotArr);
	}
	

        //////////////////// work to plot the data////////////////////////////

	public static void plot(double[]time, double[] Fox, double[]Rabbit, double[] Carrot){
		// Reimporting the data to store them as an array
        int i = 0;

        // Create a PlotPanel (you can use it like a JPanel)
        Plot2DPanel plot = new Plot2DPanel();

        // Define the legend position
        plot.addLegend("SOUTH");

        // Add a line plot to the PlotPanel
        plot.addLinePlot("Fox", time, Fox);
        plot.addLinePlot("Rabbit", time, Rabbit);
        plot.addLinePlot("Carrot", time, Carrot);
        plot.setAxisLabel(0,"Time (days)");
        plot.getAxis(0).setLabelPosition(0.5,-0.1);
        plot.setAxisLabel(1,"Population (thousands)");
        BaseLabel title = new BaseLabel("Fox&Rabbit Predator Prey Model", Color.BLACK, 0.5, 1.1);
        title.setFont(new Font("Courier", Font.BOLD, 18));
        plot.addPlotable(title);

        // Add maximums
        String[] scales = new String[2];
        scales = plot.getAxisScales();
       
        double maxZ = Fox[Fox.length-1];
        double maxS = Rabbit[Rabbit.length-1];
        double maxR = Carrot[Carrot.length-1];
        double maxT = time[time.length-1];
        String labelZ = new String("Max: "+maxZ);
        plot.addLabel(labelZ,plot.COLORLIST[0], maxT*1.1, maxZ);
        String labelS = new String("Max: "+maxS);
        plot.addLabel(labelS,plot.COLORLIST[1], maxT*1.1, maxS);
        String labelR = new String("Max: "+maxR);
        plot.addLabel(labelR,plot.COLORLIST[2], maxT*1.1, maxR);


        JFrame frame = new JFrame("Output of Fox.java");
        frame.setSize(525, 525);
        frame.setContentPane(plot);
        frame.setVisible(true);
	}
}
		
		
		
		
		
		
		

		