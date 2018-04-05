package org.usfirst.frc.team319.arcs;

import org.usfirst.frc.team319.models.SrxMotionProfile;
import org.usfirst.frc.team319.models.SrxTrajectory;

public class OneFootArc extends SrxTrajectory{
	
	// WAYPOINTS:
	// (X,Y,degrees)
	// (0.00,0.00,0.00)
	// (1.00,0.00,0.00)
	
    public OneFootArc() {
		super();
		this.highGear = true;
		centerProfile = new SrxMotionProfile(centerPoints.length, centerPoints);
	}

	
    public OneFootArc(boolean flipped) {
		super();
		this.highGear = true;
		this.flipped = flipped;
		centerProfile = new SrxMotionProfile(centerPoints.length, centerPoints);
	}

	public boolean highGear = true;

	double[][] centerPoints = {
				{0.541,10.817,10.000,0.000},
				{2.704,32.451,10.000,0.000},
				{7.572,64.903,10.000,0.000},
				{16.226,108.171,10.000,0.000},
				{29.747,162.257,10.000,0.000},
				{49.218,227.159,10.000,0.000},
				{75.720,302.879,10.000,0.000},
				{110.334,389.416,10.000,0.000},
				{154.144,486.770,10.000,0.000},
				{208.229,594.941,10.000,0.000},
				{273.673,713.929,10.000,0.000},
				{351.556,843.734,10.000,0.000},
				{442.960,984.356,10.000,0.000},
				{548.968,1135.796,10.000,0.000},
				{670.119,1287.235,10.000,0.000},
				{806.415,1438.675,10.000,0.000},
				{957.854,1590.114,10.000,0.000},
				{1124.438,1741.553,10.000,0.000},
				{1306.165,1892.993,10.000,0.000},
				{1503.036,2044.432,10.000,0.000},
				{1715.051,2195.872,10.000,0.000},
				{1942.211,2347.311,10.000,0.000},
				{2184.514,2498.750,10.000,0.000},
				{2441.961,2650.190,10.000,0.000},
				{2714.552,2801.629,10.000,0.000},
				{3002.287,2953.069,10.000,0.000},
				{3305.165,3104.508,10.000,0.000},
				{3623.188,3255.948,10.000,0.000},
				{3956.355,3407.387,10.000,0.000},
				{4304.666,3558.826,10.000,0.000},
				{4667.579,3699.449,10.000,0.000},
				{5044.015,3829.254,10.000,0.000},
				{5432.889,3948.242,10.000,0.000},
				{5833.122,4056.413,10.000,0.000},
				{6243.631,4153.767,10.000,0.000},
				{6663.335,4240.304,10.000,0.000},
				{7091.151,4316.024,10.000,0.000},
				{7525.999,4380.926,10.000,0.000},
				{7966.795,4435.012,10.000,0.000},
				{8412.460,4478.280,10.000,0.000},
				{8861.911,4510.731,10.000,0.000},
				{9314.065,4532.366,10.000,0.000},
				{9767.651,4539.343,10.000,0.000},
				{10220.852,4524.687,10.000,0.000},
				{10672.047,4499.213,10.000,0.000},
				{11120.154,4462.922,10.000,0.000},
				{11564.091,4415.814,10.000,0.000},
				{12002.776,4357.889,10.000,0.000},
				{12435.128,4289.147,10.000,0.000},
				{12860.065,4209.588,10.000,0.000},
				{13276.505,4119.212,10.000,0.000},
				{13683.366,4018.018,10.000,0.000},
				{14079.568,3906.008,10.000,0.000},
				{14464.027,3783.180,10.000,0.000},
				{14835.663,3649.536,10.000,0.000},
				{15193.393,3505.074,10.000,0.000},
				{15536.329,3353.634,10.000,0.000},
				{15864.120,3202.195,10.000,0.000},
				{16176.768,3050.756,10.000,0.000},
				{16474.271,2899.316,10.000,0.000},
				{16756.631,2747.877,10.000,0.000},
				{17023.847,2596.437,10.000,0.000},
				{17275.918,2444.998,10.000,0.000},
				{17512.846,2293.558,10.000,0.000},
				{17734.630,2142.119,10.000,0.000},
				{17941.270,1990.680,10.000,0.000},
				{18132.766,1839.240,10.000,0.000},
				{18309.118,1687.801,10.000,0.000},
				{18470.326,1536.361,10.000,0.000},
				{18616.390,1384.922,10.000,0.000},
				{18747.310,1233.482,10.000,0.000},
				{18863.087,1082.043,10.000,0.000},
				{18963.911,934.443,10.000,0.000},
				{19050.516,797.660,10.000,0.000},
				{19123.984,671.695,10.000,0.000},
				{19185.396,556.546,10.000,0.000},
				{19235.834,452.214,10.000,0.000},
				{19276.380,358.700,10.000,0.000},
				{19308.115,276.003,10.000,0.000},
				{19332.121,204.122,10.000,0.000},
				{19349.480,143.059,10.000,0.000},
				{19361.274,92.813,10.000,0.000},
				{19368.584,53.384,10.000,0.000},
				{19372.491,24.772,10.000,0.000},
				{19374.079,6.978,10.000,0.000},
				{19374.428,0.000,10.000,0.000},
				{19374.428,0.000,10.000,0.000}		};

}