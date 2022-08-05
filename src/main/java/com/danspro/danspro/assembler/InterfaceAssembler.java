package com.danspro.danspro.assembler;

public interface InterfaceAssembler <A, B> {

    // A fromDto(B dto);

    B fromEntity(A entity);
}
