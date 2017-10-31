package com.javatpoint;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cat.mnp.mvno.dao.worker.Worker;
public class TrackOperation {
	Logger log = LoggerFactory.getLogger(this.getClass());
	public void myadvice(JoinPoint jp)  {// it is advice
		log.debug(  jp.getTarget().toString()+" plSql: "+ ((Worker)jp.getTarget()).getPlSqlQuery());

	}
}