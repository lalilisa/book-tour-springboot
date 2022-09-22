package com.ttcs.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ttcs.model.dto.BookTourDTO;
import com.ttcs.model.mapper.BookTourMapper;
import com.ttcs.repository.BookTourRepository;
import com.ttcs.service.BookTourService;

@Service
public class BookTourServiceImpl implements BookTourService{
	BookTourRepository bookTourRepository;

	public BookTourServiceImpl(BookTourRepository bookTourRepository) {
		super();
		this.bookTourRepository = bookTourRepository;
	}

	@Override
	public List<BookTourDTO> findByIdAccount(Integer id) {
		return BookTourMapper.toListBookTourDTOs(bookTourRepository.findByIdAccount(id));
	}
	
	@Override
	public List<BookTourDTO> findByKey(String key) {
		return BookTourMapper.toListBookTourDTOs(bookTourRepository.findByKey(key));
	}
	
	@Override
	public List<BookTourDTO> findByUserName(String username){
		return BookTourMapper.toListBookTourDTOs(bookTourRepository.findByUsername(username));
	}
	
	@Override
	public BookTourDTO getById(Integer id) {
		return BookTourMapper.toBookTourDTO(bookTourRepository.getById(id));
	}
	
}
