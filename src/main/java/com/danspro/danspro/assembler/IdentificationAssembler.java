package com.danspro.danspro.assembler;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.danspro.danspro.beans.IdentificationDto;
import com.danspro.danspro.beans.IdentificationEntity;
import com.danspro.danspro.repository.IdentificationRepository;

@Component
    public class IdentificationAssembler implements InterfaceAssembler<IdentificationEntity, IdentificationDto> {

    @Autowired
    private IdentificationRepository repository;

    // @Override
    // public IdentificationEntity fromDto(IdentificationDto dto) {
    //     if (dto == null)
    //         return null;

    //         IdentificationEntity entity = new IdentificationEntity();
    //     if (dto.getUserName() != null) {
    //         Optional<IdentificationEntity> temp = this.repository.findById(dto.getUserName());
    //         if(temp.isPresent()){
    //             entity = temp.get();
    //         }
    //     }

    //     if (dto.getUserName() != null) entity.setUserName(dto.getUserName());
    //     if (dto.getPassword() != null) entity.setPassword(dto.getPassword());

    //     return entity;
    // }

    @Override
    public IdentificationDto fromEntity(IdentificationEntity entity) {
        if (entity == null) return null;
        return IdentificationDto.builder()
                .userName(entity.getUserName())
                .password(entity.getPassword())
                .build();
    }


}
