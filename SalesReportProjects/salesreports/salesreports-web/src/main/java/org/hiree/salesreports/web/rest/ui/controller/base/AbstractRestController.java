package org.hiree.salesreports.web.rest.ui.controller.base;

import java.util.ArrayList;
import java.util.List;

import org.hiree.salesreports.rest.dto.common.ResponseDTO;
import org.hiree.salesreports.rest.dto.common.ResponseDTOWrapper;
import org.hiree.salesreports.rest.dto.common.ResponseDTOWrapperForList;
import org.hiree.salesreports.rest.dto.grid.GridInfoDTO;
import org.hiree.salesreports.rest.dto.interfaces.Payload;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AbstractRestController implements InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public ResponseEntity<List<ResponseDTOWrapper>> getResponse( String code, String title,
			Payload payload) {

		computeGridHierarchy(payload);
		List<ResponseDTOWrapper> responseDTOWrapperList = new ArrayList<>();

		
		ResponseDTO responseDTO = getResponseDTO(code, title);
		responseDTOWrapperList.add(new ResponseDTOWrapper(responseDTO,payload));

		return new ResponseEntity<List<ResponseDTOWrapper>>(responseDTOWrapperList, HttpStatus.OK);
	}
	
	public  ResponseDTO getResponseDTO(String content,  String title) {
		
                if(null!=content && null!=title){
                	return new ResponseDTO(title, content);
                }
                return null;
				
			
	}
	public ResponseEntity<List<ResponseDTOWrapper>> getResponse(Payload payload) {
		return getResponse(null, null, payload);
	}


	public ResponseEntity<List<ResponseDTOWrapperForList>> getResponse(List<? extends Payload> payload) {
		return getResponse(null, null,  payload);
	}
  
	/*public ResponseEntity<List<ResponseDTOWrapper>> getResponse(String code, String title,
			Payload payload) {

		List<ResponseDTOWrapper> responseDTOWrapperList = new ArrayList<>();
		ResponseDTO responseDTO = getResponseDTO(code, paramList, title);
		responseDTOWrapperList.add(new ResponseDTOWrapper(responseDTO,payload));
		return new ResponseEntity<List<ResponseDTOWrapper>>(responseDTOWrapperList, HttpStatus.OK);
	}*/
	public ResponseEntity<List<ResponseDTOWrapperForList>> getResponse(String code,	String title, List<? extends Payload> payload) {
		
		
		ResponseDTO responseDTO = getResponseDTO(code, title);
		return new ResponseEntity<List<ResponseDTOWrapperForList>>(getNewListInitialized(new ResponseDTOWrapperForList(responseDTO, payload)), HttpStatus.OK);
	}
	private void computeGridHierarchy(Payload payload) {
		if (payload instanceof GridInfoDTO) {
			GridInfoDTO gridInfoDTO = (GridInfoDTO) payload;
			gridInfoDTO.computeHierarchy();
		}
	}
	
	public  <V> List<V> getNewListInitialized(@SuppressWarnings("unchecked") final V... values) {
		List<V> list = new ArrayList<V>();
		if (values!=null) {
			for (V value: values) {
				list.add(value);
			}
		}
		return list;
	}
}
