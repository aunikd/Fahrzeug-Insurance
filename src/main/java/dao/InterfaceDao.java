package dao;

	import java.util.List;
	import model.*;

	public interface InterfaceDao {
		//int estimate();
		
		String getEngine(Vehicle v);
		int getAge(String dop);
		String tp_rates(String engine);

}
