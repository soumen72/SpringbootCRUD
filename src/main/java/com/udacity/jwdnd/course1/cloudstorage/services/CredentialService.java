package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;


@Service
public class CredentialService {
    //need to create Credential mapper
    //user mapper

    private final CredentialMapper credentialMapper;
    private final EncryptionService encryptionService;
    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }
    //CRUD
    public Integer insert(Credential credential) {
        SecureRandom random = new SecureRandom();

        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedsalt = Base64.getEncoder().encodeToString(salt);

        //hashed encryptedPassword
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedsalt);
        System.out.println(encodedsalt+ " credential service encoded salt");

        //inserting
        System.out.println(null +" "+credential.getUrl()+""+ credential.getUsername()+" "+ encodedsalt +" "+encryptedPassword+" "+credential.getUserId());
        Credential credentialObject=new Credential(null, credential.getUrl(), credential.getUsername(), encodedsalt, encryptedPassword, credential.getUserId());
        return credentialMapper.insert(credentialObject);
    }

    public List<Credential> getCredentials(Integer userid) {
        List<Credential> credentials = credentialMapper.getCredentials(userid);
//        int i=0;
//        for(Credential credential: credentials){
//
//            i++;
//            System.out.println( credential.getKey() + ""+i);
//
//
//
//        }

        return credentials;
    }

    public void update(Credential credential) {

        String key = credential.getKey();
        System.out.println(key +" in credential service");

        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), key);
        credential.setPassword(encryptedPassword);

        credentialMapper.update(credential);
    }

    public void delete(Integer credentialId) {
        credentialMapper.delete(credentialId);
    }


}
