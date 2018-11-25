package controller;

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
import java.util.*;
import dao.*;
import model.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Controller
public class Estimate extends HttpServlet {


	@Autowired
	EstimateDao rdao;
	
	@RequestMapping("/calculate_premium_estimate")
	public ModelAndView calc() {
		
			
		  		ModelAndView mv = new ModelAndView("vehicleinfo");
		        List<String> model_list=rdao.getModel(); 
		        List<String> company_list=rdao.getCompany();
		        mv.addObject("model_list",model_list);
		        mv.addObject("company_list",company_list);
		        
		        
		        return mv;  
		    } 
	
	
	@RequestMapping("/vehicleinfo")
	public ModelAndView est(@ModelAttribute Vehicle v,HttpServletRequest request) {
		String engine=rdao.getEngine(v);
		
		System.out.println("vehicleinfo serv");
		
		int age=rdao.getAge(v.getVehicle_purchase_date());
		Object policy_obj=(Object)v.getVehicle_policy_type();
		String policy=(String)policy_obj;
		int premium=0;

				//calling tp_rate method
				String tp_premium=rdao.tp_rates(v.getVehicle_engine());
								
				//if policy is third party
				if(policy=="3PL") {
					//3rd party premium estimate 
					premium=Integer.parseInt(tp_premium);
					
				}
		
		//if comprehensive policy
		else if(policy=="COMP") {
			
			//calculate dep_value--
			//get dep_rate
			int dep=rdao.dep_value(age);
			
			//get selling price
			int sp=rdao.get_sp(v.getVehicle_model());
			
			//calculate idv
			int idv=sp-(sp*(dep/100));
			
			//od calculate
			int zone =Integer.parseInt(request.getParameter("zone"));			
			int od_rate=rdao.get_od(v.getVehicle_engine(),zone,age);
			int od=idv*od_rate;
			
			int comp_tp=Integer.parseInt(tp_premium);
			
			if(v.getVehicle_type()=="2W") {
				int od_premium=comp_tp+od+50;
				//comprehensive premium estimate display page
				premium=od_premium;
			}
			else if(v.getVehicle_type()=="4W")
			{
				int od_premium=comp_tp+od+100;
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