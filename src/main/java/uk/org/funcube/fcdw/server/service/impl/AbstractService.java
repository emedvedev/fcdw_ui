// FUNcube Data Warehouse
// Copyright 2013 (c) David A.Johnson, G4DPZ, AMSAT-UK
// This work is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
// To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ or send a letter
// to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.

package uk.org.funcube.fcdw.server.service.impl;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;

public class AbstractService {

	private static final int HEX_0X0F = 0x0F;
	private static final int I_256 = 256;
	private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss zzz";
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	protected static final SimpleDateFormat SDTF = new SimpleDateFormat(DATE_TIME_FORMAT);
	protected static final SimpleDateFormat SDF = new SimpleDateFormat(DATE_FORMAT);
	private static final TimeZone UTC = TimeZone.getTimeZone("UTC");
	private static final double[] PA_TEMPS = new double[I_256];
	protected static final double[] ANTS_TEMPS = new double[I_256];
	protected static double[] SOL_ILLUMINATION = new double[1024];

	static {
		SDTF.setTimeZone(UTC);
		setupPaTemps();
		setupAntsTemps();
		setupSunSensors();
	}

	protected static String convertHexBytePairToBinary(final String hexString) {
		final StringBuffer sb = new StringBuffer();

		for (int i = 0; i < hexString.length(); i += 2) {
			final String hexByte = hexString.substring(i, i + 2);
			final int hexValue = Integer.parseInt(hexByte, 16);
			sb.append(StringUtils.leftPad(Integer.toBinaryString(hexValue), 8, "0"));
		}
		return sb.toString();
	}

	private static void setupPaTemps() {
		// Data from adc 79 to 252 measured,
		// 0-79 continues using gradient of last
		// three values, 252 to 255 likewise

		final double[][] tempToAdc = { { 87.983, Double.MIN_VALUE }, { 87.983, 0 }, {
		/*
		 * first measured value
		 */55.3, 79 }, { 49.6, 91 }, { 45.3, 103 }, { 41.1, 115 }, { 37.6, 125 }, { 35.7, 129 }, { 33.6, 137 }, { 30.6, 145 },
				{ 27.6, 154 }, { 25.1, 161 }, { 22.6, 169 }, { 20, 176 }, { 17.6, 183 }, { 15.1, 189 }, { 12.6, 196 }, { 10, 203 },
				{ 7.5, 208 }, { 5, 214 }, { 2.4, 220 }, { 0, 224 }, { -2.9, 230 }, { -5, 233 }, { -7.5, 237 }, { -10, 241 },
				{ -12.3, 244 }, { -15, 247 }, { /*
												 * last measured value
												 */-20, 252 }, { -22.846, 255 }, { -22.846, Double.MAX_VALUE } };

		// calc values for all possible 8bit values
		for (int adc = 0; adc < I_256; ++adc) {
			for (int j = 0; j < tempToAdc.length; j++) {
				if (adc != 0 && adc < tempToAdc[j][1]) {
					double t1 = tempToAdc[j][0];
					double a1 = tempToAdc[j][1];
					double diffa = tempToAdc[j - 1][1] - a1;
					double difft = tempToAdc[j - 1][0] - t1;
					double value = ((adc - a1) * (difft / diffa)) + t1;
					PA_TEMPS[adc] = value;
					break;
				}
			}
		}
	}

	private static void setupSunSensors() {
		double[][] tempToAdc 
			= new double[][] {
				{5,0},{9,2.158},{19,2.477},{23,2.64},{33,2.8},
				{50,2.881},{56,2.889},{100,3.04},{133,3.14},
				{200,3.25},{265,3.346},{333,3.475},{400,3.58},
				{467,3.69},{533,3.81},{600,3.92},{666,4.03},
				{700,4.079},{732,4.13},{800,4.245},{866,4.325},
				{933,4.38},{984,4.42},{992,4.5319},{999,4.6438},
				{1007,4.7557},{1015,4.8676},{1023,4.9795}};
		
		for (int i = 0; i < 1024; ++i) {
			if (i < 984) {
				// calc values for all possible 8bit values
				for (int j = 0; j < tempToAdc.length; j++) {
					if (i < tempToAdc[j][0]) {
						// get this current value
						double[] currentPair = tempToAdc[j];
						// get the previous value
						double[] previousPair = new double[] {0,0};
						if (j != 0) {
							previousPair = tempToAdc[j - 1];
						}
						// get the adc difference
						double adcDiff = currentPair[0] - previousPair[0];
						// get the value difference
						double valueDiff = currentPair[1] - previousPair[1];
						// scale per unit
						double increment = valueDiff / adcDiff;
						// calculate the sol value for this adc value
						double value = previousPair[1] + (i - previousPair[0]) * increment;
						// save it in the array
						SOL_ILLUMINATION[i] = value;
						// break;
						break;
					}
				}
			}
			else {
				SOL_ILLUMINATION[i] = 4.420;
			}
		}
	}

