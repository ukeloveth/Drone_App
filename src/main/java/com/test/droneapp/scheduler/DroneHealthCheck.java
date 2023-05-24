package com.test.droneapp.scheduler;

import com.test.droneapp.constants.Default_Messages;
import com.test.droneapp.entity.Drone;
import com.test.droneapp.repositories.DroneRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Slf4j
@RequiredArgsConstructor
public class DroneHealthCheck {
    private final DroneRepository droneRepository;

    @Scheduled(fixedRateString = "PT16S", initialDelay = 200L)
    public void checkBatteryLevel(){
        List<Drone> drones = droneRepository.findAll();
        drones.forEach(drone ->
            log.info(Default_Messages.BATTERY_LEVEL_REPORT, drone.getSerialNumber(), drone.getBatteryPercentage())
        );
    }

}
