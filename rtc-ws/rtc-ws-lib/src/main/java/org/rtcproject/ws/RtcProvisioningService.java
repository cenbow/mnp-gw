/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.rtcproject.ws;

import javax.xml.ws.WebServiceFeature;

/**
 *
 * @author HP
 */
public interface RtcProvisioningService<T> {
    public abstract T getRTCWSSOAPHTTPPort();
    public abstract T getRTCWSSOAPHTTPPort(WebServiceFeature... features);
}