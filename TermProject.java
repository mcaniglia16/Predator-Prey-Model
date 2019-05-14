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

public class TermProject
		// Declare constants and parameters
		public static final double dt = 0.1;        		// Time step (in days)
		public static final double tFinal = 365.0;   		// Total time of simulation (in days)
		public static final double capFox = ;       		// Carrying capacity of foxes
		public static final double capRabbits = ;       	// Carrying capacity of rabbits
		public static final double capCarrots = ;       	// Carrying capaciy of carrots
		public static double numFox = ;						// Number of foxes
		public static double numRabbits = ;					// Number of rabbits
		public static double numCarrots = ;					// Number of carrots
		public static double birthFox = ;					// Birthrate of foxes
		public static double birthRabbit = ;				// Birthrate of rabbits
		public static double growCarrot = ;					// Growing rate of carrots
		public static double deathFox = ;					// Deathrate of foxes
		public static double deathRabbit = ;				// Deathrate of rabbits
		public static double foxHuntRate = 	;				// Rate at which foxes eat rabbits
		public static double rabbitEatRate = ;				// Rate at which rabbits eat carrots
	
	public static void main(String[] args)
    {