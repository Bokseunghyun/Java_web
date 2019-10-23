package com.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.mapper.SampleMapper1;
import com.spring.mapper.SampleMapper2;

@Service("sample1")
public class SampleServiceImpl implements SampleService {
	
	@Autowired
	private SampleMapper1 mapper1;
	@Autowired
	private SampleMapper2 mapper2;
	
	@Override
	@Transactional
	public void addData(String value) {
		mapper1.insertCol1(value);
		mapper2.insertCol2(value);
	}

}
