package com.epam.mapper;

import com.epam.model.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ModelMapper {

    Client toClient(ClientDto clientDto);

    Lot toLot(LotDto lotDto);

    Product toProduct(ProductDto productDto);

    Role toRole(RoleDto roleDto);

    ClientDto toClientDto(Client client);

    LotDto toLotDto(Lot lot);

    ProductDto toProductDto(Product product);

    RoleDto toRoleDto(Role role);

}
