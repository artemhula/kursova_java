// models/Person.java
package models;

abstract public class Person {
    protected String name;
    protected long phone;
    private String companyName;

    Person(String name, long phone, String companyName){
        this.name = name;
        this.phone = phone;
        this.companyName = companyName;
    }

    public String getName() {
        return name;
    }

    public long getPhone() {
        return phone;
    }

    public String getCompanyName() {
        return companyName;
    }
}
