package com.epam.mapper;

import com.epam.model.Client;
import com.epam.model.ClientDto;
import com.epam.model.Lot;
import com.epam.model.LotDto;
import com.epam.model.Product;
import com.epam.model.ProductDto;
import com.epam.model.Role;
import com.epam.model.RoleDto;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-03-19T15:48:39+0400",
    comments = "version: 1.4.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.7.1.jar, environment: Java 1.8.0_221 (Oracle Corporation)"
)
@Component
public class ModelMapperImpl implements ModelMapper {

    @Override
    public Client toClient(ClientDto clientDto) {
        if ( clientDto == null ) {
            return null;
        }

        Client client = new Client();

        if ( clientDto.getId() != null ) {
            client.setId( clientDto.getId() );
        }
        client.setFullName( clientDto.getFullName() );
        client.setAddress( clientDto.getAddress() );
        client.setLogin( clientDto.getLogin() );
        client.setPassword( clientDto.getPassword() );
        client.setRole( toRole( clientDto.getRole() ) );

        return client;
    }

    @Override
    public Lot toLot(LotDto lotDto) {
        if ( lotDto == null ) {
            return null;
        }

        Lot lot = new Lot();

        if ( lotDto.getId() != null ) {
            lot.setId( lotDto.getId() );
        }
        lot.setProductId( toProduct( lotDto.getProductId() ) );
        lot.setBidderId( toClient( lotDto.getBidderId() ) );
        lot.setBestPrice( lotDto.getBestPrice() );
        if ( lotDto.getStartPrice() != null ) {
            lot.setStartPrice( lotDto.getStartPrice() );
        }
        if ( lotDto.getBidMin() != null ) {
            lot.setBidMin( lotDto.getBidMin() );
        }
        lot.setStopDate( lotDto.getStopDate() );

        return lot;
    }

    @Override
    public Product toProduct(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Product product = new Product();

        if ( productDto.getId() != null ) {
            product.setId( productDto.getId() );
        }
        product.setUserId( toClient( productDto.getUserId() ) );
        product.setName( productDto.getName() );
        product.setDescription( productDto.getDescription() );

        return product;
    }

    @Override
    public Role toRole(RoleDto roleDto) {
        if ( roleDto == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( roleDto.getId() );
        role.setName( roleDto.getName() );

        return role;
    }

    @Override
    public ClientDto toClientDto(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientDto clientDto = new ClientDto();

        clientDto.setId( client.getId() );
        clientDto.setFullName( client.getFullName() );
        clientDto.setAddress( client.getAddress() );
        clientDto.setLogin( client.getLogin() );
        clientDto.setPassword( client.getPassword() );
        clientDto.setRole( toRoleDto( client.getRole() ) );

        return clientDto;
    }

    @Override
    public LotDto toLotDto(Lot lot) {
        if ( lot == null ) {
            return null;
        }

        LotDto lotDto = new LotDto();

        lotDto.setId( lot.getId() );
        lotDto.setProductId( toProductDto( lot.getProductId() ) );
        lotDto.setBidderId( toClientDto( lot.getBidderId() ) );
        lotDto.setBestPrice( lot.getBestPrice() );
        lotDto.setStartPrice( lot.getStartPrice() );
        lotDto.setBidMin( lot.getBidMin() );
        lotDto.setStopDate( lot.getStopDate() );

        return lotDto;
    }

    @Override
    public ProductDto toProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setId( product.getId() );
        productDto.setUserId( toClientDto( product.getUserId() ) );
        productDto.setName( product.getName() );
        productDto.setDescription( product.getDescription() );

        return productDto;
    }

    @Override
    public RoleDto toRoleDto(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setId( role.getId() );
        roleDto.setName( role.getName() );

        return roleDto;
    }
}
