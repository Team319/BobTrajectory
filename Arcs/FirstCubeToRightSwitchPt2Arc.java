package org.usfirst.frc.team319.arcs;

import org.usfirst.frc.team319.models.SrxMotionProfile;
import org.usfirst.frc.team319.models.SrxTrajectory;

public class FirstCubeToRightSwitchPt2Arc extends SrxTrajectory{
	
	// WAYPOINTS:
	// (X,Y,degrees)
	// (5.50,9.08,0.00)
	// (10.00,9.08,0.00)
	
    public FirstCubeToRightSwitchPt2Arc() {
		super();
		this.highGear = true;
		centerProfile = new SrxMotionProfile(centerPoints.length, centerPoints);
	}

	
    public FirstCubeToRightSwitchPt2Arc(boolean flipped) {
		super();
		this.highGear = true;
		this.flipped = flipped;
		centerProfile = new SrxMotionProfile(centerPoints.length, centerPoints);
	}

	public boolean highGear = true;

	double[][] centerPoints = {
				{0.551,11.012,10.000,0.000},
				{2.753,33.035,10.000,0.000},
				{7.708,66.069,10.000,0.000},
				{16.517,110.116,10.000,0.000},
				{30.282,165.174,10.000,0.000},
				{50.103,231.243,10.000,0.000},
				{77.081,308.324,10.000,0.000},
				{112.318,396.417,10.000,0.000},
				{156.915,495.521,10.000,0.000},
				{211.973,605.637,10.000,0.000},
				{278.593,726.764,10.000,0.000},
				{357.876,858.903,10.000,0.000},
				{450.924,1002.054,10.000,0.000},
				{558.838,1156.216,10.000,0.000},
				{682.167,1310.378,10.000,0.000},
				{820.913,1464.540,10.000,0.000},
				{975.076,1618.703,10.000,0.000},
				{1144.654,1772.865,10.000,0.000},
				{1329.649,1927.027,10.000,0.000},
				{1530.059,2081.189,10.000,0.000},
				{1745.886,2235.351,10.000,0.000},
				{1977.130,2389.513,10.000,0.000},
				{2223.789,2543.675,10.000,0.000},
				{2485.865,2697.838,10.000,0.000},
				{2763.356,2852.000,10.000,0.000},
				{3056.265,3006.162,10.000,0.000},
				{3364.589,3160.324,10.000,0.000},
				{3688.329,3314.486,10.000,0.000},
				{4027.486,3468.648,10.000,0.000},
				{4382.059,3622.810,10.000,0.000},
				{4752.048,3776.973,10.000,0.000},
				{5137.454,3931.135,10.000,0.000},
				{5538.275,4085.297,10.000,0.000},
				{5954.513,4239.459,10.000,0.000},
				{6386.167,4393.621,10.000,0.000},
				{6833.237,4547.783,10.000,0.000},
				{7295.724,4701.945,10.000,0.000},
				{7773.626,4856.108,10.000,0.000},
				{8266.945,5010.270,10.000,0.000},
				{8775.680,5164.432,10.000,0.000},
				{9299.831,5318.594,10.000,0.000},
				{9839.399,5472.756,10.000,0.000},
				{10394.383,5626.918,10.000,0.000},
				{10964.783,5781.080,10.000,0.000},
				{11550.599,5935.243,10.000,0.000},
				{12151.831,6089.405,10.000,0.000},
				{12768.480,6243.567,10.000,0.000},
				{13400.545,6397.729,10.000,0.000},
				{14048.026,6551.891,10.000,0.000},
				{14710.923,6706.053,10.000,0.000},
				{15389.236,6860.215,10.000,0.000},
				{16082.966,7014.378,10.000,0.000},
				{16792.112,7168.540,10.000,0.000},
				{17516.674,7322.702,10.000,0.000},
				{18256.652,7476.864,10.000,0.000},
				{19012.047,7631.026,10.000,0.000},
				{19782.857,7785.188,10.000,0.000},
				{20569.084,7939.351,10.000,0.000},
				{21370.727,8093.513,10.000,0.000},
				{22187.787,8247.675,10.000,0.000},
				{23020.262,8401.837,10.000,0.000},
				{23868.154,8555.999,10.000,0.000},
				{24731.462,8710.161,10.000,0.000},
				{25610.186,8864.323,10.000,0.000},
				{26504.327,9018.486,10.000,0.000},
				{27413.884,9172.648,10.000,0.000},
				{28338.856,9326.810,10.000,0.000},
				{29279.246,9480.972,10.000,0.000},
				{30235.051,9635.134,10.000,0.000},
				{31205.722,9778.285,10.000,0.000},
				{32190.157,9910.424,10.000,0.000},
				{33187.256,10031.551,10.000,0.000},
				{34195.917,10141.667,10.000,0.000},
				{35215.039,10240.771,10.000,0.000},
				{36243.521,10328.864,10.000,0.000},
				{37280.261,10405.945,10.000,0.000},
				{38324.159,10472.014,10.000,0.000},
				{39374.113,10527.072,10.000,0.000},
				{40429.023,10571.119,10.000,0.000},
				{41487.786,10604.153,10.000,0.000},
				{42549.303,10626.176,10.000,0.000},
				{43612.450,10636.774,10.000,0.000},
				{44675.556,10625.348,10.000,0.000},
				{45736.969,10602.911,10.000,0.000},
				{46795.588,10569.462,10.000,0.000},
				{47850.311,10525.001,10.000,0.000},
				{48900.038,10469.529,10.000,0.000},
				{49943.666,10403.045,10.000,0.000},
				{50980.096,10325.550,10.000,0.000},
				{52008.226,10237.043,10.000,0.000},
				{53026.954,10137.524,10.000,0.000},
				{54035.180,10026.994,10.000,0.000},
				{55031.802,9905.453,10.000,0.000},
				{56015.720,9772.899,10.000,0.000},
				{56985.832,9629.335,10.000,0.000},
				{57941.057,9475.172,10.000,0.000},
				{58880.866,9321.010,10.000,0.000},
				{59805.259,9166.848,10.000,0.000},
				{60714.236,9012.686,10.000,0.000},
				{61607.796,8858.524,10.000,0.000},
				{62485.940,8704.362,10.000,0.000},
				{63348.668,8550.200,10.000,0.000},
				{64195.980,8396.037,10.000,0.000},
				{65027.876,8241.875,10.000,0.000},
				{65844.355,8087.713,10.000,0.000},
				{66645.419,7933.551,10.000,0.000},
				{67431.066,7779.389,10.000,0.000},
				{68201.296,7625.227,10.000,0.000},
				{68956.111,7471.064,10.000,0.000},
				{69695.509,7316.902,10.000,0.000},
				{70419.491,7162.740,10.000,0.000},
				{71128.057,7008.578,10.000,0.000},
				{71821.207,6854.416,10.000,0.000},
				{72498.940,6700.254,10.000,0.000},
				{73161.258,6546.092,10.000,0.000},
				{73808.159,6391.929,10.000,0.000},
				{74439.644,6237.767,10.000,0.000},
				{75055.712,6083.605,10.000,0.000},
				{75656.365,5929.443,10.000,0.000},
				{76241.601,5775.281,10.000,0.000},
				{76811.421,5621.119,10.000,0.000},
				{77365.825,5466.957,10.000,0.000},
				{77904.812,5312.794,10.000,0.000},
				{78428.384,5158.632,10.000,0.000},
				{78936.539,5004.470,10.000,0.000},
				{79429.278,4850.308,10.000,0.000},
				{79906.600,4696.146,10.000,0.000},
				{80368.507,4541.984,10.000,0.000},
				{80814.997,4387.822,10.000,0.000},
				{81246.071,4233.659,10.000,0.000},
				{81661.729,4079.497,10.000,0.000},
				{82061.970,3925.335,10.000,0.000},
				{82446.796,3771.173,10.000,0.000},
				{82816.205,3617.011,10.000,0.000},
				{83170.198,3462.849,10.000,0.000},
				{83508.775,3308.687,10.000,0.000},
				{83831.935,3154.524,10.000,0.000},
				{84139.680,3000.362,10.000,0.000},
				{84432.008,2846.200,10.000,0.000},
				{84708.920,2692.038,10.000,0.000},
				{84970.415,2537.876,10.000,0.000},
				{85216.495,2383.714,10.000,0.000},
				{85447.158,2229.552,10.000,0.000},
				{85662.405,2075.389,10.000,0.000},
				{85862.236,1921.227,10.000,0.000},
				{86046.651,1767.065,10.000,0.000},
				{86215.649,1612.903,10.000,0.000},
				{86369.231,1458.741,10.000,0.000},
				{86507.397,1304.579,10.000,0.000},
				{86630.147,1150.417,10.000,0.000},
				{86737.501,996.669,10.000,0.000},
				{86830.031,853.932,10.000,0.000},
				{86908.838,722.208,10.000,0.000},
				{86975.023,601.494,10.000,0.000},
				{87029.688,491.793,10.000,0.000},
				{87073.933,393.103,10.000,0.000},
				{87108.859,305.425,10.000,0.000},
				{87135.568,228.758,10.000,0.000},
				{87155.161,163.102,10.000,0.000},
				{87168.739,108.459,10.000,0.000},
				{87177.403,64.827,10.000,0.000},
				{87182.255,32.206,10.000,0.000},
				{87184.395,10.597,10.000,0.000},
				{87184.925,0.000,10.000,0.000},
				{87184.925,0.000,10.000,0.000}		};

}