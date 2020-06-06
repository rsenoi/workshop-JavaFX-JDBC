package br.com.trainning.model;

import java.io.Serializable;
import java.util.Date;

public class Seller implements Serializable {

 	private static final long serialVersionUID = 1L;
	private int Id;
    private String Name;
    private String Email;
    private Date Birthdate;
    private Double BaseSalary;
    private Department department;
    
    public Seller() {
    }

	public Seller(int id, String name, String email, Date birthdate, Double baseSalary, Department department) {
		Id = id;
		Name = name;
		Email = email;
		Birthdate = birthdate;
		BaseSalary = baseSalary;
		this.department = department;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public Date getBirthdate() {
		return Birthdate;
	}


	public Double getBaseSalary() {
		return BaseSalary;
	}

	public void setBaseSalary(Double baseSalary) {
		BaseSalary = baseSalary;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department dep) {
		this.department = dep;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seller other = (Seller) obj;
		if (Id != other.Id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Seller [Id=" + Id + ", Name=" + Name + ", Email=" + Email + ", Birthdate=" + Birthdate + ", BaseSalary="
				+ BaseSalary + ", department=" + department + "]";
	}


	public void setBirthdate(Date date) {
		Birthdate = date;
		
	}


}
