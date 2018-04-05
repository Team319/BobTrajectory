package org.usfirst.frc.team319.arcs;

import org.usfirst.frc.team319.models.SrxMotionProfile;
import org.usfirst.frc.team319.models.SrxTrajectory;

public class CenterToLeftScalePt1Arc extends SrxTrajectory{
	
	// WAYPOINTS:
	// (X,Y,degrees)
	// (6.43,13.08,0.00)
	// (2.43,12.08,30.00)
	
    public CenterToLeftScalePt1Arc() {
		super();
		this.highGear = true;
		centerProfile = new SrxMotionProfile(centerPoints.length, centerPoints);
	}

	
    public CenterToLeftScalePt1Arc(boolean flipped) {
		super();
		this.highGear = true;
		this.flipped = flipped;
		centerProfile = new SrxMotionProfile(centerPoints.length, centerPoints);
	}

	public boolean highGear = true;

	double[][] centerPoints = {
				{-0.546,-10.914,10.000,0.000},
				{-2.728,-32.741,10.000,0.000},
				{-7.640,-65.483,10.000,0.000},
				{-16.371,-109.138,10.000,0.000},
				{-30.013,-163.707,10.000,0.000},
				{-49.658,-229.190,10.000,0.000},
				{-76.397,-305.586,10.000,0.000},
				{-111.321,-392.897,10.000,0.000},
				{-155.522,-491.121,10.000,0.000},
				{-210.091,-600.259,10.000,0.000},
				{-276.119,-720.310,10.000,0.001},
				{-354.698,-851.276,10.000,0.001},
				{-446.920,-993.155,10.000,0.002},
				{-553.875,-1145.948,10.000,0.003},
				{-676.110,-1298.742,10.000,0.004},
				{-813.623,-1451.535,10.000,0.006},
				{-966.416,-1604.328,10.000,0.008},
				{-1134.489,-1757.121,10.000,0.011},
				{-1317.841,-1909.914,10.000,0.015},
				{-1516.472,-2062.707,10.000,0.020},
				{-1730.382,-2215.500,10.000,0.025},
				{-1959.572,-2368.293,10.000,0.033},
				{-2204.041,-2521.086,10.000,0.041},
				{-2463.789,-2673.880,10.000,0.052},
				{-2738.817,-2826.673,10.000,0.064},
				{-3029.124,-2979.466,10.000,0.078},
				{-3334.710,-3132.259,10.000,0.094},
				{-3655.575,-3285.052,10.000,0.113},
				{-3991.720,-3437.845,10.000,0.135},
				{-4343.144,-3590.638,10.000,0.160},
				{-4709.848,-3743.431,10.000,0.188},
				{-5091.831,-3896.225,10.000,0.220},
				{-5489.093,-4049.018,10.000,0.256},
				{-5901.634,-4201.811,10.000,0.296},
				{-6329.455,-4354.604,10.000,0.340},
				{-6772.555,-4507.397,10.000,0.389},
				{-7230.934,-4660.190,10.000,0.444},
				{-7704.593,-4812.983,10.000,0.504},
				{-8193.531,-4965.776,10.000,0.570},
				{-8697.748,-5118.569,10.000,0.642},
				{-9217.245,-5271.363,10.000,0.721},
				{-9752.021,-5424.156,10.000,0.807},
				{-10302.076,-5576.949,10.000,0.901},
				{-10867.411,-5729.742,10.000,1.002},
				{-11448.024,-5882.535,10.000,1.112},
				{-12043.918,-6035.328,10.000,1.230},
				{-12655.090,-6188.121,10.000,1.358},
				{-13281.542,-6340.914,10.000,1.495},
				{-13923.273,-6493.708,10.000,1.643},
				{-14580.283,-6646.501,10.000,1.801},
				{-15252.573,-6799.294,10.000,1.970},
				{-15940.142,-6952.087,10.000,2.151},
				{-16642.990,-7104.880,10.000,2.344},
				{-17361.118,-7257.673,10.000,2.549},
				{-18094.525,-7410.466,10.000,2.768},
				{-18843.211,-7563.259,10.000,3.000},
				{-19607.177,-7716.052,10.000,3.246},
				{-20386.422,-7868.846,10.000,3.506},
				{-21180.946,-8021.639,10.000,3.782},
				{-21990.750,-8174.432,10.000,4.073},
				{-22815.832,-8327.225,10.000,4.380},
				{-23656.195,-8480.018,10.000,4.703},
				{-24511.836,-8632.811,10.000,5.043},
				{-25382.757,-8785.604,10.000,5.400},
				{-26268.957,-8938.397,10.000,5.774},
				{-27170.436,-9091.191,10.000,6.167},
				{-28087.195,-9243.984,10.000,6.577},
				{-29018.687,-9385.863,10.000,7.006},
				{-29963.822,-9516.829,10.000,7.453},
				{-30921.507,-9636.880,10.000,7.917},
				{-31890.652,-9746.018,10.000,8.398},
				{-32870.165,-9844.242,10.000,8.895},
				{-33858.955,-9931.553,10.000,9.407},
				{-34855.930,-10007.949,10.000,9.934},
				{-35859.999,-10073.432,10.000,10.475},
				{-36870.071,-10128.001,10.000,11.027},
				{-37885.054,-10171.656,10.000,11.591},
				{-38903.856,-10204.398,10.000,12.165},
				{-39925.388,-10226.225,10.000,12.747},
				{-40948.218,-10230.387,10.000,13.336},
				{-41970.374,-10212.721,10.000,13.929},
				{-42990.217,-10184.142,10.000,14.525},
				{-44006.656,-10144.649,10.000,15.123},
				{-45018.601,-10094.241,10.000,15.719},
				{-46024.959,-10032.921,10.000,16.313},
				{-47024.639,-9960.686,10.000,16.903},
				{-48016.550,-9877.537,10.000,17.488},
				{-48999.601,-9783.475,10.000,18.065},
				{-49972.700,-9678.499,10.000,18.634},
				{-50934.755,-9562.609,10.000,19.192},
				{-51884.676,-9435.806,10.000,19.738},
				{-52821.370,-9298.088,10.000,20.272},
				{-53743.748,-9149.457,10.000,20.791},
				{-54651.054,-8996.664,10.000,21.296},
				{-55543.081,-8843.871,10.000,21.784},
				{-56419.828,-8691.078,10.000,22.257},
				{-57281.296,-8538.284,10.000,22.713},
				{-58127.485,-8385.491,10.000,23.154},
				{-58958.394,-8232.698,10.000,23.577},
				{-59774.024,-8079.905,10.000,23.984},
				{-60574.375,-7927.112,10.000,24.375},
				{-61359.447,-7774.319,10.000,24.749},
				{-62129.239,-7621.526,10.000,25.107},
				{-62883.752,-7468.733,10.000,25.448},
				{-63622.986,-7315.939,10.000,25.774},
				{-64346.940,-7163.146,10.000,26.083},
				{-65055.615,-7010.353,10.000,26.377},
				{-65749.011,-6857.560,10.000,26.656},
				{-66427.127,-6704.767,10.000,26.920},
				{-67089.964,-6551.974,10.000,27.169},
				{-67737.522,-6399.181,10.000,27.404},
				{-68369.800,-6246.388,10.000,27.625},
				{-68986.799,-6093.595,10.000,27.833},
				{-69588.519,-5940.801,10.000,28.028},
				{-70174.960,-5788.008,10.000,28.210},
				{-70746.121,-5635.215,10.000,28.380},
				{-71302.003,-5482.422,10.000,28.538},
				{-71842.605,-5329.629,10.000,28.684},
				{-72367.928,-5176.836,10.000,28.820},
				{-72877.972,-5024.043,10.000,28.946},
				{-73372.737,-4871.250,10.000,29.062},
				{-73852.222,-4718.456,10.000,29.168},
				{-74316.428,-4565.663,10.000,29.265},
				{-74765.355,-4412.870,10.000,29.354},
				{-75199.002,-4260.077,10.000,29.435},
				{-75617.370,-4107.284,10.000,29.508},
				{-76020.459,-3954.491,10.000,29.573},
				{-76408.268,-3801.698,10.000,29.633},
				{-76780.799,-3648.905,10.000,29.686},
				{-77138.049,-3496.112,10.000,29.733},
				{-77480.021,-3343.318,10.000,29.774},
				{-77806.713,-3190.525,10.000,29.811},
				{-78118.126,-3037.732,10.000,29.843},
				{-78414.260,-2884.939,10.000,29.871},
				{-78695.114,-2732.146,10.000,29.894},
				{-78960.689,-2579.353,10.000,29.915},
				{-79210.984,-2426.560,10.000,29.932},
				{-79446.001,-2273.767,10.000,29.947},
				{-79665.738,-2120.973,10.000,29.959},
				{-79870.195,-1968.180,10.000,29.969},
				{-80059.374,-1815.387,10.000,29.976},
				{-80233.273,-1662.594,10.000,29.983},
				{-80391.893,-1509.801,10.000,29.988},
				{-80535.233,-1357.008,10.000,29.991},
				{-80663.294,-1204.215,10.000,29.994},
				{-80776.076,-1051.422,10.000,29.996},
				{-80873.916,-905.380,10.000,29.998},
				{-80957.698,-770.253,10.000,29.998},
				{-81028.512,-646.039,10.000,29.999},
				{-81087.451,-532.740,10.000,29.999},
				{-81135.606,-430.354,10.000,30.000},
				{-81174.068,-338.881,10.000,30.000},
				{-81203.928,-258.323,10.000,30.000},
				{-81226.278,-188.678,10.000,30.000},
				{-81242.209,-129.947,10.000,30.000},
				{-81252.813,-82.130,10.000,30.000},
				{-81259.181,-45.227,10.000,30.000},
				{-81262.404,-19.238,10.000,30.000},
				{-81263.574,-4.162,10.000,30.000},
				{-81263.782,-0.000,10.000,30.000},
				{-81263.782,-0.000,10.000,30.000}		};

}