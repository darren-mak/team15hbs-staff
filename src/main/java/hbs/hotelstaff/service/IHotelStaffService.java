package hbs.hotelstaff.service;

import java.util.List;

import hbs.hotelstaff.dto.HotelStaffDTO;

public interface IHotelStaffService {

	public List<HotelStaffDTO> getAll();

	public HotelStaffDTO getByStaffId(final Long staffId);

	public HotelStaffDTO update(HotelStaffDTO hotelStaffDTO);

	public HotelStaffDTO create(HotelStaffDTO hotelStaffDTO);

	public String delete(Long staffId);

	public String login(HotelStaffDTO hotelStaffDTO);
}
