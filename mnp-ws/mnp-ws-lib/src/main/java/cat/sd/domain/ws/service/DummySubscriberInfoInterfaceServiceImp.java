package cat.sd.domain.ws.service;

import java.io.InputStream;
import java.io.StringReader;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cat.mnp.clh.ws.service.DummyViewSOAPProvisioningServiceImpl;
import cat.sd.domain.SubScriberCoreBalanceResponse;
import cat.sd.domain.SubscriberInfoRequest;
import cat.sd.domain.SubscriberInfoResponse;
import cat.sd.domain.api.SubscriberInfoInterface;

public class DummySubscriberInfoInterfaceServiceImp implements SubscriberInfoInterface {
	private static final Logger logger = LoggerFactory.getLogger(DummySubscriberInfoInterfaceServiceImp.class);

	/*

	 * */
	@Override
	public SubscriberInfoResponse getInfo(SubscriberInfoRequest arg0) {
		JAXBElement<SubscriberInfoResponse> r = null ;
		try {
			InputStream in = this.getClass().getClassLoader().getResourceAsStream("cat/sd/domain/ws/service/smd.xml");
			JAXBContext jContext = JAXBContext.newInstance(SubscriberInfoResponse.class);
			Unmarshaller unmarshallerObj = jContext.createUnmarshaller();
			r = unmarshallerObj.unmarshal(new StreamSource(in), SubscriberInfoResponse.class);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return r.getValue();
	}

	@Override
	public SubScriberCoreBalanceResponse getCoreBalance(SubscriberInfoRequest arg0) {
		return null;
	}

}
