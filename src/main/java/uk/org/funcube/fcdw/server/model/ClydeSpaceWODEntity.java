package uk.org.funcube.fcdw.server.model;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("clydeSpace")
public class ClydeSpaceWODEntity extends WODEntity {

	@Transient
	ClydeSpaceWODEntity previous;

	@Transient
	ClydeSpaceWODEntity next;

	public ClydeSpaceWODEntity() {

	}

	public ClydeSpaceWODEntity(final long satelliteId, 
			final long sequenceNumber, final long frameNumber, final long c1,
			final long c2, final long c3, final long c4, final long c5,
			final long c6, final long c7, final long c8, final long c9,
			final long c10, final long c11, final long c12, final long c13,
			final long c14, final long c15, final long c16, final long c17,
			final long c18, final long c19, final long c20, final long c21,
			final long c22, final long c23, final long c24, final long c25,
			final long c26, final Date createdDate) {
		setSatelliteId(satelliteId);
		setSequenceNumber(sequenceNumber);
		setFrameNumber(frameNumber);
		setC1(c1);
		setC2(c2);
		setC3(c3);
		setC4(c4);
		setC5(c5);
		setC6(c6);
		setC7(c7);
		setC8(c8);
		setC9(c9);
		setC10(c10);
		setC11(c11);
		setC12(c12);
		setC13(c13);
		setC14(c14);
		setC15(c15);
		setC16(c16);
		setC17(c17);
		setC18(c18);
		setC19(c19);
		setC20(c20);
		setC21(c21);
		setC22(c22);
		setC23(c23);
		setC24(c24);
		setC25(c25);
		setC26(c26);
		setCreatedDate(createdDate);
	}

	public ClydeSpaceWODEntity(final long satelliteId, 
			final long sequenceNumber, final long frameNumber,
			final String binaryDataString, final Date createdDate) {
		setSatelliteId(satelliteId);
		setSequenceNumber(sequenceNumber);
		setFrameNumber(frameNumber);
		setCreatedDate(createdDate);

		int pos = 0;

		// MaterialScience
		for (; pos < 48; pos += 12) {
			final Long value = Long.parseLong(
					binaryDataString.substring(pos, pos + 12), 2);
			switch (pos / 12) {
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
			}
		}

		int start = pos;

		// SolarPanel
		for (; pos < 144; pos += 8) {
			final long value = Long.parseLong(
					binaryDataString.substring(pos, pos + 8), 2);
			switch ((pos - start) / 8) {
			case 0:
				setC5(value);
				break;
			case 1:
				setC6(value);
				break;
			case 2:
				setC7(value);
				break;
			case 3:
				setC8(value);
				break;
			case 4:
				setC9(value);
				break;
			case 5:
				setC10(value);
				break;
			case 6:
				setC11(value);
				break;
			case 7:
				setC12(value);
				break;
			case 8:
				setC13(value);
				break;
			case 9:
				setC14(value);
				break;
			case 10:
				setC15(value);
				break;
			case 11:
				setC16(value);
				break;
			}
		}

		start = pos;

		// Battery
		for (; pos < 160; pos += 8) {
			final long value = Long.parseLong(
					binaryDataString.substring(pos, pos + 8), 2);
			switch ((pos - start) / 8) {
			case 0:
				setC17(value);
				break;
			case 1:
				setC18(value);
				break;
			}
		}

		start = pos;

		for (; pos < 162; pos++) {

			final int value = 0 == Integer.parseInt(
					binaryDataString.substring(pos, pos + 1), 2) ? 1 : -1;
			switch (pos - start) {
			case 0:
				setC17(getC17() * value);
				break;
			case 1:
				setC18(getC18() * value);
				break;
			}
		}

		start = pos;

		for (; pos < 178; pos += 8) {

			final long value = Long.parseLong(
					binaryDataString.substring(pos, pos + 8), 2);
			switch ((pos - start) / 8) {
			case 0:
				setC19(value);
				break;
			case 1:
				setC20(value);
				break;
			}
		}

		start = pos;

		// CollectionStatus
		for (; pos < 184; pos += 1) {
			final int value = Integer.parseInt(
					binaryDataString.substring(pos, pos + 1), 2);
			switch (pos - start) {
			case 0:
				setC21(value);
				break;
			case 1:
				setC22(value);
				break;
			case 2:
				setC23(value);
				break;
			case 3:
				setC24(value);
				break;
			case 4:
				setC25(value);
				break;
			case 5:
				setC26(value);
				break;
			}
		}

	}

	public long getBatteryCurrent1() {
		// TODO Auto-generated method stub
		return getC17();
	}

	public long getBatteryCurrent2() {
		// TODO Auto-generated method stub
		return getC18();
	}

