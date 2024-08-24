package com.maruf.optionalprojectexample.services;

import com.maruf.optionalprojectexample.entities.User;
import com.maruf.optionalprojectexample.repostories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Parametre olarak verilen email ile eşleşen User kaydını döner.
     * @param email
     * @return
     */
    public Optional<User> getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    /**
     * Parametre olarak verilen User bilgileri ile kayıt oluşturur.
     * @param user
     * @return
     */
    public User createUser(User user){
        return userRepository.save(user);
    }

    /**
     * Parametre olarak verilen email ile eşleşen kayıt varsa kullanıcı ismini döner. Yoksa "Unkown User" döner
     * @param email
     * @return
     */
    public String getUserNameByEmail(String email){
       Optional<User> userOptional = userRepository.findByEmail(email);
       userOptional.ifPresent( user -> System.out.println("User found :"+user.getName()));

       return userOptional.map(User::getName).orElse("Unkown User");

    }

    /**
     * iki senorya mevcut. Ya yeni bir kullanıcı oluşturuacak ya da mevcut kullanıcı bilgilerini döndürebilir.
     * @param email
     * @return
     */
    public User getUserByEmailOrCreate(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.orElseGet(() -> createUser(new User("Default Name", email)));
    }

    /**
     * Parametre olarak verilen email ile kayıt sorgulanır. Bulunursa geri döndürülür, bulunamazsa hata geriye döndürülür.
     * @param email
     */
    public void deleteUserByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        User user = userOptional.orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }




}
