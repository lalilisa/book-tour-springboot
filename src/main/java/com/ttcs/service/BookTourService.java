package com.ttcs.service;

import java.util.List;

import com.ttcs.model.dto.BookTourDTO;

public interface BookTourService {

	List<BookTourDTO> findByIdAccount(Integer id);

	List<BookTourDTO> findByKey(String key);

	List<BookTourDTO> findByUserName(String username);

	BookTourDTO getById(Integer id);

}
