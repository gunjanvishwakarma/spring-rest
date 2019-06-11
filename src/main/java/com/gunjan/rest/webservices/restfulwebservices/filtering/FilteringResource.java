package com.gunjan.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringResource
{
    @GetMapping("/filtering")
    public SomeBean retrieveSomeBean(){
        return new SomeBean("value1","value2","value3");
    }
    
    @GetMapping("/filtering-list")
    public List<SomeBean> retrieveSomeBeanList(){
        return Arrays.asList(new SomeBean("value1","value2","value3"),new SomeBean("value11","value22","value33"));
    }
    
    @GetMapping("/filtering-dynamic")
    public MappingJacksonValue retrieveSomeBeanDynamicFiltering(){
        SomeBean someBean =  new SomeBean("value1","value2","value3");
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("value1");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }
    
}
