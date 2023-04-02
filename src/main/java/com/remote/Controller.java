package com.remote;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {
	@Autowired
	RestTemplate rt;
	
	
	
	@GetMapping(value="/getruns")
	public List<Bean> getting(){
		String url1="http://localhost:8080/cric1";
		String url2="http://localhost:8082/runs1/";
		ResponseEntity<List<BeanClass>>response=rt.exchange(url1,HttpMethod.GET,null,new ParameterizedTypeReference<List<BeanClass>>(){});
		List<BeanClass>b=response.getBody();
		List<Integer>nums=new ArrayList<>();
		b.forEach(x->{
			nums.add(x.getJerseyNumber());
		});
		ResponseEntity<List<Bean>>response2=rt.exchange(url2+nums,HttpMethod.GET,null,new ParameterizedTypeReference<List<Bean>>(){});
		List<Bean>c=response2.getBody();
		
		return c;
		
	}
	
	
	
}
