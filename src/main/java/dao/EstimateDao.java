package dao;

	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.List;
	import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
	import org.springframework.jdbc.core.RowMapper;
	import model.*;
	import java.text.SimpleDateFormat;
	import java.util.Date;
	public class EstimateDao  implements InterfaceDao{

		private JdbcTemplate jdbcTemplate;  
		  
		public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
		    this.jdbcTemplate = jdbcTemplate;  
		}  
		
		  public JdbcTemplate getJdbcTemplate() {
			return jdbcTemplate;
		}

		//get engine number method	
		public String getEngine(Vehicle v) {
	//		String query="select engine_no from "+v.getType()+" where model_no="+v.getModel()+" ";
	//		String engine_no = jdbcTemplate.queryForObject(query, String.class);
	//		return engine_no;
			
			 String query="select gv_cc from gr10_vehicledetails  where gv_model=? ";			 
			Object[] inputs = new Object[] {v.getVehicle_model()};						
			String engine_no = jdbcTemplate.queryForObject(query, inputs, String.class); 		
	       return engine_no;
		}
		
		//get car age method
		public int getAge(String dop) {
			
			int age=0;
			
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			Date d1 = new Date();
			Date d2 = null;
			try {
				
				d2 = format.parse(dop);
				
				//in milliseconds
				long diff = d2.getTime() - d1.getTime();
				//in years
				age = (int)(diff / (24 * 60 * 60 * 1000)%365);
			
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return age;
		}
		
		//method to fetch premium rate based on engine
		public String tp_rates(String engine) {
			System.out.println("engine"+engine);
			String query="select gt_tp_rate from gr10_tprate where "+engine+" between gt_start_cc and gt_end_cc ";			 
			//Object[] inputs = new Object[] {engine};						
			String premium = jdbcTemplate.queryForObject(query, String.class); 		
	       return premium;
			
		}
		
		//method to fetch dep percentage
		public int dep_value(int age) {
			String query="select gd_dep_rate from gr10_deprate where ? between gd_start_age and gd_end_age ";			 
			Object[] inputs = new Object[] {age};						
			int dep = jdbcTemplate.queryForObject(query, inputs, Integer.class); 				
			return dep;
		}
		//method to fetch selling price of model
		public int get_sp(String model) {
			String query="select gv_price from gr10_vehicledetails where gv_model='?' ";			 
			Object[] inputs = new Object[] {model};					
			int sp = jdbcTemplate.queryForObject(query, inputs, Integer.class); 				
			return sp;
		}
		//method to fetch own damage rate of vechicle by engine and state
				public int get_od(String engine,int state,int age) {
					String query="select go_od_rate from gr10_odrate where ? between go_od_start_age and go_od_end_age and ? between go_od_start_cc and go_od_end_cc and go_od_zone=?";			 
					Object[] input1 = new Object[] {age,engine,state};	
					
					
					int sp = jdbcTemplate.queryForObject(query, input1,Integer.class); 				
					return sp;
				}
		
		public List<String> getModel(){
			String query = "select gv_model from gr10_vehicledetails";
			List<String> strings = (List<String>) jdbcTemplate.queryForList(query, String.class);
			return strings;
                }
		
		public List<String> getCompany(){
			String query = "select DISTINCT gv_company from gr10_vehicledetails";
			List<String> strings = (List<String>) jdbcTemplate.queryForList(query, String.class);
			return strings;
                }
		
	}
		 
	

