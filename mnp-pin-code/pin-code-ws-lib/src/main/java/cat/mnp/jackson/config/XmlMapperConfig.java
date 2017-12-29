/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.jackson.config;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

/**
 *
 * @author CATr
 */
public class XmlMapperConfig {

    private XmlMapper xmlMapper;

    public void setXmlMapper(XmlMapper xmlMapper) {
        this.xmlMapper = xmlMapper;
    }

    public void init() {
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
    }
}
