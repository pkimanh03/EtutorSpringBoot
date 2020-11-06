package com.anhtt.eTutor.convert;

import com.anhtt.eTutor.dto.SlotDTO;
import com.anhtt.eTutor.dto.SlotInsertDTO;
import com.anhtt.eTutor.model.Slot;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SlotMapper {
    SlotDTO toSlotDto(Slot slot);
    List<SlotDTO> toSlotDtoList(List<Slot> slotList);

    SlotInsertDTO toSlotInsertDto(Slot slot);
    List<SlotInsertDTO> toSlotInsertDtoList(List<Slot> slotList);

    Slot toSlot(SlotDTO slotDTO);
    Slot toSlotI(SlotInsertDTO slotInsertDTO);
}
