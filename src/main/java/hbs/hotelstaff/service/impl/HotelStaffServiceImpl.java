package hbs.hotelstaff.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;

import hbs.hotelstaff.dto.HotelStaffDTO;
import hbs.hotelstaff.repositories.HotelStaffRepository;
import hbs.hotelstaff.service.IHotelStaffService;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class HotelStaffServiceImpl implements IHotelStaffService {

	@Autowired
	private HotelStaffRepository hotelStaffRepository;

	@Override
	public List<HotelStaffDTO> getAll() {
		log.debug("getAll");
		return this.hotelStaffRepository.getAllHotelStaff();
	}

	@Override
	public HotelStaffDTO getByStaffId(Long staffId) {
		log.debug("getByStaffId: " + staffId);
		return this.hotelStaffRepository.getHotelStaffById(staffId);
	}

	@Override
	public HotelStaffDTO update(HotelStaffDTO hotelStaffDTO) {
		log.debug("updateHotelStaff");
		return this.hotelStaffRepository.updateHotelStaff(hotelStaffDTO);
	}

	@Override
	public HotelStaffDTO create(HotelStaffDTO hotelStaffDTO) {
		log.debug("createHotelStaff");
		return this.hotelStaffRepository.updateHotelStaff(hotelStaffDTO);
	}

	@Override
	public String delete(Long staffId) {
		log.debug("deleteHotelStaff");
		return this.hotelStaffRepository.deleteByStaffId(staffId);
	}

	@Override
	public String login(HotelStaffDTO hotelStaffDTO) {
		log.debug("loginHotelStaff");
		if (Objects.isNull(hotelStaffDTO) || Objects.isNull(hotelStaffDTO.getLoginName())
				|| Objects.isNull(hotelStaffDTO.getPassword())) {
			log.error("NULL hotelStaffDTO");
			return "Fail";
		}
		final List<HotelStaffDTO> hotelStaffData = this.hotelStaffRepository.getAllHotelStaff();
		final HotelStaffDTO hotelStaff = hotelStaffData.stream()
				.filter(staff -> staff.getLoginName().trim().equals(hotelStaffDTO.getLoginName().trim())
						&& staff.getPassword().trim().equals(hotelStaffDTO.getPassword().trim()))
				.findFirst().orElse(null);
		if (Objects.isNull(hotelStaff)) {
			return "Fail";
		} else {
			return "Success";
		}
	}
}
