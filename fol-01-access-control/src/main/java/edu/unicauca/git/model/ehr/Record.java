package edu.unicauca.git.model.ehr;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import edu.unicauca.git.model.rbcc.User;

@Entity(name="ehr_record")
public class Record {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date date;
	
	@OneToOne
	private User user;
	
	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="record_id")
	private List<Comment> comments;
	
	protected Record() {
		
	}
	
	protected Record(User user){
		this.user=user;
		this.date=new Date();
		this.comments = new ArrayList<Comment>();
	}
	
	public void addComment(String comment, User user){
		Comment cmt = new Comment(comment, user);
		comments.add(cmt);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComents(List<Comment> comments) {
		this.comments = comments;
	}
	

}
