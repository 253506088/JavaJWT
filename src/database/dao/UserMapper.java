package database.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import database.pojo.User;

/**
 * user表的Mapper接口
 * 
 * @author hp
 *
 */
public interface UserMapper {
	/**
	 * 根据昵称获取id，用于判断该昵称是否存在，存在则返回userId
	 * 
	 * @param nikeName
	 * @return
	 */
	public Integer getUserIdByNikeName(String nikeName);

	/**
	 * 根据账号获取id，用于判断该账号是否存在，存在则返回userId
	 * 
	 * @param username
	 * @return
	 */
	public Integer getUserIdByUsername(String username);

	/**
	 * 根据邮箱获取id，用于判断该账号是否存在，存在则返回userId
	 * @param email
	 * @return
	 */
	public Integer getUserIdByEmail(String email); 
	
	/**
	 * 根据邮箱、创建时间、修改时间获取id，用于获取用邮箱注册的用户信息
	 * @param email
	 * @param gmtCreate
	 * @param gmtModified
	 * @return
	 */
	public User getUserByEmailAndTime(@Param("email") String email,@Param("gmtCreate") Date gmtCreate,
			@Param("gmtModified") Date gmtModified);
	
	/**
	 * 根据账号与密码查询用户,一般用于登录
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public User loginUsername(@Param("username") String username, @Param("password") String password);

	/**
	 * 根据邮箱与密码查询用户
	 * @param email
	 * @param password
	 * @return
	 */
	public User loginEmail(@Param("email") String email, @Param("password") String password);

	
	/**
	 * 根据id查询用户
	 * @param userId
	 * @return
	 */
	public User getUserById(Integer userId);
	
	/**
	 * 新增用户
	 * 
	 * @param user
	 * @return
	 */
	public Integer addUser(User user);
	
	/**
	 * 根据id修改用户是否激活
	 * @param userId
	 * @param activation 1激活,0未激活
	 * @return
	 */
	public Integer modifyActivationById(@Param("userId") Integer userId,@Param("activation") Integer activation);
	
	/**
	 * 根据id修改用户是否删除
	 * @param userId
	 * @param isDelete 1删除,0未删除
	 * @return
	 */
	public Integer modifyIsDeleteById(@Param("userId") Integer userId,@Param("isDelete") Integer isDelete);
}
