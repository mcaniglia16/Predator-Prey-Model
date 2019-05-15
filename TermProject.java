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
        Carrot = 40.;	// thousand carrots
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
		
        for (int i = 1; i < (N/2); i++) //Normal behavior from year 0 to year 50
		{
			Fox = Fox + ((capFox-Fox)/capFox)*((birthFox*Fox + foxGrowRate*Fox*Rabbit)*dt) - (deathFox*Fox*dt);
			FoxArr[i] = Fox;
			
            Rabbit = Rabbit + ((capRabbit-Rabbit)/capRabbit) * ((Rabbit*birthRabbit + rabbitGrowRate*Rabbit*Carrot)*dt) - (foxHuntRate*Fox*Rabbit*dt) - (deathRabbit*Rabbit*dt);
			RabbitArr[i] = Rabbit;
			
            Carrot = Carrot + ((capCarrot-Carrot)/capCarrot) * (growCarrot*Carrot*dt) - (rabbitEatRate*Rabbit*dt);
			CarrotArr[i] = Carrot;
			
            time = time + dt;
			timeArr[i] = time;
		}
		
		
		for (int i = (N/2); i < ((N/2)+365); i++) //Chemicals in the soil killing carrots for a year form year 50 until year 51
		{
			
			Fox = Fox + ((capFox-Fox)/capFox)*((birthFox*Fox + foxGrowRate*Fox*Rabbit)*dt) - (deathFox*Fox*dt);
			FoxArr[i] = Fox;
			
            Rabbit = Rabbit + ((capRabbit-Rabbit)/capRabbit) * ((Rabbit*birthRabbit + rabbitGrowRate*Rabbit*Carrot)*dt) - (foxHuntRate*Fox*Rabbit*dt) - (deathRabbit*Rabbit*dt);
			RabbitArr[i] = Rabbit;
			
            Carrot = Carrot + ((capCarrot-Carrot)/capCarrot) * (growCarrot*Carrot*dt) - (rabbitEatRate*Rabbit*dt) - (Carrot*chemicalSoilKillRate);
			CarrotArr[i] = Carrot;
			
            time = time + dt;
			timeArr[i] = time;
		} 
	
	 for (int i = ((N/2)+365); i < ((8*N)/10); i++) //Normal behaviour from year 51 until year 80
		{
			Fox = Fox + ((capFox-Fox)/capFox)*((birthFox*Fox + foxGrowRate*Fox*Rabbit)*dt) - (deathFox*Fox*dt);
			FoxArr[i] = Fox;
			
            Rabbit = Rabbit + ((capRabbit-Rabbit)/capRabbit) * ((Rabbit*birthRabbit + rabbitGrowRate*Rabbit*Carrot)*dt) - (foxHuntRate*Fox*Rabbit*dt) - (deathRabbit*Rabbit*dt);
			RabbitArr[i] = Rabbit;
			
            Carrot = Carrot + ((capCarrot-Carrot)/capCarrot) * (growCarrot*Carrot*dt) - (rabbitEatRate*Rabbit*dt);
			CarrotArr[i] = Carrot;
			
            time = time + dt;
			timeArr[i] = time;
		}
		
		 for (int i = ((8*N)/10); i < (((8*N)/10)+14); i++) //Fox population is hunted for their fur for 2 weeks starting at year 80
		{
	
			Fox = Fox + ((capFox-Fox)/capFox)*((birthFox*Fox + foxGrowRate*Fox*Rabbit)*dt) - (deathFox*Fox*dt) - (furHuntersKillRate*Fox*dt);
			FoxArr[i] = Fox;
			
            Rabbit = Rabbit + ((capRabbit-Rabbit)/capRabbit) * ((Rabbit*birthRabbit + rabbitGrowRate*Rabbit*Carrot)*dt) - (foxHuntRate*Fox*Rabbit*dt) - (deathRabbit*Rabbit*dt);
			RabbitArr[i] = Rabbit;
			
            Carrot = Carrot + ((capCarrot-Carrot)/capCarrot) * (growCarrot*Carrot*dt) - (rabbitEatRate*Rabbit*dt);
			CarrotArr[i] = Carrot;
			
            time = time + dt;
			timeArr[i] = time;
		}
		
		for (int i = (((8*N)/10)+14); i < N; i++) //Normal behaviour from two weeks into year 80 until year 100
		{
			Fox = Fox + ((capFox-Fox)/capFox)*((birthFox*Fox + foxGrowRate*Fox*Rabbit)*dt) - (deathFox*Fox*dt);
			FoxArr[i] = Fox;
			
            Rabbit = Rabbit + ((capRabbit-Rabbit)/capRabbit) * ((Rabbit*birthRabbit + rabbitGrowRate*Rabbit*Carrot)*dt) - (foxHuntRate*Fox*Rabbit*dt) - (deathRabbit*Rabbit*dt);
			RabbitArr[i] = Rabbit;
			
            Carrot = Carrot + ((capCarrot-Carrot)/capCarrot) * (growCarrot*Carrot*dt) - (rabbitEatRate*Rabbit*dt);
			CarrotArr[i] = Carrot;
			
            time = time + dt;
			timeArr[i] = time;
		}
	
		plot(timeArr, FoxArr, RabbitArr, CarrotArr);
	}
	
        //////////////////// work to plot the data////////////////////////////
							//// in a method  ////
							
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
        plot.addLabel(labelF,plot.COLORLIST[0], maxT*1.1, maxF);
        String labelR = new String("Max: "+maxR);
        plot.addLabel(labelR,plot.COLORLIST[1], maxT*1.1, maxR);
        String labelC = new String("Max: "+maxC);
        plot.addLabel(labelC,plot.COLORLIST[2], maxT*1.1, maxC);


        JFrame frame = new JFrame("Output of Fox.java");
        frame.setSize(525, 525);
        frame.setContentPane(plot);
        frame.setVisible(true);
	}
}
		
		
		
		
		
		
		

		