	public long getBatteryVoltage1() {
		// TODO Auto-generated method stub
		return getC19();
	}

	public long getBatteryVoltage2() {
		// TODO Auto-generated method stub
		return getC20();
	}

	public long getCollectionStatus1() {
		// TODO Auto-generated method stub
		return getC21();
	}

	public long getCollectionStatus2() {
		// TODO Auto-generated method stub
		return getC22();
	}

	public long getCollectionStatus3() {
		// TODO Auto-generated method stub
		return getC23();
	}

	public long getCollectionStatus4() {
		// TODO Auto-generated method stub
		return getC24();
	}

	public long getCollectionStatus5() {
		// TODO Auto-generated method stub
		return getC25();
	}

	public long getCollectionStatus6() {
		// TODO Auto-generated method stub
		return getC26();
	}

	public long getMaterialScience1() {
		// TODO Auto-generated method stub
		return getC1();
	}

	public long getMaterialScience2() {
		// TODO Auto-generated method stub
		return getC2();
	}

	public long getMaterialScience3() {
		// TODO Auto-generated method stub
		return getC3();
	}

	public long getMaterialScience4() {
		// TODO Auto-generated method stub
		return getC4();
	}

	public final ClydeSpaceWODEntity getNext() {
		return next;
	}

	public final ClydeSpaceWODEntity getPrevious() {
		return previous;
	}

	public long getSolarPanelTemperature1() {
		// TODO Auto-generated method stub
		return getC11();
	}

	public long getSolarPanelTemperature2() {
		// TODO Auto-generated method stub
		return getC12();
	}

	public long getSolarPanelTemperature3() {
		// TODO Auto-generated method stub
		return getC13();
	}

	public long getSolarPanelTemperature4() {
		// TODO Auto-generated method stub
		return getC14();
	}

	public long getSolarPanelTemperature5() {
		// TODO Auto-generated method stub
		return getC15();
	}

	public long getSolarPanelTemperature6() {
		// TODO Auto-generated method stub
		return getC16();
	}

	public long getSolarPanelVoltage1() {
		// TODO Auto-generated method stub
		return getC5();
	}

	public long getSolarPanelVoltage2() {
		// TODO Auto-generated method stub
		return getC6();
	}

	public long getSolarPanelVoltage3() {
		// TODO Auto-generated method stub
		return getC7();
	}

	public long getSolarPanelVoltage4() {
		// TODO Auto-generated method stub
		return getC8();
	}

	public long getSolarPanelVoltage5() {
		// TODO Auto-generated method stub
		return getC9();
	}

	public long getSolarPanelVoltage6() {
		// TODO Auto-generated method stub
		return getC10();
	}

	public final void setNext(final ClydeSpaceWODEntity next) {
		this.next = next;
	}

	public final void setPrevious(final ClydeSpaceWODEntity previous) {
		this.previous = previous;
	}

	public String toBinaryString() {

		final StringBuffer sb = new StringBuffer();
		sb.append(convertLongToBinary(getC1(), 12));
		sb.append(convertLongToBinary(getC2(), 12));
		sb.append(convertLongToBinary(getC3(), 12));
		sb.append(convertLongToBinary(getC4(), 12));

		sb.append(convertLongToBinary(getC5(), 8));
		sb.append(convertLongToBinary(getC6(), 8));
		sb.append(convertLongToBinary(getC7(), 8));
		sb.append(convertLongToBinary(getC8(), 8));
		sb.append(convertLongToBinary(getC9(), 8));
		sb.append(convertLongToBinary(getC10(), 8));
		sb.append(convertLongToBinary(getC11(), 8));
		sb.append(convertLongToBinary(getC12(), 8));
		sb.append(convertLongToBinary(getC13(), 8));
		sb.append(convertLongToBinary(getC14(), 8));
		sb.append(convertLongToBinary(getC15(), 8));
		sb.append(convertLongToBinary(getC16(), 8));

		sb.append(convertLongToBinary(Math.abs(getC17()), 8));
		sb.append(convertLongToBinary(Math.abs(getC18()), 8));

		// we have to extract the sign
		sb.append(getC17() > 0 ? "0" : "1");
		sb.append(getC18() > 0 ? "0" : "1");

		sb.append(convertLongToBinary(getC19(), 8));
		sb.append(convertLongToBinary(getC20(), 8));
		sb.append(convertLongToBinary(getC21(), 1));
		sb.append(convertLongToBinary(getC22(), 1));
		sb.append(convertLongToBinary(getC23(), 1));
		sb.append(convertLongToBinary(getC24(), 1));
		sb.append(convertLongToBinary(getC25(), 1));
		sb.append(convertLongToBinary(getC26(), 1));

		return sb.toString();
	}

}
