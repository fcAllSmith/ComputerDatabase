package com.excilys.computerdb.fconsigny.core.model;


@Entity
@Table(name = "user")
public class User {

	@Id @GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "enabled")
	private boolean enabled;
}
