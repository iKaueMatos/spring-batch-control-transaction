package com.example.transactionsmultiplesdbs;

import org.apache.logging.log4j.util.Strings;

public class Person {
	private int id;
	private String name;
	private String email;
	private String birthDate;
	private int age;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isValid() {
		return !Strings.isBlank(name) && !Strings.isBlank(email) && birthDate != null;
	}
}
