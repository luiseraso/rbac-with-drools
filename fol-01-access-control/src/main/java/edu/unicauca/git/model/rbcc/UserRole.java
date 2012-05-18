package edu.unicauca.git.model.rbcc;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="rbcc_user_role")
public class UserRole {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	private User user;
	@ManyToOne
	private Role role;
	private boolean enable;
	@Column(name="start_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	@Column(name="end_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	
	protected UserRole(){
		
	}
	
	public UserRole(User pUser, Role pRole){		
		this.user = pUser;
		this.role = pRole;
		this.enable = true;
		this.startDate = new Date();
		pUser.getRoles().add(this);
		pRole.getUsers().add(this);
	}
	
	public void deactivateRole(){
		this.enable = false;
		this.endDate = new Date();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	

}
