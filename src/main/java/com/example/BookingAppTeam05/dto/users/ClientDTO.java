package com.example.BookingAppTeam05.dto.users;
import com.example.BookingAppTeam05.model.users.Client;

public class ClientDTO extends UserDTO {

    public ClientDTO(){ }

    public ClientDTO(Client client){
        super(client);
        super.setPenalties(client.getPenalties());
    }


}
