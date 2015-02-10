package com.AtomicGE.mathUtil;

import java.util.Random;

public class PerlinNoise2D {
	//http://en.wikipedia.org/wiki/Perlin_noise
	
	//private long seed;
	private double xStretch;
	private double yStretch;
	private double multiplier;
	private int[] p;
	
	public PerlinNoise2D(long seed,double xStretch, double yStretch, double multiplier){
		//this.seed = seed;
		this.xStretch = xStretch;
		this.yStretch = yStretch;
		this.multiplier = multiplier;
		p = new int[512];
		Random ran = new Random(seed);
		for(int i = 0; i < p.length; i++){
			p[i] = ran.nextInt(256); // 0 - 255
		}
	}
	
	
	
	/**
	 * Evaluates this Perlin Noise at the given x and y
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return the double value representing this Perlin Noise at x,y
	 */
	public double evaluateAt(double x, double y){
		x = x * xStretch;
		y = y * yStretch;
		double simplexNoise = noise(x,y);
		return simplexNoise * multiplier;
	}
	
	
	
	/**
	 * This method evaluates simplex noise at the given x and y coordinates.
	 * This method is a refactor of Ken Perlin's original code. 
	 * @param x
	 * @param y
	 * @return
	 */
	private double noise(double x, double y) {
	      int X = (int)Math.floor(x) & 255;                  // FIND UNIT CUBE THAT
	      int Y = (int)Math.floor(y) & 255;                  // CONTAINS POINT.
	      x -= Math.floor(x);                                // FIND RELATIVE X,Y,Z
	      y -= Math.floor(y);                                // OF POINT IN CUBE.
	      double u = fade(x);                                // COMPUTE FADE CURVES
	      double v = fade(y);                                // FOR EACH OF X,Y,Z.
	      int A = p[X  ]+Y, AA = p[A], AB = p[A+1],      // HASH COORDINATES OF
	          B = p[X+1]+Y, BA = p[B], BB = p[B+1];      // THE 8 CUBE CORNERS,

	      return lerp(v, lerp(u, grad(p[AA  ], x  , y  ),  // AND ADD
	                             grad(p[BA  ], x-1, y  )), // BLENDED
	                     lerp(u, grad(p[AB  ], x  , y-1),  // RESULTS
	                             grad(p[BB  ], x-1, y-1)));// FROM  8 corners of cube
	                     
	}
	private static double fade(double t) {
		return t * t * t * (t * (t * 6 - 15) + 10);
	}
	
	private static double lerp(double t, double a, double b) {
		return a + t * (b - a); 
	}
	private static double grad(int hash, double x, double y) {
	      int h = hash & 15;                      // CONVERT LO 4 BITS OF HASH CODE
	      double u = h<8 ? x : y;                 // INTO 12 GRADIENT DIRECTIONS.
	      double v = h<4 ? y : x;//h==12||h==14 ? x : z;
	      return ((h&1) == 0 ? u : -u) + ((h&2) == 0 ? v : -v);
	}
	  /* static final int p[] = new int[512], permutation[] = { 151,160,137,91,90,15,
	   131,13,201,95,96,53,194,233,7,225,140,36,103,30,69,142,8,99,37,240,21,10,23,
	   190, 6,148,247,120,234,75,0,26,197,62,94,252,219,203,117,35,11,32,57,177,33,
	   88,237,149,56,87,174,20,125,136,171,168, 68,175,74,165,71,134,139,48,27,166,
	   77,146,158,231,83,111,229,122,60,211,133,230,220,105,92,41,55,46,245,40,244,
	   102,143,54, 65,25,63,161, 1,216,80,73,209,76,132,187,208, 89,18,169,200,196,
	   135,130,116,188,159,86,164,100,109,198,173,186, 3,64,52,217,226,250,124,123,
	   5,202,38,147,118,126,255,82,85,212,207,206,59,227,47,16,58,17,182,189,28,42,
	   223,183,170,213,119,248,152, 2,44,154,163, 70,221,153,101,155,167, 43,172,9,
	   129,22,39,253, 19,98,108,110,79,113,224,232,178,185, 112,104,218,246,97,228,
	   251,34,242,193,238,210,144,12,191,179,162,241, 81,51,145,235,249,14,239,107,
	   49,192,214, 31,181,199,106,157,184, 84,204,176,115,121,50,45,127, 4,150,254,
	   138,236,205,93,222,114,67,29,24,72,243,141,128,195,78,66,215,61,156,180
	   };
	   static { for (int i=0; i < 256 ; i++) p[256+i] = p[i] = permutation[i]; }*/
}
