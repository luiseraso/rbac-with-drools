package edu.unicauca.git.model.rbcc;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity(name="rbcc_role")
public class Role {
	
	// Attributes
	@Id @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Integer id;
	@Column(nullable=false)
	private String name;
	private String comments;
	@OneToMany(mappedBy="role",cascade=CascadeType.PERSIST)
	@JoinColumn(name="rbcc_role_id")
	private List<UserRole> users;
	
	// Constructors
	public Role(){
		this("", null);
	}
	
	public Role(String pName){		
		this(pName, null);
	}
	
	public Role(String pName, String pComments){		
		this.name = pName;
		this.comments = pComments;
		this.users = new ArrayList<UserRole>();
	}
	
	public void addUser(User u){
		boolean userAcive = false; 
		for(UserRole iterator: users){
			if( iterator.getUser()==u && iterator.isEnable() ){
				userAcive = true;
				break;
			}			
		}
		
		if(!userAcive){
			new UserRole(u, this);//Crear nueva enlace usuario-role
		}		
	}
	
	// Getters, setters
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getComments() {
		return comments;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}

	public List<UserRole> getUsers() {
		return users;
	}

	public void setUsers(List<UserRole> users) {
		this.users = users;
	}

}
