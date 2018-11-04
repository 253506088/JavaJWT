package database.pojo;

import java.util.Date;

/**
 * user表的持久化类
 * 
 * @author hp
 *
 */
public class User {
	private Integer userId;
	private String username;
	private String password;
	private String nikeName;
	private String email;
	private Integer isDelete;
	private Date gmtCreate;
	private Date gmtModified;
	private Integer activation;

	public User() {
		super();
	}

	public User(String username, String password, String nikeName,String email, Integer isDelete, Date gmtCreate,
			Date gmtModified,Integer activation) {
		this.username = username;
		this.password = password;
		this.nikeName = nikeName;
		this.email = email;
		this.isDelete = isDelete;
		this.gmtCreate = gmtCreate;
		this.gmtModified = gmtModified;
		this.activation = activation;
	}

	public Integer getActivation() {
		return activation;
	}

	public void setActivation(Integer activation) {
		this.activation = activation;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNikeName() {
		return nikeName;
	}

	public void setNikeName(String nikeName) {
		this.nikeName = nikeName;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
}
