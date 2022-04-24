package hbs.hotelstaff.repositories;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;

import hbs.hotelstaff.dto.HotelStaffDTO;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Repository
public class HotelStaffRepository {

	@Autowired
	private DynamoDBMapper dynamoDBMapper;

	static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();

	public HotelStaffDTO saveHotelStaff(HotelStaffDTO hotelStaffDto) {
		log.debug("saveHotelStaff START");
		try {
			this.dynamoDBMapper.save(hotelStaffDto);
			return hotelStaffDto;
		} catch (Exception e) {
			log.error("Error saving staff: {}", e.getCause());
			return null;
		}
	}

	public HotelStaffDTO getHotelStaffById(Long staffId) {
		log.debug("getHotelStaffById START");
		final HotelStaffDTO hotelStaffDTO = this.dynamoDBMapper.load(HotelStaffDTO.class, staffId);
		log.debug("response {}", hotelStaffDTO);
		return hotelStaffDTO;
	}

	public String deleteByStaffId(Long staffId) {
		log.debug("deleteByStaffId START");
		final HotelStaffDTO hotelStaffDTO = this.dynamoDBMapper.load(HotelStaffDTO.class, staffId);
		if (Objects.isNull(hotelStaffDTO)) {
			log.error("Fail to delete hotelStaff, staff does not exist");
			return "Fail to delete staff";
		} else {
			this.dynamoDBMapper.delete(hotelStaffDTO);
			return "Staff deleted";
		}
	}

	public HotelStaffDTO updateHotelStaff(HotelStaffDTO hotelStaff) {
		log.debug("updateHotelStaff START");
		this.dynamoDBMapper.save(hotelStaff, new DynamoDBSaveExpression().withExpectedEntry("staffId",
				new ExpectedAttributeValue(new AttributeValue().withS(hotelStaff.getStaffId().toString()))));
		return getHotelStaffById(hotelStaff.getStaffId());
	}

	public List<HotelStaffDTO> getAllHotelStaff() {
		log.debug("getAllHotelStaff START");
		try {
			DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
			return this.dynamoDBMapper.scan(HotelStaffDTO.class, scanExpression);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return null;
		}
	}

}
