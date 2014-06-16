package uk.org.funcube.fcdw.server.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class SatelliteStatusEntityTest {

	@Test
	public final void testGetModeAndTransponderState() {
		SatelliteStatusEntity satelliteStatus = new SatelliteStatusEntity();
		// 0,0,0
		assertEquals("Manual", satelliteStatus.getMode());
		assertEquals("Off", satelliteStatus.getTransponderState());
		// 0,0,1
		satelliteStatus.setEclipseModeForced(false);
		satelliteStatus.setEclipsed(true);
		assertEquals("Manual", satelliteStatus.getMode());
		assertEquals("Off", satelliteStatus.getTransponderState());
		// 0,1,0
		satelliteStatus.setEclipseModeForced(true);
		satelliteStatus.setEclipsed(false);
		assertEquals("Manual", satelliteStatus.getMode());
		assertEquals("On", satelliteStatus.getTransponderState());
		// 0,1,1
		satelliteStatus.setEclipseModeForced(true);
		satelliteStatus.setEclipsed(true);
		assertEquals("Manual", satelliteStatus.getMode());
		assertEquals("On", satelliteStatus.getTransponderState());
		// 1,0,0
		satelliteStatus.setEclipseSwitch(true);
		satelliteStatus.setEclipseModeForced(false);
		satelliteStatus.setEclipsed(false);
		assertEquals("Auto", satelliteStatus.getMode());
		assertEquals("Off", satelliteStatus.getTransponderState());
		// 1,0,1
		satelliteStatus.setEclipseModeForced(false);
		satelliteStatus.setEclipsed(true);
		assertEquals("Auto", satelliteStatus.getMode());
		assertEquals("On", satelliteStatus.getTransponderState());
		// 1,1,0
		satelliteStatus.setEclipseModeForced(true);
		satelliteStatus.setEclipsed(false);
		assertEquals("Auto", satelliteStatus.getMode());
		assertEquals("Off", satelliteStatus.getTransponderState());
		// 1,1,1
		satelliteStatus.setEclipseModeForced(true);
		satelliteStatus.setEclipsed(true);
		assertEquals("Auto", satelliteStatus.getMode());
		assertEquals("On", satelliteStatus.getTransponderState());
	}

}
