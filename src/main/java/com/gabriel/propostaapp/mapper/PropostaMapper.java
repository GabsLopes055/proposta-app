package com.gabriel.propostaapp.mapper;


import com.gabriel.propostaapp.DTOs.request.PropostaRequestDTO;
import com.gabriel.propostaapp.entity.Proposta;
import org.mapstruct.Mapper;

@Mapper
public interface PropostaMapper {

    Proposta convertDtoToProposta(PropostaRequestDTO requestDTO);

}
