package org.usfirst.frc.team319.arcs;

import org.usfirst.frc.team319.models.SrxMotionProfile;
import org.usfirst.frc.team319.models.SrxTrajectory;

public class RightWallToRightSwitchArc extends SrxTrajectory{
	
	// WAYPOINTS:
	// (X,Y,degrees)
	// (1.63,3.79,0.00)
	// (18.63,2.79,-30.00)
	
    public RightWallToRightSwitchArc() {
		super();
		centerProfile = new SrxMotionProfile(centerPoints.length, centerPoints);
	}

	
    public RightWallToRightSwitchArc(boolean flipped) {
		super();
		this.flipped = flipped;
		centerProfile = new SrxMotionProfile(centerPoints.length, centerPoints);
	}

	double[][] centerPoints = {
				{-0.152,-3.047,10.000,0.000},
				{-0.762,-6.095,10.000,0.000},
				{-2.133,-13.713,10.000,0.000},
				{-4.571,-24.380,10.000,0.000},
				{-8.381,-38.094,10.000,0.000},
				{-13.866,-54.857,10.000,0.000},
				{-21.333,-74.669,10.000,0.000},
				{-31.086,-97.533,10.000,0.000},
				{-43.431,-123.449,10.000,0.000},
				{-58.673,-152.420,10.000,0.000},
				{-76.966,-182.924,10.000,0.000},
				{-98.309,-213.438,10.000,0.000},
				{-122.706,-243.965,10.000,0.001},
				{-150.157,-274.507,10.000,0.001},
				{-180.663,-305.064,10.000,0.001},
				{-214.227,-335.639,10.000,0.002},
				{-250.850,-366.234,10.000,0.002},
				{-290.535,-396.850,10.000,0.003},
				{-333.284,-427.488,10.000,0.004},
				{-379.099,-458.151,10.000,0.005},
				{-427.983,-488.840,10.000,0.007},
				{-479.939,-519.556,10.000,0.008},
				{-534.969,-550.301,10.000,0.010},
				{-593.077,-581.076,10.000,0.013},
				{-654.265,-611.883,10.000,0.016},
				{-718.537,-642.724,10.000,0.019},
				{-785.897,-673.599,10.000,0.022},
				{-856.348,-704.509,10.000,0.027},
				{-929.894,-735.457,10.000,0.031},
				{-1006.538,-766.443,10.000,0.037},
				{-1086.285,-797.468,10.000,0.042},
				{-1169.138,-828.533,10.000,0.049},
				{-1255.102,-859.640,10.000,0.056},
				{-1344.181,-890.789,10.000,0.064},
				{-1436.379,-921.982,10.000,0.073},
				{-1531.701,-953.218,10.000,0.083},
				{-1630.151,-984.500,10.000,0.094},
				{-1731.734,-1015.827,10.000,0.105},
				{-1836.454,-1047.200,10.000,0.118},
				{-1944.316,-1078.619,10.000,0.132},
				{-2055.324,-1110.086,10.000,0.147},
				{-2169.484,-1141.600,10.000,0.163},
				{-2286.801,-1173.162,10.000,0.180},
				{-2407.278,-1204.773,10.000,0.199},
				{-2530.921,-1236.431,10.000,0.219},
				{-2657.735,-1268.138,10.000,0.241},
				{-2787.724,-1299.894,10.000,0.264},
				{-2920.894,-1331.697,10.000,0.288},
				{-3057.249,-1363.549,10.000,0.314},
				{-3196.794,-1395.449,10.000,0.342},
				{-3339.533,-1427.396,10.000,0.371},
				{-3485.472,-1459.390,10.000,0.402},
				{-3634.615,-1491.431,10.000,0.435},
				{-3786.967,-1523.518,10.000,0.470},
				{-3942.532,-1555.650,10.000,0.506},
				{-4101.315,-1587.826,10.000,0.545},
				{-4263.319,-1620.046,10.000,0.585},
				{-4428.550,-1652.308,10.000,0.628},
				{-4597.011,-1684.612,10.000,0.673},
				{-4768.707,-1716.956,10.000,0.720},
				{-4943.641,-1749.339,10.000,0.769},
				{-5121.817,-1781.760,10.000,0.820},
				{-5303.239,-1814.217,10.000,0.874},
				{-5487.909,-1846.708,10.000,0.930},
				{-5675.833,-1879.232,10.000,0.988},
				{-5867.011,-1911.787,10.000,1.048},
				{-6061.448,-1944.371,10.000,1.111},
				{-6259.147,-1976.982,10.000,1.177},
				{-6460.109,-2009.619,10.000,1.245},
				{-6664.336,-2042.278,10.000,1.315},
				{-6871.832,-2074.958,10.000,1.388},
				{-7082.598,-2107.656,10.000,1.464},
				{-7296.635,-2140.369,10.000,1.542},
				{-7513.944,-2173.096,10.000,1.623},
				{-7734.528,-2205.833,10.000,1.706},
				{-7958.385,-2238.578,10.000,1.792},
				{-8185.518,-2271.328,10.000,1.881},
				{-8415.926,-2304.080,10.000,1.972},
				{-8649.609,-2336.830,10.000,2.065},
				{-8886.567,-2369.577,10.000,2.162},
				{-9126.798,-2402.315,10.000,2.261},
				{-9370.303,-2435.043,10.000,2.362},
				{-9617.078,-2467.757,10.000,2.466},
				{-9867.124,-2500.453,10.000,2.573},
				{-10120.436,-2533.128,10.000,2.682},
				{-10377.014,-2565.778,10.000,2.794},
				{-10636.854,-2598.400,10.000,2.908},
				{-10899.953,-2630.989,10.000,3.024},
				{-11166.307,-2663.543,10.000,3.143},
				{-11435.913,-2696.057,10.000,3.265},
				{-11708.766,-2728.527,10.000,3.388},
				{-11984.861,-2760.950,10.000,3.514},
				{-12264.193,-2793.321,10.000,3.642},
				{-12546.757,-2825.637,10.000,3.772},
				{-12832.546,-2857.892,10.000,3.904},
				{-13121.554,-2890.085,10.000,4.038},
				{-13413.775,-2922.209,10.000,4.174},
				{-13709.201,-2954.261,10.000,4.312},
				{-14007.825,-2986.238,10.000,4.451},
				{-14309.639,-3018.134,10.000,4.593},
				{-14614.633,-3049.946,10.000,4.735},
				{-14922.800,-3081.669,10.000,4.879},
				{-15234.130,-3113.301,10.000,5.025},
				{-15548.614,-3144.835,10.000,5.171},
				{-15866.241,-3176.269,10.000,5.319},
				{-16187.000,-3207.598,10.000,5.468},
				{-16510.882,-3238.819,10.000,5.618},
				{-16837.875,-3269.927,10.000,5.768},
				{-17167.967,-3300.919,10.000,5.919},
				{-17501.146,-3331.790,10.000,6.070},
				{-17837.400,-3362.538,10.000,6.222},
				{-18176.715,-3393.157,10.000,6.374},
				{-18519.080,-3423.645,10.000,6.526},
				{-18864.480,-3453.998,10.000,6.677},
				{-19212.901,-3484.213,10.000,6.829},
				{-19564.330,-3514.285,10.000,6.980},
				{-19918.751,-3544.213,10.000,7.130},
				{-20276.150,-3573.991,10.000,7.280},
				{-20636.512,-3603.618,10.000,7.428},
				{-20999.821,-3633.089,10.000,7.576},
				{-21366.061,-3662.403,10.000,7.722},
				{-21735.217,-3691.556,10.000,7.866},
				{-22107.271,-3720.545,10.000,8.009},
				{-22482.208,-3749.367,10.000,8.150},
				{-22860.010,-3778.021,10.000,8.289},
				{-23240.660,-3806.502,10.000,8.426},
				{-23624.141,-3834.809,10.000,8.560},
				{-24010.435,-3862.939,10.000,8.691},
				{-24399.524,-3890.889,10.000,8.820},
				{-24791.390,-3918.658,10.000,8.945},
				{-25186.014,-3946.243,10.000,9.067},
				{-25583.378,-3973.642,10.000,9.186},
				{-25983.463,-4000.853,10.000,9.301},
				{-26386.251,-4027.873,10.000,9.412},
				{-26791.565,-4053.143,10.000,9.518},
				{-27199.076,-4075.109,10.000,9.621},
				{-27608.454,-4093.785,10.000,9.718},
				{-28019.373,-4109.184,10.000,9.810},
				{-28431.505,-4121.322,10.000,9.897},
				{-28844.526,-4130.212,10.000,9.979},
				{-29258.113,-4135.872,10.000,10.055},
				{-29671.945,-4138.318,10.000,10.124},
				{-30085.702,-4137.568,10.000,10.188},
				{-30499.066,-4133.638,10.000,10.246},
				{-30911.875,-4128.088,10.000,10.297},
				{-31324.121,-4122.464,10.000,10.341},
				{-31735.798,-4116.769,10.000,10.380},
				{-32146.899,-4111.005,10.000,10.411},
				{-32557.416,-4105.173,10.000,10.436},
				{-32967.344,-4099.276,10.000,10.454},
				{-33376.675,-4093.314,10.000,10.465},
				{-33785.404,-4087.289,10.000,10.470},
				{-34193.524,-4081.204,10.000,10.467},
				{-34601.030,-4075.059,10.000,10.457},
				{-35007.916,-4068.856,10.000,10.441},
				{-35414.175,-4062.596,10.000,10.417},
				{-35819.803,-4056.280,10.000,10.385},
				{-36224.794,-4049.909,10.000,10.347},
				{-36629.143,-4043.486,10.000,10.301},
				{-37032.844,-4037.010,10.000,10.248},
				{-37435.892,-4030.484,10.000,10.187},
				{-37838.283,-4023.908,10.000,10.119},
				{-38240.011,-4017.283,10.000,10.043},
				{-38641.072,-4010.612,10.000,9.959},
				{-39041.462,-4003.894,10.000,9.868},
				{-39441.175,-3997.132,10.000,9.769},
				{-39840.208,-3990.326,10.000,9.662},
				{-40238.556,-3983.478,10.000,9.548},
				{-40636.215,-3976.590,10.000,9.425},
				{-41033.181,-3969.664,10.000,9.295},
				{-41429.451,-3962.700,10.000,9.156},
				{-41825.021,-3955.700,10.000,9.010},
				{-42219.888,-3948.668,10.000,8.855},
				{-42614.048,-3941.603,10.000,8.692},
				{-43007.499,-3934.510,10.000,8.522},
				{-43400.238,-3927.390,10.000,8.343},
				{-43792.263,-3920.246,10.000,8.155},
				{-44183.571,-3913.080,10.000,7.960},
				{-44574.160,-3905.896,10.000,7.757},
				{-44964.030,-3898.696,10.000,7.545},
				{-45353.178,-3891.484,10.000,7.325},
				{-45741.605,-3884.264,10.000,7.096},
				{-46129.308,-3877.039,10.000,6.859},
				{-46516.290,-3869.814,10.000,6.615},
				{-46902.549,-3862.593,10.000,6.361},
				{-47288.087,-3855.380,10.000,6.100},
				{-47672.905,-3848.181,10.000,5.830},
				{-48057.005,-3841.001,10.000,5.552},
				{-48440.390,-3833.845,10.000,5.266},
				{-48823.062,-3826.719,10.000,4.972},
				{-49205.024,-3819.629,10.000,4.669},
				{-49586.283,-3812.583,10.000,4.359},
				{-49966.841,-3805.585,10.000,4.040},
				{-50346.706,-3798.645,10.000,3.713},
				{-50725.883,-3791.768,10.000,3.379},
				{-51104.379,-3784.963,10.000,3.037},
				{-51482.203,-3778.237,10.000,2.687},
				{-51859.363,-3771.600,10.000,2.329},
				{-52235.868,-3765.058,10.000,1.964},
				{-52611.730,-3758.622,10.000,1.592},
				{-52986.960,-3752.299,10.000,1.212},
				{-53361.570,-3746.099,10.000,0.825},
				{-53735.573,-3740.031,10.000,0.432},
				{-54108.984,-3734.106,10.000,0.031},
				{-54481.817,-3728.331,10.000,-0.376},
				{-54854.089,-3722.718,10.000,-0.790},
				{-55225.816,-3717.275,10.000,-1.209},
				{-55597.018,-3712.012,10.000,-1.635},
				{-55967.711,-3706.939,10.000,-2.067},
				{-56337.918,-3702.066,10.000,-2.504},
				{-56707.658,-3697.402,10.000,-2.947},
				{-57076.954,-3692.957,10.000,-3.394},
				{-57445.828,-3688.738,10.000,-3.847},
				{-57814.303,-3684.756,10.000,-4.304},
				{-58182.405,-3681.018,10.000,-4.765},
				{-58550.159,-3677.534,10.000,-5.231},
				{-58917.590,-3674.310,10.000,-5.700},
				{-59284.725,-3671.353,10.000,-6.172},
				{-59651.587,-3668.621,10.000,-6.648},
				{-60018.062,-3664.750,10.000,-7.126},
				{-60383.906,-3658.435,10.000,-7.606},
				{-60748.874,-3649.685,10.000,-8.087},
				{-61112.725,-3638.506,10.000,-8.568},
				{-61475.215,-3624.902,10.000,-9.050},
				{-61836.102,-3608.873,10.000,-9.530},
				{-62195.144,-3590.417,10.000,-10.009},
				{-62552.097,-3569.529,10.000,-10.485},
				{-62906.717,-3546.201,10.000,-10.958},
				{-63258.764,-3520.473,10.000,-11.428},
				{-63608.134,-3493.697,10.000,-11.893},
				{-63954.851,-3467.173,10.000,-12.354},
				{-64298.940,-3440.888,10.000,-12.809},
				{-64640.423,-3414.828,10.000,-13.260},
				{-64979.321,-3388.979,10.000,-13.706},
				{-65315.653,-3363.327,10.000,-14.146},
				{-65649.439,-3337.858,10.000,-14.580},
				{-65980.695,-3312.559,10.000,-15.008},
				{-66309.437,-3287.416,10.000,-15.431},
				{-66635.678,-3262.415,10.000,-15.846},
				{-66959.433,-3237.544,10.000,-16.256},
				{-67280.712,-3212.789,10.000,-16.659},
				{-67599.526,-3188.139,10.000,-17.055},
				{-67915.884,-3163.580,10.000,-17.445},
				{-68229.794,-3139.102,10.000,-17.827},
				{-68541.263,-3114.692,10.000,-18.203},
				{-68850.297,-3090.340,10.000,-18.572},
				{-69156.901,-3066.036,10.000,-18.933},
				{-69461.078,-3041.770,10.000,-19.288},
				{-69762.831,-3017.531,10.000,-19.635},
				{-70062.162,-2993.311,10.000,-19.975},
				{-70359.072,-2969.101,10.000,-20.308},
				{-70653.561,-2944.893,10.000,-20.634},
				{-70945.629,-2920.680,10.000,-20.953},
				{-71235.275,-2896.453,10.000,-21.264},
				{-71522.495,-2872.207,10.000,-21.568},
				{-71807.289,-2847.934,10.000,-21.865},
				{-72089.652,-2823.630,10.000,-22.156},
				{-72369.580,-2799.287,10.000,-22.439},
				{-72647.070,-2774.902,10.000,-22.715},
				{-72922.117,-2750.468,10.000,-22.984},
				{-73194.716,-2725.983,10.000,-23.246},
				{-73464.860,-2701.441,10.000,-23.502},
				{-73732.544,-2676.839,10.000,-23.751},
				{-73997.761,-2652.173,10.000,-23.993},
				{-74260.505,-2627.441,10.000,-24.228},
				{-74520.769,-2602.639,10.000,-24.457},
				{-74778.545,-2577.765,10.000,-24.680},
				{-75033.827,-2552.816,10.000,-24.896},
				{-75286.606,-2527.790,10.000,-25.106},
				{-75536.875,-2502.686,10.000,-25.310},
				{-75784.625,-2477.502,10.000,-25.508},
				{-76029.848,-2452.235,10.000,-25.700},
				{-76272.537,-2426.886,10.000,-25.886},
				{-76512.682,-2401.452,10.000,-26.066},
				{-76750.275,-2375.933,10.000,-26.241},
				{-76985.308,-2350.328,10.000,-26.410},
				{-77217.772,-2324.636,10.000,-26.573},
				{-77447.657,-2298.857,10.000,-26.731},
				{-77674.956,-2272.989,10.000,-26.884},
				{-77899.660,-2247.034,10.000,-27.032},
				{-78121.759,-2220.990,10.000,-27.174},
				{-78341.244,-2194.857,10.000,-27.312},
				{-78558.108,-2168.636,10.000,-27.445},
				{-78772.341,-2142.326,10.000,-27.573},
				{-78983.933,-2115.928,10.000,-27.696},
				{-79192.878,-2089.441,10.000,-27.815},
				{-79399.164,-2062.865,10.000,-27.929},
				{-79602.784,-2036.202,10.000,-28.039},
				{-79803.729,-2009.451,10.000,-28.144},
				{-80001.991,-1982.612,10.000,-28.246},
				{-80197.559,-1955.687,10.000,-28.343},
				{-80390.427,-1928.675,10.000,-28.436},
				{-80580.584,-1901.577,10.000,-28.526},
				{-80768.024,-1874.393,10.000,-28.612},
				{-80952.736,-1847.124,10.000,-28.694},
				{-81134.713,-1819.771,10.000,-28.772},
				{-81313.947,-1792.334,10.000,-28.847},
				{-81490.428,-1764.814,10.000,-28.919},
				{-81664.149,-1737.211,10.000,-28.987},
				{-81835.102,-1709.526,10.000,-29.052},
				{-82003.278,-1681.760,10.000,-29.114},
				{-82168.669,-1653.914,10.000,-29.173},
				{-82331.268,-1625.987,10.000,-29.229},
				{-82491.066,-1597.982,10.000,-29.283},
				{-82648.056,-1569.898,10.000,-29.333},
				{-82802.230,-1541.737,10.000,-29.381},
				{-82953.580,-1513.500,10.000,-29.426},
				{-83102.098,-1485.187,10.000,-29.469},
				{-83247.778,-1456.798,10.000,-29.510},
				{-83390.612,-1428.336,10.000,-29.548},
				{-83530.592,-1399.801,10.000,-29.584},
				{-83667.711,-1371.194,10.000,-29.617},
				{-83801.963,-1342.515,10.000,-29.649},
				{-83933.339,-1313.766,10.000,-29.679},
				{-84061.834,-1284.948,10.000,-29.707},
				{-84187.441,-1256.062,10.000,-29.733},
				{-84310.151,-1227.109,10.000,-29.757},
				{-84429.960,-1198.090,10.000,-29.780},
				{-84546.861,-1169.005,10.000,-29.801},
				{-84660.847,-1139.857,10.000,-29.820},
				{-84771.911,-1110.646,10.000,-29.838},
				{-84880.049,-1081.374,10.000,-29.855},
				{-84985.253,-1052.041,10.000,-29.870},
				{-85087.518,-1022.650,10.000,-29.884},
				{-85186.838,-993.200,10.000,-29.897},
				{-85283.207,-963.695,10.000,-29.909},
				{-85376.621,-934.134,10.000,-29.920},
				{-85467.072,-904.519,10.000,-29.930},
				{-85554.558,-874.851,10.000,-29.939},
				{-85639.071,-845.133,10.000,-29.947},
				{-85720.607,-815.365,10.000,-29.954},
				{-85799.162,-785.549,10.000,-29.960},
				{-85874.731,-755.686,10.000,-29.966},
				{-85947.309,-725.779,10.000,-29.971},
				{-86016.892,-695.827,10.000,-29.976},
				{-86083.475,-665.834,10.000,-29.980},
				{-86147.055,-635.800,10.000,-29.983},
				{-86207.628,-605.728,10.000,-29.986},
				{-86265.190,-575.618,10.000,-29.989},
				{-86319.737,-545.474,10.000,-29.991},
				{-86371.267,-515.295,10.000,-29.993},
				{-86419.775,-485.084,10.000,-29.994},
				{-86465.259,-454.844,10.000,-29.996},
				{-86507.717,-424.575,10.000,-29.997},
				{-86547.145,-394.279,10.000,-29.998},
				{-86583.541,-363.958,10.000,-29.998},
				{-86616.902,-333.615,10.000,-29.999},
				{-86647.227,-303.250,10.000,-29.999},
				{-86674.514,-272.866,10.000,-29.999},
				{-86698.760,-242.465,10.000,-30.000},
				{-86719.965,-212.048,10.000,-30.000},
				{-86738.127,-181.618,10.000,-30.000},
				{-86753.250,-151.233,10.000,-30.000},
				{-86765.492,-122.419,10.000,-30.000},
				{-86775.156,-96.644,10.000,-30.000},
				{-86782.547,-73.911,10.000,-30.000},
				{-86787.970,-54.222,10.000,-30.000},
				{-86791.727,-37.578,10.000,-30.000},
				{-86794.125,-23.980,10.000,-30.000},
				{-86795.468,-13.428,10.000,-30.000},
				{-86796.061,-5.924,10.000,-30.000},
				{-86796.207,-1.467,10.000,-30.000},
				{-86796.207,-0.000,10.000,-30.000}		};

}