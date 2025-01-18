package iuh.fit;

import iuh.fit.entities.Address;
import iuh.fit.entities.Person;
import iuh.fit.entities.PhoneNumber;
import iuh.fit.utils.PersonUtil;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import net.datafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        //create 10 people (Phone: 0 - 2)
        //generate to JsonArray
        //write to stream (write to file)

        Random random = new Random();
        Faker faker = new Faker();
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Person person = new Person();
            person.setFirstName(faker.name().firstName());
            person.setLastName(faker.name().lastName());
            person.setAge(faker.number().numberBetween(18, 60));

            Address address = new Address();
            address.setCity(faker.address().fullAddress());
            address.setState(faker.address().state());
            address.setStreetAddress(faker.address().streetAddress());
            address.setPostalCode(Integer.parseInt(faker.address().zipCode()));

            person.setAddress(address);

            //Phone numbers
            int n = random.nextInt(3);//0 - 2
            if(n > 0) {
                List<PhoneNumber> phones = new ArrayList<>();
                for (int j = 0; j < n; j++) {
                    phones.add(new PhoneNumber(
                            faker.options().option("Fax", "Home", "Work"),
                            faker.phoneNumber().phoneNumber()
                    ));
                }
                person.setPhoneNumbers(phones);
            }
            people.add(person);
        }
//        people.forEach(person -> System.out.println(person));
//        people.forEach(System.out::println);
        JsonArray jaPeople = PersonUtil.toJson(people);
        System.out.println(jaPeople);//console
        PersonUtil.write2file(jaPeople, "json/person.json");
    }
}