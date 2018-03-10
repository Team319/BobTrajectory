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
		centerProfile = new SrxMotionProfile(centerPoints.length, centerPoints);
	}

	
    public OneFootArc(boolean flipped) {
		super();
		this.flipped = flipped;
		centerProfile = new SrxMotionProfile(centerPoints.length, centerPoints);
	}

	double[][] centerPoints = {
				{0.150,2.991,10.000,0.000},
				{0.748,5.981,10.000,0.000},
				{2.093,13.458,10.000,0.000},
				{4.486,23.925,10.000,0.000},
				{8.224,37.382,10.000,0.000},
				{13.607,53.831,10.000,0.000},
				{20.934,73.270,10.000,0.000},
				{30.504,95.699,10.000,0.000},
				{42.616,121.119,10.000,0.000},
				{57.569,149.530,10.000,0.000},
				{75.513,179.436,10.000,0.000},
				{96.447,209.342,10.000,0.000},
				{120.372,239.248,10.000,0.000},
				{147.287,269.154,10.000,0.000},
				{177.193,299.060,10.000,0.000},
				{210.089,328.966,10.000,0.000},
				{245.977,358.872,10.000,0.000},
				{284.854,388.778,10.000,0.000},
				{326.723,418.684,10.000,0.000},
				{371.582,448.589,10.000,0.000},
				{419.431,478.495,10.000,0.000},
				{470.271,508.401,10.000,0.000},
				{524.102,538.307,10.000,0.000},
				{580.923,568.213,10.000,0.000},
				{640.735,598.119,10.000,0.000},
				{703.538,628.025,10.000,0.000},
				{769.331,657.931,10.000,0.000},
				{838.115,687.837,10.000,0.000},
				{909.889,717.743,10.000,0.000},
				{984.654,747.649,10.000,0.000},
				{1062.409,777.555,10.000,0.000},
				{1143.156,807.461,10.000,0.000},
				{1226.892,837.367,10.000,0.000},
				{1313.620,867.273,10.000,0.000},
				{1403.337,897.179,10.000,0.000},
				{1496.046,927.085,10.000,0.000},
				{1591.745,956.991,10.000,0.000},
				{1690.285,985.402,10.000,0.000},
				{1791.367,1010.822,10.000,0.000},
				{1894.692,1033.251,10.000,0.000},
				{1999.961,1052.690,10.000,0.000},
				{2106.875,1069.138,10.000,0.000},
				{2215.135,1082.596,10.000,0.000},
				{2324.441,1093.063,10.000,0.000},
				{2434.495,1100.540,10.000,0.000},
				{2544.998,1105.025,10.000,0.000},
				{2655.520,1105.220,10.000,0.000},
				{2765.632,1101.122,10.000,0.000},
				{2875.035,1094.034,10.000,0.000},
				{2983.431,1083.955,10.000,0.000},
				{3090.519,1070.886,10.000,0.000},
				{3196.002,1054.826,10.000,0.000},
				{3299.580,1035.776,10.000,0.000},
				{3400.953,1013.735,10.000,0.000},
				{3499.823,988.703,10.000,0.000},
				{3595.891,960.681,10.000,0.000},
				{3688.988,930.969,10.000,0.000},
				{3779.095,901.063,10.000,0.000},
				{3866.210,871.157,10.000,0.000},
				{3950.336,841.251,10.000,0.000},
				{4031.470,811.345,10.000,0.000},
				{4109.614,781.439,10.000,0.000},
				{4184.767,751.533,10.000,0.000},
				{4256.930,721.627,10.000,0.000},
				{4326.102,691.721,10.000,0.000},
				{4392.284,661.815,10.000,0.000},
				{4455.475,631.909,10.000,0.000},
				{4515.675,602.003,10.000,0.000},
				{4572.885,572.097,10.000,0.000},
				{4627.104,542.191,10.000,0.000},
				{4678.332,512.285,10.000,0.000},
				{4726.570,482.380,10.000,0.000},
				{4771.818,452.474,10.000,0.000},
				{4814.074,422.568,10.000,0.000},
				{4853.341,392.662,10.000,0.000},
				{4889.616,362.756,10.000,0.000},
				{4922.901,332.850,10.000,0.000},
				{4953.195,302.944,10.000,0.000},
				{4980.499,273.038,10.000,0.000},
				{5004.812,243.132,10.000,0.000},
				{5026.135,213.226,10.000,0.000},
				{5044.467,183.320,10.000,0.000},
				{5059.808,153.414,10.000,0.000},
				{5072.289,124.809,10.000,0.000},
				{5082.189,99.001,10.000,0.000},
				{5089.808,76.183,10.000,0.000},
				{5095.443,56.355,10.000,0.000},
				{5099.395,39.519,10.000,0.000},
				{5101.962,25.673,10.000,0.000},
				{5103.444,14.817,10.000,0.000},
				{5104.139,6.952,10.000,0.000},
				{5104.347,2.078,10.000,0.000},
				{5104.366,0.194,10.000,0.000},
				{5104.366,0.000,10.000,0.000}		};

}