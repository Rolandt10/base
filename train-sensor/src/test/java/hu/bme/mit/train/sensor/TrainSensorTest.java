package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class TrainSensorTest {

    TrainSensor sensor;
    TrainUser user;
    TrainController controller;

    @Before
    public void init() {
        controller = mock(TrainController.class);
        user = mock(TrainUser.class);
        sensor = new TrainSensorImpl(controller, user);
    }

    @Test
    public void SpeedLimitIsInRange() {
        sensor.overrideSpeedLimit(20);
        when(user.getAlarmState()).thenReturn(false);
        verify(user, times(0)).setAlarmState(true);
        verify(user, times(0)).setAlarmState(true);
    }

    @Test
    public void SpeedLimitIsOnEdge1() {
        sensor.overrideSpeedLimit(0);
        when(user.getAlarmState()).thenReturn(false);
        verify(user, times(0)).setAlarmState(true);
        verify(user, times(0)).setAlarmState(true);
    }

    @Test
    public void SpeedLimitIsOnEdge2() {
        sensor.overrideSpeedLimit(500);
        when(user.getAlarmState()).thenReturn(false);
        verify(user, times(0)).setAlarmState(true);
        verify(user, times(0)).setAlarmState(true);
    }

    @Test
    public void SpeedLimitIsLessThanZero() {
        sensor.overrideSpeedLimit(-20);
        verify(user, times(1)).setAlarmState(true);
        when(user.getAlarmState()).thenReturn(true);
    }

    @Test
    public void SpeedLimitIsMoreThanFiveHundred() {
        sensor.overrideSpeedLimit(550);
        verify(user, times(1)).setAlarmState(true);
        when(user.getAlarmState()).thenReturn(true);
    }

    @Test
    public void ByDefaultThereIsNoAlarm() {
        when(user.getAlarmState()).thenReturn(false);
    }

}
