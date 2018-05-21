package com.licenta2018.spring;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.licenta2018.spring.model.Request;
import com.licenta2018.spring.model.User;
import com.licenta2018.spring.repository.AuthorityRepository;
import com.licenta2018.spring.repository.RequestRepository;
import com.licenta2018.spring.repository.UserRepository;
import com.licenta2018.spring.security.SecurityConfig;

@SpringBootApplication
public class Licenta2018WebApplication implements CommandLineRunner {

	/**
	 * The main() method uses Spring Boot’s SpringApplication.run() method to launch an application.
	 * The run() method returns an ApplicationContext where all the beans that were created 
	 * either by your app or automatically added thanks to Spring Boot are.
	 * @param args
	 */
	@Autowired
	UserRepository users;

	@Autowired
	AuthorityRepository authorities;

	@Autowired
	RequestRepository requests;

	public static void main(String[] args) {
		SpringApplication.run(Licenta2018WebApplication.class, args);
	}

	@Override
	public void run(String... strings)
	{
		Iterator<User> it = users.findAll().iterator();

		while(it.hasNext()){
			User u1 = it.next();
			String pass = u1.getPassword();
			u1.setPassword(SecurityConfig.encoder.encode(pass));
			users.save(u1);
		}

		Iterator<Request> itRequest = requests.findAll().iterator();

		while(itRequest.hasNext()){
			Request request = itRequest.next();
			Date begin = request.getIssueDate();
			Date end = request.getApprovalDate();
			List<Date> dates = new ArrayList<Date>();
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(begin);

			while (calendar.getTime().getTime() <= end.getTime())
			{
				Date result = calendar.getTime();			
				dates.add(result);				
				calendar.add(Calendar.DATE, 1);       
			}  

			Map<Date, Long> tmpMap = new HashMap<Date, Long>();

			for(Date d : dates)
			{
				tmpMap.put(d, request.getId());
			}

			requests.save(request);
		}
	}

	public Date removeTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
}


