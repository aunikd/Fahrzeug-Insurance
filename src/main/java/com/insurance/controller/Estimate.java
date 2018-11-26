package com.insurance.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.insurance.dao.*;
import com.insurance.model.*;
import com.insurance.service.EstimateService;

import java.util.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Controller
public class Estimate extends HttpServlet {


	@Autowired
	EstimateService eserv;
	
	@RequestMapping("/calculate_premium_estimate")
	public ModelAndView calc() {
		
			
		  		ModelAndView mv = new ModelAndView("vehicleinfo");
		  		
		        List<String> model_list=eserv.getModel(); 
		        List<String> company_list=eserv.getCompany();
		        mv.addObject("model_list",model_list);
		        mv.addObject("company_list",company_list);
		        
		        
		        
		        return mv;  
		    } 
	
	
	@RequestMapping("/vehicleinfo")
	public ModelAndView est(@ModelAttribute Vehicle v,HttpServletRequest request) {
		String engine=eserv.getEngine(v);
		
		v.setVehicle_engine(engine);
		
		float age=eserv.getAge(v.getVehicle_purchase_date());
		
		
	//	Object policy_obj=(Object)v.getVehicle_policy_type();
	//	String policy=(String)policy_obj;
		String policy =request.getParameter("policy");
		System.out.println("policy"+policy);
		//String policy="COMP";
		 float premium=0;
		
		

				//calling tp_rate method
				String tp_premium=eserv.tp_rates(v.getVehicle_engine());
								
				//if policy is third party
				if(policy.equals("3PL")) {
					//3rd party premium estimate 
					
					int tp1=Integer.parseInt(tp_premium);
					float tp2=0.55f*tp1;
					premium=tp2;
					
				}
		
		//if comprehensive policy
		else if(policy.equals("COMP")) {
			
				//calculate dep_value--
			//get dep_rate
			float dep=(float)(eserv.dep_value(age));
					
			System.out.println("dep"+dep);
			
			//get selling price
			int sp=eserv.get_sp(v.getVehicle_model());
			
			System.out.println("sp"+sp);
			
			//calculate idv
			//HOW TO DIVIDE?!
			float d1=dep/100.00f; System.out.println("d1-"+d1);
			float s1=d1*sp; System.out.println("s1-"+s1);
			float i1=sp-s1; System.out.println("i1-"+i1);
			
			float idv=(sp-(sp*(dep/100.00f)));
			System.out.println("idv"+idv);
			
			//od calculate
			int zone =Integer.parseInt(request.getParameter("zone"));	
			
			
			float od_rate=eserv.get_od(v.getVehicle_engine(),zone,age);
			System.out.println("od rate"+od_rate);
			
			float od=idv*(od_rate/100.00f);
			System.out.println("od"+od);
			
			int comp_tp=Integer.parseInt(tp_premium);
			System.out.println("comp_tp"+comp_tp);
			
			
			if(v.getVehicle_type().equals("2W")) {
				int tp1=comp_tp;
				float tp2=0.55f*tp1;
				od=od*0.55f;
				
				
				
				float od_premium=tp2+od+50;
				//comprehensive premium estimate display page
				premium=od_premium;
			}
			else if(v.getVehicle_type().equals("4W"))
			{
				int tp1=comp_tp;
				float tp2=0.55f*tp1;
				od=od*0.55f;
				float od_premium=tp2+od+100;
				//comprehensive premium estimate display page
				premium=od_premium;
				}
			
		
		}
		else
		{
			//error page
			return new ModelAndView("Error_page");
		}
				
				System.out.println("premium"+premium);
				//comprehensive premium estimate display page
				return new ModelAndView("estimate_display","premium",premium);
		
	}
	
	
	/*@RequestMapping(value = "/confrim", method = RequestMethod.GET)
		public ModelAndView confirm(@ModelAttribute Recharge r) {
		ModelAndView mv = new ModelAndView("confirm");
		mv.addObject("msg",r);
		return mv;*/
	}