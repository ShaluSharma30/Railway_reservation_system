/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package railwaySystem.gui;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import railwaySystem.dao.StationDao;
import railwaySystem.pojo.Station;
/**
 *
 * @author shalu
 */
public class StationMethods {
    private static final Scanner kb=new Scanner(System.in);
    public static void addStation() throws SQLException{
        System.out.println("Enter station code:");
        String scode=kb.nextLine();
        System.out.println("Enter station name:");
        String sname=kb.nextLine();
        Station station=new Station(scode,sname);
        if(StationDao.addStation(station))
            System.out.println("Sation added...");
        else
            System.out.println("Cannot add station! Try again.");
    }
    
    public static void removeStation() throws SQLException{
        System.out.println("Enter station code:");
        String scode=kb.nextLine();
        if(StationDao.removeStation(scode))
            System.out.println("Sation removed...");
        else
            System.out.println("Cannot remove the station! Try again.");
    }
    
    public static void displayAllStations() throws SQLException{
        List<Station> stations=StationDao.getAllStations();
        System.out.println("Station Code\t Station name");
        for(Station s:stations){
            System.out.println(s.getStationCode()+"\t"+s.getStationName());
            
        }
    }
}
