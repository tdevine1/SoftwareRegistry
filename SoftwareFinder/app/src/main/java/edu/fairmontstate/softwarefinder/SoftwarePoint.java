/* Class that holds the specific data of each software request.
 * Has fields for the software name, building name, and room number
 */
package edu.fairmontstate.softwarefinder;

import java.io.Serializable;

public class SoftwarePoint implements Serializable {
    private String softwareName;
    private String buildingName;
    private String roomNumber;

    public SoftwarePoint() {
        softwareName = "";
        buildingName = "";
        roomNumber = "";
    } // end default constructor SoftwarePoint().
//================================================================================================================================
    public void setSoftwareName(String softwareName) {
        this.softwareName = softwareName;
    } // end method setSoftwareName().
//================================================================================================================================
    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    } // end method setBuildingName().
//================================================================================================================================
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    } // end method setRoomNumber().
//================================================================================================================================
    public String getSoftwareName() {
        return softwareName;
    } // end method getSoftwareName().
//================================================================================================================================
    public String getBuildingName() {
        return buildingName;
    } // end method getBuildingName().
//================================================================================================================================
    public String getRoomNumber() {
        return roomNumber;
    } // end method getRoomNumber().
} // end class SoftwarePoint.

