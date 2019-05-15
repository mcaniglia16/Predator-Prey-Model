
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

public class TwoPopPractice{
		/*  Declare constants and parameters
			All constants are /day
		*/
		public static final double dt = 1.;				// Time step (in days)
		public static final double tFinal = 8000.0;		// Total time of simulation (in days)
		public static final double capFox = 20;			// Carrying capacity of foxes
		public static final double capRabbit = 100;		// Carrying capacity of rabbits
		
		
//		public static double birthFox = 0.0005;			// Birthrate of foxes		gestation period for foxes ~50 days
//		public static double deathRabbit = 0.000525;	// Deathrate of rabbits
		
		public static double deathFox = 0.003;		// Deathrate of foxes
		public static double foxHuntRate = 	0.0005;		// Rate at which foxes eat rabbits
		public static double foxGrowRate = 0.001;		// Rate at which foxes increase by consuming rabbits
		public static double birthRabbit = 0.0045;		// Birthrate of rabbits		gestation period for rabbits ~25 days
		
		
		
		public static void main (String args[]){ 
		
		//Populations:
		double Fox;		// Population of Foxes
        double Rabbit;	// Population of Rabbits
        double Carrot;	// Population of Carrots
        double time;	// Time of the simulation (in days)

        Fox = 10.;		// 10 000 foxes
        Rabbit = 40.;	// 50 000 rabbit
        time = 0.;
		
	
        /////////////////// Max number of steps/////////////////////////////////////////////
        int N = (int)(tFinal / dt) + 1;

        ////////////////////// Euler loop for first scenario////////////////////////////////
		
        for (int i = 1; i < N; i++)
		{
			Fox = Fox + ((capFox-Fox)/capFox)*((foxGrowRate*Fox*Rabbit)*dt) - (deathFox*Fox*dt);
            Rabbit = Rabbit + ((capRabbit-Rabbit)/capRabbit) * ((Rabbit*birthRabbit)*dt) - (foxHuntRate*Fox*Rabbit*dt);
            time = time + dt;
			
			if(i%20==0){
			System.out.println("Foxes: " + Fox + " \tRabbits: " + Rabbit + "\tTime:" + time);
			}
			
		}
		
		
		}
}