	private static void setupAntsTemps() {
		// data from ANTS manual, start and end values added
		// to ensure out of range is obvious (-255 or +255)
		int[][] tempToAdc = new int[][] { { -255, Integer.MIN_VALUE }, { -255, 2616 }, { -50, 2616 }, { -49, 2607 }, { -48, 2598 },
				{ -47, 2589 }, { -46, 2580 }, { -45, 2571 }, { -44, 2562 }, { -43, 2553 }, { -42, 2543 }, { -41, 2533 }, { -40, 2522 },
				{ -39, 2512 }, { -38, 2501 }, { -37, 2491 }, { -36, 2481 }, { -35, 2470 }, { -34, 2460 }, { -33, 2449 }, { -32, 2439 },
				{ -31, 2429 }, { -30, 2418 }, { -29, 2408 }, { -28, 2397 }, { -27, 2387 }, { -26, 2376 }, { -25, 2366 }, { -24, 2355 },
				{ -23, 2345 }, { -22, 2334 }, { -21, 2324 }, { -20, 2313 }, { -19, 2302 }, { -18, 2292 }, { -17, 2281 }, { -16, 2271 },
				{ -15, 2260 }, { -14, 2250 }, { -13, 2239 }, { -12, 2228 }, { -11, 2218 }, { -10, 2207 }, { -9, 2197 }, { -8, 2186 },
				{ -7, 2175 }, { -6, 2164 }, { -5, 2154 }, { -4, 2143 }, { -3, 2132 }, { -2, 2122 }, { -1, 2111 }, { 0, 2100 }, { 1, 2089 },
				{ 2, 2079 }, { 3, 2068 }, { 4, 2057 }, { 5, 2047 }, { 6, 2036 }, { 7, 2025 }, { 8, 2014 }, { 9, 2004 }, { 10, 1993 },
				{ 11, 1982 }, { 12, 1971 }, { 13, 1961 }, { 14, 1950 }, { 15, 1939 }, { 16, 1928 }, { 17, 1918 }, { 18, 1907 },
				{ 19, 1896 }, { 20, 1885 }, { 21, 1874 }, { 22, 1864 }, { 23, 1853 }, { 24, 1842 }, { 25, 1831 }, { 26, 1820 },
				{ 27, 1810 }, { 28, 1799 }, { 29, 1788 }, { 30, 1777 }, { 31, 1766 }, { 32, 1756 }, { 33, 1745 }, { 34, 1734 },
				{ 35, 1723 }, { 36, 1712 }, { 37, 1701 }, { 38, 1690 }, { 39, 1679 }, { 40, 1668 }, { 41, 1657 }, { 42, 1646 },
				{ 43, 1635 }, { 44, 1624 }, { 45, 1613 }, { 46, 1602 }, { 47, 1591 }, { 48, 1580 }, { 49, 1569 }, { 50, 1558 },
				{ 51, 1547 }, { 52, 1536 }, { 53, 1525 }, { 54, 1514 }, { 55, 1503 }, { 56, 1492 }, { 57, 1481 }, { 58, 1470 },
				{ 59, 1459 }, { 60, 1448 }, { 61, 1436 }, { 62, 1425 }, { 63, 1414 }, { 64, 1403 }, { 65, 1391 }, { 66, 1380 },
				{ 67, 1369 }, { 68, 1358 }, { 69, 1346 }, { 70, 1335 }, { 71, 1324 }, { 72, 1313 }, { 73, 1301 }, { 74, 1290 },
				{ 75, 1279 }, { 76, 1268 }, { 77, 1257 }, { 78, 1245 }, { 79, 1234 }, { 80, 1223 }, { 81, 1212 }, { 82, 1201 },
				{ 83, 1189 }, { 84, 1178 }, { 85, 1167 }, { 86, 1155 }, { 87, 1144 }, { 88, 1133 }, { 89, 1122 }, { 90, 1110 },
				{ 91, 1099 }, { 92, 1088 }, { 93, 1076 }, { 94, 1065 }, { 95, 1054 }, { 96, 1042 }, { 97, 1031 }, { 98, 1020 },
				{ 99, 1008 }, { 100, 997 }, { 101, 986 }, { 102, 974 }, { 103, 963 }, { 104, 951 }, { 105, 940 }, { 106, 929 },
				{ 107, 917 }, { 108, 906 }, { 109, 895 }, { 110, 883 }, { 111, 872 }, { 112, 860 }, { 113, 849 }, { 114, 837 },
				{ 115, 826 }, { 116, 814 }, { 117, 803 }, { 118, 791 }, { 119, 780 }, { 120, 769 }, { 121, 757 }, { 122, 745 },
				{ 123, 734 }, { 124, 722 }, { 125, 711 }, { 126, 699 }, { 127, 688 }, { 128, 676 }, { 129, 665 }, { 130, 653 },
				{ 131, 642 }, { 132, 630 }, { 133, 618 }, { 134, 607 }, { 135, 595 }, { 136, 584 }, { 137, 572 }, { 138, 560 },
				{ 139, 549 }, { 140, 537 }, { 141, 525 }, { 142, 514 }, { 143, 502 }, { 144, 490 }, { 145, 479 }, { 146, 467 },
				{ 147, 455 }, { 148, 443 }, { 149, 432 }, { 150, 420 }, { 255, 420 }, { 255, Integer.MIN_VALUE } };
		for (int i = 0; i < 256; ++i) {
			// calc values for all possible 8bit values
			double adc = (i * 3300.0) / 255.75;
			for (int j = 0; j < tempToAdc.length; j++) {
				if (j != 0 && adc > tempToAdc[j][1]) {
					double t1 = tempToAdc[j][0];
					double a1 = tempToAdc[j][1];
					double diffa = tempToAdc[j - 1][1] - a1;
					double difft = tempToAdc[j - 1][0] - t1;
					ANTS_TEMPS[i] = ((adc - a1) * (difft / diffa)) + t1;
					break;
				}
			}
		}
	}

