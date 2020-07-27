package dev.sandrocaseiro.springbootitExample.mappers;

import dev.sandrocaseiro.springbootitExample.models.api.ACep;
import dev.sandrocaseiro.springbootitExample.models.dto.cep.DPesquisaCepResp;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CepMapper {
    DPesquisaCepResp toDPesquisaCepResp(ACep model);
}
