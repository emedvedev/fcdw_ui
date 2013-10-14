// FUNcube Data Warehouse
// Copyright 2013 (c) David A.Johnson, G4DPZ, AMSAT-UK
// This work is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
// To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ or send a letter
// to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.

package uk.org.funcube.fcdw.server.model;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("clydeSpace")
public class ClydeSpaceHPEntity extends HPEntity {

	public ClydeSpaceHPEntity() {
	}

	public ClydeSpaceHPEntity(long satelliteId, long sequenceNumber, String binaryDataString, Date createdDate) {
		setSatelliteId(satelliteId);
		setCreatedDate(createdDate);
		setSequenceNumber(sequenceNumber);
		
		int pos = 0;

		for (; pos < 60; pos += 10) {
			final long value = Long.parseLong(
					binaryDataString.substring(pos, pos + 10), 2);
			switch (pos / 10) {
			case 0:
				setC1(value);
				break;
			case 1:
				setC2(value);
				break;
			case 2:
				setC3(value);
				break;
			case 3:
				setC4(value);
				break;
			case 4:
				setC5(value);
				break;
			case 5:
				setC6(value);
				break;
			}

		}

		for (; pos < 60 + 18; pos += 9) {
			final long value = Long.parseLong(
					binaryDataString.substring(pos, pos + 9), 2);
			switch ((pos - 60) / 9) {
			case 0:
				setC7(value);
				break;
			case 1:
				setC8(value);
				break;
			}
		}

		for (; pos < 60 + 18 + 2; pos++) {
			final long value = Long.parseLong(
					binaryDataString.substring(pos, pos + 1), 2);
			switch ((pos - 60 - 18)) {
			case 0:
				setC9(value);
				break;
			case 1:
				setC10(value);
				break;
			}
		}
	}

	public ClydeSpaceHPEntity(
			long c1, long c2, long c3, long c4, long c5, long c6,long c7, long c8, long c9, long c10) {
		setC1(c1);setC2(c2);setC3(c3);setC4(c4);setC5(c5);setC6(c6);setC7(c7);setC8(c8);setC9(c9);setC10(c10);
	}

	public Long getPanelX1Voltage() {
		return getC1();
	}
	
	public Long getPanelY1Voltage() {
		return getC2();
	}
	
	public Long getPanelZ1Voltage() {
		return getC3();
	}
	
	public Long getPanelX2Voltage() {
		return getC4();
	}
	
	public Long getPanelY2Voltage() {
		return getC5();
	}
	
	public Long getPanelZ2Voltage() {
		return getC6();
	}
	
	public Long getBattery1Current() {
		return getC7();
	}
	
	public Long getBattery2Current() {
		return getC8();
	}
	
	public Long getBattery1CurrentDirection() {
		return getC9();
	}
	
	public Long getBattery2CurrentDirection() {
		return getC10();
	}
	

	public String toBinaryString() {
		final StringBuffer sb = new StringBuffer();
		sb.append(convertLongToBinary(getC1(), 10));
		sb.append(convertLongToBinary(getC2(), 10));
		sb.append(convertLongToBinary(getC3(), 10));
		sb.append(convertLongToBinary(getC4(), 10));
		sb.append(convertLongToBinary(getC5(), 10));
		sb.append(convertLongToBinary(getC6(), 10));
		sb.append(convertLongToBinary(getC7(), 9));
		sb.append(convertLongToBinary(getC8(), 9));
		sb.append(convertLongToBinary(getC9(), 1));
		sb.append(convertLongToBinary(getC10(), 1));
		return sb.toString();
	}
}
