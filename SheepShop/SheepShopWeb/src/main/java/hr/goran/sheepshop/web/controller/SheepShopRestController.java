package hr.goran.sheepshop.web.controller;

import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import hr.goran.sheepshop.business.manager.HerdManager;
import hr.goran.sheepshop.business.manager.OrderManager;
import hr.goran.sheepshop.business.manager.StockManager;
import hr.goran.sheepshop.model.CustomerOrder;
import hr.goran.sheepshop.model.Herd;
import hr.goran.sheepshop.model.Order;
import hr.goran.sheepshop.model.Sheep;
import hr.goran.sheepshop.model.Stock;
import hr.goran.sheepshop.web.common.OrderFieldSerializer;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Goran
 */
@RestController
@Slf4j
public class SheepShopRestController {

	@Autowired
	private HerdManager herdManager;
	@Autowired
	private StockManager stockManager;
	@Autowired
	private OrderManager orderManager;

	@RequestMapping(method = RequestMethod.POST, value="/initialize")
	public ResponseEntity<String> getInitializeHerd(@RequestParam("file") MultipartFile file) throws JAXBException, IOException{
		logInfo("INIT - request");
		if (!file.isEmpty()) {
			JAXBContext jaxbContext = JAXBContext.newInstance(Herd.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Herd herd = (Herd) jaxbUnmarshaller.unmarshal(file.getInputStream());

			herdManager.saveHerd(herd);
			
			if(log.isDebugEnabled()){
				for (Sheep sheep : herd.getHerd()) {
					log.debug(sheep.getName());
				}
			}
			
			logInfo("INIT - ok");
		}
		return new ResponseEntity<String>("OK", HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value="/herd/{day}")
	public Herd getHerd(@PathVariable("day") int day){
		logInfo("REQUEST - herd, day " + day);
		validateDay(day);
		return this.herdManager.getHerd(day-1);
	}

	@RequestMapping(method = RequestMethod.GET, value="/stock/{day}")
	public Stock getStock(@PathVariable("day") int day){
		logInfo("REQUEST - stock, day " + day);
		validateDay(day);
		return stockManager.getStock(day-1);
	}

	@RequestMapping(method = RequestMethod.POST, value="/order/{day}") //, @RequestBody CustomerOrder customerOrder
	public ResponseEntity<String> getOrder(@PathVariable("day") int day, @RequestBody CustomerOrder customerOrder) throws JsonProcessingException{
		logInfo("ORDER, day " + day);
		validateDay(day);
		ObjectMapper mapper = new ObjectMapper();
		FilterProvider filters = new SimpleFilterProvider().addFilter("orderFilter", new OrderFieldSerializer(customerOrder.getOrder()));

		Order order = orderManager.order(customerOrder, day-1);

		if(order.getMilk()==customerOrder.getOrder().getMilk() && order.getSkins() == customerOrder.getOrder().getSkins())	
			return new ResponseEntity<String>(mapper.writer(filters).writeValueAsString(order), HttpStatus.CREATED); //201
		else if(order.getMilk()!=customerOrder.getOrder().getMilk() && order.getSkins() != customerOrder.getOrder().getSkins())	
			return new ResponseEntity<String>("", HttpStatus.NOT_FOUND); //404
		else
			return new ResponseEntity<String>(mapper.writer(filters).writeValueAsString(order), HttpStatus.PARTIAL_CONTENT); //206
	}


	/**
	 * Day start with 1.
	 * @param day
	 */
	private void validateDay(int day) {
		if(day < 1)
			throw new IllegalArgumentException("Day can't be less then 1.");
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgumentExc(IllegalArgumentException e){
		log.error("Exception in service - ", e);
		return new ResponseEntity<String>("Isprika. Došlo je do pogreške.", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(JsonProcessingException.class)
	public ResponseEntity<String> handleJsonProcessingExc(JsonProcessingException e){
		log.error("Exception in service - ", e);
		return new ResponseEntity<String>("Isprika. Došlo je do pogreške.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(JAXBException.class)
	public ResponseEntity<String> handleJAXBExceptionExc(JAXBException e){
		log.error("Exception in service - ", e);
		return new ResponseEntity<String>("Isprika. Došlo je do pogreške.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(IOException.class)
	public ResponseEntity<String> handleIOExceptionExc(IOException e){
		log.error("Exception in service - ", e);
		return new ResponseEntity<String>("Isprika. Došlo je do pogreške.", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleRuntimeExc(RuntimeException e){
		log.error("Exception in service - ", e);
		return new ResponseEntity<String>("Isprika. Došlo je do pogreške.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	private void logInfo(String message){
		if(log.isInfoEnabled())
			log.info(message);
	}
}
