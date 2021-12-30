package fr.myhome.server.testhelper;

import java.util.ArrayList;
import java.util.List;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import fr.myhome.server.model.User;
import fr.myhome.server.model.enumerate.Role;
import fr.myhome.server.repository.UserRepository;

@Component
public class TestFactory extends Faker {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static final Integer NUMBER_MAX = 20_000_000;

    public static final int LENGTH_STANDARD = 30;

    private List<String> listRandomString = new ArrayList<>();
    private List<String> listRandomCaseSensitiveString = new ArrayList<>();
    private List<String> listRandomEmail = new ArrayList<>();
    private List<String> listRandomName = new ArrayList<>();

    public void resetAllList(){

        this.listRandomString = new ArrayList<>();
        this.listRandomCaseSensitiveString = new ArrayList<>();
        this.listRandomEmail = new ArrayList<>();
        this.listRandomName = new ArrayList<>();
    }

    public String getUniqueRandomAlphanumericString(final int length){

        boolean isNotUnique = true;
        String randomString = "";

        while (isNotUnique){
            randomString = RandomStringUtils.randomAlphanumeric(length);
            isNotUnique = listRandomString.contains(randomString);
        }

        listRandomString.add(randomString);

        return randomString;
    }

    public String getUniqueRandomAlphanumericStringCaseSensitive(final int length){
        boolean isNotUnique = true;
        String randomString = "";

        while (isNotUnique){
            randomString = RandomStringUtils.randomAlphanumeric(length).toLowerCase();
            isNotUnique = listRandomCaseSensitiveString.contains(randomString);
        }

        listRandomCaseSensitiveString.add(randomString);

        return randomString;
    }


    public String getUniqueRandomAlphanumericString(){

        boolean isNotUnique = true;
        String randomString = "";

        while (isNotUnique){
            randomString = this.getRandomAlphanumericString();
            isNotUnique = listRandomString.contains(randomString);
        }

        listRandomString.add(randomString);

        return randomString;
    }

    public String getRandomAlphanumericString(final int length){

        return RandomStringUtils.randomAlphanumeric(length);
    }

    public String getRandomAlphanumericString(){

        return RandomStringUtils.randomAlphanumeric(LENGTH_STANDARD);
    }

    public Name getUniqueRandomName(){

        boolean isNotUnique = true;
        Name randomName = this.name();

        while (isNotUnique){
            randomName = this.name();
            isNotUnique = listRandomName.contains(randomName.username());
        }

        listRandomName.add(randomName.username());

        return randomName;
    }

    public String getUniqueRandomEmail(){
        boolean isNotUnique = true;
        String email = "";

        while (isNotUnique){
            email = this.internet().emailAddress();
            isNotUnique = listRandomEmail.contains(email);
        }

        listRandomEmail.add(email);

        return email;

    }

    public int getRandomInteger(final Integer max){
        final double random = Math.random() * max;
        return (int) random;
    }

    public double getRandomDouble(){
        return this.random().nextDouble();
    }

    public int getRandomInteger(){
        return this.getRandomInteger(NUMBER_MAX);
    }

    public User getUser(){
        final User user = new User();
        user.setEmail(this.internet().emailAddress());
        final String firstName = StringUtils.capitalize(this.name().firstName().toLowerCase());
        user.setFirstName(firstName);
        user.setLastName(this.name().lastName().toUpperCase());
        user.setUsername(this.name().username().toLowerCase());
        user.setPassword(this.passwordEncoder.encode(this.getRandomAlphanumericString()));
        user.setRememberMe(false);
        
        final List<Role> roles = new ArrayList<>();
        roles.add(Role.USER);
        user.setRoles(roles);

        return this.userRepository.save(user);
    }

    public User getAdmin(){
        final User admin = this.getUser();
        admin.getRoles().add(Role.ADMIN);
        return this.userRepository.save(admin);
    }
}
