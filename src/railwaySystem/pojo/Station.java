/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package railwaySystem.pojo;

/**
 *
 * @author shalu
 */
public class Station {

    public Station(String StationCode, String StationName) {
        this.StationCode = StationCode;
        this.StationName = StationName;
    }

    public String getStationCode() {
        return StationCode;
    }

    public void setStationCode(String StationCode) {
        this.StationCode = StationCode;
    }

    public String getStationName() {
        return StationName;
    }

    public void setStationName(String StationName) {
        this.StationName = StationName;
    }

    @Override
    public String toString() {
        return "Station{" + "StationCode=" + StationCode + ", StationName=" + StationName + '}';
    }
    private String StationCode,StationName;
}