	protected static String convertToHex(final byte[] data) {
		final StringBuffer buf = new StringBuffer();
		for (final byte element : data) {
			int halfbyte = element >>> 4 & HEX_0X0F;
			int twoHalfs = 0;
			do {
				if (0 <= halfbyte && halfbyte <= 9) {
					buf.append((char) ('0' + halfbyte));
				} else {
					buf.append((char) ('a' + (halfbyte - 10)));
				}
				halfbyte = element & HEX_0X0F;
			} while (twoHalfs++ < 1);
		}
		return buf.toString();
	}

	protected static double scaleAndOffset(final long value, final double multiplier, final double offset) {
		return (value * multiplier) + offset;
	}

	public AbstractService() {
		super();
	}

	public static final double getPaTemp(final long value) {
		if (value == 99999 || value == -99999) {
			return 0.0;
		}
		return PA_TEMPS[(int) value];
	}

	public static final double getAntsTemp(final int value) {
		return ANTS_TEMPS[value];
	}

	public static double getPaPower(final Long value) {
		return 0.005 * Math.pow(value.doubleValue(), 2.0629);
	}

	public static double getPaCurrent(final long value) {
		return (value * 0.5496) + 2.5425;
	}

	public static String getLastDTMFCommand(final int dtmfLastCommand) {

		String commandName;

		switch (dtmfLastCommand) {
		case 0x02:
			commandName = "CPLD set power on mode";
			break;
		case 0x04:
			commandName = "CPLD change mode";
			break;

		case 0x08:
			commandName = "Extended Command Start";
			break;
		case 0x09:
			commandName = "Data collect state";
			break;
		case 0x0A:
			commandName = "Data collect state";
			break;
		case 0x0C:
			commandName = "Ants Arm";
			break;
		case 0x0D:
			commandName = "Ants Deploy";
			break;
		case 0x0E:
			commandName = "Restart MCU";
			break;

		case 0x11:
			commandName = "Fitter Save";
			break;
		case 0x12:
			commandName = "Fitter Copy";
			break;
		case 0x13:
			commandName = "Debug Read Mem";
			break;
		case 0x14:
			commandName = "Debug Read Fram";
			break;
		case 0x15:
			commandName = "Debug Write Fram";
			break;
		case 0x16:
			commandName = "Debug I2c Command";
			break;
		case 0x17:
			commandName = "Update Idle Time";
			break;
		case 0x18:
			commandName = "Update Call Sign";
			break;
		case 0x19:
			commandName = "Update Eclipse Mode";
			break;
		case 0x1A:
			commandName = "Update Safe Mode";
			break;
		case 0x1B:
			commandName = "Eps Set PPT Volt";
			break;
		case 0x1C:
			commandName = "Eps Reset";
			break;
		case 0x1D:
			commandName = "Set Auto Flags";
			break;
		case 0x1E:
			commandName = "PreFlight";
			break;
		case 0x1F:
			commandName = "EpsSetPvAuto";
			break;
		default:
			commandName = String.format("Unknown Command (%d)", dtmfLastCommand);
			break;
		}
		return commandName;
	}

}
