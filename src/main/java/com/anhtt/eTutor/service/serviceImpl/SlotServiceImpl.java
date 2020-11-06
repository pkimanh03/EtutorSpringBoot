package com.anhtt.eTutor.service.serviceImpl;

import java.util.*;
import java.util.stream.Collectors;

import com.anhtt.eTutor.dto.SlotResponseObject;
import com.anhtt.eTutor.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anhtt.eTutor.convert.SlotMapper;
import com.anhtt.eTutor.dto.SlotDTO;
import com.anhtt.eTutor.dto.SlotInsertDTO;
import com.anhtt.eTutor.model.Slot;
import com.anhtt.eTutor.model.Student;
import com.anhtt.eTutor.repository.SlotRepository;
import com.anhtt.eTutor.service.iservice.SlotService;

@Service
public class SlotServiceImpl implements SlotService{

	@Autowired
	SlotRepository slot;

	@Autowired
	SlotMapper slotMapper;

	@Override
	public List<SlotResponseObject> retrieveAllSlot() {
		List<Slot> slotList = slot.findByisDeletedFalse();
		if (!slotList.isEmpty()) {
			List<SlotDTO> slotDTOList = slotMapper.toSlotDtoList(slotList);
			List<SlotResponseObject> result = new ArrayList<>();
			List<SlotDTO> mondaySlots = new ArrayList<>();
			List<SlotDTO> tuesdaySlots = new ArrayList<>();
			List<SlotDTO> wednesdaySlots = new ArrayList<>();
			List<SlotDTO> thursdaySlots = new ArrayList<>();
			List<SlotDTO> fridaySlots = new ArrayList<>();
			List<SlotDTO> saturdaySlots = new ArrayList<>();
			List<SlotDTO> sundaySlots = new ArrayList<>();
			for (SlotDTO slot:slotDTOList) {
				String timeInSlot = convertTimeSlotToString(slot.getStartTime(), slot.getEndTime());
				slot.setTimeInSlot(timeInSlot);
				switch (slot.getDayInWeek()) {
					case Constants.SLOT_MONDAY:		mondaySlots.add(slot);
													break;
					case Constants.SLOT_TUESDAY:	tuesdaySlots.add(slot);
													break;
					case Constants.SLOT_WEDNESDAY:	wednesdaySlots.add(slot);
													break;
					case Constants.SLOT_THURSDAY:	thursdaySlots.add(slot);
													break;
					case Constants.SLOT_FRIDAY:		fridaySlots.add(slot);
													break;
					case Constants.SLOT_SATURDAY:	saturdaySlots.add(slot);
													break;
					case Constants.SLOT_SUNDAY:		sundaySlots.add(slot);
													break;
				}
			}

			mondaySlots = mondaySlots.stream().sorted(Comparator.comparing(SlotDTO::getStartTime)).collect(Collectors.toList());
			SlotResponseObject monday = new SlotResponseObject(Constants.SLOT_MONDAY, mondaySlots);

			tuesdaySlots = tuesdaySlots.stream().sorted(Comparator.comparing(SlotDTO::getStartTime)).collect(Collectors.toList());
			SlotResponseObject tuesday = new SlotResponseObject(Constants.SLOT_TUESDAY, tuesdaySlots);

			wednesdaySlots = wednesdaySlots.stream().sorted(Comparator.comparing(SlotDTO::getStartTime)).collect(Collectors.toList());
			SlotResponseObject wednesday = new SlotResponseObject(Constants.SLOT_WEDNESDAY, wednesdaySlots);

			thursdaySlots = thursdaySlots.stream().sorted(Comparator.comparing(SlotDTO::getStartTime)).collect(Collectors.toList());
			SlotResponseObject thursday = new SlotResponseObject(Constants.SLOT_THURSDAY, thursdaySlots);

			fridaySlots = fridaySlots.stream().sorted(Comparator.comparing(SlotDTO::getStartTime)).collect(Collectors.toList());
			SlotResponseObject friday = new SlotResponseObject(Constants.SLOT_FRIDAY, fridaySlots);

			saturdaySlots = saturdaySlots.stream().sorted(Comparator.comparing(SlotDTO::getStartTime)).collect(Collectors.toList());
			SlotResponseObject saturday = new SlotResponseObject(Constants.SLOT_SATURDAY, saturdaySlots);

			sundaySlots = sundaySlots.stream().sorted(Comparator.comparing(SlotDTO::getStartTime)).collect(Collectors.toList());
			SlotResponseObject sunday = new SlotResponseObject(Constants.SLOT_SUNDAY, sundaySlots);
			result.add(monday);
			result.add(tuesday);
			result.add(wednesday);
			result.add(thursday);
			result.add(friday);
			result.add(saturday);
			result.add(sunday);
			return result;
		}
        return null;
	}


	private String convertTimeSlotToString(double from, double to) {
		String result = null;

		int fromHour = (int) from;
		int fromMinute = (int) ((from - fromHour)*60);
		String fromString = fromHour + ":" + fromMinute;

		int toHour = (int) to;
		int tominute = (int) ((to - toHour)*60);
		String toString = toHour + ":" + tominute;

		result = fromString + " - " + toString;
		return result;
	}

	@Override
	public SlotInsertDTO saveSlot(SlotInsertDTO att) {
		Slot sl = slotMapper.toSlotI(att);
		slot.save(sl);
		return slotMapper.toSlotInsertDto(sl);
	}

	@Override
	public SlotInsertDTO updateSlot(SlotInsertDTO s, UUID id) {
		Optional<Slot> sl = slot.findById(id);
		if(sl!=null) {
			
			Slot a = slotMapper.toSlotI(s);
			a.setId(sl.get().getId());
			slot.save(a);
			return s;
		}
		return null;
	}

	@Override
	public Optional<SlotDTO> findByIdSlot(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteSlot(UUID id) {
		slot.deleteById(id);

	}


}
