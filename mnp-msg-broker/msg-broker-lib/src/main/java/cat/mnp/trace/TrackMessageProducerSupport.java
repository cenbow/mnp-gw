package cat.mnp.trace;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class TrackMessageProducerSupport {
	Logger log = LoggerFactory.getLogger(this.getClass());

	public void sendMessageAdvice(JoinPoint jp) {// it is advice
		//log.info(jp.getTarget().toString() + " plSql: " + ((Worker) jp.getTarget()).getPlSqlQuery()); // TODO: debug later
		log.warn(jp.toString());
	}
}