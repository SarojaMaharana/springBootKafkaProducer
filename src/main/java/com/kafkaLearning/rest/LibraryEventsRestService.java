package com.kafkaLearning.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafkaLearning.domain.LibraryEvent;
import com.kafkaLearning.producer.LibraryEventsProducer;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class LibraryEventsRestService {
	
	@Autowired
	LibraryEventsProducer libraryEventsProducer;
	
	@PostMapping("/v1/libraryevent")
	public ResponseEntity<LibraryEvent> addBookToLibrary(@RequestBody LibraryEvent libraryEvent) throws JsonProcessingException {
		
		log.info("Before Call Library Event");
		/*invoke kafka producer. Asynchronoss call Approach 1
		 * 
		 libraryEventsProducer.sendLibraryEvent(libraryEvent);
		 
		 */
		
		/* invoke kafka producer. Synchronoss call
		 * 
		SendResult<Integer, String> sendResult = libraryEventsProducer.sendLibraryEventSynchronoss(libraryEvent);
		log.info("sendResult details {}", sendResult.toString());
		
		*/
		
		/*invoke kafka producer. Asynchronoss call Approach 2
		 */
		 libraryEventsProducer.sendLibraryEvent_Approach2(libraryEvent);
		 
		 
		log.info("After Call Library Event");
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(libraryEvent);
		
	}

}
