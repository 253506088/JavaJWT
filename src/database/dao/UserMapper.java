package database.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import database.pojo.User;

/**
 * user���Mapper�ӿ�
 * 
 * @author hp
 *
 */
public interface UserMapper {
	/**
	 * �����ǳƻ�ȡid�������жϸ��ǳ��Ƿ���ڣ������򷵻�userId
	 * 
	 * @param nikeName
	 * @return
	 */
	public Integer getUserIdByNikeName(String nikeName);

	/**
	 * �����˺Ż�ȡid�������жϸ��˺��Ƿ���ڣ������򷵻�userId
	 * 
	 * @param username
	 * @return
	 */
	public Integer getUserIdByUsername(String username);

	/**
	 * ���������ȡid�������жϸ��˺��Ƿ���ڣ������򷵻�userId
	 * @param email
	 * @return
	 */
	public Integer getUserIdByEmail(String email); 
	
	/**
	 * �������䡢����ʱ�䡢�޸�ʱ���ȡid�����ڻ�ȡ������ע����û���Ϣ
	 * @param email
	 * @param gmtCreate
	 * @param gmtModified
	 * @return
	 */
	public User getUserByEmailAndTime(@Param("email") String email,@Param("gmtCreate") Date gmtCreate,
			@Param("gmtModified") Date gmtModified);
	
	/**
	 * �����˺��������ѯ�û�,һ�����ڵ�¼
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public User loginUsername(@Param("username") String username, @Param("password") String password);

	/**
	 * ���������������ѯ�û�
	 * @param email
	 * @param password
	 * @return
	 */
	public User loginEmail(@Param("email") String email, @Param("password") String password);

	
	/**
	 * ����id��ѯ�û�
	 * @param userId
	 * @return
	 */
	public User getUserById(Integer userId);
	
	/**
	 * �����û�
	 * 
	 * @param user
	 * @return
	 */
	public Integer addUser(User user);
	
	/**
	 * ����id�޸��û��Ƿ񼤻�
	 * @param userId
	 * @param activation 1����,0δ����
	 * @return
	 */
	public Integer modifyActivationById(@Param("userId") Integer userId,@Param("activation") Integer activation);
	
	/**
	 * ����id�޸��û��Ƿ�ɾ��
	 * @param userId
	 * @param isDelete 1ɾ��,0δɾ��
	 * @return
	 */
	public Integer modifyIsDeleteById(@Param("userId") Integer userId,@Param("isDelete") Integer isDelete);
}
