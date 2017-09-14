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
public enum ChannelType {
    USSD("USD")
    ,SMS("SMS")
    ,WEBSITE("WEB");
    
    private final String shortName;
    
    ChannelType(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }
    
}
