/**
 Term Project - Predator Prey model

 Authors: Josh Rosenfeld & Marco Caniglia
 Current version written: February 2019
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

public class TermProject{
		/*  Declare constants and parameters
			All constants are /day
		*/
		public static final double dt = 0.1;        		// Time step (in days)
		public static final double tFinal = 365.0;   		// Total time of simulation (in days)
		public static final double capFox = ; 5000      	// Carrying capacity of foxes
		public static final double capRabbits = 20000;      // Carrying capacity of rabbits
		public static final double capCarrots = 1000000;    // Carrying capaciy of carrots
		public static double numFox = ;						// Number of foxes
		public static double numRabbits = ;					// Number of rabbits
		public static double numCarrots = ;					// Number of carrots
		public static double birthFox = 0.005;				// Birthrate of foxes				//gestation period for foxes ~50 days
		public static double birthRabbit = 0.1;				// Birthrate of rabbits				//gestation period for rabbits ~25 days
		public static double growCarrot = 0.5;				// Growing rate of carrots			//~70 days for carrots to mature
		public static double deathFox = 0.0025;				// Deathrate of foxes
		public static double deathRabbit = 0.05;			// Deathrate of rabbits
		public static double foxHuntRate = 	2;				// Rate at which foxes eat rabbits
		public static double rabbitEatRate = 200;				// Rate at which rabbits eat carrots
	
	
	//start main method
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
		double
		double
		double 
		double 
		
		
		
		
		
		
		
		
        //////////////////// work to plot the data////////////////////////////

		// Reimporting the data to store them as an array
        int i = 0;

        // Open the file
        File file = new File(program);

        // Allocate the arrays
        double[] Time = new double[N];
        double[] Foxes = new double[N];
        double[] Rabbits = new double[N];
        double[] Carrots = new double[N];

        try
        {
            Scanner inputFile = new Scanner(file);
            while (inputFile.hasNext() && i < N)
            {
                try
                {
                    String str = inputFile.next();
                    Time[i] = Double.parseDouble(str);

                    str = inputFile.next();
                    Foxes[i] = Double.parseDouble(str);

                    str = inputFile.next();
                    Rabbits[i] = Double.parseDouble(str);

                    str = inputFile.next();
                    Carrots[i] = Double.parseDouble(str);
                }
                catch (java.util.InputMismatchException ex)
                {
                    System.out.println("value " + time[i]);
                }

                i++;
            }
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("File not found.");
        }


        
       /////////////////// Now we can plot ///////////////////////////

        // Create a PlotPanel 
        Plot2DPanel plot = new Plot2DPanel();

        // Define the legend position
        plot.addLegend("SOUTH");

        // Add a line plot to the PlotPanel
        plot.addLinePlot("Foxes", time, Foxes);
        plot.addLinePlot("Rabbits", time, Rabbits);
        plot.addLinePlot("Carrots", time, Carrots);
        plot.setAxisLabel(0,"Time (days)");
        plot.getAxis(0).setLabelPosition(0.5,-0.1);
        plot.setAxisLabel(1,"Population (thousands)");
        BaseLabel title = new BaseLabel("Zombie Outbreak", Color.BLACK, 0.5, 1.1);
        title.setFont(new Font("Courier", Font.BOLD, 14));
        plot.addPlotable(title);

        // Add maximums
        String[] scales = new String[2];
        scales = plot.getAxisScales();
        Arrays.sort(Foxes);
        Arrays.sort(Rabbits);
        Arrays.sort(Carrots);
        Arrays.sort(time);
        double maxZ = Foxes[Foxes.length-1];
        double maxS = Rabbits[Rabbits.length-1];
        double maxR = Carrots[Carrots.length-1];
        double maxT = time[time.length-1];
        String labelZ = new String("Max: "+maxZ);
        plot.addLabel(labelZ,plot.COLORLIST[0], maxT*1.1, maxZ);
        String labelS = new String("Max: "+maxS);
        plot.addLabel(labelS,plot.COLORLIST[1], maxT*1.1, maxS);
        String labelR = new String("Max: "+maxR);
        plot.addLabel(labelR,plot.COLORLIST[2], maxT*1.1, maxR);


        JFrame frame = new JFrame("TermProject.java");
        frame.setSize(525, 525);
        frame.setContentPane(plot);
        frame.setVisible(true);
    }
}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
		