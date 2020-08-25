package com.laioffer.travelplanner.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.laioffer.travelplanner.jwtUtils.JwtTokenProvider;
import com.laioffer.travelplanner.model.common.OperationResponse;
import com.laioffer.travelplanner.model.plan.PlanSaveRequestModel;
import com.laioffer.travelplanner.services.PlanService;

@RestController
@RequestMapping("plan")
public class PlanController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PlanController.class);
	
	@Autowired
	private PlanService planService;
	
	
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
	/**
	 * Descriptive: save user basic plan to database
	 * 
	 * @author Rocky
	 * @since 2020-08-25
	 * 
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ResponseEntity<OperationResponse> savePlan(@RequestBody PlanSaveRequestModel planSaveRequestModel) {

		
		if(!jwtTokenProvider.authenToken(planSaveRequestModel.getAuthModel().getToken())){
			return new ResponseEntity<>(OperationResponse.getFailedResponse("No such user Or token is wrong"), HttpStatus.OK);
		}
		
		OperationResponse res = new OperationResponse();
		
		try {
			res = planService.savePlan(planSaveRequestModel);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			return new ResponseEntity<>(OperationResponse.getFailedResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
