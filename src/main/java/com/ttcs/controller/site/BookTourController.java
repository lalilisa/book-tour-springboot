package com.ttcs.controller.site;

import java.util.List;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ttcs.domain.BookTour;
import com.ttcs.model.dto.BookTourDTO;
import com.ttcs.model.mapper.BookTourMapper;
import com.ttcs.repository.AccountRepository;
import com.ttcs.repository.BookTourRepository;
import com.ttcs.repository.TourRepository;
import com.ttcs.service.BookTourService;
@Tag(name = "BookTour Controller")
@RestController
@RequestMapping("/data/secure")
public class BookTourController {
	@Autowired
	private BookTourService bookTourService;
	@Autowired
	private BookTourRepository bookTourRepository;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private TourRepository tourRepository;
	
	@GetMapping(value="/listbooktour", produces = "application/json")
	public List<BookTourDTO> getAll(){
		return BookTourMapper.toListBookTourDTOs(bookTourRepository.findAll());
	}
	
	@GetMapping(value="/statisticbooktour", produces = "application/json")
	public Integer statisticBooktour(){
		return bookTourRepository.findAll().size();
	}
	
	@GetMapping(value="/statisticincome", produces = "application/json")
	public long statisticIncome(){
		List<BookTourDTO> list = BookTourMapper.toListBookTourDTOs(bookTourRepository.findAll());
		long income = 0;
		for (BookTourDTO i : list) {
			income += tourRepository.getById(i.getTourId()).getTourCost()*i.getQuantity();
		}
		return income;
	}
	
	
	@GetMapping(value="/listbooktourbykey", produces = "application/json")
	public List<BookTourDTO> getByKey(@RequestParam("key") String key){
		return bookTourService.findByKey(key);
	}
	
	@GetMapping(value="/listbooktourbyaccid", produces = "application/json")
	public List<BookTourDTO> getByKey(@RequestParam("id") Integer id){
		return bookTourService.findByIdAccount(id);
	}
	
	@GetMapping(value="/listbooktourbyusername", produces = "application/json")
	public List<BookTourDTO> getByUsername(@RequestParam("username") String username){
		return bookTourService.findByUserName(username);
	}
	
	@GetMapping(value="/booktour/{id}", produces = "application/json")
	public BookTourDTO getById(@PathVariable("id") Integer id) {
		return bookTourService.getById(id);
	}
	
	@PostMapping(value = "/bookTour")
	public void createBookTour(@RequestBody BookTourDTO bookTourDTO) {
		BookTour bt = new BookTour();
		bt.setAccount(accountRepository.findByUserName(bookTourDTO.getUsernameacc()));
		bt.setTour(tourRepository.getById(bookTourDTO.getTourId()));
		bt.setDayStart(bookTourDTO.getDayStart());
		bt.setQuantity(bookTourDTO.getQuantity());
		bt.setPayment(bookTourDTO.getPayment());
		bt.setDatebook(bookTourDTO.getBookdate());
		bookTourRepository.save(bt);
	}
	
	@DeleteMapping(value = "/booktour/{id}")
	public void deleteBookTour(@PathVariable("id") Integer id) {
		bookTourRepository.deleteById(id);
	}
	
}
