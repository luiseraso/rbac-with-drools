package edu.unicauca.git.model.rbcc;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity(name="rbcc_user")
public class User {
	
	// Attributes
	@Id @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Integer id;
	@Column(nullable=false)
	private String login;
	@Column(nullable=false)
	private String password;
	@Column(nullable=false)
	private String name;
	@OneToMany(mappedBy="user",cascade=CascadeType.PERSIST)
	@JoinColumn(name="rbcc_user_id")
	private List<UserRole> roles;
	
	// Constructors
	public User(){
		this("", null, null);
	}
	
	public User(String pLogin){
		this(pLogin, null, null);
	}
	
	public User(String pLogin, String pPassword, String pName){
		this.login = pLogin;
		this.password = pPassword;
		this.name = pName;
		this.roles = new ArrayList<UserRole>();
	}

	public void addRole(Role r){
		
		boolean roleAcive = false; 
		for(UserRole iterator: roles){
			if( iterator.getRole()==r && iterator.isEnable() ){
				roleAcive = true;
				break;
			}			
		}
		
		if(!roleAcive){			
			new UserRole(this, r);//Crear nuevo enlace usuario-role
		}		
	}
	
	public boolean hasRole(String roleName){
		
		for(UserRole iterator: roles){
			if( iterator.getRole().getName().equals(roleName) ){
				return true;				
			}			
		}
		
		return false;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	public List<UserRole> getRoles() {
		return roles;
	}
	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
	}
	
	public String toString(){
		return "User{id="+id+"; login="+login+"; name="+name+"}";
	}
	
}
