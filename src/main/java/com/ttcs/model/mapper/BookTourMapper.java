package com.ttcs.model.mapper;

import java.util.ArrayList;
import java.util.List;

import com.ttcs.domain.BookTour;
import com.ttcs.model.dto.BookTourDTO;

public class BookTourMapper {
	public static BookTourDTO toBookTourDTO(BookTour bookTour) {
		BookTourDTO tmp = new BookTourDTO();
		tmp.setBookId(bookTour.getBookId());
		tmp.setTourId(bookTour.getTour().getTourId());
		tmp.setUsernameacc(bookTour.getAccount().getUserName());
		tmp.setDayStart(bookTour.getDayStart());
		tmp.setQuantity(bookTour.getQuantity());
		tmp.setPayment(bookTour.getPayment());
		tmp.setBookdate(bookTour.getDatebook());
		return tmp;
	}
	
	public static List<BookTourDTO> toListBookTourDTOs(List<BookTour> list){
		List<BookTourDTO> tmp = new ArrayList<>();
		for (BookTour i : list) {
			tmp.add(toBookTourDTO(i));
		}
		return tmp;
	}
}
