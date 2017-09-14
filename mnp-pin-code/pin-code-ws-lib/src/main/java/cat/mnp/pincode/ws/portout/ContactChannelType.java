/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.pincode.ws.portout;

/**
 *
 * @author anuchitr
 */
public enum ContactChannelType {
    SMS("SMS", "SMS")
    ,EMAIL("EML", "Email");
    
    private final String shortName;
    private final String displayName;
    
    ContactChannelType(String shortName, String displayName) {
        this.shortName = shortName;
        this.displayName = displayName;
    }

    public String getShortName() {
        return shortName;
    }

    public String getDisplayName() {
        return displayName;
    }
    
}
