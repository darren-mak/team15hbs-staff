package hbs.hotelstaff.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hbs.hotelstaff.dto.HotelStaffDTO;
import hbs.hotelstaff.service.IHotelStaffService;

@RestController
@RequestMapping("hotelstaff")
public class HotelStaffController {

	@Autowired
	private IHotelStaffService hotelStaffService;

	@PostMapping("/create")
	public HotelStaffDTO createHotelStaff(@RequestBody HotelStaffDTO hotelStaffDto) {
		return this.hotelStaffService.create(hotelStaffDto);
	}

	@PostMapping("/update")
	public HotelStaffDTO updateHotelStaff(@RequestBody HotelStaffDTO hotelStaffDto) {
		return this.hotelStaffService.update(hotelStaffDto);
	}

	@GetMapping("/getById/{staffId}")
	public HotelStaffDTO getHotelStaff(Long staffId) {
		return this.hotelStaffService.getByStaffId(staffId);
	}

	@GetMapping("/getAll")
	public List<HotelStaffDTO> getAllHotelStaff() {
		return this.hotelStaffService.getAll();
	}

	@PostMapping("/delete")
	public String deleteHotelStaff(Long staffId) {
		return this.hotelStaffService.delete(staffId);
	}
}
