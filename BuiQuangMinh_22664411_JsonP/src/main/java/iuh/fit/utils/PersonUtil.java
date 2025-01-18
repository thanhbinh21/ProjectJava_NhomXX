package iuh.fit.utils;

import iuh.fit.entities.Address;
import iuh.fit.entities.Person;
import iuh.fit.entities.PhoneNumber;
import jakarta.json.*;
import jakarta.json.stream.JsonGenerator;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Java Object Model API
public class PersonUtil {
    public static void main(String[] args) {
//        Person person = fromJson("json/person.json");
//        System.out.println(person);
        List<Person> people = fromJsonList("json/person.json");
        System.out.println(people);
    }
    //Checked vs Unchecked exception
    public static Person fromJson(String fileName) {
        Person person = null;
        try(JsonReader reader = Json.createReader(new FileReader(fileName))) {
            JsonObject jo = reader.readObject();
            if(jo != null) {
                person = new Person();
                String fName = jo.getString("firstName");
                person.setFirstName(fName);
                person.setLastName(jo.getString("lastName"));
                person.setAge(jo.getInt("age"));

                JsonObject joAddr = jo.getJsonObject("address");
                if(joAddr != null) {
                    Address address = new Address();
                    address.setCity(joAddr.getString("city"));
                    address.setState(joAddr.getString("state"));
                    address.setStreetAddress(joAddr.getString("streetAddress"));
                    address.setPostalCode(joAddr.getInt("postalCode"));

                    person.setAddress(address);
                }

                JsonArray jaPhones = jo.getJsonArray("phoneNumbers");
                if(jaPhones != null) {
                    List<PhoneNumber> phones = jaPhones
                            .stream()
                            .map(jv -> (JsonObject)jv) //casting from JasonValue to JsonObject
                            .map(joPhone -> {
                                PhoneNumber phone = new PhoneNumber();//POJO
                                phone.setNumber(joPhone.getString("number"));
                                phone.setType(joPhone.getString("type"));
                                return phone;
                            })//convert form JsonObject to Java Object
                            .toList();
                    person.setPhoneNumbers(phones);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return person;
    }

    public static List<Person> fromJsonList(String fileName) {
        List<Person> people = null;
        try(JsonReader reader = Json.createReader(new FileReader(fileName))) {
            JsonArray ja = reader.readArray();
            if(ja != null) {
                people = ja.stream()
                .map(jsonValue -> (JsonObject) jsonValue)
                .map(jo -> {
                    Person person = new Person();
                    String fName = jo.getString("firstName");
                    person.setFirstName(fName);
                    person.setLastName(jo.getString("lastName"));
                    person.setAge(jo.getInt("age"));
                    JsonObject joAddr = jo.getJsonObject("address");
                    if(joAddr != null) {
                        Address address = new Address();
                        address.setCity(joAddr.getString("city"));
                        address.setState(joAddr.getString("state"));
                        address.setStreetAddress(joAddr.getString("streetAddress"));
                        address.setPostalCode(joAddr.getInt("postalCode"));
                        person.setAddress(address);
                    }
                    JsonArray jaPhones = jo.getJsonArray("phoneNumbers");
                    if(jaPhones != null) {
                        List<PhoneNumber> phones = jaPhones
                                .stream()
                                .map(jv -> (JsonObject)jv) //casting from JasonValue to JsonObject
                                .map(joPhone -> {
                                    PhoneNumber phone = new PhoneNumber();//POJO
                                    phone.setNumber(joPhone.getString("number"));
                                    phone.setType(joPhone.getString("type"));
                                    return phone;
                                })//convert form JsonObject to Java Object
                                .toList();
                        person.setPhoneNumbers(phones);
                    }
                    return person;
                }).toList();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return people;
    }

    public static JsonArray toJson(List<Person> people) {
        JsonArrayBuilder jaBuilder = Json.createArrayBuilder();
        JsonObjectBuilder joBuilder = Json.createObjectBuilder();

        people.stream()//List<Person>
                .map(person -> {
                            joBuilder
                            .add("firstName", person.getFirstName())
                            .add("lastName", person.getLastName())
                            .add("age", person.getAge())
                            .add("address", toJson(person.getAddress()));
                    if(person.getPhoneNumbers() != null) {
                        joBuilder.add("phoneNumbers", phonesToJson(person.getPhoneNumbers()));
                    }
                    return joBuilder.build();
                })//List<JsonObject>
                .forEach(jo -> jaBuilder.add(jo));

        return jaBuilder.build();
    }

    private static JsonArray phonesToJson(List<PhoneNumber> phoneNumbers) {
        JsonArrayBuilder jaBuilder = Json.createArrayBuilder();
        JsonObjectBuilder joBuilder = Json.createObjectBuilder();
        phoneNumbers.stream()
                .map(phone -> {
                    return joBuilder.add("number", phone.getNumber())
                            .add("type", phone.getType())
                            .build();
                }).forEach(jo ->jaBuilder.add(jo));
        return jaBuilder.build();
    }

    private static JsonObject toJson(Address address) {
        JsonObjectBuilder joBuilder = Json.createObjectBuilder();
        return joBuilder.add("streetAddress", address.getStreetAddress())
                        .add("city", address.getCity())
                        .add("state", address.getState())
                        .add("postalCode", address.getPostalCode())
                        .build();
    }

    public static void write2file(JsonArray ja, String fileName) {
        Map<String, Boolean> conf = new HashMap<>();
        conf.put(JsonGenerator.PRETTY_PRINTING, true);
        JsonWriterFactory factory = Json.createWriterFactory(conf);
        try(JsonWriter writer = factory.createWriter(new FileWriter(fileName))) {
            writer.writeArray(ja);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
