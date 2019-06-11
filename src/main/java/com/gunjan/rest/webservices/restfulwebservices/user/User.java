package com.gunjan.rest.webservices.restfulwebservices.user;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;


@Entity
public class User
{
    @Id
    private Integer id;
    
    @Size(min = 2, message = "Name should have at least two character")
    private String name;
    
    @Past
    private Date birthDate;
    
    protected User()
    {
    
    }
    public User(Integer id)
    {
        this.id = id;
    }
    
    public User(String name, Date birthDate)
    {
        this.name = name;
        this.birthDate = birthDate;
    }
    
    public User(Integer id, String name, Date birthDate)
    {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }
    
    public Integer getId()
    {
        return id;
    }
    
    public void setId(Integer id)
    {
        this.id = id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public Date getBirthDate()
    {
        return birthDate;
    }
    
    public void setBirthDate(Date birthDate)
    {
        this.birthDate = birthDate;
    }
    
    @Override
    public String toString()
    {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
    
    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        User user = (User)o;
        return Objects.equals(id, user.id);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }
}
