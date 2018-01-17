package com;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import jaxb.clh.npcbulksync.NPCData;

public class TestPortSync {
	public static void main(String[] args) {
		try {
			File file = new File("misc/portSyncRespFile.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(NPCData.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			NPCData npcData = (NPCData) jaxbUnmarshaller.unmarshal(file);

			System.out.println(npcData);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
