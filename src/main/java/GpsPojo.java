/**
 * Copyright 2017 Hortonworks.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/

public class GpsPojo {

    private String latitude;
    private String longitude;
    private String speed;
    private String vehicleId;
    private String heading;
    private String altitude;
    private String vehicleTime;
    private String speedlimit;
    private String speeding;
    private String accuracy;


    public GpsPojo() {
    }


    public GpsPojo(String latitude, String longitude, String speed, String vehicleId, String time) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.speed = speed;
        this.vehicleId = vehicleId;
        this.time = time;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public String getVehicleTime() {
        return vehicleTime;
    }

    public void setVehicleTime(String vehicleTime) {
        this.vehicleTime = vehicleTime;
    }

    public String getSpeedlimit() {
        return speedlimit;
    }

    public void setSpeedlimit(String speedlimit) {
        this.speedlimit = speedlimit;
    }

    public String getSpeeding() {
        return speeding;
    }

    public void setSpeeding(String speeding) {
        this.speeding = speeding;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    public String getLatitude(String s) {

        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String time;


}
