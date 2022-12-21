package com.bezkoder.springjwt.DailyReports.DailyReportServices;

import com.bezkoder.springjwt.DailyReports.Enums.ClientNameEnum;
import com.bezkoder.springjwt.DailyReports.Enums.ClientNameEnums;
import com.bezkoder.springjwt.DailyReports.Repository.ClientNameEnumRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class ClientService {

    @Autowired
    private ClientNameEnumRepo clientNameEnumRepo;

    private ClientNameEnum clientNameEnum;

    public List<ClientNameEnums> getAllClients(){

         try {
            return clientNameEnumRepo.findAll();
        }catch (Exception e) {
            log.info("Error {} "+e);
            return null;
        }
    }

    public Optional<ClientNameEnums> getClientById(Long id){

        try {
            return Optional.ofNullable(clientNameEnumRepo.findById(id).orElse(null));
        }catch (Exception e) {
            log.info("Error {} "+e);
            return null;
        }

    }


    public ClientNameEnums addClient(ClientNameEnums clientNameEnums) {
        try {
            clientNameEnums.setClientName(clientNameEnums.getClientName());
            //clientNameEnums.setClientName(String.valueOf(clientNameEnums));
            ClientNameEnums newClientNameEnums= clientNameEnumRepo.save(clientNameEnums);
            return newClientNameEnums;
        }catch (Exception e) {
            log.info("Error {} "+e);
            return null;
        }
    }
}
