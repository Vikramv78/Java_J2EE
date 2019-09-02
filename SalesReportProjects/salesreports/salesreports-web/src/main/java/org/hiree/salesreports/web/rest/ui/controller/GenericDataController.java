package org.hiree.salesreports.web.rest.ui.controller;

import java.util.List;

import org.hiree.salesreports.rest.annotation.User;
import org.hiree.salesreports.rest.dto.ResponseBaseDTO;
import org.hiree.salesreports.rest.dto.UserDTO;
import org.hiree.salesreports.rest.dto.common.ResponseDTOWrapper;
import org.hiree.salesreports.rest.dto.dialog.DialogColumnRowInfoDTO;
import org.hiree.salesreports.rest.dto.grid.GridInfoDTO;
import org.hiree.salesreports.services.interfaces.IGenericDataService;
import org.hiree.salesreports.web.rest.ui.controller.base.AbstractRestController;
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
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/generic")
public class GenericDataController extends AbstractRestController{
	
	 @Autowired  IGenericDataService genericDataService;
	 
	 
	@User
	@RequestMapping(path = "/alltablerecords/{tableName}", method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE})
	@CrossOrigin
	public ResponseEntity<List<ResponseDTOWrapper>> getGenericTableRecords(@PathVariable String tableName) throws Exception {
		UserDTO user = getUser();
		GridInfoDTO gridInfoDTO = genericDataService.getGenericDataAllRowsInfoFromTable(tableName);
		return getResponse(gridInfoDTO);
	}
	
	
	@User
	@RequestMapping(path = "/genericedit/{tableName}/{id}", method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE})
	@CrossOrigin
	public ResponseEntity<List<ResponseDTOWrapper>> getGenericDialogColumnRowInfoDTOList(@PathVariable String tableName,@PathVariable String id) throws Exception {
		
		DialogColumnRowInfoDTO dialogColumnRowInfoDTO = null;	
		dialogColumnRowInfoDTO = genericDataService.getGenericDataSelectedRowInfo(tableName,id);
		return getResponse(dialogColumnRowInfoDTO);
		
	}
	
	@User
	@RequestMapping(path = "/genericupdate/{tableName}", method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
	@CrossOrigin
	public ResponseEntity<List<ResponseDTOWrapper>> updateGenericDialogColumnRowInfoDTOList(@PathVariable String tableName,@RequestBody DialogColumnRowInfoDTO dialogColumnRowInfoDTO) throws Exception {
		
		genericDataService.updateGenericDataSelectedRowInfo(tableName,dialogColumnRowInfoDTO);
		ResponseBaseDTO<?> responseBaseDTO = new ResponseBaseDTO<Object>();
		responseBaseDTO.setMessage("Record Create Sucess ");
		responseBaseDTO.setStatus("Sucess");
		return getResponse(null,null,responseBaseDTO);
		
	}
	
	@User
	@RequestMapping(path = "/genericcreate/{tableName}", method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
	@CrossOrigin
	public ResponseEntity<List<ResponseDTOWrapper>> createGenericDialogColumnRowInfoDTOList(@PathVariable String tableName,@RequestBody DialogColumnRowInfoDTO dialogColumnRowInfoDTO) throws Exception {
		
		genericDataService.createGenericDataSelectedRowInfo(tableName,dialogColumnRowInfoDTO);
		ResponseBaseDTO<?> responseBaseDTO = new ResponseBaseDTO<Object>();
		responseBaseDTO.setMessage("Record Create Sucess ");
		responseBaseDTO.setStatus("Sucess");
		return getResponse(null,null,responseBaseDTO);
		
	}
	

}
