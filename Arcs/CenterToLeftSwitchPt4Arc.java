package org.usfirst.frc.team319.arcs;

import org.usfirst.frc.team319.models.SrxMotionProfile;
import org.usfirst.frc.team319.models.SrxTrajectory;

public class CenterToLeftSwitchPt4Arc extends SrxTrajectory{
	
	// WAYPOINTS:
	// (X,Y,degrees)
	// (6.63,13.08,0.00)
	// (2.53,13.08,0.00)
	
    public CenterToLeftSwitchPt4Arc() {
		super();
		this.highGear = true;
		centerProfile = new SrxMotionProfile(centerPoints.length, centerPoints);
	}

	
    public CenterToLeftSwitchPt4Arc(boolean flipped) {
		super();
		this.highGear = true;
		this.flipped = flipped;
		centerProfile = new SrxMotionProfile(centerPoints.length, centerPoints);
	}

	public boolean highGear = true;

	double[][] centerPoints = {
				{-0.439,-8.770,10.000,0.000},
				{-2.193,-26.311,10.000,0.000},
				{-6.139,-52.621,10.000,0.000},
				{-13.155,-87.702,10.000,0.000},
				{-24.118,-131.554,10.000,0.000},
				{-39.905,-184.175,10.000,0.000},
				{-61.392,-245.567,10.000,0.000},
				{-89.457,-315.729,10.000,0.000},
				{-124.976,-394.661,10.000,0.000},
				{-168.827,-482.364,10.000,0.000},
				{-221.887,-578.836,10.000,0.000},
				{-285.033,-684.079,10.000,0.000},
				{-359.142,-798.092,10.000,0.000},
				{-445.090,-920.876,10.000,0.000},
				{-543.755,-1052.430,10.000,0.000},
				{-656.014,-1192.754,10.000,0.000},
				{-782.745,-1341.848,10.000,0.000},
				{-924.823,-1499.712,10.000,0.000},
				{-1083.126,-1666.347,10.000,0.000},
				{-1258.530,-1841.752,10.000,0.000},
				{-1451.476,-2017.157,10.000,0.000},
				{-1661.962,-2192.562,10.000,0.000},
				{-1889.988,-2367.967,10.000,0.000},
				{-2135.555,-2543.372,10.000,0.000},
				{-2398.663,-2718.777,10.000,0.000},
				{-2679.310,-2894.182,10.000,0.000},
				{-2977.499,-3069.586,10.000,0.000},
				{-3293.228,-3244.991,10.000,0.000},
				{-3626.497,-3420.396,10.000,0.000},
				{-3977.307,-3595.801,10.000,0.000},
				{-4345.657,-3771.206,10.000,0.000},
				{-4731.548,-3946.611,10.000,0.000},
				{-5134.980,-4122.016,10.000,0.000},
				{-5555.952,-4297.421,10.000,0.000},
				{-5994.464,-4472.826,10.000,0.000},
				{-6450.517,-4648.231,10.000,0.000},
				{-6924.110,-4823.636,10.000,0.000},
				{-7415.244,-4999.041,10.000,0.000},
				{-7923.918,-5174.446,10.000,0.000},
				{-8450.133,-5349.851,10.000,0.000},
				{-8993.888,-5525.256,10.000,0.000},
				{-9555.184,-5700.661,10.000,0.000},
				{-10134.020,-5876.066,10.000,0.000},
				{-10730.397,-6051.470,10.000,0.000},
				{-11344.315,-6226.875,10.000,0.000},
				{-11975.772,-6402.280,10.000,0.000},
				{-12624.771,-6577.685,10.000,0.000},
				{-13291.309,-6753.090,10.000,0.000},
				{-13975.389,-6928.495,10.000,0.000},
				{-14677.008,-7103.900,10.000,0.000},
				{-15395.730,-7270.535,10.000,0.000},
				{-16130.677,-7428.399,10.000,0.000},
				{-16880.972,-7577.493,10.000,0.000},
				{-17645.737,-7717.817,10.000,0.000},
				{-18424.096,-7849.371,10.000,0.000},
				{-19215.173,-7972.155,10.000,0.000},
				{-20018.089,-8086.168,10.000,0.000},
				{-20831.968,-8191.411,10.000,0.000},
				{-21655.933,-8287.883,10.000,0.000},
				{-22489.106,-8375.586,10.000,0.000},
				{-23330.611,-8454.518,10.000,0.000},
				{-24179.571,-8524.680,10.000,0.000},
				{-25035.109,-8586.072,10.000,0.000},
				{-25896.347,-8638.693,10.000,0.000},
				{-26762.409,-8682.545,10.000,0.000},
				{-27632.417,-8717.626,10.000,0.000},
				{-28505.495,-8743.936,10.000,0.000},
				{-29380.766,-8761.477,10.000,0.000},
				{-30257.352,-8770.247,10.000,0.000},
				{-31134.071,-8764.121,10.000,0.000},
				{-32009.738,-8749.225,10.000,0.000},
				{-32883.477,-8725.558,10.000,0.000},
				{-33754.411,-8693.121,10.000,0.000},
				{-34621.663,-8651.914,10.000,0.000},
				{-35484.355,-8601.937,10.000,0.000},
				{-36341.612,-8543.189,10.000,0.000},
				{-37192.555,-8475.671,10.000,0.000},
				{-38036.307,-8399.383,10.000,0.000},
				{-38871.993,-8314.325,10.000,0.000},
				{-39698.734,-8220.496,10.000,0.000},
				{-40515.654,-8117.897,10.000,0.000},
				{-41321.875,-8006.528,10.000,0.000},
				{-42116.521,-7886.389,10.000,0.000},
				{-42898.714,-7757.479,10.000,0.000},
				{-43667.578,-7619.800,10.000,0.000},
				{-44422.236,-7473.350,10.000,0.000},
				{-45161.809,-7318.129,10.000,0.000},
				{-45885.423,-7154.139,10.000,0.000},
				{-46592.199,-6981.378,10.000,0.000},
				{-47281.566,-6805.973,10.000,0.000},
				{-47953.393,-6630.568,10.000,0.000},
				{-48607.680,-6455.163,10.000,0.000},
				{-49244.426,-6279.758,10.000,0.000},
				{-49863.632,-6104.353,10.000,0.000},
				{-50465.297,-5928.948,10.000,0.000},
				{-51049.421,-5753.543,10.000,0.000},
				{-51616.005,-5578.138,10.000,0.000},
				{-52165.049,-5402.733,10.000,0.000},
				{-52696.552,-5227.329,10.000,0.000},
				{-53210.515,-5051.924,10.000,0.000},
				{-53706.937,-4876.519,10.000,0.000},
				{-54185.818,-4701.114,10.000,0.000},
				{-54647.159,-4525.709,10.000,0.000},
				{-55090.960,-4350.304,10.000,0.000},
				{-55517.220,-4174.899,10.000,0.000},
				{-55925.940,-3999.494,10.000,0.000},
				{-56317.119,-3824.089,10.000,0.000},
				{-56690.758,-3648.684,10.000,0.000},
				{-57046.856,-3473.279,10.000,0.000},
				{-57385.413,-3297.874,10.000,0.000},
				{-57706.431,-3122.469,10.000,0.000},
				{-58009.907,-2947.064,10.000,0.000},
				{-58295.843,-2771.659,10.000,0.000},
				{-58564.239,-2596.254,10.000,0.000},
				{-58815.094,-2420.849,10.000,0.000},
				{-59048.409,-2245.445,10.000,0.000},
				{-59264.183,-2070.040,10.000,0.000},
				{-59462.417,-1894.635,10.000,0.000},
				{-59643.110,-1719.230,10.000,0.000},
				{-59806.569,-1549.951,10.000,0.000},
				{-59953.539,-1389.442,10.000,0.000},
				{-60084.896,-1237.704,10.000,0.000},
				{-60201.518,-1094.736,10.000,0.000},
				{-60304.282,-960.538,10.000,0.000},
				{-60394.064,-835.110,10.000,0.000},
				{-60471.742,-718.453,10.000,0.000},
				{-60538.193,-610.566,10.000,0.000},
				{-60594.294,-511.449,10.000,0.000},
				{-60640.922,-421.102,10.000,0.000},
				{-60678.953,-339.526,10.000,0.000},
				{-60709.265,-266.720,10.000,0.000},
				{-60732.736,-202.684,10.000,0.000},
				{-60750.241,-147.419,10.000,0.000},
				{-60762.658,-100.923,10.000,0.000},
				{-60770.864,-63.198,10.000,0.000},
				{-60775.736,-34.243,10.000,0.000},
				{-60778.151,-14.059,10.000,0.000},
				{-60778.986,-2.644,10.000,0.000},
				{-60779.118,-0.000,10.000,0.000},
				{-60779.118,-0.000,10.000,0.000}		};

}