package org.hiree.salesreports.web.rest.ui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hiree.salesreports.rest.dto.ResponseBaseDTO;
import org.hiree.salesreports.rest.dto.TestDTO;
import org.hiree.salesreports.rest.dto.common.ResponseDTOWrapper;
import org.hiree.salesreports.rest.dto.common.ResponseDTOWrapperForList;
import org.hiree.salesreports.rest.dto.dialog.DialogColumnRowInfoDTO;
import org.hiree.salesreports.rest.dto.dialog.RowColumnInfoDTO;
import org.hiree.salesreports.rest.dto.grid.GridInfoDTO;
import org.hiree.salesreports.rest.dto.interfaces.Payload;
import org.hiree.salesreports.services.interfaces.ITestService;
import org.hiree.salesreports.web.rest.ui.controller.base.AbstractRestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/test")
public class TestController extends AbstractRestController {

    @Autowired  ITestService testService;
	Logger logger = LoggerFactory.getLogger(TestController.class);
	List records= new ArrayList<TestDTO>();
	boolean flag = false;
	@RequestMapping(path = "/firsttest", method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE})
	@CrossOrigin
	public ResponseEntity<List<ResponseDTOWrapper>> getSampleMessage() {

		ResponseBaseDTO<?> responseBaseDTO = new ResponseBaseDTO<Object>();
		responseBaseDTO.setMessage("Sample ");
		responseBaseDTO.setStatus("Sucess");
		return getResponse(null,null,responseBaseDTO);
	}
	
	/*@RequestMapping(path = "/getrecords", method = RequestMethod.GET)
	public ResponseEntity<List<ResponseDTOWrapperForList>> getTestRecords() throws Exception {
		
		@SuppressWarnings("rawtypes")
		List records= new ArrayList<TestDTO>(testService.getTestData());
		return getResponse(null,null,(List<Payload>)records);
	}*/

	
	@RequestMapping(path = "/addrecord", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
	@CrossOrigin
	public ResponseEntity<List<ResponseDTOWrapper>> createRecord(@RequestBody TestDTO testDTO, HttpServletRequest request) {
		
		System.out.println(testDTO.getEmpNumber());
		System.out.println(testDTO.getName());
		System.out.println(testDTO.getDept());
		records.add(testDTO);
		ResponseBaseDTO<?> responseBaseDTO = new ResponseBaseDTO<Object>();
		responseBaseDTO.setMessage("Record Create Sucess ");
		responseBaseDTO.setStatus("Sucess");
		return getResponse(null,null,responseBaseDTO);
		
	}
	@RequestMapping(path = "/gettablerecords", method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE})
	@CrossOrigin
	public ResponseEntity<List<ResponseDTOWrapper>> getTableTestRecordsFromList() throws Exception {
		
		GridInfoDTO gridInfoDTO = testService.getTestTableData();
		
		return getResponse(gridInfoDTO);
	}
	
	@RequestMapping(path = "/getgenerictablerecords/{tableName}", method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE})
	@CrossOrigin
	public ResponseEntity<List<ResponseDTOWrapper>> getGenericTableRecords(@PathVariable String tableName) throws Exception {
		
		GridInfoDTO gridInfoDTO = testService.getGenericTableData(tableName);
		
		return getResponse(gridInfoDTO);
	}
	
	@RequestMapping(path = "/getrecords", method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE})
	@CrossOrigin
	public ResponseEntity<List<ResponseDTOWrapperForList>> getTestRecordsFromList() throws Exception {
		
		TestDTO testDTO = new TestDTO();
		testDTO.setEmpNumber(1);
		testDTO.setDept(1);
		testDTO.setName("Vikram");
		
		TestDTO testDTO1 = new TestDTO();
		testDTO1.setEmpNumber(2);
		testDTO1.setDept(1);
		testDTO1.setName("ANIL");
		
		TestDTO testDTO2 = new TestDTO();
		testDTO2.setEmpNumber(3);
		testDTO2.setDept(2);
		testDTO2.setName("Prakash");
	
		if(!flag){
			records.add(testDTO);
			records.add(testDTO1);
			records.add(testDTO2);
			flag=true;
		}
	
		
		return getResponse(null,null,(List<Payload>)records);
	}

	@RequestMapping(path = "/getdialogrecords/{tableName}/{id}", method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE})
	@CrossOrigin
	public ResponseEntity<List<ResponseDTOWrapper>> getDialogColumnRowInfoDTOList(@PathVariable String tableName,@PathVariable String id) throws Exception {
		
		DialogColumnRowInfoDTO dialogColumnRowInfoDTO = null;	
		
		if(tableName.equals("TESTVO")){
			dialogColumnRowInfoDTO = testService.getTestTableRecord(id);
		}
		
		return getResponse(dialogColumnRowInfoDTO);
		
	}
	
	@RequestMapping(path = "/genericedit/{tableName}/{id}", method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE})
	@CrossOrigin
	public ResponseEntity<List<ResponseDTOWrapper>> getGenericDialogColumnRowInfoDTOList(@PathVariable String tableName,@PathVariable String id) throws Exception {
		
		DialogColumnRowInfoDTO dialogColumnRowInfoDTO = null;	
		
		if(tableName.equals("TEST")){
			dialogColumnRowInfoDTO = testService.getGenericRecord(id);
		}
		
		return getResponse(dialogColumnRowInfoDTO);
		
	}
	
	@RequestMapping(path = "/genericupdate/{tableName}", method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
	@CrossOrigin
	public ResponseEntity<List<ResponseDTOWrapper>> updateGenericDialogColumnRowInfoDTOList(@PathVariable String tableName,@RequestBody DialogColumnRowInfoDTO dialogColumnRowInfoDTO) throws Exception {
		
		testService.updateGenericRecord("TEST",dialogColumnRowInfoDTO);
		ResponseBaseDTO<?> responseBaseDTO = new ResponseBaseDTO<Object>();
		responseBaseDTO.setMessage("Record Create Sucess ");
		responseBaseDTO.setStatus("Sucess");
		return getResponse(null,null,responseBaseDTO);
		
	}
	
	

	@RequestMapping(path = "/testasync", method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE})
	@CrossOrigin
	public ResponseEntity<List<ResponseDTOWrapperForList>> getSync() throws Exception {
		
		TestDTO testDTO = new TestDTO();
		testDTO.setEmpNumber(1);
		testDTO.setDept(1);
		testDTO.setName("Vikram");
		
		TestDTO testDTO1 = new TestDTO();
		testDTO1.setEmpNumber(2);
		testDTO1.setDept(1);
		testDTO1.setName("ANIL");
		
		TestDTO testDTO2 = new TestDTO();
		testDTO2.setEmpNumber(3);
		testDTO2.setDept(2);
		testDTO2.setName("Prakash");
	
		if(!flag){
			records.add(testDTO);
			records.add(testDTO1);
			records.add(testDTO2);
			flag=true;
		}
		logger.info("Started  " );
		testService.asyncMethodWithConfiguredExecutor();
		logger.info("End  " );
		
		/*Future<String> process1 = testService.process();
		while (!(process1.isDone() )) {
			Thread.sleep(2000);
		}

		logger.info("Process 1: " + process1.get());*/
		
		return getResponse(null,null,(List<Payload>)records);
		
	}
	
	

}
