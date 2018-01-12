package cat.mnp.trace;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cat.mnp.mvno.dao.worker.Worker;
public class TrackWorker {
	Logger log = LoggerFactory.getLogger(this.getClass());
	public void myadvice(JoinPoint jp)  {// it is advice
		log.info(  jp.getTarget().toString()+" plSql: "+ ((Worker)jp.getTarget()).getPlSqlQuery());

	}
}