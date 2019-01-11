//WAYPOINTS:
//ImageCalibrator
//(0.00,10.00,0.00,0.00,0.00)
//(27.00,10.00,0.00,0.00,10.00)
//END WAYPOINTS
package frc.arcs;

import com.team319.follower.SrxMotionProfile;
import com.team319.follower.SrxTrajectory;

public class ImageCalibratorArc extends SrxTrajectory {	
    public ImageCalibratorArc() {
		super();
		this.highGear = true;
		centerProfile = new SrxMotionProfile(centerPoints.length, centerPoints);
	}

	
    public ImageCalibratorArc(boolean flipped) {
		super();
		this.highGear = true;
		this.flipped = flipped;
		centerProfile = new SrxMotionProfile(centerPoints.length, centerPoints);
	}

	public boolean highGear = true;

	double[][] centerPoints = {
				{0.000,0.000,10.000,0.000},
				{3.129,31.291,10.000,0.000},
				{9.387,62.582,10.000,0.000},
				{18.775,93.873,10.000,0.000},
				{31.291,125.165,10.000,0.000},
				{46.937,156.456,10.000,0.000},
				{65.711,187.747,10.000,0.000},
				{87.615,219.038,10.000,0.000},
				{112.648,250.329,10.000,0.000},
				{140.810,281.620,10.000,0.000},
				{172.101,312.911,10.000,0.000},
				{206.521,344.202,10.000,0.000},
				{244.071,375.494,10.000,0.000},
				{284.749,406.785,10.000,0.000},
				{328.557,438.076,10.000,0.000},
				{375.494,469.367,10.000,0.000},
				{425.559,500.658,10.000,0.000},
				{478.754,531.949,10.000,0.000},
				{535.078,563.240,10.000,0.000},
				{594.532,594.532,10.000,0.000},
				{657.114,625.823,10.000,0.000},
				{722.825,657.114,10.000,0.000},
				{791.666,688.405,10.000,0.000},
				{863.635,719.696,10.000,0.000},
				{938.734,750.987,10.000,0.000},
				{1016.962,782.278,10.000,0.000},
				{1098.319,813.570,10.000,0.000},
				{1182.805,844.861,10.000,0.000},
				{1270.420,876.152,10.000,0.000},
				{1361.164,907.443,10.000,0.000},
				{1455.038,938.734,10.000,0.000},
				{1552.040,970.025,10.000,0.000},
				{1652.172,1001.316,10.000,0.000},
				{1755.433,1032.607,10.000,0.000},
				{1861.823,1063.899,10.000,0.000},
				{1971.342,1095.190,10.000,0.000},
				{2083.990,1126.481,10.000,0.000},
				{2199.767,1157.772,10.000,0.000},
				{2318.673,1189.063,10.000,0.000},
				{2440.709,1220.354,10.000,0.000},
				{2565.873,1251.645,10.000,0.000},
				{2694.167,1282.937,10.000,0.000},
				{2825.589,1314.228,10.000,0.000},
				{2960.141,1345.519,10.000,0.000},
				{3097.822,1376.810,10.000,0.000},
				{3238.632,1408.101,10.000,0.000},
				{3382.572,1439.392,10.000,0.000},
				{3529.640,1470.683,10.000,0.000},
				{3679.837,1501.974,10.000,0.000},
				{3833.164,1533.266,10.000,0.000},
				{3989.620,1564.557,10.000,0.000},
				{4149.205,1595.848,10.000,0.000},
				{4311.918,1627.139,10.000,0.000},
				{4477.761,1658.430,10.000,0.000},
				{4646.734,1689.721,10.000,0.000},
				{4818.835,1721.012,10.000,0.000},
				{4994.065,1752.304,10.000,0.000},
				{5172.425,1783.595,10.000,0.000},
				{5353.913,1814.886,10.000,0.000},
				{5538.531,1846.177,10.000,0.000},
				{5726.278,1877.468,10.000,0.000},
				{5917.154,1908.759,10.000,0.000},
				{6111.159,1940.050,10.000,0.000},
				{6308.293,1971.342,10.000,0.000},
				{6508.556,2002.633,10.000,0.000},
				{6711.948,2033.924,10.000,0.000},
				{6918.470,2065.215,10.000,0.000},
				{7128.121,2096.506,10.000,0.000},
				{7340.900,2127.797,10.000,0.000},
				{7556.809,2159.088,10.000,0.000},
				{7775.847,2190.379,10.000,0.000},
				{7998.014,2221.671,10.000,0.000},
				{8223.310,2252.962,10.000,0.000},
				{8451.736,2284.253,10.000,0.000},
				{8683.290,2315.544,10.000,0.000},
				{8917.973,2346.835,10.000,0.000},
				{9155.786,2378.126,10.000,0.000},
				{9396.728,2409.417,10.000,0.000},
				{9640.799,2440.709,10.000,0.000},
				{9887.999,2472.000,10.000,0.000},
				{10138.328,2503.291,10.000,0.000},
				{10391.786,2534.582,10.000,0.000},
				{10648.373,2565.873,10.000,0.000},
				{10908.090,2597.164,10.000,0.000},
				{11170.935,2628.455,10.000,0.000},
				{11436.910,2659.746,10.000,0.000},
				{11706.014,2691.038,10.000,0.000},
				{11978.246,2722.329,10.000,0.000},
				{12253.608,2753.620,10.000,0.000},
				{12532.100,2784.911,10.000,0.000},
				{12813.720,2816.202,10.000,0.000},
				{13098.469,2847.493,10.000,0.000},
				{13386.348,2878.784,10.000,0.000},
				{13677.355,2910.076,10.000,0.000},
				{13971.492,2941.367,10.000,0.000},
				{14268.758,2972.658,10.000,0.000},
				{14569.152,3003.949,10.000,0.000},
				{14872.676,3035.240,10.000,0.000},
				{15179.330,3066.531,10.000,0.000},
				{15489.112,3097.822,10.000,0.000},
				{15802.023,3129.114,10.000,0.000},
				{16118.064,3160.405,10.000,0.000},
				{16437.233,3191.696,10.000,0.000},
				{16759.532,3222.987,10.000,0.000},
				{17084.960,3254.278,10.000,0.000},
				{17413.517,3285.569,10.000,0.000},
				{17745.203,3316.860,10.000,0.000},
				{18080.018,3348.151,10.000,0.000},
				{18417.962,3379.443,10.000,0.000},
				{18759.035,3410.734,10.000,0.000},
				{19103.238,3442.025,10.000,0.000},
				{19450.570,3473.316,10.000,0.000},
				{19801.030,3504.607,10.000,0.000},
				{20154.620,3535.898,10.000,0.000},
				{20511.339,3567.189,10.000,0.000},
				{20871.187,3598.481,10.000,0.000},
				{21234.164,3629.772,10.000,0.000},
				{21600.271,3661.063,10.000,0.000},
				{21969.506,3692.354,10.000,0.000},
				{22341.870,3723.645,10.000,0.000},
				{22717.364,3754.936,10.000,0.000},
				{23095.987,3786.227,10.000,0.000},
				{23477.739,3817.518,10.000,0.000},
				{23862.620,3848.810,10.000,0.000},
				{24250.630,3880.101,10.000,0.000},
				{24641.769,3911.392,10.000,0.000},
				{25032.908,3911.392,10.000,0.000},
				{25424.047,3911.392,10.000,0.000},
				{25815.186,3911.392,10.000,0.000},
				{26206.326,3911.392,10.000,0.000},
				{26597.465,3911.392,10.000,0.000},
				{26988.604,3911.392,10.000,0.000},
				{27379.743,3911.392,10.000,0.000},
				{27770.882,3911.392,10.000,0.000},
				{28162.022,3911.392,10.000,0.000},
				{28553.161,3911.392,10.000,0.000},
				{28944.300,3911.392,10.000,0.000},
				{29335.439,3911.392,10.000,0.000},
				{29726.578,3911.392,10.000,0.000},
				{30117.717,3911.392,10.000,0.000},
				{30508.857,3911.392,10.000,0.000},
				{30899.996,3911.392,10.000,0.000},
				{31291.135,3911.392,10.000,0.000},
				{31682.274,3911.392,10.000,0.000},
				{32073.413,3911.392,10.000,0.000},
				{32464.553,3911.392,10.000,0.000},
				{32855.692,3911.392,10.000,0.000},
				{33246.831,3911.392,10.000,0.000},
				{33637.970,3911.392,10.000,0.000},
				{34029.109,3911.392,10.000,0.000},
				{34420.249,3911.392,10.000,0.000},
				{34811.388,3911.392,10.000,0.000},
				{35202.527,3911.392,10.000,0.000},
				{35593.666,3911.392,10.000,0.000},
				{35984.805,3911.392,10.000,0.000},
				{36375.944,3911.392,10.000,0.000},
				{36767.084,3911.392,10.000,0.000},
				{37158.223,3911.392,10.000,0.000},
				{37549.362,3911.392,10.000,0.000},
				{37940.501,3911.392,10.000,0.000},
				{38331.640,3911.392,10.000,0.000},
				{38722.780,3911.392,10.000,0.000},
				{39113.919,3911.392,10.000,0.000},
				{39505.058,3911.392,10.000,0.000},
				{39896.197,3911.392,10.000,0.000},
				{40287.336,3911.392,10.000,0.000},
				{40678.476,3911.392,10.000,0.000},
				{41069.615,3911.392,10.000,0.000},
				{41460.754,3911.392,10.000,0.000},
				{41851.893,3911.392,10.000,0.000},
				{42243.032,3911.392,10.000,0.000},
				{42634.172,3911.392,10.000,0.000},
				{43025.311,3911.392,10.000,0.000},
				{43416.450,3911.392,10.000,0.000},
				{43807.589,3911.392,10.000,0.000},
				{44198.728,3911.392,10.000,0.000},
				{44589.867,3911.392,10.000,0.000},
				{44981.007,3911.392,10.000,0.000},
				{45372.146,3911.392,10.000,0.000},
				{45763.285,3911.392,10.000,0.000},
				{46154.424,3911.392,10.000,0.000},
				{46545.563,3911.392,10.000,0.000},
				{46936.703,3911.392,10.000,0.000},
				{47327.842,3911.392,10.000,0.000},
				{47718.981,3911.392,10.000,0.000},
				{48110.120,3911.392,10.000,0.000},
				{48501.259,3911.392,10.000,0.000},
				{48892.399,3911.392,10.000,0.000},
				{49283.538,3911.392,10.000,0.000},
				{49674.677,3911.392,10.000,0.000},
				{50065.816,3911.392,10.000,0.000},
				{50456.955,3911.392,10.000,0.000},
				{50848.094,3911.392,10.000,0.000},
				{51239.234,3911.392,10.000,0.000},
				{51630.373,3911.392,10.000,0.000},
				{52021.512,3911.392,10.000,0.000},
				{52412.651,3911.392,10.000,0.000},
				{52803.790,3911.392,10.000,0.000},
				{53194.930,3911.392,10.000,0.000},
				{53586.069,3911.392,10.000,0.000},
				{53977.208,3911.392,10.000,0.000},
				{54368.347,3911.392,10.000,0.000},
				{54759.486,3911.392,10.000,0.000},
				{55150.626,3911.392,10.000,0.000},
				{55541.765,3911.392,10.000,0.000},
				{55932.904,3911.392,10.000,0.000},
				{56324.043,3911.392,10.000,0.000},
				{56715.182,3911.392,10.000,0.000},
				{57106.321,3911.392,10.000,0.000},
				{57497.461,3911.392,10.000,0.000},
				{57888.600,3911.392,10.000,0.000},
				{58279.739,3911.392,10.000,0.000},
				{58670.878,3911.392,10.000,0.000},
				{59062.017,3911.392,10.000,0.000},
				{59453.157,3911.392,10.000,0.000},
				{59844.296,3911.392,10.000,0.000},
				{60235.435,3911.392,10.000,0.000},
				{60626.574,3911.392,10.000,0.000},
				{61017.713,3911.392,10.000,0.000},
				{61408.853,3911.392,10.000,0.000},
				{61799.992,3911.392,10.000,0.000},
				{62191.131,3911.392,10.000,0.000},
				{62582.270,3911.392,10.000,0.000},
				{62973.409,3911.392,10.000,0.000},
				{63364.548,3911.392,10.000,0.000},
				{63755.688,3911.392,10.000,0.000},
				{64146.827,3911.392,10.000,0.000},
				{64537.966,3911.392,10.000,0.000},
				{64929.105,3911.392,10.000,0.000},
				{65320.244,3911.392,10.000,0.000},
				{65711.384,3911.392,10.000,0.000},
				{66102.523,3911.392,10.000,0.000},
				{66493.662,3911.392,10.000,0.000},
				{66884.801,3911.392,10.000,0.000},
				{67275.940,3911.392,10.000,0.000},
				{67667.080,3911.392,10.000,0.000},
				{68058.219,3911.392,10.000,0.000},
				{68449.358,3911.392,10.000,0.000},
				{68840.497,3911.392,10.000,0.000},
				{69231.636,3911.392,10.000,0.000},
				{69622.775,3911.392,10.000,0.000},
				{70013.915,3911.392,10.000,0.000},
				{70405.054,3911.392,10.000,0.000},
				{70796.193,3911.392,10.000,0.000},
				{71187.332,3911.392,10.000,0.000},
				{71578.471,3911.392,10.000,0.000},
				{71969.611,3911.392,10.000,0.000},
				{72360.750,3911.392,10.000,0.000},
				{72751.889,3911.392,10.000,0.000},
				{73143.028,3911.392,10.000,0.000},
				{73534.167,3911.392,10.000,0.000},
				{73925.307,3911.392,10.000,0.000},
				{74316.446,3911.392,10.000,0.000},
				{74707.585,3911.392,10.000,0.000},
				{75098.724,3911.392,10.000,0.000},
				{75489.863,3911.392,10.000,0.000},
				{75881.002,3911.392,10.000,0.000},
				{76272.142,3911.392,10.000,0.000},
				{76663.281,3911.392,10.000,0.000},
				{77054.420,3911.392,10.000,0.000},
				{77445.559,3911.392,10.000,0.000},
				{77836.698,3911.392,10.000,0.000},
				{78227.838,3911.392,10.000,0.000},
				{78618.977,3911.392,10.000,0.000},
				{79010.116,3911.392,10.000,0.000},
				{79401.255,3911.392,10.000,0.000},
				{79792.394,3911.392,10.000,0.000},
				{80183.534,3911.392,10.000,0.000},
				{80574.673,3911.392,10.000,0.000},
				{80965.812,3911.392,10.000,0.000},
				{81356.951,3911.392,10.000,0.000},
				{81744.961,3880.101,10.000,0.000},
				{82129.842,3848.810,10.000,0.000},
				{82511.594,3817.518,10.000,0.000},
				{82890.217,3786.227,10.000,0.000},
				{83265.710,3754.936,10.000,0.000},
				{83638.075,3723.645,10.000,0.000},
				{84007.310,3692.354,10.000,0.000},
				{84373.417,3661.063,10.000,0.000},
				{84736.394,3629.772,10.000,0.000},
				{85096.242,3598.481,10.000,0.000},
				{85452.961,3567.189,10.000,0.000},
				{85806.551,3535.898,10.000,0.000},
				{86157.011,3504.607,10.000,0.000},
				{86504.343,3473.316,10.000,0.000},
				{86848.545,3442.025,10.000,0.000},
				{87189.619,3410.734,10.000,0.000},
				{87527.563,3379.443,10.000,0.000},
				{87862.378,3348.151,10.000,0.000},
				{88194.064,3316.860,10.000,0.000},
				{88522.621,3285.569,10.000,0.000},
				{88848.049,3254.278,10.000,0.000},
				{89170.348,3222.987,10.000,0.000},
				{89489.517,3191.696,10.000,0.000},
				{89805.558,3160.405,10.000,0.000},
				{90118.469,3129.114,10.000,0.000},
				{90428.251,3097.822,10.000,0.000},
				{90734.904,3066.531,10.000,0.000},
				{91038.428,3035.240,10.000,0.000},
				{91338.823,3003.949,10.000,0.000},
				{91636.089,2972.658,10.000,0.000},
				{91930.226,2941.367,10.000,0.000},
				{92221.233,2910.076,10.000,0.000},
				{92509.112,2878.784,10.000,0.000},
				{92793.861,2847.493,10.000,0.000},
				{93075.481,2816.202,10.000,0.000},
				{93353.972,2784.911,10.000,0.000},
				{93629.334,2753.620,10.000,0.000},
				{93901.567,2722.329,10.000,0.000},
				{94170.671,2691.038,10.000,0.000},
				{94436.646,2659.746,10.000,0.000},
				{94699.491,2628.455,10.000,0.000},
				{94959.208,2597.164,10.000,0.000},
				{95215.795,2565.873,10.000,0.000},
				{95469.253,2534.582,10.000,0.000},
				{95719.582,2503.291,10.000,0.000},
				{95966.782,2472.000,10.000,0.000},
				{96210.853,2440.709,10.000,0.000},
				{96451.795,2409.417,10.000,0.000},
				{96689.607,2378.126,10.000,0.000},
				{96924.291,2346.835,10.000,0.000},
				{97155.845,2315.544,10.000,0.000},
				{97384.271,2284.253,10.000,0.000},
				{97609.567,2252.962,10.000,0.000},
				{97831.734,2221.671,10.000,0.000},
				{98050.772,2190.379,10.000,0.000},
				{98266.681,2159.088,10.000,0.000},
				{98479.460,2127.797,10.000,0.000},
				{98689.111,2096.506,10.000,0.000},
				{98895.632,2065.215,10.000,0.000},
				{99099.025,2033.924,10.000,0.000},
				{99299.288,2002.633,10.000,0.000},
				{99496.422,1971.342,10.000,0.000},
				{99690.427,1940.050,10.000,0.000},
				{99881.303,1908.759,10.000,0.000},
				{100069.050,1877.468,10.000,0.000},
				{100253.668,1846.177,10.000,0.000},
				{100435.156,1814.886,10.000,0.000},
				{100613.516,1783.595,10.000,0.000},
				{100788.746,1752.304,10.000,0.000},
				{100960.847,1721.012,10.000,0.000},
				{101129.819,1689.721,10.000,0.000},
				{101295.662,1658.430,10.000,0.000},
				{101458.376,1627.139,10.000,0.000},
				{101617.961,1595.848,10.000,0.000},
				{101774.417,1564.557,10.000,0.000},
				{101927.743,1533.266,10.000,0.000},
				{102077.941,1501.974,10.000,0.000},
				{102225.009,1470.683,10.000,0.000},
				{102368.948,1439.392,10.000,0.000},
				{102509.758,1408.101,10.000,0.000},
				{102647.439,1376.810,10.000,0.000},
				{102781.991,1345.519,10.000,0.000},
				{102913.414,1314.228,10.000,0.000},
				{103041.708,1282.937,10.000,0.000},
				{103166.872,1251.645,10.000,0.000},
				{103288.908,1220.354,10.000,0.000},
				{103407.814,1189.063,10.000,0.000},
				{103523.591,1157.772,10.000,0.000},
				{103636.239,1126.481,10.000,0.000},
				{103745.758,1095.190,10.000,0.000},
				{103852.148,1063.899,10.000,0.000},
				{103955.409,1032.607,10.000,0.000},
				{104055.540,1001.316,10.000,0.000},
				{104152.543,970.025,10.000,0.000},
				{104246.416,938.734,10.000,0.000},
				{104337.161,907.443,10.000,0.000},
				{104424.776,876.152,10.000,0.000},
				{104509.262,844.861,10.000,0.000},
				{104590.619,813.570,10.000,0.000},
				{104668.847,782.278,10.000,0.000},
				{104743.945,750.987,10.000,0.000},
				{104815.915,719.696,10.000,0.000},
				{104884.756,688.405,10.000,0.000},
				{104950.467,657.114,10.000,0.000},
				{105013.049,625.823,10.000,0.000},
				{105072.502,594.532,10.000,0.000},
				{105128.826,563.240,10.000,0.000},
				{105182.021,531.949,10.000,0.000},
				{105232.087,500.658,10.000,0.000},
				{105279.024,469.367,10.000,0.000},
				{105322.831,438.076,10.000,0.000},
				{105363.510,406.785,10.000,0.000},
				{105401.059,375.494,10.000,0.000},
				{105435.480,344.202,10.000,0.000},
				{105466.771,312.911,10.000,0.000},
				{105494.933,281.620,10.000,0.000},
				{105519.966,250.329,10.000,0.000},
				{105541.869,219.038,10.000,0.000},
				{105560.644,187.747,10.000,0.000},
				{105576.290,156.456,10.000,0.000},
				{105588.806,125.165,10.000,0.000},
				{105598.193,93.873,10.000,0.000},
				{105604.452,62.582,10.000,0.000}		};

}