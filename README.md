
# Drone Dispatch REST API
---

*scroll: **START**


### Introduction

This REST API allows clients to communicate with the drones. The drone is able to make deliveries to locations that are inaccessible. The application is developed to perform the following tasks:
---
* A Drone can be registered with the application.
* Medical & other items can be loaded onto a drone.
* Loaded medication items can be checked for a given drone.
* Drone can be checked if it is available for loading.
* Battery level of a drone can be checked.

### How to run this application
To run this application:
* Open the terminal and navigate to the folder containing the application.
* Run the following command on terminal: mvn spring-boot:run
* Navigate through your browser to http://localhost:8080/swagger-ui/index.html

### dispatch-controller
url: http://localhost:8080/api/v1/drone

###  fetch all drones
###  http method : get
url: http://localhost:8080/api/v1/drone/all



### check for a medication for a given drone
###  http method : get
url: http://localhost:8080/api/v1/drone/medication



### check all available drones
###  http method : get
url: http://localhost:8080/api/v1/drone/available



### check drone battery level
###  http method : get
url : http://localhost:8080/api/v1/drone/battery-level?drone-id=1




### register a drone
### http method : post
url : http://localhost:8080/api/v1/drone




### load drone with a medication
### http method : post
url : http://localhost:8080/api/v1/drone



### register medication
### http method : post
url : http://localhost:8080/api/v1/medication



* Log file for checking battery level:/Users/mac/Downloads/DroneApp/drone_battery_report.log

# END

