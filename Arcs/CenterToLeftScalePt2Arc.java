package org.usfirst.frc.team319.arcs;

import org.usfirst.frc.team319.models.SrxMotionProfile;
import org.usfirst.frc.team319.models.SrxTrajectory;

public class CenterToLeftScalePt2Arc extends SrxTrajectory{
	
	// WAYPOINTS:
	// (X,Y,degrees)
	// (2.13,12.08,30.00)
	// (16.13,16.08,-15.00)
	
    public CenterToLeftScalePt2Arc() {
		super();
		this.highGear = true;
		centerProfile = new SrxMotionProfile(centerPoints.length, centerPoints);
	}

	
    public CenterToLeftScalePt2Arc(boolean flipped) {
		super();
		this.highGear = true;
		this.flipped = flipped;
		centerProfile = new SrxMotionProfile(centerPoints.length, centerPoints);
	}

	public boolean highGear = true;

	double[][] centerPoints = {
				{0.568,11.351,10.000,30.000},
				{2.838,34.054,10.000,30.000},
				{7.946,68.107,10.000,30.000},
				{17.027,113.512,10.000,30.000},
				{31.216,170.268,10.000,30.000},
				{51.648,238.376,10.000,30.000},
				{79.459,317.834,10.000,30.000},
				{115.783,408.644,10.000,30.000},
				{161.755,510.805,10.000,30.000},
				{218.511,624.318,10.000,30.000},
				{287.186,749.181,10.000,30.000},
				{368.915,885.396,10.000,30.000},
				{464.833,1032.962,10.000,30.000},
				{576.075,1191.879,10.000,30.000},
				{703.209,1350.797,10.000,30.000},
				{846.234,1509.714,10.000,30.000},
				{1005.152,1668.631,10.000,30.000},
				{1179.961,1827.548,10.000,30.000},
				{1370.661,1986.466,10.000,30.000},
				{1577.254,2145.383,10.000,30.000},
				{1799.738,2304.300,10.000,30.010},
				{2038.114,2463.217,10.000,30.010},
				{2292.381,2622.135,10.000,30.010},
				{2562.541,2781.052,10.000,30.010},
				{2848.592,2939.969,10.000,30.010},
				{3150.534,3098.886,10.000,30.020},
				{3468.369,3257.804,10.000,30.020},
				{3802.095,3416.721,10.000,30.020},
				{4151.713,3575.638,10.000,30.030},
				{4517.223,3734.555,10.000,30.030},
				{4898.624,3893.472,10.000,30.040},
				{5295.917,4052.390,10.000,30.040},
				{5709.102,4211.307,10.000,30.050},
				{6138.179,4370.224,10.000,30.060},
				{6583.147,4529.141,10.000,30.070},
				{7044.007,4688.059,10.000,30.070},
				{7520.759,4846.976,10.000,30.080},
				{8013.402,5005.893,10.000,30.100},
				{8521.937,5164.810,10.000,30.110},
				{9046.364,5323.728,10.000,30.120},
				{9586.683,5482.645,10.000,30.130},
				{10142.893,5641.562,10.000,30.150},
				{10714.995,5800.479,10.000,30.170},
				{11302.989,5959.397,10.000,30.180},
				{11906.875,6118.314,10.000,30.200},
				{12526.652,6277.231,10.000,30.220},
				{13162.321,6436.148,10.000,30.240},
				{13813.881,6595.066,10.000,30.260},
				{14481.334,6753.983,10.000,30.290},
				{15164.678,6912.900,10.000,30.310},
				{15863.914,7071.817,10.000,30.340},
				{16579.042,7230.735,10.000,30.370},
				{17310.061,7389.652,10.000,30.400},
				{18056.972,7548.569,10.000,30.430},
				{18819.775,7707.486,10.000,30.460},
				{19598.469,7866.404,10.000,30.500},
				{20393.055,8025.321,10.000,30.530},
				{21203.533,8184.238,10.000,30.570},
				{22029.903,8343.155,10.000,30.610},
				{22872.164,8502.073,10.000,30.650},
				{23730.318,8660.990,10.000,30.690},
				{24604.362,8819.907,10.000,30.730},
				{25494.299,8978.824,10.000,30.770},
				{26400.127,9137.742,10.000,30.820},
				{27321.847,9296.659,10.000,30.870},
				{28259.459,9455.576,10.000,30.920},
				{29212.962,9614.493,10.000,30.970},
				{30182.358,9773.411,10.000,31.020},
				{31167.645,9932.328,10.000,31.070},
				{32168.823,10091.245,10.000,31.120},
				{33185.894,10250.162,10.000,31.180},
				{34218.856,10409.080,10.000,31.230},
				{35267.709,10567.997,10.000,31.290},
				{36332.455,10726.914,10.000,31.350},
				{37413.092,10885.831,10.000,31.400},
				{38509.054,11033.397,10.000,31.460},
				{39619.204,11169.612,10.000,31.520},
				{40742.409,11294.476,10.000,31.580},
				{41877.532,11407.988,10.000,31.640},
				{43023.439,11510.149,10.000,31.700},
				{44178.994,11600.959,10.000,31.760},
				{45343.063,11680.417,10.000,31.820},
				{46514.510,11748.525,10.000,31.870},
				{47692.200,11805.281,10.000,31.930},
				{48874.999,11850.686,10.000,31.990},
				{50061.770,11884.740,10.000,32.040},
				{51251.379,11907.442,10.000,32.100},
				{52442.691,11918.793,10.000,32.150},
				{53634.570,11918.793,10.000,32.200},
				{54826.449,11918.793,10.000,32.250},
				{56018.329,11918.793,10.000,32.300},
				{57210.208,11918.793,10.000,32.340},
				{58402.087,11918.793,10.000,32.380},
				{59593.967,11918.793,10.000,32.430},
				{60785.846,11918.793,10.000,32.460},
				{61977.725,11918.793,10.000,32.500},
				{63169.605,11918.793,10.000,32.540},
				{64361.484,11918.793,10.000,32.570},
				{65553.363,11918.793,10.000,32.600},
				{66745.243,11918.793,10.000,32.620},
				{67937.122,11918.793,10.000,32.650},
				{69129.001,11918.793,10.000,32.670},
				{70320.881,11918.793,10.000,32.690},
				{71512.760,11918.793,10.000,32.700},
				{72704.639,11918.793,10.000,32.720},
				{73896.519,11918.793,10.000,32.730},
				{75088.398,11918.793,10.000,32.730},
				{76280.277,11918.793,10.000,32.740},
				{77472.157,11918.793,10.000,32.740},
				{78664.036,11918.793,10.000,32.740},
				{79855.915,11918.793,10.000,32.730},
				{81047.795,11918.793,10.000,32.720},
				{82239.674,11918.793,10.000,32.710},
				{83431.553,11918.793,10.000,32.690},
				{84623.433,11918.793,10.000,32.670},
				{85815.312,11918.793,10.000,32.650},
				{87007.191,11918.793,10.000,32.620},
				{88199.071,11918.793,10.000,32.590},
				{89390.950,11918.793,10.000,32.550},
				{90582.829,11918.793,10.000,32.510},
				{91774.709,11918.793,10.000,32.470},
				{92966.588,11918.793,10.000,32.420},
				{94158.467,11918.793,10.000,32.370},
				{95350.347,11918.793,10.000,32.320},
				{96542.226,11918.793,10.000,32.260},
				{97734.105,11918.793,10.000,32.190},
				{98925.985,11918.793,10.000,32.120},
				{100117.864,11918.793,10.000,32.050},
				{101309.743,11918.793,10.000,31.970},
				{102501.623,11918.793,10.000,31.890},
				{103693.502,11918.793,10.000,31.810},
				{104885.381,11918.793,10.000,31.720},
				{106077.261,11918.793,10.000,31.620},
				{107269.140,11918.793,10.000,31.520},
				{108461.019,11918.793,10.000,31.420},
				{109652.899,11918.793,10.000,31.310},
				{110844.778,11918.793,10.000,31.200},
				{112036.657,11918.793,10.000,31.080},
				{113228.537,11918.793,10.000,30.950},
				{114420.416,11918.793,10.000,30.830},
				{115612.295,11918.793,10.000,30.690},
				{116804.175,11918.793,10.000,30.550},
				{117996.054,11918.793,10.000,30.410},
				{119187.933,11918.793,10.000,30.260},
				{120379.813,11918.793,10.000,30.110},
				{121571.692,11918.793,10.000,29.950},
				{122763.571,11918.793,10.000,29.790},
				{123955.451,11918.793,10.000,29.620},
				{125147.330,11918.793,10.000,29.440},
				{126339.209,11918.793,10.000,29.260},
				{127531.089,11918.793,10.000,29.080},
				{128722.968,11918.793,10.000,28.890},
				{129914.847,11918.793,10.000,28.690},
				{131106.727,11918.793,10.000,28.490},
				{132298.606,11918.793,10.000,28.290},
				{133490.485,11918.793,10.000,28.070},
				{134682.365,11918.793,10.000,27.860},
				{135874.244,11918.793,10.000,27.630},
				{137066.123,11918.793,10.000,27.400},
				{138258.003,11918.793,10.000,27.170},
				{139449.882,11918.793,10.000,26.930},
				{140641.761,11918.793,10.000,26.680},
				{141833.641,11918.793,10.000,26.430},
				{143025.520,11918.793,10.000,26.180},
				{144217.399,11918.793,10.000,25.910},
				{145409.279,11918.793,10.000,25.650},
				{146601.158,11918.793,10.000,25.370},
				{147793.037,11918.793,10.000,25.090},
				{148984.917,11918.793,10.000,24.810},
				{150176.796,11918.793,10.000,24.520},
				{151368.675,11918.793,10.000,24.220},
				{152560.555,11918.793,10.000,23.920},
				{153752.434,11918.793,10.000,23.620},
				{154944.313,11918.793,10.000,23.300},
				{156136.193,11918.793,10.000,22.990},
				{157328.072,11918.793,10.000,22.660},
				{158519.951,11918.793,10.000,22.330},
				{159711.831,11918.793,10.000,22.000},
				{160903.710,11918.793,10.000,21.660},
				{162095.589,11918.793,10.000,21.320},
				{163287.469,11918.793,10.000,20.970},
				{164479.348,11918.793,10.000,20.620},
				{165671.227,11918.793,10.000,20.260},
				{166863.107,11918.793,10.000,19.890},
				{168054.986,11918.793,10.000,19.520},
				{169246.865,11918.793,10.000,19.150},
				{170438.745,11918.793,10.000,18.770},
				{171630.624,11918.793,10.000,18.390},
				{172822.503,11918.793,10.000,18.010},
				{174014.383,11918.793,10.000,17.620},
				{175206.262,11918.793,10.000,17.220},
				{176398.141,11918.793,10.000,16.820},
				{177590.021,11918.793,10.000,16.420},
				{178781.900,11918.793,10.000,16.010},
				{179973.779,11918.793,10.000,15.600},
				{181165.659,11918.793,10.000,15.190},
				{182357.538,11918.793,10.000,14.780},
				{183549.417,11918.793,10.000,14.360},
				{184741.297,11918.793,10.000,13.930},
				{185933.176,11918.793,10.000,13.510},
				{187125.055,11918.793,10.000,13.080},
				{188316.935,11918.793,10.000,12.650},
				{189508.814,11918.793,10.000,12.220},
				{190700.693,11918.793,10.000,11.790},
				{191892.573,11918.793,10.000,11.350},
				{193084.452,11918.793,10.000,10.920},
				{194276.331,11918.793,10.000,10.480},
				{195468.211,11918.793,10.000,10.040},
				{196660.090,11918.793,10.000,9.600},
				{197851.969,11918.793,10.000,9.160},
				{199043.849,11918.793,10.000,8.720},
				{200235.728,11918.793,10.000,8.280},
				{201427.607,11918.793,10.000,7.830},
				{202619.487,11918.793,10.000,7.390},
				{203811.366,11918.793,10.000,6.950},
				{205003.245,11918.793,10.000,6.510},
				{206195.125,11918.793,10.000,6.070},
				{207387.004,11918.793,10.000,5.630},
				{208578.883,11918.793,10.000,5.190},
				{209770.763,11918.793,10.000,4.760},
				{210962.642,11918.793,10.000,4.320},
				{212154.521,11918.793,10.000,3.890},
				{213346.401,11918.793,10.000,3.460},
				{214538.280,11918.793,10.000,3.030},
				{215730.159,11918.793,10.000,2.600},
				{216922.039,11918.793,10.000,2.180},
				{218113.918,11918.793,10.000,1.760},
				{219305.797,11918.793,10.000,1.340},
				{220497.677,11918.793,10.000,0.920},
				{221689.556,11918.793,10.000,0.510},
				{222881.435,11918.793,10.000,0.100},
				{224073.315,11918.793,10.000,-0.300},
				{225265.194,11918.793,10.000,-0.710},
				{226457.073,11918.793,10.000,-1.100},
				{227648.953,11918.793,10.000,-1.500},
				{228840.832,11918.793,10.000,-1.890},
				{230032.711,11918.793,10.000,-2.270},
				{231224.591,11918.793,10.000,-2.650},
				{232416.470,11918.793,10.000,-3.030},
				{233608.349,11918.793,10.000,-3.400},
				{234800.229,11918.793,10.000,-3.770},
				{235992.108,11918.793,10.000,-4.130},
				{237183.987,11918.793,10.000,-4.490},
				{238375.867,11918.793,10.000,-4.850},
				{239567.746,11918.793,10.000,-5.190},
				{240759.625,11918.793,10.000,-5.540},
				{241951.505,11918.793,10.000,-5.870},
				{243143.384,11918.793,10.000,-6.210},
				{244335.263,11918.793,10.000,-6.530},
				{245527.143,11918.793,10.000,-6.850},
				{246719.022,11918.793,10.000,-7.170},
				{247910.901,11918.793,10.000,-7.480},
				{249102.781,11918.793,10.000,-7.780},
				{250294.660,11918.793,10.000,-8.080},
				{251486.185,11911.700,10.000,-8.380},
				{252676.433,11893.255,10.000,-8.660},
				{253864.268,11863.458,10.000,-8.940},
				{255048.557,11822.311,10.000,-9.220},
				{256228.163,11769.812,10.000,-9.480},
				{257401.952,11705.962,10.000,-9.740},
				{258568.788,11630.761,10.000,-10.000},
				{259727.536,11544.209,10.000,-10.240},
				{260877.062,11446.305,10.000,-10.480},
				{262016.230,11337.050,10.000,-10.710},
				{263143.904,11216.444,10.000,-10.930},
				{264258.951,11084.487,10.000,-11.150},
				{265360.234,10941.179,10.000,-11.350},
				{266446.619,10786.519,10.000,-11.550},
				{267517.325,10627.602,10.000,-11.740},
				{268572.140,10468.684,10.000,-11.920},
				{269611.062,10309.767,10.000,-12.100},
				{270634.093,10150.850,10.000,-12.260},
				{271641.232,9991.933,10.000,-12.420},
				{272632.479,9833.015,10.000,-12.580},
				{273607.835,9674.098,10.000,-12.720},
				{274567.299,9515.181,10.000,-12.860},
				{275510.871,9356.264,10.000,-13.000},
				{276438.552,9197.346,10.000,-13.120},
				{277350.341,9038.429,10.000,-13.250},
				{278246.238,8879.512,10.000,-13.360},
				{279126.243,8720.595,10.000,-13.470},
				{279990.357,8561.677,10.000,-13.580},
				{280838.578,8402.760,10.000,-13.670},
				{281670.909,8243.843,10.000,-13.770},
				{282487.347,8084.926,10.000,-13.860},
				{283287.894,7926.008,10.000,-13.940},
				{284072.549,7767.091,10.000,-14.020},
				{284841.312,7608.174,10.000,-14.100},
				{285594.183,7449.257,10.000,-14.170},
				{286331.163,7290.339,10.000,-14.230},
				{287052.251,7131.422,10.000,-14.300},
				{287757.448,6972.505,10.000,-14.350},
				{288446.752,6813.588,10.000,-14.410},
				{289120.165,6654.670,10.000,-14.460},
				{289777.686,6495.753,10.000,-14.510},
				{290419.316,6336.836,10.000,-14.550},
				{291045.054,6177.919,10.000,-14.600},
				{291654.900,6019.001,10.000,-14.630},
				{292248.854,5860.084,10.000,-14.670},
				{292826.916,5701.167,10.000,-14.700},
				{293389.087,5542.250,10.000,-14.730},
				{293935.366,5383.332,10.000,-14.760},
				{294465.754,5224.415,10.000,-14.790},
				{294980.249,5065.498,10.000,-14.810},
				{295478.853,4906.581,10.000,-14.830},
				{295961.566,4747.664,10.000,-14.850},
				{296428.386,4588.746,10.000,-14.870},
				{296879.315,4429.829,10.000,-14.890},
				{297314.352,4270.912,10.000,-14.900},
				{297733.497,4111.995,10.000,-14.920},
				{298136.751,3953.077,10.000,-14.930},
				{298524.113,3794.160,10.000,-14.940},
				{298895.583,3635.243,10.000,-14.950},
				{299251.161,3476.326,10.000,-14.960},
				{299590.848,3317.408,10.000,-14.960},
				{299914.643,3158.491,10.000,-14.970},
				{300222.546,2999.574,10.000,-14.980},
				{300514.558,2840.657,10.000,-14.980},
				{300790.677,2681.739,10.000,-14.980},
				{301050.906,2522.822,10.000,-14.990},
				{301295.242,2363.905,10.000,-14.990},
				{301523.687,2204.988,10.000,-14.990},
				{301736.239,2046.070,10.000,-14.990},
				{301932.901,1887.153,10.000,-15.000},
				{302113.670,1728.236,10.000,-15.000},
				{302278.548,1569.319,10.000,-15.000},
				{302427.534,1410.401,10.000,-15.000},
				{302560.628,1251.484,10.000,-15.000},
				{302677.831,1092.567,10.000,-15.000},
				{302779.496,940.743,10.000,-15.000},
				{302866.547,800.271,10.000,-15.000},
				{302940.118,671.150,10.000,-15.000},
				{303001.344,553.380,10.000,-15.000},
				{303051.362,446.962,10.000,-15.000},
				{303091.304,351.894,10.000,-15.000},
				{303122.308,268.178,10.000,-15.000},
				{303145.508,195.813,10.000,-15.000},
				{303162.038,134.800,10.000,-15.000},
				{303173.035,85.137,10.000,-15.000},
				{303179.633,46.826,10.000,-15.000},
				{303182.968,19.866,10.000,-15.000},
				{303184.174,4.257,10.000,-15.000},
				{303184.387,0.000,10.000,-15.000},
				{303184.387,0.000,10.000,-15.000}		};

}